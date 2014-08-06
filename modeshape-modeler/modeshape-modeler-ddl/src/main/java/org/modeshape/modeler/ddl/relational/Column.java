package org.modeshape.modeler.ddl.relational;

import java.util.Map;

/**
 * Column
 * The relational column class
 */
public class Column extends RelationalObject {

	Column(IObjectDelegate modelObject) {
		super(modelObject);
	}
	
    /**
     * @return nullValueCount
     */
    public int getNullValueCount() {
    	return Integer.parseInt(getPropertyValue(RelationalConstants.COLUMN_DDL_OPTION_KEYS.NULL_VALUE_COUNT).toString());
    }
    
    /**
     * @param nullValueCount Sets nullValueCount to the specified value.
     */
    public void setNullValueCount( int nullValueCount ) {
    	setPropertyValue(RelationalConstants.COLUMN_DDL_OPTION_KEYS.NULL_VALUE_COUNT,nullValueCount);
    }
    
    /**
     * @return datatype
     */
    public String getDatatypeName() {
    	Object propValue = getPropertyValue(RelationalConstants.COLUMN_DDL_PROPERTY_KEYS.DATATYPE_NAME);
    	return propValue==null ? null : propValue.toString();
    }
    
    /**
     * @param typeName Sets datatype to the specified value.
     */
    public void setDatatypeName( String typeName ) {
    	setPropertyValue(RelationalConstants.COLUMN_DDL_PROPERTY_KEYS.DATATYPE_NAME,typeName);
    }
    
    /**
     * @param datatype the datatype
     */
    public void setDatatype( String datatype ) {
    	//ArgCheck.isNotNull(datatype);
    	setPropertyValue(RelationalConstants.COLUMN_DDL_OPTION_KEYS.DATATYPE,datatype);
    }
    
    /**
     * @return the datatype
     */
    public String getDatatype( ) {
    	Object propValue = getPropertyValue(RelationalConstants.COLUMN_DDL_OPTION_KEYS.DATATYPE);
    	return propValue==null ? null : propValue.toString();
    }
    
    /**
     * @return nativeType
     */
    public String getNativeType() {
    	Object propValue = getPropertyValue(RelationalConstants.COLUMN_DDL_OPTION_KEYS.NATIVE_TYPE);
    	return propValue==null ? null : propValue.toString();
    }
    
    /**
     * @param nativeType Sets nativeType to the specified value.
     */
    public void setNativeType( String nativeType ) {
    	setPropertyValue(RelationalConstants.COLUMN_DDL_OPTION_KEYS.NATIVE_TYPE,nativeType);
    }
    
    /**
     * @return nullable
     */
    public String getNullable() {
		return getPropertyValue(RelationalConstants.COLUMN_DDL_PROPERTY_KEYS.NULLABLE).toString();
    }

    /**
     * @param nullable Sets nullable to the specified value.
     */
    public void setNullable( String nullable ) {
//    	ArgCheck.isNotEmpty(nullable);
//    	String[] allowedValues = NULLABLE.AS_ARRAY;
//    	boolean matchFound = false;
//    	for(int i=0; i<allowedValues.length; i++) {
//    		if(allowedValues[i].equalsIgnoreCase(nullable)) {
//    			this.nullable = allowedValues[i];
//    			matchFound = true;
//    		}
//    	}
//    	if(!matchFound) throw new IllegalArgumentException(Messages.getString(RELATIONAL.columnError_Nullable_NotAllowable,nullable));
    }
    
    /**
     * @return autoIncremented
     */
    public boolean isAutoIncremented() {
    	return Boolean.parseBoolean(getPropertyValue(RelationalConstants.COLUMN_DDL_PROPERTY_KEYS.AUTO_INCREMENTED).toString());
    }
        
    /**
     * @param autoIncremented Sets autoIncremented to the specified value.
     */
    public void setAutoIncremented( boolean autoIncremented ) {
    	setPropertyValue(RelationalConstants.COLUMN_DDL_PROPERTY_KEYS.AUTO_INCREMENTED,autoIncremented);
    }
    
