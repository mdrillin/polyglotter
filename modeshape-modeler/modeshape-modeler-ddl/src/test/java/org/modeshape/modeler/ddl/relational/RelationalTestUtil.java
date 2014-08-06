/*************************************************************************************
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
 ************************************************************************************/
package org.modeshape.modeler.ddl.relational;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class RelationalTestUtil {
	
	/**
	 * Get the model table with the provided name
	 * @param model the model
	 * @param tableName the table name
	 * @return the table
	 */
	public static Table getChildTable(RelationalModel model, String tableName) {
    	Table table = null;
    	List<RelationalObject> children = model.getChildren();
    	for(RelationalObject childObj : children) {
    		if(childObj.getType()==RelationalConstants.Type.TABLE && childObj.getName().equals(tableName)) {
    			table = (Table)childObj;
    			break;
    		}
    	}
    	return table;
	}

	/**
	 * Get the model procedure with the provided name
	 * @param model the model
	 * @param procName the procedure name
	 * @return the procedure
	 */
	public static Procedure getChildProcedure(RelationalModel model, String procName) {
		Procedure proc = null;
    	List<RelationalObject> children = model.getChildren();
    	for(RelationalObject childObj : children) {
    		if(childObj.getType()==RelationalConstants.Type.PROCEDURE && childObj.getName().equals(procName)) {
    			proc = (Procedure)childObj;
    			break;
    		}
    	}
    	return proc;
	}

	/**
	 * Get the child column from the provided object
	 * @param relObj the relational object
	 * @param colName the name of the column
	 * @return the column
	 */
	public static Column getChildColumn(RelationalObject relObj, String colName) {
		Column column = null;
    	List<RelationalObject> children = relObj.getChildren();
    	for(RelationalObject childObj : children) {
    		if(childObj.getType()==RelationalConstants.Type.COLUMN && childObj.getName().equals(colName)) {
    			column = (Column)childObj;
    			break;
    		}
    	}
    	return column;
	}
	
    /**
     * Determine if the parent has child objects of supplied type that match the list of supplied names
     * @param model the parent
     * @param objectNames the array of names
     * @param objType the type of object
     * @return 'true' if objects match the supplied names, 'false' if not
     */
    public static boolean childrenMatch(RelationalModel model, String[] objectNames, RelationalConstants.Type objType) {
    	List<RelationalObject> rObjs = new ArrayList<RelationalObject>();
    	Collection<RelationalObject> children = model.getChildren();
    	for(RelationalObject relObj : children) {
    		if(relObj.getType()==objType) {
    			rObjs.add(relObj);
    		}
    	}
    	
    	// Must have equal numbers
    	if(objectNames.length!=rObjs.size()) {
    		return false;
    	}
    	
    	for(RelationalObject rObj : rObjs) {
    		String objName = rObj.getName();
    		boolean found = false;
    		for(String roName : objectNames) {
    			if(roName.equalsIgnoreCase(objName)) {
    				found = true;
    				break;
    			}
    		}
    		if(!found) {
    			return false;
    		}
    	}
    	
    	return true;
    }
    
    /**
     * Determine if the parent has child objects of supplied type that match the list of supplied names
     * @param parent the parent
     * @param objectNames the array of names
     * @param objType the type of object
     * @return 'true' if objects match the supplied names, 'false' if not
     */
    public static boolean childrenMatch(RelationalObject parent, String[] objectNames, RelationalConstants.Type objType) {
    	List<RelationalObject> rObjs = new ArrayList<RelationalObject>();
    	Collection<RelationalObject> children = parent.getChildren();
    	for(RelationalObject relObj : children) {
    		RelationalConstants.Type roType = relObj.getType();
    		if(roType==objType) {
    			rObjs.add(relObj);
    		}
    	}
    	
    	// Must have equal numbers
    	if(objectNames.length!=rObjs.size()) {
    		return false;
    	}
    	
    	for(RelationalObject rObj : rObjs) {
    		String objName = rObj.getName();
    		boolean found = false;
    		for(String roName : objectNames) {
    			if(roName.equalsIgnoreCase(objName)) {
    				found = true;
    				break;
    			}
    		}
    		if(!found) {
    			return false;
    		}
    	}
    	
    	return true;
    }
    
    /**
     * Determine if the object properties match the expected properties
     * @param relObj the relational object
     * @param expectedProps the expected properties
     * @return 'true' if properties match, 'false' if not
     */
    public static String compareProperties(RelationalObject relObj, Map<String,Object> expectedProps) {
    	String result = "OK";  //$NON-NLS-1$
    	Map<String,Object> actualProps = relObj.getProperties();
    	if(actualProps.size()>expectedProps.size()) {
    		for(String actualKey : actualProps.keySet()) {
    			if(!expectedProps.keySet().contains(actualKey)) {
    				result = "Actual Props has property ["+actualKey+"], but expected props does not";
    				break;
    			}
    		}
    		return result;
    	} else if(expectedProps.size()>actualProps.size()) {
    		for(String expectedKey : expectedProps.keySet()) {
    			if(!actualProps.keySet().contains(expectedKey)) {
    				result = "Actual Props has property ["+expectedKey+"], but expected props does not";
    				break;
    			}
    		}
    		return result;
    	}
    	for(String expectedName : expectedProps.keySet()) {
    		if(!actualProps.keySet().contains(expectedName)) {
    			result = "Object properties do not contain property '"+expectedName+"'";  //$NON-NLS-1$ //$NON-NLS-2$
    			break;
    		} 
    		Object pVal = expectedProps.get(expectedName);
    		String expectedValue = (pVal != null) ? pVal.toString() : "null";
    		Object aVal = actualProps.get(expectedName);
    		String actualValue = (aVal != null) ? aVal.toString() : "null";
    		if(   (expectedValue==null && actualValue!=null)
    	       || (actualValue==null && expectedValue!=null)
     		   || (actualValue!=null && !actualValue.equalsIgnoreCase(expectedValue))) {
    			result = "'"+expectedName+"' Actual value ["+actualValue+"] does not match Expected value ["+expectedValue+"]";  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    			break;
    		}
    	}
    	return result;
    }

}
