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
package org.modeshape.modeler.spi.metamodel;

import java.io.OutputStream;

import org.modeshape.modeler.Model;
import org.modeshape.modeler.ModelerException;

/**
 * 
 */
public interface Exporter {

    /**
     * @param model
     *        a model
     * @param stream
     *        the output stream to which the model will be exported
     * @throws ModelerException
     *         if any error occurs
     */
    void execute( Model model,
                  OutputStream stream ) throws ModelerException;

    /**
     * @return the metamodel ID
     */
    String metamodelId();

    /**
     * @param mimeType
     *        a MIME type
     * @return <code>true</code> if this exporter supports data of the supplied MIME type
     * @throws ModelerException
     *         if any error occurs
     */
    boolean supports( String mimeType ) throws ModelerException;
}