    /**
     * @return caseSensitive
     */
    public boolean isCaseSensitive() {
    	return Boolean.parseBoolean(getPropertyValue(RelationalConstants.COLUMN_DDL_OPTION_KEYS.CASE_SENSITIVE).toString());
    }
    
    /**
     * @param caseSensitive Sets caseSensitive to the specified value.
     */
    public void setCaseSensitive( boolean caseSensitive ) {
    	setPropertyValue(RelationalConstants.COLUMN_DDL_OPTION_KEYS.CASE_SENSITIVE,caseSensitive);
    }
    
    /**
     * @return currency
     */
    public boolean isCurrency() {
    	return Boolean.parseBoolean(getPropertyValue(RelationalConstants.COLUMN_DDL_OPTION_KEYS.CURRENCY).toString());
    }
    
    /**
     * @param currency Sets currency to the specified value.
     */
    public void setCurrency( boolean currency ) {
    	setPropertyValue(RelationalConstants.COLUMN_DDL_OPTION_KEYS.CURRENCY,currency);
    }
    
    /**
     * @return defaultValue
     */
    public String getDefaultValue() {
    	Object propValue = getPropertyValue(RelationalConstants.COLUMN_DDL_PROPERTY_KEYS.DEFAULT_VALUE);
    	return propValue==null ? null : propValue.toString();
    }
   
	/**
     * @param defaultValue Sets defaultValue to the specified value.
     */
    public void setDefaultValue( String defaultValue ) {
    	setPropertyValue(RelationalConstants.COLUMN_DDL_PROPERTY_KEYS.DEFAULT_VALUE,defaultValue);
    }
    
    /**
     * @return length
     */
    public long getLength() {
    	return Long.parseLong(getPropertyValue(RelationalConstants.COLUMN_DDL_PROPERTY_KEYS.LENGTH).toString());
    }
    
    /**
     * @param length Sets length to the specified value.
     */
    public void setLength( long length ) {
    	setPropertyValue(RelationalConstants.COLUMN_DDL_PROPERTY_KEYS.LENGTH,length);
    }
    
    /**
     * @return lengthFixed
     */
    public boolean isLengthFixed() {
    	return Boolean.parseBoolean(getPropertyValue(RelationalConstants.COLUMN_DDL_OPTION_KEYS.FIXED_LENGTH).toString());
    }
    /**
     * @param lengthFixed Sets lengthFixed to the specified value.
     */
    public void setLengthFixed( boolean lengthFixed ) {
    	setPropertyValue(RelationalConstants.COLUMN_DDL_OPTION_KEYS.FIXED_LENGTH,lengthFixed);
    }
    
    /**
     * @return maximumValue
     */
    public String getMaximumValue() {
		return getPropertyValue(RelationalConstants.COLUMN_DDL_OPTION_KEYS.MAX_VALUE).toString();
    }
    
    /**
     * @param maximumValue Sets maximumValue to the specified value.
     */
    public void setMaximumValue( String maximumValue ) {
		setPropertyValue(RelationalConstants.COLUMN_DDL_OPTION_KEYS.MAX_VALUE,maximumValue);
    }
    
    /**
     * @return minimumValue
     */
    public String getMinimumValue() {
		return getPropertyValue(RelationalConstants.COLUMN_DDL_OPTION_KEYS.MIN_VALUE).toString();
    }
    
    /**
     * @param minimumValue Sets minimumValue to the specified value.
     */
    public void setMinimumValue( String minimumValue ) {
		setPropertyValue(RelationalConstants.COLUMN_DDL_OPTION_KEYS.MIN_VALUE,minimumValue);
    }
    
    /**
     * @return precision
     */
    public int getPrecision() {
    	return Integer.parseInt(getPropertyValue(RelationalConstants.COLUMN_DDL_PROPERTY_KEYS.PRECISION).toString());
    }
    
    /**
     * @param precision Sets precision to the specified value.
     */
    public void setPrecision( int precision ) {
		setPropertyValue(RelationalConstants.COLUMN_DDL_PROPERTY_KEYS.PRECISION,precision);
    }
    
