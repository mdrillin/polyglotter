/*
 * JBoss, Home of Professional Open Source.
*
* See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
*
* See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
*/
package org.modeshape.modeler.ddl.relational;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modeshape.modeler.Model;
import org.modeshape.modeler.ModelObject;

/**
 * Implementation of the model delegate based on ModeShape Modeler
 */
public class MMModelDelegate implements IModelDelegate {
	Model model;
	
	/**
	 * Constructor
	 * @param model the Modeshape Modeler model
	 */
	public MMModelDelegate(Model model) {
		this.model = model;
	}
	
	@SuppressWarnings("javadoc")
	@Override
	public String getName() {
		String mName = null;
		try {
			mName = this.model.name();
		} catch (Exception ex) {
            //KLog.getLogger().error(ex.getLocalizedMessage(), ex);
		}
		return mName;
	}
	
	@SuppressWarnings("javadoc")
	@Override
	public boolean hasChildren() {
		boolean hasChildren = false;
		try {
			hasChildren = !getChildren().isEmpty();
		} catch (Exception ex) {
            //KLog.getLogger().error(ex.getLocalizedMessage(), ex);
		}
		return hasChildren;
	}	
	
	@SuppressWarnings("javadoc")
	@Override
	public List<RelationalObject> getChildren() {
		List<RelationalObject> roChildren = new ArrayList<RelationalObject>();
		ModelObject[] moChildren = null;
		try {
			// Model has one child (ddl:statements) that has children statements
			moChildren = this.model.children();
		} catch (Exception ex) {
            //KLog.getLogger().error(ex.getLocalizedMessage(), ex);
		}
		if(moChildren==null) return roChildren;
		
		try {
			if(moChildren.length==1 && moChildren[0].hasChildren()) {
				// the statement children for the model
				moChildren = moChildren[0].children();
			}
		} catch (Exception ex) {
			//KLog.getLogger().error(ex.getLocalizedMessage(), ex);
		}
		if(moChildren==null) return roChildren;
		
		for(ModelObject mo : moChildren) {
			RelationalConstants.Type moType = getRelationalType(mo);
			if(moType != null) {
				if(moType==RelationalConstants.Type.TABLE) {
					roChildren.add(new Table(new MMRelationalObjDelegate(mo)));
				} else if(moType==RelationalConstants.Type.PROCEDURE) {
					roChildren.add(new Procedure(new MMRelationalObjDelegate(mo)));
				}
			}
		}
		return roChildren;
	}
	
	@SuppressWarnings("javadoc")
	@Override
	public Map<String, String> getNamespaceMap() {
		Map<String,String> namespaceMap = new HashMap<String,String>();
		ModelObject[] moChildren = null;
		try {
			moChildren = this.model.children();
		} catch (Exception ex) {
            //KLog.getLogger().error(ex.getLocalizedMessage(), ex);
		}
		if(moChildren==null) return namespaceMap;
		
		try {
			if(moChildren.length==1 && moChildren[0].hasChildren()) {
				moChildren = moChildren[0].children();
			}
		} catch (Exception ex) {
			//KLog.getLogger().error(ex.getLocalizedMessage(), ex);
		}
		if(moChildren==null) return namespaceMap;
		
		for(ModelObject mo : moChildren) {
			if(isNamespaceOption(mo)) {
				String nsPrefix = null;
				String nsUri = null;
				try {
					nsPrefix = mo.name();
					nsUri = mo.stringValue(TeiidDdlLexicon.OptionNamespace.URI);
					namespaceMap.put(nsPrefix, nsUri);
				} catch (Exception ex) {
					//KLog.getLogger().error(ex.getLocalizedMessage(), ex);
				}
			}
		}
		return namespaceMap;
	}
	
	
	private boolean isNamespaceOption(ModelObject modelObject) {
		boolean isNamespace = false;
		String[] mixinTypes = null;
		try {
			mixinTypes = modelObject.mixinTypes();
		} catch (Exception ex) {
            //KLog.getLogger().error(ex.getLocalizedMessage(), ex);
		}
		if(mixinTypes==null) return false;
		for( String type : mixinTypes) {
			if(type.equals(TeiidDdlLexicon.OptionNamespace.STATEMENT)) {
				isNamespace = true;
			}
		}
		return isNamespace;
	}
	
	private RelationalConstants.Type getRelationalType(ModelObject modelObject) {
		RelationalConstants.Type relationalType = null;
		String[] mixinTypes = null;
		Object constraintType = null;
		try {
			mixinTypes = modelObject.mixinTypes();
			constraintType = modelObject.value(TeiidDdlLexicon.Constraint.TYPE);
		} catch (Exception ex) {
            //KLog.getLogger().error(ex.getLocalizedMessage(), ex);
		}
		
		if(mixinTypes==null) return null;
		String cType = (constraintType==null) ? null : constraintType.toString();
		for( String type : mixinTypes) {
			relationalType = TeiidLexiconMapper.getRelationalType(type,cType);
			if(relationalType!=null) {
				break;
			}
		}
		return relationalType;
	}

}
