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

import org.modeshape.modeler.ddl.relational.RelationalConstants;

/**
 *  Interface for delegate Relational Objects
 */
public interface IObjectDelegate {
	
    /**
     * Get the object name
     * @return name
     */
	public String getName();
	
    /**
     * Get the object type
     * @return the object type
     */
	public RelationalConstants.Type getType( );
	
    /**
     * Determine if object has children
     * @return 'true' if has children, 'false' if not
     */
	public boolean hasChildren();
	
    /**
     * Get the object children
     * @return the children
     */
	public List<RelationalObject> getChildren();
	
    /**
     * Get the value of objects option with specified name
     * @param optionName the option key
     * @return the option value
     */
	public Object getOptionValue(String optionName);
	
    /**
     * Get the value of objects option with specified name
     * @param optionName the option key
     * @param value the value
     * @param additionalValues additional values for multi-value props
     * @return 'true' if property was set, 'false' if not
     */
	public boolean setOptionValue(String optionName, Object value, Object... additionalValues);

    /**
     * Unset the objects option with specified name (remove it)
     * @param optionName the option key
     * @return 'true' if property was unset, 'false' if not
     */
	public boolean unsetOptionValue(String optionName);
	
    /**
     * Get the value of objects property with specified name
     * @param propName the property key
     * @return the property value
     */
	public Object getPropertyValue(String propName);

	/**
     * Get the values of objects multi-valued property with specified name
     * @param propName the property key
     * @return the property values
     */
	public Object[] getPropertyValues(String propName);
	
    /**
     * Set the value of object property with specified name
     * @param propName the property key
     * @param value the value
     * @param additionalValues additional values for multi-value props
     * @return 'true' if property was set, 'false' if not
     */
	public boolean setPropertyValue(String propName, Object value, Object... additionalValues);

    /**
     * Determine if the supplied key is an extension property
     * @param propName the property name
     * @return 'true' if the property is an extension, 'false' if not
     */
	public boolean isExtensionProperty(String propName);
	
    /**
     * Get the extension properties (prefixed options)
     * @return the extension property map
     */
	public Map<String,String> getExtensionProperties();
}
