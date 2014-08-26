/*
 * JBoss, Home of Professional Open Source.
* See the COPYRIGHT.txt file distributed with this work for information
* regarding copyright ownership. Some portions may be licensed
* to Red Hat, Inc. under one or more contributor license agreements.
*
* This library is free software; you can redistribute it and/or
* modify it under the terms of the GNU Lesser General Public
* License as published by the Free Software Foundation; either
* version 2.1 of the License, or (at your option) any later version.
*
* This library is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
* Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public
* License along with this library; if not, write to the Free Software
* Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
* 02110-1301 USA.
 */
package org.modeshape.modeler.ddl.relational;

import java.util.Map;

/**
 * Parameter
 * The relational parameter class
 */
public class Parameter extends RelationalObject {

    Parameter(IObjectDelegate modelObject) {
		super(modelObject);
	}
    
    /**
     * @return datatype
     */
    public String getDatatypeName() {
    	return getPropertyValue(RelationalConstants.PARAMETER_DDL_PROPERTY_KEYS.DATATYPE_NAME).toString();
    }
    
    /**
     * Set the datatype name
     * @param datatype the datatype name
     */
    public void setDatatypeName(String datatype) {
    	setPropertyValue(RelationalConstants.PARAMETER_DDL_PROPERTY_KEYS.DATATYPE_NAME,datatype);
    }

    /**
     * @return nativeType
     */
    public String getNativeType() {
    	return getPropertyValue(RelationalConstants.PARAMETER_DDL_OPTION_KEYS.NATIVE_TYPE).toString();
    }

    /**
     * Set the nativeType
     * @param nativeType the native type
     */
    public void setNativeType(String nativeType) {
    	setPropertyValue(RelationalConstants.PARAMETER_DDL_OPTION_KEYS.NATIVE_TYPE,nativeType);
    }

    /**
     * @return nullable
     */
    public String getNullable() {
    	return getPropertyValue(RelationalConstants.PARAMETER_DDL_PROPERTY_KEYS.NULLABLE).toString();
    }
    
    /**
     * @param nullable Sets nullable to the specified value.
     */
    public void setNullable( String nullable ) {
    	//ArgCheck.isNotEmpty(nullable);
    	String[] allowedValues = RelationalConstants.NULLABLE_OPTIONS.AS_ARRAY;
    	boolean matchFound = false;
    	for(int i=0; i<allowedValues.length; i++) {
    		if(allowedValues[i].equalsIgnoreCase(nullable)) {
    			setPropertyValue(RelationalConstants.PARAMETER_DDL_PROPERTY_KEYS.NULLABLE,allowedValues[i]);
    			matchFound = true;
    		}
    	}
    	//if(!matchFound) throw new IllegalArgumentException(Messages.getString(RELATIONAL.columnError_Nullable_NotAllowable,nullable));
    }
        
    /**
     * @return direction
     */
    public String getDirection() {
    	return getPropertyValue(RelationalConstants.PARAMETER_DDL_PROPERTY_KEYS.DIRECTION).toString();
    }
    
    /**
     * @param direction Sets direction to the specified value.
     */
    public void setDirection( String direction ) {
    	//ArgCheck.isNotEmpty(direction);
    	String[] allowedValues = RelationalConstants.DIRECTION_OPTIONS.AS_ARRAY;
    	boolean matchFound = false;
    	for(int i=0; i<allowedValues.length; i++) {
    		if(allowedValues[i].equalsIgnoreCase(direction)) {
    			setPropertyValue(RelationalConstants.PARAMETER_DDL_PROPERTY_KEYS.DIRECTION,allowedValues[i]);
    			matchFound = true;
    		}
    	}
    	//if(!matchFound) throw new IllegalArgumentException(Messages.getString(RELATIONAL.parameterError_Direction_NotAllowable,direction));
    }
    
    /**
     * @return defaultValue
     */
    public String getDefaultValue() {
    	return getPropertyValue(RelationalConstants.PARAMETER_DDL_PROPERTY_KEYS.DEFAULT_VALUE).toString();
    }

    /**
     * @param defaultValue the default value
     */
    public void setDefaultValue(String defaultValue) {
    	setPropertyValue(RelationalConstants.PARAMETER_DDL_PROPERTY_KEYS.DEFAULT_VALUE,defaultValue);
    }

    /**
     * @return length
     */
    public long getLength() {
    	return Long.parseLong(getPropertyValue(RelationalConstants.PARAMETER_DDL_PROPERTY_KEYS.LENGTH).toString());
    }

