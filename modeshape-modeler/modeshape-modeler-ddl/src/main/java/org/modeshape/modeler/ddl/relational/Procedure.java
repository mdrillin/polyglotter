package org.modeshape.modeler.ddl.relational;

import java.util.List;
import java.util.Map;

/**
 * Procedure
 * The relational procedure class
 */
public class Procedure extends RelationalObject {
    
	Procedure(IObjectDelegate modelObject) {
		super(modelObject);
	}
	
    /**
     * Get the child Parameters
     * @return Parameter list
     */
    public List<Parameter> getParameters() {
    	return getChildren(Parameter.class);
    }
    
    /**
     * Get the Parameter with the specified name (if it exists)
     * @param paramName the parameter name
     * @return Parameter
     */
    public Parameter getParameter(String paramName) {
    	Parameter result = null;
    	List<Parameter> params = getChildren(Parameter.class);
    	for(Parameter param : params) {
    		if(param.getName().equalsIgnoreCase(paramName)) {
    			result = param;
    			break;
    		}
    	}
    	return result;
    }
    
    /**
     * Get the child ResultSet, 'null' if none defined
     * @return ResultSet
     */
    public ProcedureResultSet getProcedureResultSet() {
    	List<ProcedureResultSet> rsList = getChildren(ProcedureResultSet.class);
    	return (rsList.size()!=1) ? null : rsList.get(0);
    }
    
    /**
     * @return updateCount
     */
    public String getUpdateCount() {
    	return getPropertyValue(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.UPDATECOUNT).toString();
    }
    
    /**
     * @param updateCount the update count
     */
    public void setUpdateCount(String updateCount) {
    	setPropertyValue(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.UPDATECOUNT,updateCount);
    }
    
    /**
     * @return function
     */
    public boolean isNonPrepared() {
    	return Boolean.parseBoolean(getPropertyValue(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.NON_PREPARED).toString());
    }
    
    /**
     * @param nonPrepared value
     */
    public void setNonPrepared(boolean nonPrepared) {
    	setPropertyValue(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.NON_PREPARED,nonPrepared);
    }
    
    /**
     * @return function
     */
//    public boolean isFunction() {
//    	String propValue = getPropertyValue(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.);
//		return propValue == null ? RelationalConstants.PROCEDURE_DEFAULT.NON_PREPARED : Boolean.parseBoolean(propValue);
//    }

    /**
     * @return deterministic
     */
    public String getDeterminism() {
    	return getPropertyValue(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.DETERMINISM).toString();
    }
    
    /**
     * @param determinism value
     */
    public void setDeterminism(String determinism) {
    	setPropertyValue(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.DETERMINISM,determinism);
    }
    
    /**
     * @return returnsNullOnNull
     */
    public boolean isReturnsNullOnNull() {
    	return Boolean.parseBoolean(getPropertyValue(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.NULL_ON_NULL).toString());
    }
    
    /**
     * @param returnsNullOnNull value
     */
    public void setReturnsNullOnNull(boolean returnsNullOnNull) {
    	setPropertyValue(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.NULL_ON_NULL,returnsNullOnNull);
    }
    
    /**
     * @return variableArguments
     */
    public boolean isVariableArguments() {
    	return Boolean.parseBoolean(getPropertyValue(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.VARARGS).toString());
    }
    
    /**
     * @param variableArguments value
     */
    public void setVariableArguments(boolean variableArguments) {
    	setPropertyValue(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.VARARGS,variableArguments);
    }
    
    /**
     * @return aggregate
     */
    public boolean isAggregate() {
    	return Boolean.parseBoolean(getPropertyValue(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.AGGREGATE).toString());
    }
    
    /**
     * @param aggregate is aggregate value
     */
    public void setAggregate(boolean aggregate) {
    	setPropertyValue(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.AGGREGATE,aggregate);
    }
    
    /**
     * @return allowsDistinct
     */
    public boolean isAllowsDistinct() {
    	return Boolean.parseBoolean(getPropertyValue(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.ALLOWS_DISTINCT).toString());
    }
    
    /**
     * @param allowsDistinct value
     */
    public void setAllowsDistinct(boolean allowsDistinct) {
    	setPropertyValue(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.ALLOWS_DISTINCT,allowsDistinct);
    }
    
    /**
     * @return allowsOrderBy
     */
    public boolean isAllowsOrderBy() {
    	return Boolean.parseBoolean(getPropertyValue(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.ALLOWS_ORDERBY).toString());
    }
    
    /**
     * @param allowsOrderBy value
     */
    public void setAllowsOrderBy(boolean allowsOrderBy) {
    	setPropertyValue(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.ALLOWS_ORDERBY,allowsOrderBy);
    }
    
