/*
 * JBoss, Home of Professional Open Source.
*
* See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
*
* See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
*/
package org.modeshape.modeler.ddl.relational;

import java.util.List;
import java.util.Map;

/**
 *  Interface for delegate Relational Models
 */
public interface IModelDelegate {
	
    /**
     * Get the model name
     * @return name
     */
	public String getName();
	
    /**
     * Determine if model has children
     * @return 'true' if has children, 'false' if not
     */
	public boolean hasChildren();
	
    /**
     * Get the model children
     * @return the children
     */
	public List<RelationalObject> getChildren();
	
    /**
     * Get the namespace mappings if any
     * @return the prefix to uri mapping
     */
	public Map<String,String> getNamespaceMap();
}
