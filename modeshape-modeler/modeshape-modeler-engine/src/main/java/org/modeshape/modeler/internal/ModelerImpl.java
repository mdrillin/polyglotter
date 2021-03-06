/*
 * Polyglotter (http://polyglotter.org)
 * See the COPYRIGHT.txt file distributed with this work for information
 * regarding copyright ownership.  Some portions may be licensed
 * to Red Hat, Inc. under one or more contributor license agreements.
 * See the AUTHORS.txt file in the distribution for a full listing of 
 * individual contributors.
 *
 * Polyglotter is free software. Unless otherwise indicated, all code in Polyglotter
 * is licensed to you under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * Polyglotter is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.modeshape.modeler.internal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.modeshape.common.collection.Problem;
import org.modeshape.common.collection.Problems;
import org.modeshape.common.util.CheckArg;
import org.modeshape.jcr.JcrLexicon;
import org.modeshape.jcr.JcrRepository;
import org.modeshape.jcr.ModeShapeEngine;
import org.modeshape.jcr.NoSuchRepositoryException;
import org.modeshape.jcr.RepositoryConfiguration;
import org.modeshape.jcr.api.JcrTools;
import org.modeshape.jcr.api.ValueFactory;
import org.modeshape.jcr.api.sequencer.Sequencer;
import org.modeshape.modeler.Metamodel;
import org.modeshape.modeler.MetamodelManager;
import org.modeshape.modeler.Model;
import org.modeshape.modeler.Modeler;
import org.modeshape.modeler.ModelerException;
import org.modeshape.modeler.ModelerI18n;
import org.modeshape.modeler.ModelerLexicon;
import org.modeshape.modeler.internal.task.SystemTask;
import org.modeshape.modeler.internal.task.SystemTaskWithResult;
import org.modeshape.modeler.internal.task.Task;
import org.modeshape.modeler.internal.task.TaskWithResult;
import org.modeshape.modeler.internal.task.WriteSystemTask;
import org.modeshape.modeler.internal.task.WriteTask;
import org.modeshape.modeler.internal.task.WriteTaskWithResult;
import org.modeshape.modeler.spi.metamodel.DependencyProcessor;
import org.modeshape.modeler.spi.metamodel.Exporter;
import org.polyglotter.common.CommonI18n;
import org.polyglotter.common.Logger;

/**
 * 
 */
public class ModelerImpl implements Modeler {

    static final Logger LOGGER = Logger.getLogger( ModelerImpl.class );

    /**
     * The path to the default configuration, which uses a file-based repository
     */
    public static final String DEFAULT_CONFIGURATION_PATH = "config.json";

    /**
     * 
     */
    public static final String REPOSITORY_STORE_PARENT_PATH_PROPERTY = "org.modeshape.modeler.repositoryStoreParentPath";

    private ModeShapeEngine engine;
    private JcrRepository repository;
    private MetamodelManagerImpl metamodelManager;
    private final String configurationPath;

    /**
     * Uses a default configuration.
     * 
     * @param repositoryStoreParentPath
     *        the path to the folder that should contain the repository store
     */
    public ModelerImpl( final String repositoryStoreParentPath ) {
        this( repositoryStoreParentPath, DEFAULT_CONFIGURATION_PATH );
    }

    /**
     * @param repositoryStoreParentPath
     *        the path to the folder that should contain the repository store
     * @param configurationPath
     *        the path to a configuration file
     */
    public ModelerImpl( final String repositoryStoreParentPath,
                        final String configurationPath ) {
        CheckArg.isNotEmpty( repositoryStoreParentPath, "repositoryStoreParentPath" );
        CheckArg.isNotEmpty( configurationPath, "configurationPath" );
        System.setProperty( REPOSITORY_STORE_PARENT_PATH_PROPERTY, repositoryStoreParentPath );
        this.configurationPath = configurationPath;
    }

    String absolutePath( String path ) {
        if ( path == null ) return "/";
        path = path.trim();
        if ( path.isEmpty() ) return "/";
        if ( path.charAt( 0 ) == '/' ) return path;
        return '/' + path;
    }