    /**
     * @return scale
     */
    public int getScale() {
    	return Integer.parseInt(getPropertyValue(RelationalConstants.COLUMN_DDL_PROPERTY_KEYS.SCALE).toString());
    }
    
    /**
     * @param scale Sets scale to the specified value.
     */
    public void setScale( int scale ) {
		setPropertyValue(RelationalConstants.COLUMN_DDL_PROPERTY_KEYS.SCALE,scale);
    }
   
    /**
     * @return radix
     */
    public int getRadix() {
    	return Integer.parseInt(getPropertyValue(RelationalConstants.COLUMN_DDL_OPTION_KEYS.RADIX).toString());
    }
   
    /**
     * @param radix Sets radix to the specified value.
     */
    public void setRadix( int radix ) {
		setPropertyValue(RelationalConstants.COLUMN_DDL_OPTION_KEYS.RADIX,radix);
    }
    
    /**
	 * @return the characterOctetLength
	 */
	public int getCharacterOctetLength() {
		return Integer.parseInt(getPropertyValue(RelationalConstants.COLUMN_DDL_OPTION_KEYS.CHAR_OCTET_LENGTH).toString());
	}

	/**
	 * @param characterOctetLength the characterOctetLength to set
	 */
	public void setCharacterOctetLength(int characterOctetLength) {
		setPropertyValue(RelationalConstants.COLUMN_DDL_OPTION_KEYS.CHAR_OCTET_LENGTH,characterOctetLength);
	}

	/**
     * @return signed
     */
    public boolean isSigned() {
    	return Boolean.parseBoolean(getPropertyValue(RelationalConstants.COLUMN_DDL_OPTION_KEYS.SIGNED).toString());
    }
    
    /**
     * @param signed Sets signed to the specified value.
     */
    public void setSigned( boolean signed ) {
    	setPropertyValue(RelationalConstants.COLUMN_DDL_OPTION_KEYS.SIGNED,signed);
    }
    
    /**
     * @return searchability
     */
    public String getSearchability() {
		return getPropertyValue(RelationalConstants.COLUMN_DDL_OPTION_KEYS.SEARCHABLE).toString();
    }
    
    /**
     * @param searchability Sets searchability to the specified value.
     */
    public void setSearchability( String searchability ) {
//    	ArgCheck.isNotEmpty(searchability);
//    	String[] allowedValues = SEARCHABILITY.AS_ARRAY;
//    	boolean matchFound = false;
//    	for(int i=0; i<allowedValues.length; i++) {
//    		if(allowedValues[i].equalsIgnoreCase(searchability)) {
//    			this.searchability = allowedValues[i];
//    			matchFound = true;
//    		}
//    	}
//    	if(!matchFound) throw new IllegalArgumentException(Messages.getString(RELATIONAL.columnError_Searchability_NotAllowable,searchability));
    }

    /**
     * @return selectable
     */
    public boolean isSelectable() {
    	return Boolean.parseBoolean(getPropertyValue(RelationalConstants.COLUMN_DDL_OPTION_KEYS.SELECTABLE).toString());
    }
    
    /**
     * @param selectable Sets selectable to the specified value.
     */
    public void setSelectable( boolean selectable ) {
    	setPropertyValue(RelationalConstants.COLUMN_DDL_OPTION_KEYS.SELECTABLE,selectable);
    }
    
    /**
     * @return updateable
     */
    public boolean isUpdateable() {
    	return Boolean.parseBoolean(getPropertyValue(RelationalConstants.COLUMN_DDL_OPTION_KEYS.UPDATABLE).toString());
    }
    
    /**
     * @param updateable Sets updateable to the specified value.
     */
    public void setUpdateable( boolean updateable ) {
    	setPropertyValue(RelationalConstants.COLUMN_DDL_OPTION_KEYS.UPDATABLE,updateable);
    }
	    	
