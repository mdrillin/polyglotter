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

import java.util.ArrayList;
import java.util.List;

/**
 * AccessPatter
 * The relational access pattern class
 */
public class AccessPattern extends RelationalObject {
    
	AccessPattern(IObjectDelegate modelObject) {
		super(modelObject);
	}
    
    /**
     * @return columns
     */
    public List<Column> getColumns() {
    	List<Column> columns = new ArrayList<Column>();
    	for(RelationalObject relObj : getChildren()) {
    		if(relObj.getType()==RelationalConstants.Type.COLUMN) {
    			columns.add((Column)relObj);
    		}
    	}
        return columns;
    }
    
//    /**
//     * Add a column
//     * @param column the relational column to add
//     */
//    public void addColumn( Column column ) {
//        this.columns.add(column);
//    }
//    
//    /**
//     * Remove a column
//     * @param column the column to remove
//     * @return 'true' if the move was successful
//     */
//    public boolean removeColumn(Column column) {
//    	if( this.columns.remove(column) ) {
//    		return true;
//    	}
//    	return false;
//    }
//    
//    /**
//     * Get the table
//     * @return the relational table
//     */
//    public Table getTable() {
//    	if( getParent() != null ) {
//    		return (Table)getParent();
//    	}
//    	
//    	return null;
//    }
//    
//    /**
//     * Set properties
//     * @param props the properties
//     */
//    @Override
//	public void setProperties(Properties props) {
//    	// Set common properties
//    	super.setProperties(props);
//    	
//        handleInfoChanged();
//    }
//    
//	@Override
//	public String toString() {
//		StringBuilder sb = new StringBuilder();
//		sb.append(this.getClass().getName());
//		sb.append(" : name = ").append(getName()); //$NON-NLS-1$
//		if( !getColumns().isEmpty() ) {
//			sb.append("\n\t").append(getColumns().size()).append(" columns"); //$NON-NLS-1$  //$NON-NLS-2$
//			for( Column col : getColumns() ) {
//				sb.append("\n\tcol = ").append(col); //$NON-NLS-1$
//			}
//		}
//		return sb.toString();
//	}
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
//        final AccessPattern other = (AccessPattern)object;
//
//        // Columns
//        Collection<Column> thisColumns = getColumns();
//        Collection<Column> thatColumns = other.getColumns();
//
//        if (thisColumns.size() != thatColumns.size()) {
//            return false;
//        }
//        
//        if (!thisColumns.isEmpty() && !thisColumns.containsAll(thatColumns)) {
//            return false;
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
//        List<Column> cols = getColumns();
//        for(Column col: cols) {
//            result = HashCodeUtil.hashCode(result, col);
//        }
//        
//        return result;
//    }    

}
