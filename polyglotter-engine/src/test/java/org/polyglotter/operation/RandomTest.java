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
package org.polyglotter.operation;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.polyglotter.PolyglotterI18n;
import org.polyglotter.common.PolyglotterException;
import org.polyglotter.transformation.OperationCategory.BuiltInCategory;

@SuppressWarnings( { "javadoc" } )
public final class RandomTest {

    private Random operation;

    @Before
    public void beforeEach() {
        this.operation = new Random( OperationTestConstants.TEST_TRANSFORMATION );
    }

    @Test
    public void shouldBeAbleToGetResultAfterConstruction() throws PolyglotterException {
        this.operation.get();
    }

    @Test
    public void shouldCreateOperation() {
        assertThat( Random.DESCRIPTOR.newInstance( OperationTestConstants.TEST_TRANSFORMATION ),
                    is( instanceOf( Random.class ) ) );
    }

    @Test
    public void shouldHaveCorrectCategory() {
        assertThat( this.operation.categories().size(), is( 1 ) );
        assertThat( this.operation.categories().contains( BuiltInCategory.ARITHMETIC ), is( true ) );
    }

    @Test( expected = UnsupportedOperationException.class )
    public void shouldNotBeAbleToModifyTermsList() {
        this.operation.inputs().add( OperationTestConstants.INT_1_TERM );
    }

    @Test
    public void shouldNotHaveErrorsAfterConstruction() {
        assertThat( this.operation.problems().isError(), is( false ) );
    }

    @Test
    public void shouldNotHaveProblemsAfterConstruction() {
        assertThat( this.operation.problems().isEmpty(), is( true ) );
    }

    @Test
    public void shouldNotHaveTermsAfterConstruction() {
        assertThat( this.operation.inputs().isEmpty(), is( true ) );
    }

    @Test
    public void shouldProvideDescription() {
        assertThat( this.operation.description(), is( PolyglotterI18n.randomOperationDescription.text() ) );
    }

    @Test
    public void shouldProvideName() {
        assertThat( this.operation.name(), is( PolyglotterI18n.randomOperationName.text() ) );
    }

}