	@SuppressWarnings("javadoc")
	@Override
	public Map<String, Object> getProperties() {
    	Map<String,Object> props = super.getProperties();
    	
    	// Add values for column properties
    	String[] propKeys = RelationalConstants.COLUMN_DDL_PROPERTY_KEYS.toArray();
    	for(int i=0; i<propKeys.length; i++) {
    		props.put(propKeys[i], getPropertyValue(propKeys[i]));
    	}
    	
    	// Add values for column properties
    	propKeys = RelationalConstants.COLUMN_DDL_OPTION_KEYS.toArray();
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
		
		if(propertyKey.equals(RelationalConstants.COLUMN_DDL_OPTION_KEYS.SELECTABLE)) {
			propertyValue = this.delegate.getOptionValue(propertyKey);
			propertyValue = (propertyValue==null) ? RelationalConstants.COLUMN_DEFAULT.SELECTABLE : propertyValue;
		} else if(propertyKey.equals(RelationalConstants.COLUMN_DDL_OPTION_KEYS.UPDATABLE)) {
			propertyValue = this.delegate.getOptionValue(propertyKey);
			propertyValue = (propertyValue==null) ? RelationalConstants.COLUMN_DEFAULT.UPDATABLE : propertyValue;
		} else if(propertyKey.equals(RelationalConstants.COLUMN_DDL_OPTION_KEYS.CURRENCY)) {
			propertyValue = this.delegate.getOptionValue(propertyKey);
			propertyValue = (propertyValue==null) ? RelationalConstants.COLUMN_DEFAULT.CURRENCY : propertyValue;
		} else if(propertyKey.equals(RelationalConstants.COLUMN_DDL_OPTION_KEYS.CASE_SENSITIVE)) {
			propertyValue = this.delegate.getOptionValue(propertyKey);
			propertyValue = (propertyValue==null) ? RelationalConstants.COLUMN_DEFAULT.CASE_SENSITIVE : propertyValue;
		} else if(propertyKey.equals(RelationalConstants.COLUMN_DDL_OPTION_KEYS.SIGNED)) {
			propertyValue = this.delegate.getOptionValue(propertyKey);
			propertyValue = (propertyValue==null) ? RelationalConstants.COLUMN_DEFAULT.SIGNED : propertyValue;
		} else if(propertyKey.equals(RelationalConstants.COLUMN_DDL_OPTION_KEYS.FIXED_LENGTH)) {
			propertyValue = this.delegate.getOptionValue(propertyKey);
			propertyValue = (propertyValue==null) ? RelationalConstants.COLUMN_DEFAULT.FIXED_LENGTH : propertyValue;
		} else if(propertyKey.equals(RelationalConstants.COLUMN_DDL_OPTION_KEYS.SEARCHABLE)) {
			propertyValue = this.delegate.getOptionValue(propertyKey);
			propertyValue = (propertyValue==null) ? RelationalConstants.COLUMN_DEFAULT.SEARCHABILITY : propertyValue;
		} else if(propertyKey.equals(RelationalConstants.COLUMN_DDL_OPTION_KEYS.MIN_VALUE)) {
			propertyValue = this.delegate.getOptionValue(propertyKey);
			propertyValue = (propertyValue==null) ? RelationalConstants.COLUMN_DEFAULT.MINIMUM_VALUE : propertyValue;
		} else if(propertyKey.equals(RelationalConstants.COLUMN_DDL_OPTION_KEYS.MAX_VALUE)) {
			propertyValue = this.delegate.getOptionValue(propertyKey);
			propertyValue = (propertyValue==null) ? RelationalConstants.COLUMN_DEFAULT.MAXIMUM_VALUE : propertyValue;
		} else if(propertyKey.equals(RelationalConstants.COLUMN_DDL_OPTION_KEYS.NATIVE_TYPE)) {
			propertyValue = this.delegate.getOptionValue(propertyKey);
			propertyValue = (propertyValue==null) ? RelationalConstants.COLUMN_DEFAULT.NATIVE_TYPE : propertyValue;
		} else if(propertyKey.equals(RelationalConstants.COLUMN_DDL_OPTION_KEYS.NULL_VALUE_COUNT)) {
			propertyValue = this.delegate.getOptionValue(propertyKey);
			propertyValue = (propertyValue==null) ? RelationalConstants.COLUMN_DEFAULT.NULL_VALUE_COUNT : propertyValue;
		} else if(propertyKey.equals(RelationalConstants.COLUMN_DDL_OPTION_KEYS.RADIX)) {
			propertyValue = this.delegate.getOptionValue(propertyKey);
			propertyValue = (propertyValue==null) ? RelationalConstants.COLUMN_DEFAULT.RADIX : propertyValue;
		} else if(propertyKey.equals(RelationalConstants.COLUMN_DDL_OPTION_KEYS.CHAR_OCTET_LENGTH)) {
			propertyValue = this.delegate.getOptionValue(propertyKey);
			propertyValue = (propertyValue==null) ? RelationalConstants.COLUMN_DEFAULT.CHAR_OCTET_LENGTH : propertyValue;
		} else if(propertyKey.equals(RelationalConstants.COLUMN_DDL_PROPERTY_KEYS.NULLABLE)) {
			propertyValue = this.delegate.getPropertyValue(StandardDdlLexicon.NULLABLE);
			propertyValue = (propertyValue==null) ? RelationalConstants.COLUMN_DEFAULT.NULLABLE : propertyValue;
		} else if(propertyKey.equals(RelationalConstants.COLUMN_DDL_PROPERTY_KEYS.AUTO_INCREMENTED)) {
			propertyValue = this.delegate.getPropertyValue(TeiidDdlLexicon.CreateTable.AUTO_INCREMENT);
			propertyValue = (propertyValue==null) ? RelationalConstants.COLUMN_DEFAULT.AUTO_INCREMENTED : propertyValue;
		} else if(propertyKey.equals(RelationalConstants.COLUMN_DDL_PROPERTY_KEYS.DATATYPE_NAME)) {
			propertyValue = this.delegate.getPropertyValue(StandardDdlLexicon.DATATYPE_NAME);
			propertyValue = (propertyValue==null) ? RelationalConstants.COLUMN_DEFAULT.DATATYPE_NAME : propertyValue;
		} else if(propertyKey.equals(RelationalConstants.COLUMN_DDL_PROPERTY_KEYS.LENGTH)) {
			propertyValue = this.delegate.getPropertyValue(StandardDdlLexicon.DATATYPE_LENGTH);
			propertyValue = (propertyValue==null) ? RelationalConstants.COLUMN_DEFAULT.LENGTH : propertyValue;
		} else if(propertyKey.equals(RelationalConstants.COLUMN_DDL_PROPERTY_KEYS.PRECISION)) {
			propertyValue = this.delegate.getPropertyValue(StandardDdlLexicon.DATATYPE_PRECISION);
			propertyValue = (propertyValue==null) ? RelationalConstants.COLUMN_DEFAULT.PRECISION : propertyValue;
		} else if(propertyKey.equals(RelationalConstants.COLUMN_DDL_PROPERTY_KEYS.SCALE)) {
			propertyValue = this.delegate.getPropertyValue(StandardDdlLexicon.DATATYPE_SCALE);
			propertyValue = (propertyValue==null) ? RelationalConstants.COLUMN_DEFAULT.SCALE : propertyValue;
		} else if(propertyKey.equals(RelationalConstants.COLUMN_DDL_PROPERTY_KEYS.DEFAULT_VALUE)) {
			propertyValue = this.delegate.getPropertyValue(StandardDdlLexicon.DEFAULT_VALUE);
			propertyValue = (propertyValue==null) ? RelationalConstants.COLUMN_DEFAULT.DEFAULT_VALUE : propertyValue;
		}
		return propertyValue;
	}
	
