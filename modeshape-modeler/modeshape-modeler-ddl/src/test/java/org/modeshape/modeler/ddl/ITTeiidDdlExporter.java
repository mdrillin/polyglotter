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
package org.modeshape.modeler.ddl;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.File;

import org.junit.Test;
import org.modeshape.modeler.Model;
import org.modeshape.modeler.spi.metamodel.Exporter;

@SuppressWarnings( "javadoc" )
public class ITTeiidDdlExporter extends TeiidDdlIntegrationTest {

    @Test
    public void shouldExport() throws Exception {
        metamodelManager().install( TeiidDdlLexicon.DDL_METAMODEL_CATEGORY );
        final Model model = modeler().importModel( new File( "src/test/resources/Teiid-MySQLAccounts.ddl" ),
                                                   null,
                                                   metamodelManager().metamodel( TeiidDdlLexicon.DDL_METAMODEL_ID ) );
        final Exporter exporter = model.metamodel().exporter();
        assertThat( exporter, is( notNullValue() ) );

        // try ( final ByteArrayOutputStream stream = new ByteArrayOutputStream() ) {
        // exporter.execute( model, stream );
        // assertThat( stream.toString().startsWith( XML_DECLARATION + "\n<xsd:schema " ), is( true ) );
        // }
    }
}