    /**
     * @param length the length
     */
    public void setLength(long length) {
    	setPropertyValue(RelationalConstants.PARAMETER_DDL_PROPERTY_KEYS.LENGTH,length);
    }

    /**
     * @return precision
     */
    public int getPrecision() {
    	return Integer.parseInt(getPropertyValue(RelationalConstants.PARAMETER_DDL_PROPERTY_KEYS.PRECISION).toString());
    }

    /**
     * @param precision the precision
     */
    public void setPrecision(long precision) {
    	setPropertyValue(RelationalConstants.PARAMETER_DDL_PROPERTY_KEYS.PRECISION,precision);
    }

    /**
     * @return scale
     */
    public int getScale() {
    	return Integer.parseInt(getPropertyValue(RelationalConstants.PARAMETER_DDL_PROPERTY_KEYS.SCALE).toString());
    }

    /**
     * @param scale the scale
     */
    public void setScale(long scale) {
    	setPropertyValue(RelationalConstants.PARAMETER_DDL_PROPERTY_KEYS.SCALE,scale);
    }

    
    /**
     * Get the properties for this object
     * @return the properties
     */
	@Override
	public Map<String, Object> getProperties() {
    	Map<String,Object> props = super.getProperties();
    	
    	// Add values for parameter properties
    	String[] propKeys = RelationalConstants.PARAMETER_DDL_PROPERTY_KEYS.toArray();
    	for(int i=0; i<propKeys.length; i++) {
    		props.put(propKeys[i], getPropertyValue(propKeys[i]));
    	}
    	
    	// Add values for column options
    	propKeys = RelationalConstants.PARAMETER_DDL_OPTION_KEYS.toArray();
    	for(int i=0; i<propKeys.length; i++) {
    		props.put(propKeys[i], getPropertyValue(propKeys[i]));
    	}
    	
    	return props;
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @see org.modeshape.modeler.ddl.relational.RelationalObject#getPropertyValue(java.lang.String)
	 */
	@Override
	public Object getPropertyValue(String propertyKey) {
		Object propertyValue = super.getPropertyValue(propertyKey);
		
		if(propertyKey.equals(RelationalConstants.PARAMETER_DDL_PROPERTY_KEYS.LENGTH)) {
			propertyValue = this.delegate.getPropertyValue(StandardDdlLexicon.DATATYPE_LENGTH);
			propertyValue = (propertyValue==null) ? RelationalConstants.PARAMETER_DEFAULT.LENGTH : propertyValue;
		} else if(propertyKey.equals(RelationalConstants.PARAMETER_DDL_PROPERTY_KEYS.DATATYPE_NAME)) {
			propertyValue = this.delegate.getPropertyValue(StandardDdlLexicon.DATATYPE_NAME);
			propertyValue = (propertyValue==null) ? RelationalConstants.PARAMETER_DEFAULT.DATATYPE_NAME : propertyValue;
		} else if(propertyKey.equals(RelationalConstants.PARAMETER_DDL_PROPERTY_KEYS.DEFAULT_VALUE)) {
			propertyValue = this.delegate.getPropertyValue(StandardDdlLexicon.DEFAULT_VALUE);
			propertyValue = (propertyValue==null) ? RelationalConstants.PARAMETER_DEFAULT.DEFAULT_VALUE : propertyValue;
		} else if(propertyKey.equals(RelationalConstants.PARAMETER_DDL_PROPERTY_KEYS.NULLABLE)) {
			propertyValue = this.delegate.getPropertyValue(StandardDdlLexicon.NULLABLE);
			propertyValue = (propertyValue==null) ? RelationalConstants.PARAMETER_DEFAULT.NULLABLE : propertyValue;
		} else if(propertyKey.equals(RelationalConstants.PARAMETER_DDL_PROPERTY_KEYS.PRECISION)) {
			propertyValue = this.delegate.getPropertyValue(StandardDdlLexicon.DATATYPE_PRECISION);
			propertyValue = (propertyValue==null) ? RelationalConstants.PARAMETER_DEFAULT.PRECISION : propertyValue;
		} else if(propertyKey.equals(RelationalConstants.PARAMETER_DDL_PROPERTY_KEYS.SCALE)) {
			propertyValue = this.delegate.getPropertyValue(StandardDdlLexicon.DATATYPE_SCALE);
			propertyValue = (propertyValue==null) ? RelationalConstants.PARAMETER_DEFAULT.SCALE : propertyValue;
		} else if(propertyKey.equals(RelationalConstants.PARAMETER_DDL_PROPERTY_KEYS.DIRECTION)) {
			propertyValue = this.delegate.getPropertyValue(TeiidDdlLexicon.CreateProcedure.PARAMETER_TYPE);
			propertyValue = (propertyValue==null) ? RelationalConstants.PARAMETER_DEFAULT.DIRECTION : propertyValue;
		} else if(propertyKey.equals(RelationalConstants.PARAMETER_DDL_OPTION_KEYS.NATIVE_TYPE)) {
			propertyValue = this.delegate.getOptionValue(propertyKey);
			propertyValue = (propertyValue==null) ? RelationalConstants.PARAMETER_DEFAULT.NATIVE_TYPE : propertyValue;
		}
		return propertyValue;
	}
	
	@Override
	@SuppressWarnings("javadoc")
	public boolean setPropertyValue(String propertyKey, Object propertyValue) {
		boolean wasSet = super.setPropertyValue(propertyKey,propertyValue);
		if(wasSet) return true;
		
		// ---------------------------
		// Properties
		// ---------------------------
		if(propertyKey.equals(RelationalConstants.PARAMETER_DDL_PROPERTY_KEYS.LENGTH)) {
			Long pVal = (propertyValue==null) ? RelationalConstants.PARAMETER_DEFAULT.LENGTH : (Long)propertyValue;
			wasSet = this.delegate.setPropertyValue(StandardDdlLexicon.DATATYPE_LENGTH, pVal);
		} else if(propertyKey.equals(RelationalConstants.PARAMETER_DDL_PROPERTY_KEYS.DATATYPE_NAME)) {
			String pVal = (propertyValue==null) ? RelationalConstants.PARAMETER_DEFAULT.DATATYPE_NAME : (String)propertyValue;
			wasSet = this.delegate.setPropertyValue(StandardDdlLexicon.DATATYPE_NAME, pVal);
		} else if(propertyKey.equals(RelationalConstants.PARAMETER_DDL_PROPERTY_KEYS.DEFAULT_VALUE)) {
			String pVal = (propertyValue==null) ? RelationalConstants.PARAMETER_DEFAULT.DEFAULT_VALUE : (String)propertyValue;
			wasSet = this.delegate.setPropertyValue(StandardDdlLexicon.DEFAULT_VALUE, pVal);
		} else if(propertyKey.equals(RelationalConstants.PARAMETER_DDL_PROPERTY_KEYS.NULLABLE)) {
			String pVal = (propertyValue==null) ? RelationalConstants.PARAMETER_DEFAULT.NULLABLE : (String)propertyValue;
			wasSet = this.delegate.setPropertyValue(StandardDdlLexicon.NULLABLE, pVal);
		} else if(propertyKey.equals(RelationalConstants.PARAMETER_DDL_PROPERTY_KEYS.PRECISION)) {
			Long pVal = (propertyValue==null) ? RelationalConstants.PARAMETER_DEFAULT.PRECISION : (Long)propertyValue;
			wasSet = this.delegate.setPropertyValue(StandardDdlLexicon.DATATYPE_PRECISION, pVal);
		} else if(propertyKey.equals(RelationalConstants.PARAMETER_DDL_PROPERTY_KEYS.SCALE)) {
			Long pVal = (propertyValue==null) ? RelationalConstants.PARAMETER_DEFAULT.SCALE : (Long)propertyValue;
			wasSet = this.delegate.setPropertyValue(StandardDdlLexicon.DATATYPE_SCALE, pVal);
		} else if(propertyKey.equals(RelationalConstants.PARAMETER_DDL_PROPERTY_KEYS.DIRECTION)) {
			String pVal = (propertyValue==null) ? RelationalConstants.PARAMETER_DEFAULT.DIRECTION : (String)propertyValue;
			wasSet = this.delegate.setPropertyValue(TeiidDdlLexicon.CreateProcedure.PARAMETER_TYPE, pVal);
		// ---------------------------
		// Statement Options
		// ---------------------------
		} else if(propertyKey.equals(RelationalConstants.PARAMETER_DDL_OPTION_KEYS.NATIVE_TYPE)) {
			if(propertyValue==null || ((String)propertyValue).equals(RelationalConstants.PARAMETER_DEFAULT.NATIVE_TYPE)) {
				wasSet = this.delegate.unsetOptionValue(propertyKey);
			} else {
				wasSet = this.delegate.setOptionValue(propertyKey, propertyValue);
			}
		}
		return wasSet;
	}

//    /**
//     * Set properties
//     * @param props the properties
//     */
//    @Override
//	public void setProperties(Properties props) {
//    	// Set common properties
//    	super.setProperties(props);
//    	
//        for( Object key : props.keySet() ) {
//            String keyStr = (String)key;
//            String value = props.getProperty(keyStr);
//
//            if( value != null && value.length() == 0 ) {
//                continue;
//            }
//            
//            if(keyStr.equalsIgnoreCase(KEY_LENGTH) ) {
//                setLength(Integer.parseInt(value));
//            } else if(keyStr.equalsIgnoreCase(KEY_DATATYPE) ) {
//                setDatatypeName(value);
//            } else if(keyStr.equalsIgnoreCase(KEY_DEFAULT_VALUE) ) {
//                setDefaultValue(value);
//            } else if(keyStr.equalsIgnoreCase(KEY_DIRECTION) ) {
//                setDirection(value);
//            } else if(keyStr.equalsIgnoreCase(KEY_NATIVE_TYPE) ) {
//                setNativeType(value);
//            } else if(keyStr.equalsIgnoreCase(KEY_NULLABLE) ) {
//                setNullable(value);
//            } else if(keyStr.equalsIgnoreCase(KEY_PRECISION) ) {
//                setPrecision(Integer.parseInt(value));
//            } else if(keyStr.equalsIgnoreCase(KEY_SCALE) ) {
//                setScale(Integer.parseInt(value));
//            } else if(keyStr.equalsIgnoreCase(KEY_RADIX) ) {
//                setRadix(Integer.parseInt(value));
//            } 
//        }
//    	
//        handleInfoChanged();
//    }
//    
//    /**
//     * {@inheritDoc}
//     * 
//     * @see java.lang.Object#equals(java.lang.Object)
//     */
//    @Override
//    public boolean equals( final Object object ) {
//		if (!super.equals(object)) {
//			return false;
//		}
//        if (this == object)
//            return true;
//        if (object == null)
//            return false;
//        if (getClass() != object.getClass())
//            return false;
//        final Parameter other = (Parameter)object;
//
//        // string properties
//        if (!StringUtil.valuesAreEqual(getDatatypeName(), other.getDatatypeName()) ||
//        		!StringUtil.valuesAreEqual(getDefaultValue(), other.getDefaultValue()) ||
//        		!StringUtil.valuesAreEqual(getDirection(), other.getDirection()) ||
//        		!StringUtil.valuesAreEqual(getNativeType(), other.getNativeType()) ||
//        		!StringUtil.valuesAreEqual(getNullable(), other.getNullable()) ) {
//        	return false;
//        }
//        
//        if( !(getLength()==other.getLength()) ||
//            !(getPrecision()==other.getPrecision()) ||
//            !(getRadix()==other.getRadix()) ||
//            !(getScale()==other.getScale()) ) {
//        	return false;
//        }
//        
//        return true;
//    }
//    
//    /**
//     * {@inheritDoc}
//     * 
//     * @see java.lang.Object#hashCode()
//     */
//    @Override
//    public int hashCode() {
//        int result = super.hashCode();
//
//        // string properties
//        if (!StringUtil.isEmpty(getDatatypeName())) {
//            result = HashCodeUtil.hashCode(result, getDatatype());
//        }
//        if (!StringUtil.isEmpty(getDefaultValue())) {
//            result = HashCodeUtil.hashCode(result, getDefaultValue());
//        }
//        if (!StringUtil.isEmpty(getDirection())) {
//            result = HashCodeUtil.hashCode(result, getDirection());
//        }
//        if (!StringUtil.isEmpty(getNativeType())) {
//            result = HashCodeUtil.hashCode(result, getNativeType());
//        }
//        if (!StringUtil.isEmpty(getNullable())) {
//            result = HashCodeUtil.hashCode(result, getNullable());
//        }
//
//        result = HashCodeUtil.hashCode(result, getLength());
//        result = HashCodeUtil.hashCode(result, getPrecision());
//        result = HashCodeUtil.hashCode(result, getRadix());
//        result = HashCodeUtil.hashCode(result, getScale());
//
//        return result;
//    }    

}
