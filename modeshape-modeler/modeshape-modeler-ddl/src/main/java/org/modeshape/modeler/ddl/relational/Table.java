package org.modeshape.modeler.ddl.relational;

import java.util.List;
import java.util.Map;

/**
 * Table
 * The relational table class
 */
public class Table extends RelationalObject {
    
	Table(IObjectDelegate modelObject) {
		super(modelObject);
	}
	
    /**
     * @return cardinality
     */
	public int getCardinality() {
		return Integer.parseInt(getPropertyValue(RelationalConstants.TABLE_DDL_OPTION_KEYS.CARDINALITY).toString());
	}
	
    /**
     * Set the cardinality
     * @param cardinality the cardinality
     */
	public void setCardinality(int cardinality) {
		setPropertyValue(RelationalConstants.TABLE_DDL_OPTION_KEYS.CARDINALITY,cardinality);
	}

    /**
     * @return isMaterialized
     */
	public boolean isMaterialized() {
		return Boolean.parseBoolean(getPropertyValue(RelationalConstants.TABLE_DDL_OPTION_KEYS.MATERIALIZED).toString());
	}
	
    /**
     * Set isMaterialized
     * @param isMaterialized materialized state
     */
	public void setMaterialized(boolean isMaterialized) {
		setPropertyValue(RelationalConstants.TABLE_DDL_OPTION_KEYS.MATERIALIZED,isMaterialized);
	}

	/**
     * @return Materialized tablename
     */
	public String getMaterializedTable() {
		Object propValue = getPropertyValue(RelationalConstants.TABLE_DDL_OPTION_KEYS.MATERIALIZED_TABLE);
		return  propValue==null ? null : propValue.toString();
	}

	/**
	 * Set the materialized tablename
     * @param tablename the Materialized tablename
     */
	public void setMaterializedTable(String tablename) {
		setPropertyValue(RelationalConstants.TABLE_DDL_OPTION_KEYS.MATERIALIZED_TABLE,tablename);
	}
	
    /**
     * @return supportsUpdate
     */
	public boolean supportsUpdate() {
		return Boolean.parseBoolean(getPropertyValue(RelationalConstants.TABLE_DDL_OPTION_KEYS.UPDATABLE).toString());
	}
	
    /**
     * Set updatable property
     * @param supportsUpdate 'true' if supportsUpdate, 'false' if not.
     */
	public void setSupportsUpdate(boolean supportsUpdate) {
		setPropertyValue(RelationalConstants.TABLE_DDL_OPTION_KEYS.UPDATABLE, Boolean.valueOf(supportsUpdate));
	}
	
    /**
     * Get the child Columns
     * @return Columns
     */
    public List<Column> getColumns() {
    	return getChildren(Column.class);
    }
    
    /**
     * Get the Column with the specified name (if it exists)
     * @param colName the column name
     * @return Column
     */
    public Column getColumn(String colName) {
    	Column result = null;
    	List<Column> cols = getColumns();
    	for(Column col : cols) {
    		if(col.getName().equalsIgnoreCase(colName)) {
    			result = col;
    			break;
    		}
    	}
    	return result;
    }
    
    /**
     * Get the child AccessPatterns
     * @return AccessPatterns
     */
    public List<AccessPattern> getAccessPatterns() {
    	return getChildren(AccessPattern.class);
    }
    
    /**
     * Get the child ForeignKeys
     * @return ForeignKeys
     */
    public List<ForeignKey> getForeignKeys() {
    	return getChildren(ForeignKey.class);
    }
    
    /**
     * Get the child UniqueConstraints
     * @return UniqueConstraints
     */
    public List<UniqueConstraint> getUniqueConstraints() {
    	return getChildren(UniqueConstraint.class);
    }
    
    /**
     * Get the PrimaryKey
     * @return PrimaryKey
     */
    public PrimaryKey getPrimaryKey() {
    	List<PrimaryKey> pkList = getChildren(PrimaryKey.class);
    	return (pkList.size()!=1) ? null : pkList.get(0);
    }
    
