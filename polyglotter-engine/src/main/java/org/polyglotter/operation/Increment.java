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

import org.polyglotter.PolyglotterI18n;
import org.polyglotter.common.PolyglotterException;
import org.polyglotter.transformation.Operation;
import org.polyglotter.transformation.OperationCategory.BuiltInCategory;
import org.polyglotter.transformation.OperationDescriptor;
import org.polyglotter.transformation.Transformation;
import org.polyglotter.transformation.TransformationFactory;
import org.polyglotter.transformation.ValidationProblem;
import org.polyglotter.transformation.Value;
import org.polyglotter.transformation.ValueDescriptor;

/**
 * Increments the term.
 */
public final class Increment extends AbstractOperation< Integer > {

    /**
     * The input term descriptor.
     */
    public static final ValueDescriptor< Integer > TERM_DESCRIPTOR =
        TransformationFactory.createWritableBoundedOneValueDescriptor( TransformationFactory.createId( Increment.class, "input" ),
                                                                       PolyglotterI18n.incrementOperationInputDescription.text(),
                                                                       PolyglotterI18n.incrementOperationInputName.text(),
                                                                       Integer.class );

    /**
     * The input descriptors.
     */
    private static final ValueDescriptor< ? >[] INPUT_DESCRIPTORS = { TERM_DESCRIPTOR };

    /**
     * The output descriptor.
     */
    public static final OperationDescriptor< Integer > DESCRIPTOR =
        new AbstractOperationDescriptor< Integer >( TransformationFactory.createId( Increment.class ),
                                                    PolyglotterI18n.incrementOperationDescription.text(),
                                                    PolyglotterI18n.incrementOperationName.text(),
                                                    Integer.class,
                                                    INPUT_DESCRIPTORS ) {

            /**
             * {@inheritDoc}
             * 
             * @see org.polyglotter.transformation.OperationDescriptor#newInstance(org.polyglotter.transformation.Transformation)
             */
            @Override
            public Operation< Integer > newInstance( final Transformation transformation ) {
                return new Increment( transformation );
            }

        };

    /**
     * @param transformation
     *        the transformation containing this operation (cannot be <code>null</code>)
     * @throws IllegalArgumentException
     *         if the input is <code>null</code>
     */
    Increment( final Transformation transformation ) {
        super( DESCRIPTOR, transformation );

        try {
            addCategory( BuiltInCategory.ARITHMETIC );
        } catch ( final PolyglotterException e ) {
            this.logger.error( e, PolyglotterI18n.errorAddingBuiltInCategory, transformationId() );
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.polyglotter.operation.AbstractOperation#calculate()
     */
    @Override
    protected Integer calculate() throws PolyglotterException {
        assert !problems().isError();
        int value = ( Integer ) inputs().get( 0 ).get();
        return ++value;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.polyglotter.operation.AbstractOperation#validate()
     */
    @Override
    protected void validate() {
        // make sure there are terms
        if ( inputs().size() != 1 ) {
            final ValidationProblem problem =
                TransformationFactory.createError( transformationId(),
                                                   PolyglotterI18n.incrementOperationMustHaveOneTerm.text( transformationId() ) );
            problems().add( problem );
        } else {
            // make sure term is an int
            final Value< ? > term = inputs().get( 0 );
            Object value;

            try {
                value = term.get();

                if ( !( value instanceof Integer ) ) {
                    final ValidationProblem problem =
                        TransformationFactory.createError( transformationId(),
                                                           PolyglotterI18n.incrementOperationInvalidTermType.text( transformationId() ) );
                    problems().add( problem );
                }
            } catch ( final PolyglotterException e ) {
                final ValidationProblem problem =
                    TransformationFactory.createError( transformationId(),
                                                       PolyglotterI18n.operationValidationError.text( name(),
                                                                                                      transformationId() ) );
                problems().add( problem );
                this.logger.error( e, PolyglotterI18n.message, problem.message() );
            }
        }
    }

}