    /**
     * @return analytic
     */
    public boolean isAnalytic() {
    	return Boolean.parseBoolean(getPropertyValue(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.ANALYTIC).toString());
    }
    
    /**
     * @param analytic value
     */
    public void setAnalytic(boolean analytic) {
    	setPropertyValue(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.ANALYTIC,analytic);
    }
    
    /**
     * @return decomposable
     */
    public boolean isDecomposable() {
    	return Boolean.parseBoolean(getPropertyValue(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.DECOMPOSABLE).toString());
    }
    
    /**
     * @param decomposable value
     */
    public void setDecomposable(boolean decomposable) {
    	setPropertyValue(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.DECOMPOSABLE,decomposable);
    }
    
    /**
     * @return useDistinctRows
     */
    public boolean isUseDistinctRows() {
    	return Boolean.parseBoolean(getPropertyValue(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.USES_DISTINCT_ROWS).toString());
    }
    
    /**
     * @param useDistinctRows value
     */
    public void setUseDistinctRows(boolean useDistinctRows) {
    	setPropertyValue(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.USES_DISTINCT_ROWS,useDistinctRows);
    }
    
    /**
     * @return is source function
     */
//    public boolean isSourceFunction() {
//    	String propValue = getPropertyValue(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.NON_PREPARED);
//		return propValue == null ? RelationalConstants.PROCEDURE_DEFAULT.NON_PREPARED : Boolean.parseBoolean(propValue);
//    }
    
    /**
     * @return java class name for function may be null
     */
    public String getJavaClassName() {
    	return getPropertyValue(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.JAVA_CLASS).toString();
    }
    
    /**
     * @param className value
     */
    public void setJavaClassName(String className) {
    	setPropertyValue(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.JAVA_CLASS,className);
    }
    
    /**
     * @return java class name for function may be null
     */
    public String getJavaMethodName() {
    	return getPropertyValue(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.JAVA_METHOD).toString();
    }
    
    /**
     * @param methodName value
     */
    public void setJavaMethodName(String methodName) {
    	setPropertyValue(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.JAVA_METHOD,methodName);
    }
    
    /**
     * @return function category
     */
    public String getFunctionCategory() {
    	return getPropertyValue(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.CATEGORY).toString();
    }
    
    /**
     * @param category value
     */
    public void setFunctionCategory(String category) {
    	setPropertyValue(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.CATEGORY,category);
    }
    
    /**
     * @return nativeQuery may be null
     */
    public String getNativeQuery() {
    	return getPropertyValue(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.NATIVE_QUERY).toString();
    }
        	
    /**
     * @param nativeQuery may be null
     */
    public void setNativeQuery(String nativeQuery) {
    	setPropertyValue(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.NATIVE_QUERY,nativeQuery);
    }
        	
	@SuppressWarnings("javadoc")
	@Override
	public Map<String, Object> getProperties() {
    	Map<String,Object> props = super.getProperties();
    	
    	// Add values for column properties
    	String[] propKeys = RelationalConstants.PROCEDURE_DDL_PROPERTY_KEYS.toArray();
    	for(int i=0; i<propKeys.length; i++) {
    		props.put(propKeys[i], getPropertyValue(propKeys[i]));
    	}
    	
    	// Add values for column options
    	propKeys = RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.toArray();
    	for(int i=0; i<propKeys.length; i++) {
    		props.put(propKeys[i], getPropertyValue(propKeys[i]));
    	}
    	
    	return props;
	}
	