	@SuppressWarnings("javadoc")
	@Override
	public Map<String, Object> getProperties() {
    	Map<String,Object> props = super.getProperties();
    	
    	// Add property values for column
    	String[] propKeys = RelationalConstants.TABLE_DDL_OPTION_KEYS.toArray();
    	for(int i=0; i<propKeys.length; i++) {
    		props.put(propKeys[i], getPropertyValue(propKeys[i]));
    	}

    	return props;
	}
	
	@SuppressWarnings("javadoc")
	@Override
	public Object getPropertyValue(String propertyKey) {
		Object propertyValue = super.getPropertyValue(propertyKey);

		if(propertyKey.equals(RelationalConstants.TABLE_DDL_OPTION_KEYS.CARDINALITY)) {
			propertyValue = this.delegate.getOptionValue(propertyKey);
			return propertyValue == null ? RelationalConstants.TABLE_DEFAULT.CARDINALITY : propertyValue;
		} else if(propertyKey.equals(RelationalConstants.TABLE_DDL_OPTION_KEYS.MATERIALIZED)) {
			propertyValue = this.delegate.getOptionValue(propertyKey);
			return propertyValue == null ? RelationalConstants.TABLE_DEFAULT.MATERIALIZED : propertyValue;
		} else if(propertyKey.equals(RelationalConstants.TABLE_DDL_OPTION_KEYS.MATERIALIZED_TABLE)) {
			propertyValue = this.delegate.getOptionValue(propertyKey);
			return propertyValue == null ? RelationalConstants.TABLE_DEFAULT.MATERIALIZED_TABLE : propertyValue;
		} else if(propertyKey.equals(RelationalConstants.TABLE_DDL_OPTION_KEYS.UPDATABLE)) {
			propertyValue = this.delegate.getOptionValue(propertyKey);
			return propertyValue == null ? RelationalConstants.TABLE_DEFAULT.UPDATABLE : propertyValue;
		}
		return propertyValue;
	}
	
	@Override
	@SuppressWarnings("javadoc")
	public boolean setPropertyValue(String propertyKey, Object propValue) {
		boolean wasSet = super.setPropertyValue(propertyKey,propValue);
		if(wasSet) return true;
		
		// ---------------------------
		// Statement Options
		// ---------------------------
		if(propertyKey.equals(RelationalConstants.TABLE_DDL_OPTION_KEYS.CARDINALITY)) {
			if(propValue==null || ((Integer)propValue)==RelationalConstants.TABLE_DEFAULT.CARDINALITY) {
				wasSet = this.delegate.unsetOptionValue(propertyKey);
			} else {
				wasSet = this.delegate.setOptionValue(propertyKey, propValue);
			}
		} else if(propertyKey.equals(RelationalConstants.TABLE_DDL_OPTION_KEYS.MATERIALIZED)) {
			if(propValue==null || ((Boolean)propValue)==RelationalConstants.TABLE_DEFAULT.MATERIALIZED) {
				wasSet = this.delegate.unsetOptionValue(propertyKey);
			} else {
				wasSet = this.delegate.setOptionValue(propertyKey, propValue);
			}
		} else if(propertyKey.equals(RelationalConstants.TABLE_DDL_OPTION_KEYS.MATERIALIZED_TABLE)) {
			if(propValue==null || ((String)propValue).equals(RelationalConstants.TABLE_DEFAULT.MATERIALIZED_TABLE)) {
				wasSet = this.delegate.unsetOptionValue(propertyKey);
			} else {
				wasSet = this.delegate.setOptionValue(propertyKey, propValue);
			}
		} else if(propertyKey.equals(RelationalConstants.TABLE_DDL_OPTION_KEYS.UPDATABLE)) {
			if(propValue==null || ((Boolean)propValue)==RelationalConstants.TABLE_DEFAULT.UPDATABLE) {
				wasSet = this.delegate.unsetOptionValue(propertyKey);
			} else {
				wasSet = this.delegate.setOptionValue(propertyKey, propValue);
			}
		}
		return wasSet;
	}
	
}
