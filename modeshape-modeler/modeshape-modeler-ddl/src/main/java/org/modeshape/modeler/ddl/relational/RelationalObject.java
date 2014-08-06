package org.modeshape.modeler.ddl.relational;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * RelationalObject
 * - the basis for all relational objects
 */
public abstract class RelationalObject implements RelationalConstants {
	
	@SuppressWarnings("javadoc")
	protected IObjectDelegate delegate;
	
	RelationalObject(IObjectDelegate delegate) {
		this.delegate = delegate;
	}
	
    /**
     * Get the object name
     * @return name
     */
	public String getName() {
		return getPropertyValue(RelationalConstants.COMMON_DDL_PROPERTY_KEYS.NAME).toString();
	}
	
    /**
     * Get the object type
     * @return the object type
     */
	public RelationalConstants.Type getType() {
		return this.delegate.getType();
	}

    /**
     * Get the object name in source
     * @return name in source
     */
	public String getNameInSource() {
		Object propValue = getPropertyValue(RelationalConstants.COMMON_DDL_OPTION_KEYS.NAME_IN_SOURCE);
		return (propValue==null) ? null : propValue.toString();
	}
	
    /**
     * Set the object name in source
     * @param nameInSource the name in source
     */
	public void setNameInSource(String nameInSource) {
		setPropertyValue(RelationalConstants.COMMON_DDL_OPTION_KEYS.NAME_IN_SOURCE,nameInSource);
	}
	
    /**
     * Get the object description
     * @return description
     */
	public String getDescription() {
		Object propValue = getPropertyValue(RelationalConstants.COMMON_DDL_OPTION_KEYS.DESCRIPTION);
		return (propValue==null) ? null : propValue.toString();
	}

    /**
     * Set the object description
     * @param description the description
     */
	public void setDescription(String description) {
		setPropertyValue(RelationalConstants.COMMON_DDL_OPTION_KEYS.DESCRIPTION,description);
	}
	
    /**
     * Determine if object has children
     * @return 'true' if has children, 'false' if not
     */
	public boolean hasChildren() {
		return this.delegate.hasChildren();
	}	
	
    /**
     * Get the object children
     * @return the children
     */
	public List<RelationalObject> getChildren() {
		return this.delegate.getChildren();
	}
	
    /**
     * Get children of provided type
     * @param type the type
     * @return the children of given type
     */
    @SuppressWarnings("unchecked")
	protected <T> List<T> getChildren(Class<T> type) {
    	List<T> result = new ArrayList<T>();
    	for(Object e : getChildren()) {
    		if(type.isAssignableFrom(e.getClass())) {
    			result.add((T) e);
    		}
    	}
    	return result;
    }
    
    /**
     * Get the object properties
     * @return the properties
     */
	public Map<String, Object> getProperties() {
    	Map<String,Object> props = new HashMap<String,Object>();
    	
    	// Add values for common properties
    	String[] propKeys = RelationalConstants.COMMON_DDL_PROPERTY_KEYS.toArray();
    	for(int i=0; i<propKeys.length; i++) {
    		props.put(propKeys[i], getPropertyValue(propKeys[i]));
    	}

    	// Add values for common options
    	propKeys = RelationalConstants.COMMON_DDL_OPTION_KEYS.toArray();
    	for(int i=0; i<propKeys.length; i++) {
    		props.put(propKeys[i], getPropertyValue(propKeys[i]));
    	}
    	
    	// Add extension properties
    	props.putAll(getExtensionProperties());
    	
    	return props;
	}
	
	/**
	 * Get the object's extension properties.  These are the non-standard properties that are prefixed.
	 * @return the Map of extension properties
	 */
	public Map<String,String> getExtensionProperties() {
		return this.delegate.getExtensionProperties();
	}
	
    /**
     * Get the value of a property
     * @param propertyKey the property key
     * @return the property value
     */
	public Object getPropertyValue(String propertyKey) {
		Object propertyValue = null;
		if(!this.delegate.isExtensionProperty(propertyKey)) {
			if(propertyKey.equals(RelationalConstants.COMMON_DDL_PROPERTY_KEYS.NAME)) {
				propertyValue = this.delegate.getName();
			} else if(propertyKey.equals(RelationalConstants.COMMON_DDL_OPTION_KEYS.NAME_IN_SOURCE)) {
				propertyValue = this.delegate.getOptionValue(propertyKey);
			} else if(propertyKey.equals(RelationalConstants.COMMON_DDL_OPTION_KEYS.DESCRIPTION)) {
				return this.delegate.getOptionValue(propertyKey);
			}
		} else {
			Map<String,String> extensionProps = this.delegate.getExtensionProperties();
			for(String key : extensionProps.keySet()) {
				if(key.equals(propertyKey)) {
					propertyValue = extensionProps.get(key);
					break;
				}
			}
		}
		return propertyValue;
	}
	
	@SuppressWarnings("javadoc")
	public boolean setPropertyValue(String propertyKey, Object propValue) {
		boolean wasSet = false;
		
		// ---------------------------
		// Properties
		// ---------------------------
		if(propertyKey.equals(RelationalConstants.COMMON_DDL_PROPERTY_KEYS.NAME)) {
			wasSet = this.delegate.setPropertyValue(propertyKey,propValue);
		// ---------------------------
		// Statement Options
		// ---------------------------
		} else if(propertyKey.equals(RelationalConstants.COMMON_DDL_OPTION_KEYS.NAME_IN_SOURCE)) {
			if(propValue==null || ((String)propValue).equals(RelationalConstants.COMMON_DEFAULT.NAME_IN_SOURCE)) {
				wasSet = this.delegate.unsetOptionValue(propertyKey);
			} else {
				wasSet = this.delegate.setOptionValue(propertyKey, propValue);
			}
		} else if(propertyKey.equals(RelationalConstants.COMMON_DDL_OPTION_KEYS.DESCRIPTION)) {
			if(propValue==null || ((String)propValue).equals(RelationalConstants.COMMON_DEFAULT.DESCRIPTION)) {
				wasSet = this.delegate.unsetOptionValue(propertyKey);
			} else {
				wasSet = this.delegate.setOptionValue(propertyKey, propValue);
			}
		}
		
		return wasSet;
	}
	
    /**
     * Set the value of a property
     * @param propertyKey the property key
     * @param propertyValue the property value
     */
//	public void setPropertyValue(String propertyKey,Object propertyValue) {
//		if(!this.delegate.isExtensionProperty(propertyKey)) {
//			if(propertyKey.equals(RelationalConstants.COMMON_DDL_PROPERTY_KEYS.NAME)) {
//				propertyValue = this.delegate.getName();
//			} else if(propertyKey.equals(RelationalConstants.COMMON_DDL_OPTION_KEYS.NAME_IN_SOURCE)) {
//				propertyValue = this.delegate.getOptionValue(propertyKey);
//			} else if(propertyKey.equals(RelationalConstants.COMMON_DDL_OPTION_KEYS.DESCRIPTION)) {
//				return this.delegate.getOptionValue(propertyKey);
//			}
//		} else {
//			Map<String,String> extensionProps = this.delegate.getExtensionProperties();
//			for(String key : extensionProps.keySet()) {
//				if(key.equals(propertyKey)) {
//					propertyValue = extensionProps.get(key);
//					break;
//				}
//			}
//		}
//	}
	
}