	@Override
	@SuppressWarnings("javadoc")
	public boolean setPropertyValue(String propertyKey, Object propertyValue) {
		boolean wasSet = super.setPropertyValue(propertyKey,propertyValue);
		if(wasSet) return true;
		
		// ---------------------------
		// Statement Options
		// ---------------------------
		if(propertyKey.equals(RelationalConstants.COLUMN_DDL_OPTION_KEYS.SELECTABLE)) {
			if(propertyValue==null || ((Boolean)propertyValue)==RelationalConstants.COLUMN_DEFAULT.SELECTABLE) {
				wasSet = this.delegate.unsetOptionValue(propertyKey);
			} else {
				wasSet = this.delegate.setOptionValue(propertyKey, propertyValue);
			}
		} else if(propertyKey.equals(RelationalConstants.COLUMN_DDL_OPTION_KEYS.UPDATABLE)) {
			if(propertyValue==null || ((Boolean)propertyValue)==RelationalConstants.COLUMN_DEFAULT.UPDATABLE) {
				wasSet = this.delegate.unsetOptionValue(propertyKey);
			} else {
				wasSet = this.delegate.setOptionValue(propertyKey, propertyValue);
			}
		} else if(propertyKey.equals(RelationalConstants.COLUMN_DDL_OPTION_KEYS.CURRENCY)) {
			if(propertyValue==null || ((Boolean)propertyValue)==RelationalConstants.COLUMN_DEFAULT.CURRENCY) {
				wasSet = this.delegate.unsetOptionValue(propertyKey);
			} else {
				wasSet = this.delegate.setOptionValue(propertyKey, propertyValue);
			}
		} else if(propertyKey.equals(RelationalConstants.COLUMN_DDL_OPTION_KEYS.CASE_SENSITIVE)) {
			if(propertyValue==null || ((Boolean)propertyValue)==RelationalConstants.COLUMN_DEFAULT.CASE_SENSITIVE) {
				wasSet = this.delegate.unsetOptionValue(propertyKey);
			} else {
				wasSet = this.delegate.setOptionValue(propertyKey, propertyValue);
			}
		} else if(propertyKey.equals(RelationalConstants.COLUMN_DDL_OPTION_KEYS.SIGNED)) {
			if(propertyValue==null || ((Boolean)propertyValue)==RelationalConstants.COLUMN_DEFAULT.SIGNED) {
				wasSet = this.delegate.unsetOptionValue(propertyKey);
			} else {
				wasSet = this.delegate.setOptionValue(propertyKey, propertyValue);
			}
		} else if(propertyKey.equals(RelationalConstants.COLUMN_DDL_OPTION_KEYS.FIXED_LENGTH)) {
			if(propertyValue==null || ((Boolean)propertyValue)==RelationalConstants.COLUMN_DEFAULT.FIXED_LENGTH) {
				wasSet = this.delegate.unsetOptionValue(propertyKey);
			} else {
				wasSet = this.delegate.setOptionValue(propertyKey, propertyValue);
			}
		} else if(propertyKey.equals(RelationalConstants.COLUMN_DDL_OPTION_KEYS.SEARCHABLE)) {
			if(propertyValue==null || ((String)propertyValue).equals(RelationalConstants.COLUMN_DEFAULT.SEARCHABILITY)) {
				wasSet = this.delegate.unsetOptionValue(propertyKey);
			} else {
				wasSet = this.delegate.setOptionValue(propertyKey, propertyValue);
			}
		} else if(propertyKey.equals(RelationalConstants.COLUMN_DDL_OPTION_KEYS.MIN_VALUE)) {
			if(propertyValue==null || ((String)propertyValue).equals(RelationalConstants.COLUMN_DEFAULT.MINIMUM_VALUE)) {
				wasSet = this.delegate.unsetOptionValue(propertyKey);
			} else {
				wasSet = this.delegate.setOptionValue(propertyKey, propertyValue);
			}
		} else if(propertyKey.equals(RelationalConstants.COLUMN_DDL_OPTION_KEYS.MAX_VALUE)) {
			if(propertyValue==null || ((String)propertyValue).equals(RelationalConstants.COLUMN_DEFAULT.MAXIMUM_VALUE)) {
				wasSet = this.delegate.unsetOptionValue(propertyKey);
			} else {
				wasSet = this.delegate.setOptionValue(propertyKey, propertyValue);
			}
		} else if(propertyKey.equals(RelationalConstants.COLUMN_DDL_OPTION_KEYS.NATIVE_TYPE)) {
			if(propertyValue==null || ((String)propertyValue).equals(RelationalConstants.COLUMN_DEFAULT.NATIVE_TYPE)) {
				wasSet = this.delegate.unsetOptionValue(propertyKey);
			} else {
				wasSet = this.delegate.setOptionValue(propertyKey, propertyValue);
			}
		} else if(propertyKey.equals(RelationalConstants.COLUMN_DDL_OPTION_KEYS.NULL_VALUE_COUNT)) {
			if(propertyValue==null || ((Long)propertyValue)==RelationalConstants.COLUMN_DEFAULT.NULL_VALUE_COUNT) {
				wasSet = this.delegate.unsetOptionValue(propertyKey);
			} else {
				wasSet = this.delegate.setOptionValue(propertyKey, propertyValue);
			}
		} else if(propertyKey.equals(RelationalConstants.COLUMN_DDL_OPTION_KEYS.RADIX)) {
			if(propertyValue==null || ((Long)propertyValue)==RelationalConstants.COLUMN_DEFAULT.RADIX) {
				wasSet = this.delegate.unsetOptionValue(propertyKey);
			} else {
				wasSet = this.delegate.setOptionValue(propertyKey, propertyValue);
			}
		} else if(propertyKey.equals(RelationalConstants.COLUMN_DDL_OPTION_KEYS.CHAR_OCTET_LENGTH)) {
			if(propertyValue==null || ((Long)propertyValue)==RelationalConstants.COLUMN_DEFAULT.CHAR_OCTET_LENGTH) {
				wasSet = this.delegate.unsetOptionValue(propertyKey);
			} else {
				wasSet = this.delegate.setOptionValue(propertyKey, propertyValue);
			}
		// ---------------------------
		// Properties
		// ---------------------------
		} else if(propertyKey.equals(RelationalConstants.COLUMN_DDL_PROPERTY_KEYS.NULLABLE)) {
			String pVal = (propertyValue==null) ? RelationalConstants.COLUMN_DEFAULT.NULLABLE : (String)propertyValue;
			wasSet = this.delegate.setPropertyValue(propertyKey, pVal);
		} else if(propertyKey.equals(RelationalConstants.COLUMN_DDL_PROPERTY_KEYS.AUTO_INCREMENTED)) {
			Boolean pVal = (propertyValue==null) ? RelationalConstants.COLUMN_DEFAULT.AUTO_INCREMENTED : (Boolean)propertyValue;
			wasSet = this.delegate.setPropertyValue(propertyKey, pVal);
		} else if(propertyKey.equals(RelationalConstants.COLUMN_DDL_PROPERTY_KEYS.DATATYPE_NAME)) {
			String pVal = (propertyValue==null) ? RelationalConstants.COLUMN_DEFAULT.DATATYPE_NAME : (String)propertyValue;
			wasSet = this.delegate.setPropertyValue(propertyKey, pVal);
		} else if(propertyKey.equals(RelationalConstants.COLUMN_DDL_PROPERTY_KEYS.LENGTH)) {
			Long pVal = (propertyValue==null) ? RelationalConstants.COLUMN_DEFAULT.LENGTH : (Long)propertyValue;
			wasSet = this.delegate.setPropertyValue(propertyKey, pVal);
		} else if(propertyKey.equals(RelationalConstants.COLUMN_DDL_PROPERTY_KEYS.PRECISION)) {
			Long pVal = (propertyValue==null) ? RelationalConstants.COLUMN_DEFAULT.PRECISION : (Long)propertyValue;
			wasSet = this.delegate.setPropertyValue(propertyKey, pVal);
		} else if(propertyKey.equals(RelationalConstants.COLUMN_DDL_PROPERTY_KEYS.SCALE)) {
			Long pVal = (propertyValue==null) ? RelationalConstants.COLUMN_DEFAULT.SCALE : (Long)propertyValue;
			wasSet = this.delegate.setPropertyValue(propertyKey, pVal);
		} else if(propertyKey.equals(RelationalConstants.COLUMN_DDL_PROPERTY_KEYS.DEFAULT_VALUE)) {
			String pVal = (propertyValue==null) ? RelationalConstants.COLUMN_DEFAULT.DEFAULT_VALUE : (String)propertyValue;
			wasSet = this.delegate.setPropertyValue(propertyKey, pVal);
		}
		return wasSet;
	}
	
}
