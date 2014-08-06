package org.modeshape.modeler.ddl.relational;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * RelationalModel
 * - the relational model object
 */
public class RelationalModel {
	
	private IModelDelegate delegate;
	
	/**
	 * Constructor
	 * @param delegate the delegate
	 */
	public RelationalModel(IModelDelegate delegate) {
		this.delegate = delegate;
	}
	
    /**
     * Get the model name
     * @return name
     */
	public String getName() {
		return this.delegate.getName();
	}
	
    /**
     * Determine if model has children
     * @return 'true' if has children, 'false' if not
     */
	public boolean hasChildren() {
		return this.delegate.hasChildren();
	}	
	
    /**
     * Get the model children
     * @return the children
     */
	public List<RelationalObject> getChildren() {
		return this.delegate.getChildren();
	}
	
    /**
     * Get mapping of namespaces - if any
     * @return the namespace key to uri map
     */
	public Map<String,String> getNamespaceMap() {
		return this.delegate.getNamespaceMap();
	}
	
    /**
     * Get the child Tables
     * @return Table
     */
    public List<Table> getTables() {
    	return getChildren(Table.class);
    }
    
    /**
     * Get the Table with the specified name (if it exists)
     * @param tableName the Table name
     * @return Table
     */
    public Table getTable(String tableName) {
    	Table result = null;
    	List<Table> kids = getTables();
    	for(Table kid : kids) {
    		if(kid.getName().equalsIgnoreCase(tableName)) {
    			result = kid;
    			break;
    		}
    	}
    	return result;
    }
    
    /**
     * Get the child getProcedures
     * @return Procedure list
     */
    public List<Procedure> getProcedures() {
    	return getChildren(Procedure.class);
    }
    
    /**
     * Get the getProcedure with the specified name (if it exists)
     * @param procName the Procedure name
     * @return Procedure
     */
    public Procedure getProcedure(String procName) {
    	Procedure result = null;
    	List<Procedure> kids = getProcedures();
    	for(Procedure kid : kids) {
    		if(kid.getName().equalsIgnoreCase(procName)) {
    			result = kid;
    			break;
    		}
    	}
    	return result;
    }
    
    /**
     * Get children of provided type
     * @param type the type
     * @return the children of given type
     */
    @SuppressWarnings("unchecked")
	private <T> List<T> getChildren(Class<T> type) {
    	List<T> result = new ArrayList<T>();
    	for(Object e : getChildren()) {
    		if(type.isAssignableFrom(e.getClass())) {
    			result.add((T) e);
    		}
    	}
    	return result;
    }
    
	
}