	@SuppressWarnings("javadoc")
	@Override
	public Object getPropertyValue(String propertyKey) {
		Object propertyValue = super.getPropertyValue(propertyKey);
		
		if(propertyKey.equals(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.UPDATECOUNT)) {
			propertyValue = this.delegate.getOptionValue(propertyKey);
			propertyValue = (propertyValue==null) ? RelationalConstants.PROCEDURE_DEFAULT.UPDATECOUNT : propertyValue;
		} else if (propertyKey.equals(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.CATEGORY)) {
			propertyValue = this.delegate.getOptionValue(propertyKey);
			propertyValue = (propertyValue==null) ? RelationalConstants.PROCEDURE_DEFAULT.CATEGORY : propertyValue;
		} else if (propertyKey.equals(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.AGGREGATE)) {
			propertyValue = this.delegate.getOptionValue(propertyKey);
			propertyValue = (propertyValue==null) ? RelationalConstants.PROCEDURE_DEFAULT.AGGREGATE : propertyValue;
		} else if (propertyKey.equals(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.ALLOWS_DISTINCT)) {
			propertyValue = this.delegate.getOptionValue(propertyKey);
			propertyValue = (propertyValue==null) ? RelationalConstants.PROCEDURE_DEFAULT.ALLOWS_DISTINCT : propertyValue;
		} else if (propertyKey.equals(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.ALLOWS_ORDERBY)) {
			propertyValue = this.delegate.getOptionValue(propertyKey);
			propertyValue = (propertyValue==null) ? RelationalConstants.PROCEDURE_DEFAULT.ALLOWS_ORDERBY : propertyValue;
		} else if (propertyKey.equals(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.ANALYTIC)) {
			propertyValue = this.delegate.getOptionValue(propertyKey);
			propertyValue = (propertyValue==null) ? RelationalConstants.PROCEDURE_DEFAULT.ANALYTIC : propertyValue;
		} else if (propertyKey.equals(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.DECOMPOSABLE)) {
			propertyValue = this.delegate.getOptionValue(propertyKey);
			propertyValue = (propertyValue==null) ? RelationalConstants.PROCEDURE_DEFAULT.DECOMPOSABLE : propertyValue;
		} else if (propertyKey.equals(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.NON_PREPARED)) {
			propertyValue = this.delegate.getOptionValue(propertyKey);
			propertyValue = (propertyValue==null) ? RelationalConstants.PROCEDURE_DEFAULT.NON_PREPARED : propertyValue;
		} else if (propertyKey.equals(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.NULL_ON_NULL)) {
			propertyValue = this.delegate.getOptionValue(propertyKey);
			propertyValue = (propertyValue==null) ? RelationalConstants.PROCEDURE_DEFAULT.NULL_ON_NULL : propertyValue;
		} else if (propertyKey.equals(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.USES_DISTINCT_ROWS)) {
			propertyValue = this.delegate.getOptionValue(propertyKey);
			propertyValue = (propertyValue==null) ? RelationalConstants.PROCEDURE_DEFAULT.USES_DISTINCT_ROWS : propertyValue;
		} else if (propertyKey.equals(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.VARARGS)) {
			propertyValue = this.delegate.getOptionValue(propertyKey);
			propertyValue = (propertyValue==null) ? RelationalConstants.PROCEDURE_DEFAULT.VARARGS : propertyValue;
		} else if (propertyKey.equals(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.DETERMINISM)) {
			propertyValue = this.delegate.getOptionValue(propertyKey);
			propertyValue = (propertyValue==null) ? RelationalConstants.PROCEDURE_DEFAULT.DETERMINISM : propertyValue;
		} else if (propertyKey.equals(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.NATIVE_QUERY)) {
			propertyValue = this.delegate.getOptionValue(propertyKey);
			propertyValue = (propertyValue==null) ? RelationalConstants.PROCEDURE_DEFAULT.NATIVE_QUERY : propertyValue;
		} else if (propertyKey.equals(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.JAVA_CLASS)) {
			propertyValue = this.delegate.getOptionValue(propertyKey);
			propertyValue = (propertyValue==null) ? RelationalConstants.PROCEDURE_DEFAULT.JAVA_CLASS : propertyValue;
		} else if (propertyKey.equals(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.JAVA_METHOD)) {
			propertyValue = this.delegate.getOptionValue(propertyKey);
			propertyValue = (propertyValue==null) ? RelationalConstants.PROCEDURE_DEFAULT.JAVA_METHOD : propertyValue;
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
		if(propertyKey.equals(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.UPDATECOUNT)) {
			if(propertyValue==null || ((String)propertyValue).equals(RelationalConstants.PROCEDURE_DEFAULT.UPDATECOUNT)) {
				wasSet = this.delegate.unsetOptionValue(propertyKey);
			} else {
				wasSet = this.delegate.setOptionValue(propertyKey, propertyValue);
			}
		} else if (propertyKey.equals(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.CATEGORY)) {
			if(propertyValue==null || ((String)propertyValue).equals(RelationalConstants.PROCEDURE_DEFAULT.CATEGORY)) {
				wasSet = this.delegate.unsetOptionValue(propertyKey);
			} else {
				wasSet = this.delegate.setOptionValue(propertyKey, propertyValue);
			}
		} else if (propertyKey.equals(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.AGGREGATE)) {
			if(propertyValue==null || ((Boolean)propertyValue)==RelationalConstants.PROCEDURE_DEFAULT.AGGREGATE) {
				wasSet = this.delegate.unsetOptionValue(propertyKey);
			} else {
				wasSet = this.delegate.setOptionValue(propertyKey, propertyValue);
			}
		} else if (propertyKey.equals(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.ALLOWS_DISTINCT)) {
			if(propertyValue==null || ((Boolean)propertyValue)==RelationalConstants.PROCEDURE_DEFAULT.ALLOWS_DISTINCT) {
				wasSet = this.delegate.unsetOptionValue(propertyKey);
			} else {
				wasSet = this.delegate.setOptionValue(propertyKey, propertyValue);
			}
		} else if (propertyKey.equals(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.ALLOWS_ORDERBY)) {
			if(propertyValue==null || ((Boolean)propertyValue)==RelationalConstants.PROCEDURE_DEFAULT.ALLOWS_ORDERBY) {
				wasSet = this.delegate.unsetOptionValue(propertyKey);
			} else {
				wasSet = this.delegate.setOptionValue(propertyKey, propertyValue);
			}
		} else if (propertyKey.equals(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.ANALYTIC)) {
			if(propertyValue==null || ((Boolean)propertyValue)==RelationalConstants.PROCEDURE_DEFAULT.ANALYTIC) {
				wasSet = this.delegate.unsetOptionValue(propertyKey);
			} else {
				wasSet = this.delegate.setOptionValue(propertyKey, propertyValue);
			}
		} else if (propertyKey.equals(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.DECOMPOSABLE)) {
			if(propertyValue==null || ((Boolean)propertyValue)==RelationalConstants.PROCEDURE_DEFAULT.DECOMPOSABLE) {
				wasSet = this.delegate.unsetOptionValue(propertyKey);
			} else {
				wasSet = this.delegate.setOptionValue(propertyKey, propertyValue);
			}
		} else if (propertyKey.equals(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.NON_PREPARED)) {
			if(propertyValue==null || ((Boolean)propertyValue)==RelationalConstants.PROCEDURE_DEFAULT.NON_PREPARED) {
				wasSet = this.delegate.unsetOptionValue(propertyKey);
			} else {
				wasSet = this.delegate.setOptionValue(propertyKey, propertyValue);
			}
		} else if (propertyKey.equals(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.NULL_ON_NULL)) {
			if(propertyValue==null || ((Boolean)propertyValue)==RelationalConstants.PROCEDURE_DEFAULT.NULL_ON_NULL) {
				wasSet = this.delegate.unsetOptionValue(propertyKey);
			} else {
				wasSet = this.delegate.setOptionValue(propertyKey, propertyValue);
			}
		} else if (propertyKey.equals(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.USES_DISTINCT_ROWS)) {
			if(propertyValue==null || ((Boolean)propertyValue)==RelationalConstants.PROCEDURE_DEFAULT.USES_DISTINCT_ROWS) {
				wasSet = this.delegate.unsetOptionValue(propertyKey);
			} else {
				wasSet = this.delegate.setOptionValue(propertyKey, propertyValue);
			}
		} else if (propertyKey.equals(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.VARARGS)) {
			if(propertyValue==null || ((Boolean)propertyValue)==RelationalConstants.PROCEDURE_DEFAULT.VARARGS) {
				wasSet = this.delegate.unsetOptionValue(propertyKey);
			} else {
				wasSet = this.delegate.setOptionValue(propertyKey, propertyValue);
			}
		} else if (propertyKey.equals(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.DETERMINISM)) {
			if(propertyValue==null || ((String)propertyValue).equals(RelationalConstants.PROCEDURE_DEFAULT.DETERMINISM)) {
				wasSet = this.delegate.unsetOptionValue(propertyKey);
			} else {
				wasSet = this.delegate.setOptionValue(propertyKey, propertyValue);
			}
		} else if (propertyKey.equals(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.NATIVE_QUERY)) {
			if(propertyValue==null || ((String)propertyValue).equals(RelationalConstants.PROCEDURE_DEFAULT.NATIVE_QUERY)) {
				wasSet = this.delegate.unsetOptionValue(propertyKey);
			} else {
				wasSet = this.delegate.setOptionValue(propertyKey, propertyValue);
			}
		} else if (propertyKey.equals(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.JAVA_CLASS)) {
			if(propertyValue==null || ((String)propertyValue).equals(RelationalConstants.PROCEDURE_DEFAULT.JAVA_CLASS)) {
				wasSet = this.delegate.unsetOptionValue(propertyKey);
			} else {
				wasSet = this.delegate.setOptionValue(propertyKey, propertyValue);
			}
		} else if (propertyKey.equals(RelationalConstants.PROCEDURE_DDL_OPTION_KEYS.JAVA_METHOD)) {
			if(propertyValue==null || ((String)propertyValue).equals(RelationalConstants.PROCEDURE_DEFAULT.JAVA_METHOD)) {
				wasSet = this.delegate.unsetOptionValue(propertyKey);
			} else {
				wasSet = this.delegate.setOptionValue(propertyKey, propertyValue);
			}
		}
		return wasSet;
	}
	
}