    private String absolutePath( String path,
                                 final String name ) {
        path = absolutePath( path );
        return path.endsWith( "/" ) ? path + name : path + '/' + name;
    }

    /**
     * {@inheritDoc}
     * 
     * @see AutoCloseable#close()
     */
    @Override
    public void close() throws ModelerException {
        try {
            if ( engine != null ) engine.shutdown().get();
        } catch ( InterruptedException | ExecutionException e ) {
            throw new ModelerException( e );
        }
        Logger.getLogger( getClass() ).info( ModelerI18n.modelerStopped );
    }

    /**
     * {@inheritDoc}
     * 
     * @see Modeler#configurationPath()
     */
    @Override
    public String configurationPath() {
        return configurationPath;
    }

    Node dataNode( final Session session,
                   final String filePath ) throws Exception {
        try {
            return session.getNode( filePath.charAt( 0 ) == '/' ? filePath : '/' + filePath );
        } catch ( final PathNotFoundException e ) {
            throw new IllegalArgumentException( e );
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see Modeler#export(Model, File)
     */
    @Override
    public void export( final Model model,
                        final File file ) throws ModelerException {
        CheckArg.isNotNull( model, "model" );
        CheckArg.isNotNull( file, "file" );
        try {
            export( model, new FileOutputStream( file ) );
        } catch ( final FileNotFoundException e ) {
            throw new ModelerException( e );
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see Modeler#export(Model, OutputStream)
     */
    @Override
    public void export( final Model model,
                        final OutputStream stream ) throws ModelerException {
        CheckArg.isNotNull( model, "model" );
        CheckArg.isNotNull( stream, "stream" );

        final Metamodel metamodel = model.metamodel();

        if ( metamodel != null ) {
            final Exporter exporter = metamodel.exporter();

            if ( exporter != null ) {
                exporter.execute( model, stream );
            }
        }

        throw new ModelerException( ModelerI18n.modelExporterNotFound, model.name() );
    }

    /**
     * {@inheritDoc}
     * 
     * @see Modeler#export(Model, URL)
     */
    @Override
    public void export( final Model model,
                        final URL url ) throws ModelerException {
        CheckArg.isNotNull( model, "model" );
        CheckArg.isNotNull( url, "url" );
        try ( OutputStream stream = url.openConnection().getOutputStream() ) {
            export( model, stream );
        } catch ( final IOException e ) {
            throw new ModelerException( e );
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see Modeler#generateModel(String, String)
     */
    @Override
    public Model generateModel( final String dataPath,
                                final String modelPath ) throws ModelerException {
        return generateModel( dataPath, modelPath, null, true );
    }

    /**
     * {@inheritDoc}
     * 
     * @see Modeler#generateModel(String, String, Metamodel)
     */
    @Override
    public Model generateModel( final String dataPath,
                                final String modelPath,
                                final Metamodel metamodel ) throws ModelerException {
        return generateModel( dataPath, modelPath, metamodel, true );
    }

    /**
     * @param dataPath
     *        the workspace path to the data
     * @param modelPath
     *        the workspace path to the model
     * @param metamodel
     *        the model's metamodel
     * @param persistArtifact
     *        <code>true</code> if the data should be persisted after import
     * @return the model
     * @throws ModelerException
     *         if an error occurs
     */
    public Model generateModel( final String dataPath,
                                final String modelPath,
                                final Metamodel metamodel,
                                final boolean persistArtifact ) throws ModelerException {
        CheckArg.isNotEmpty( dataPath, "dataPath" );
        CheckArg.isNotEmpty( modelPath, "modelPath" );

        try {
            return run( new WriteTaskWithResult< Model >() {

                @Override
                public Model run( final Session session ) throws Exception {
                    final Node dataNode = dataNode( session, dataPath );
                    Metamodel actualMetamodel = metamodel;
                    if ( actualMetamodel == null ) {
                        // If no metamodel supplied, use default metamodel if one exists
                        final MetamodelManagerImpl manager = ( MetamodelManagerImpl ) metamodelManager();
                        actualMetamodel = manager.defaultMetamodel( dataNode, manager.metamodels( dataNode ) );
                        if ( actualMetamodel == null )
                            throw new IllegalArgumentException( ModelerI18n.unableToDetermineDefaultMetamodel.text( dataPath ) );
                        throw new UnsupportedOperationException( "Not yet implemented" );
                    }
                    // Build the model
                    final ValueFactory valueFactory = ( ValueFactory ) session.getValueFactory();
                    final Calendar cal = Calendar.getInstance();
                    final Node modelNode = new JcrTools().findOrCreateNode( session, absolutePath( modelPath ) );
                    modelNode.addMixin( ModelerLexicon.Model.MODEL_MIXIN );
                    if ( dataNode.hasProperty( ModelerLexicon.Model.EXTERNAL_LOCATION ) )
                        modelNode.setProperty( ModelerLexicon.Model.EXTERNAL_LOCATION,
                                               dataNode.getProperty( ModelerLexicon.Model.EXTERNAL_LOCATION ).getString() );
                    final SequencerImporter importer = ( SequencerImporter ) actualMetamodel.importer();
                    final boolean save = importer.execute( dataNode.getNode( JcrLexicon.CONTENT.getString() )
                                                                   .getProperty( JcrLexicon.DATA.getString() ),
                                                           modelNode,
                                                           new Sequencer.Context() {

                                                               @Override
                                                               public Calendar getTimestamp() {
                                                                   return cal;
                                                               }

                                                               @Override
                                                               public ValueFactory valueFactory() {
                                                                   return valueFactory;
                                                               }
                                                           } );

                    if ( save ) {
                        modelNode.setProperty( ModelerLexicon.Model.METAMODEL, actualMetamodel.id() );
                        final ModelImpl model = new ModelImpl( ModelerImpl.this, modelNode.getPath() );
                        session.save();
                        processDependencies( dataPath, modelNode, model, persistArtifact );
                        return model;
                    }

                    throw new ModelerException( ModelerI18n.unableToCreateModel, actualMetamodel.name(), modelPath, dataPath );
                }
            } );
        } finally {
            if ( !persistArtifact ) {
                removeTemporaryArtifact( dataPath );
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see Modeler#importData(File, String)
     */
    @Override
    public String importData( final File file,
                              final String workspaceFolder ) throws ModelerException {
        return importData( file, workspaceFolder, null );
    }

    /**
     * {@inheritDoc}
     * 
     * @see Modeler#importData(File, String, String)
     */
    @Override
    public String importData( final File file,
                              final String workspaceFolder,
                              final String workspaceName ) throws ModelerException {
        CheckArg.isNotNull( file, "file" );
        try {
            return importData( file.toURI().toURL(), workspaceFolder, workspaceName );
        } catch ( final MalformedURLException e ) {
            throw new ModelerException( e );
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see Modeler#importData(InputStream, String)
     */
    @Override
    public String importData( final InputStream stream,
                              final String workspacePath ) throws ModelerException {
        CheckArg.isNotNull( stream, "stream" );
        CheckArg.isNotEmpty( workspacePath, "workspacePath" );
        return run( new WriteTaskWithResult< String >() {

            @Override
            public String run( final Session session ) throws Exception {
                // Ensure the path is non-null, absolute, and ends with a slash
                final Node node = new JcrTools().uploadFile( session, absolutePath( workspacePath ), stream );
                // Add unstructured mix-in to allow node to contain anything else, like models created later
                node.addMixin( ModelerLexicon.UNSTRUCTURED_MIXIN );
                return node.getPath();
            }
        } );
    }

    /**
     * {@inheritDoc}
     * 
     * @see Modeler#importData(URL, String)
     */
    @Override
    public String importData( final URL url,
                              final String workspaceFolder ) throws ModelerException {
        return importData( url, workspaceFolder, null );
    }

    /**
     * {@inheritDoc}
     * 
     * @see Modeler#importData(URL, String, String)
     */
    @Override
    public String importData( final URL url,
                              final String workspaceFolder,
                              final String workspaceName ) throws ModelerException {
        CheckArg.isNotNull( url, "url" );
        try {
            final String path = importData( url.openStream(), absolutePath( workspaceFolder, name( workspaceName, url ) ) );
            saveExternalLocation( path, url.toString() );
            return path;
        } catch ( final FileNotFoundException e ) {
            throw new IllegalArgumentException( e );
        } catch ( final IOException e ) {
            throw new ModelerException( e );
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see Modeler#importModel(File, String, Metamodel)
     */
    @Override
    public Model importModel( final File file,
                              final String modelFolder,
                              final Metamodel metamodel ) throws ModelerException {
        return importModel( file, modelFolder, null, metamodel );
    }

    /**
     * {@inheritDoc}
     * 
     * @see Modeler#importModel(File, String, String, Metamodel)
     */
    @Override
    public Model importModel( final File file,
                              final String modelFolder,
                              final String modelName,
                              final Metamodel metamodel ) throws ModelerException {
        CheckArg.isNotNull( file, "file" );
        try {
            return importModel( file.toURI().toURL(), modelFolder, modelName, metamodel );
        } catch ( final MalformedURLException e ) {
            throw new ModelerException( e );
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see Modeler#importModel(InputStream, String, Metamodel)
     */
    @Override
    public Model importModel( final InputStream stream,
                              final String modelPath,
                              final Metamodel metamodel ) throws ModelerException {
        final String dataPath = importData( stream, ModelerLexicon.TEMP_FOLDER + "/file" );
        return generateModel( dataPath, modelPath, metamodel, false );
    }

    /**
     * {@inheritDoc}
     * 
     * @see Modeler#importModel(URL, String, Metamodel)
     */
    @Override
    public Model importModel( final URL dataUrl,
                              final String modelFolder,
                              final Metamodel metamodel ) throws ModelerException {
        return importModel( dataUrl, modelFolder, null, metamodel );
    }

    /**
     * {@inheritDoc}
     * 
     * @see Modeler#importModel(URL, String, String, Metamodel)
     */
    @Override
    public Model importModel( final URL dataUrl,
                              final String modelFolder,
                              final String modelName,
                              final Metamodel metamodel ) throws ModelerException {
        final String dataPath = importData( dataUrl, ModelerLexicon.TEMP_FOLDER );
        return generateModel( dataPath, absolutePath( modelFolder, name( modelName, dataUrl ) ), metamodel, false );
    }

    /**
     * {@inheritDoc}
     * 
     * @see Modeler#metamodelManager()
     */
    @Override
    public MetamodelManager metamodelManager() throws ModelerException {
        if ( metamodelManager == null ) metamodelManager = new MetamodelManagerImpl( this );
        return metamodelManager;
    }

    /**
     * {@inheritDoc}
     * 
     * @see Modeler#model(String)
     */
    @Override
    public Model model( final String path ) throws ModelerException {
        CheckArg.isNotEmpty( path, "path" );
        return run( new TaskWithResult< Model >() {

            @Override
            public Model run( final Session session ) throws Exception {
                try {
                    final String absPath = absolutePath( path );
                    final Node node = session.getNode( absPath );
                    if ( !node.isNodeType( ModelerLexicon.Model.MODEL_MIXIN ) )
                        throw new IllegalArgumentException( ModelerI18n.notModelPath.text( absPath ) );
                    return new ModelImpl( ModelerImpl.this, absPath );
                } catch ( final PathNotFoundException e ) {
                    return null;
                }
            }
        } );
    }

    private String name( String workspaceName,
                         final URL url ) {
        if ( workspaceName != null && !workspaceName.trim().isEmpty() ) return workspaceName;
        workspaceName = url.getPath();
        workspaceName = workspaceName.substring( workspaceName.lastIndexOf( '/' ) + 1 );
        return workspaceName;
    }

    void processDependencies( final String dataPath,
                              final Node modelNode,
                              final ModelImpl model,
                              final boolean persistArtifacts ) throws Exception {
        if ( model.metamodel() == null ) {
            Logger.getLogger( getClass() ).debug( "No metamodel found for model '%s'", modelNode.getName() );
            return;
        }

        final DependencyProcessor dependencyProcessor = model.metamodel().dependencyProcessor();

        if ( dependencyProcessor == null ) {
            Logger.getLogger( getClass() ).debug( "No dependency processor found for model '%s'", modelNode.getName() );
        } else {
            dependencyProcessor.process( dataPath, modelNode, this, persistArtifacts );
        }
    }

    private void removeTemporaryArtifact( final String dataPath ) throws ModelerException {
        run( new WriteTask() {

            @Override
            public void run( final Session session ) throws Exception {
                session.getNode( dataPath ).remove();
            }
        } );
    }

    JcrRepository repository() throws ModelerException {
        if ( repository == null ) {
            try {
                engine = new ModeShapeEngine();
                engine.start();
                final RepositoryConfiguration config = RepositoryConfiguration.read( configurationPath );
                final Problems problems = config.validate();
                if ( problems.hasProblems() ) {
                    for ( final Problem problem : problems )
                        Logger.getLogger( getClass() ).error( problem.getThrowable(), CommonI18n.text, problem.getMessageString() );
                    throw problems.iterator().next().getThrowable();
                }
                JcrRepository repository;
                try {
                    repository = engine.getRepository( config.getName() );
                } catch ( final NoSuchRepositoryException err ) {
                    repository = engine.deploy( config );
                }
                this.repository = repository;
                Logger.getLogger( getClass() ).info( ModelerI18n.modelerStarted );
            } catch ( final Throwable e ) {
                throw new ModelerException( e );
            }
        }
        return repository;
    }

    /**
     * {@inheritDoc}
     * 
     * @see Modeler#repositoryStoreParentPath()
     */
    @Override
    public String repositoryStoreParentPath() {
        return System.getProperty( REPOSITORY_STORE_PARENT_PATH_PROPERTY );
    }

    /**
     * Runs the supplied task after logging into and starting a session with the underlying repository engine that uses the system
     * workspace, then afterwards closes the session. It is the caller's responsibility to save any changes made within the session.
     * 
     * @param systemObject
     *        the object whose simple class name will be passed as a node to the supplied task
     * @param task
     *        the system task to run
     * @throws ModelerException
     *         if any error occurs
     */
    public void run( final Object systemObject,
                     final SystemTask task
                    ) throws ModelerException {
        try {
            final Session session = systemSession();
            try {
                task.run( session, systemNode( session, systemObject ) );
            } catch ( final RuntimeException | ModelerException e ) {
                throw e;
            } catch ( final Exception e ) {
                throw new ModelerException( e );
            } finally {
                session.logout();
            }
        } catch ( final RepositoryException e ) {
            throw new ModelerException( e );
        }
    }

    /**
     * Runs the supplied task after logging into and starting a session with the underlying repository engine that uses the system
     * workspace, then afterwards closes the session. It is the caller's responsibility to save any changes made within the session.
     * 
     * @param systemObject
     *        the object whose simple class name will be passed as a node to the supplied task
     * @param task
     *        the system task to run
     * @return the return value of the supplied task
     * @throws ModelerException
     *         if any error occurs
     */
    public < T > T run( final Object systemObject,
                        final SystemTaskWithResult< T > task
                    ) throws ModelerException {
        try {
            final Session session = systemSession();
            try {
                return task.run( session, systemNode( session, systemObject ) );
            } catch ( final RuntimeException | ModelerException e ) {
                throw e;
            } catch ( final Exception e ) {
                throw new ModelerException( e );
            } finally {
                session.logout();
            }
        } catch ( final RepositoryException e ) {
            throw new ModelerException( e );
        }
    }

    /**
     * Runs the supplied task after logging into and starting a session with the underlying repository engine that uses the system
     * workspace, then afterwards saves and closes the session.
     * 
     * @param systemObject
     *        the object whose simple class name will be passed as a node to the supplied task
     * @param task
     *        the system task to run
     * @throws ModelerException
     *         if any error occurs
     */
    public void run( final Object systemObject,
                     final WriteSystemTask task
                    ) throws ModelerException {
        try {
            final Session session = systemSession();
            try {
                task.run( session, systemNode( session, systemObject ) );
                session.save();
                LOGGER.debug( "Session saved" );
            } catch ( final RuntimeException | ModelerException e ) {
                throw e;
            } catch ( final Exception e ) {
                throw new ModelerException( e );
            } finally {
                session.logout();
            }
        } catch ( final RepositoryException e ) {
            throw new ModelerException( e );
        }
    }

    /**
     * Runs the supplied task after logging into and starting a session with the underlying repository engine, then afterwards
     * closes the session. It is the caller's responsibility to save any changes made within the session.
     * 
     * @param task
     *        the task to run
     * @throws ModelerException
     *         if any error occurs
     */
    public void run( final Task task ) throws ModelerException {
        try {
            final Session session = session();
            try {
                task.run( session );
            } catch ( final RuntimeException | ModelerException e ) {
                throw e;
            } catch ( final Exception e ) {
                throw new ModelerException( e );
            } finally {
                session.logout();
            }
        } catch ( final RepositoryException e ) {
            throw new ModelerException( e );
        }
    }

    /**
     * Runs the supplied task after logging into and starting a session with the underlying repository engine, then afterwards
     * closes the session. It is the caller's responsibility to save any changes made within the session.
     * 
     * @param task
     *        the task to run
     * @return the return value of the supplied task
     * @throws ModelerException
     *         if any error occurs
     */
    public < T > T run( final TaskWithResult< T > task ) throws ModelerException {
        try {
            final Session session = session();
            try {
                return task.run( session );
            } catch ( final RuntimeException | ModelerException e ) {
                throw e;
            } catch ( final Exception e ) {
                throw new ModelerException( e );
            } finally {
                session.logout();
            }
        } catch ( final RepositoryException e ) {
            throw new ModelerException( e );
        }
    }

    /**
     * Runs the supplied task after logging into and starting a session with the underlying repository engine, then afterwards saves
     * and closes the session.
     * 
     * @param task
     *        the write task to run
     * @throws ModelerException
     *         if any error occurs
     */
    void run( final WriteTask task ) throws ModelerException {
        try {
            final Session session = session();
            try {
                task.run( session );
                session.save();
                LOGGER.debug( "Session saved" );
            } catch ( final RuntimeException | ModelerException e ) {
                throw e;
            } catch ( final Exception e ) {
                throw new ModelerException( e );
            } finally {
                session.logout();
            }
        } catch ( final RepositoryException e ) {
            throw new ModelerException( e );
        }
    }

    /**
     * Runs the supplied task after logging into and starting a session with the underlying repository engine, then afterwards saves
     * and closes the session.
     * 
     * @param task
     *        the write task to run
     * @return the return value of the supplied task
     * @throws ModelerException
     *         if any error occurs
     */
    < T > T run( final WriteTaskWithResult< T > task ) throws ModelerException {
        try {
            final Session session = session();
            try {
                final T returnValue = task.run( session );
                session.save();
                LOGGER.debug( "Session saved" );
                return returnValue;
            } catch ( final RuntimeException | ModelerException e ) {
                throw e;
            } catch ( final Exception e ) {
                throw new ModelerException( e );
            } finally {
                session.logout();
            }
        } catch ( final RepositoryException e ) {
            throw new ModelerException( e );
        }
    }

    private void saveExternalLocation( final String path,
                                       final String location ) throws ModelerException {
        run( new WriteTask() {

            @Override
            public void run( final Session session ) throws Exception {
                session.getNode( path ).setProperty( ModelerLexicon.Model.EXTERNAL_LOCATION, location );
            }
        } );
    }

    private Session session() throws ModelerException, RepositoryException {
        return repository().login( "default" );
    }

    private Node systemNode( final Session session,
                             final Object systemObject ) throws RepositoryException {
        final String path = '/' + systemObject.getClass().getSimpleName();
        if ( session.nodeExists( path ) ) return session.getNode( path );
        return session.getRootNode().addNode( path );
    }

    private Session systemSession() throws ModelerException, RepositoryException {
        return repository().login( Modeler.class.getSimpleName() );
    }
}
