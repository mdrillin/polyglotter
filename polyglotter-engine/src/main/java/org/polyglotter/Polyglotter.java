/*
 * See the COPYRIGHT.txt file distributed with this work for information
 * regarding copyright ownership.
 *
 * This software is made available by Red Hat, Inc. under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution and is
 * available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * See the AUTHORS.txt file in the distribution for a full listing of
 * individual contributors.
 */
package org.polyglotter;

import javax.jcr.Repository;
import javax.jcr.Session;

import org.modeshape.common.collection.Problem;
import org.modeshape.common.collection.Problems;
import org.modeshape.common.logging.Logger;
import org.modeshape.jcr.ModeShapeEngine;
import org.modeshape.jcr.NoSuchRepositoryException;
import org.modeshape.jcr.RepositoryConfiguration;

/**
 * 
 */
public final class Polyglotter {
    
    /**
     * 
     */
    public static final String DEFAULT_MODESHAPE_CONFIGURATION_PATH = "jcr/modeShapeConfig.json";
    
    private String modeShapeConfigurationPath = DEFAULT_MODESHAPE_CONFIGURATION_PATH;
    private ModeShapeEngine modeShape;
    private Session session;
    
    /**
     * @return the path to the ModeShape configuration file. Default is {@value #DEFAULT_MODESHAPE_CONFIGURATION_PATH}.
     */
    public String modeShapeConfigurationPath() {
        return modeShapeConfigurationPath;
    }
    
    /**
     * @return the Polyglotter JCR repository session (never <code>null</code>)
     * @throws Throwable
     */
    public Session session() throws Throwable {
        if ( this.session == null ) {
            modeShape = new ModeShapeEngine();
            modeShape.start();
            final RepositoryConfiguration config = RepositoryConfiguration.read( modeShapeConfigurationPath );
            final Problems problems = config.validate();
            if ( problems.hasProblems() ) {
                for ( final Problem problem : problems )
                    Logger.getLogger( getClass() ).error( problem.getMessage(), problem.getThrowable() );
                throw problems.iterator().next().getThrowable();
            }
            Repository repository;
            try {
                repository = modeShape.getRepository( config.getName() );
            } catch ( final NoSuchRepositoryException err ) {
                repository = modeShape.deploy( config );
            }
            session = repository.login( "default" );
            Logger.getLogger( getClass() ).info( PolyglotterI18n.polyglotterStarted );
        }
        return session;
    }
    
    /**
     * @param modeShapeConfigurationPath
     */
    public void setModeShapeConfigurationPath( final String modeShapeConfigurationPath ) {
        this.modeShapeConfigurationPath =
            modeShapeConfigurationPath == null ? DEFAULT_MODESHAPE_CONFIGURATION_PATH : modeShapeConfigurationPath;
    }
    
    /**
     * 
     */
    public void stop() {
        if ( this.session == null ) Logger.getLogger( getClass() ).debug( "Attempt to stop Polyglotter when it is already stopped" );
        else {
            this.session.logout();
            this.session = null;
            this.modeShape = null;
            Logger.getLogger( getClass() ).info( PolyglotterI18n.polyglotterStopped );
        }
    }
    
}