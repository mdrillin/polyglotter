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

import org.modeshape.modeler.ModelObject;
import org.modeshape.modeler.ModelerException;

/**
 * Implementation of the RelationalObject delegate based on ModeShape Modeler
 */
public class MMRelationalObjDelegate implements IObjectDelegate {
	ModelObject modelObj;
	
	/**
	 * JSR-283 character encodings
	 */
	char asterisk = '\uF02A';
	char fwdSlash = '\uF02F';
	char colon = '\uF03A';
	char leftBracket = '\uF05B';
	char rightBracket = '\uF05D';
	char pipe = '\uF07C';
		
	MMRelationalObjDelegate(ModelObject modelObj) {
		this.modelObj = modelObj;
	}
	
	@SuppressWarnings("javadoc")
	@Override
	public String getName() {
		String name = null;
		try {
			name = this.modelObj.name();
		} catch (Exception ex) {
			//KLog.getLogger().error(ex.getLocalizedMessage(), ex);
		}
		return name;
	}
	
	@SuppressWarnings("javadoc")
	@Override
	public RelationalConstants.Type getType( ) {
		RelationalConstants.Type relationalType = null;
		String[] mixinTypes = null;
		Object constraintType = null;
		try {
			mixinTypes = modelObj.mixinTypes();
			constraintType = modelObj.value(TeiidDdlLexicon.Constraint.TYPE);
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
	
	@SuppressWarnings("javadoc")
	@Override
	public boolean hasChildren() {
		return getChildren().isEmpty() ? false : true;
	}	
	
	@SuppressWarnings("javadoc")
	@Override
	public List<RelationalObject> getChildren() {
		List<RelationalObject> roChildren = new ArrayList<RelationalObject>();

		ModelObject[] moChildren = null;
		try {
			moChildren = this.modelObj.children();
		} catch (Exception ex) {
            //KLog.getLogger().error(ex.getLocalizedMessage(), ex);
		}
		if(moChildren==null) return roChildren;
		
		for(ModelObject mo : moChildren) {
			IObjectDelegate roDelegate = new MMRelationalObjDelegate(mo);
			RelationalConstants.Type relationalType = roDelegate.getType();
			if(relationalType==null) continue;
			switch (relationalType) {
			case TABLE:
				roChildren.add(new Table(roDelegate));
				break;
			case ACCESS_PATTERN:
				roChildren.add(new AccessPattern(roDelegate));
				break;
			case CATALOG:
				break;
			case COLUMN:
				roChildren.add(new Column(roDelegate));
				break;
			case FOREIGN_KEY:
				roChildren.add(new ForeignKey(roDelegate));
				break;
			case INDEX:
				roChildren.add(new Index(roDelegate));
				break;
			case MODEL:
				break;
			case PARAMETER:
				roChildren.add(new Parameter(roDelegate));
				break;
			case PRIMARY_KEY:
				roChildren.add(new PrimaryKey(roDelegate));
				break;
			case PROCEDURE:
				roChildren.add(new Procedure(roDelegate));
				break;
			case RESULT_SET:
				roChildren.add(new ProcedureResultSet(roDelegate));
				break;
			case SCHEMA:
				roChildren.add(new Schema(roDelegate));
				break;
			case UNIQUE_CONSTRAINT:
				roChildren.add(new UniqueConstraint(roDelegate));
				break;
			case VIEW:
				roChildren.add(new View(roDelegate));
				break;
			default:
				break;
			}
		}
		return roChildren;
	}
	
	/**
	 * Get the ModelObjects that are statement options
	 * @return the list of statement option objects
	 */
	private List<ModelObject> getStatementOptions() {
		List<ModelObject> statementOptions = new ArrayList<ModelObject>();
		
		ModelObject[] moChildren = null;
		try {
			moChildren = this.modelObj.children();
		} catch (Exception ex) {
            //KLog.getLogger().error(ex.getLocalizedMessage(), ex);
		}
		if(moChildren==null) return statementOptions;
		
		for(ModelObject mo : moChildren) {
			if(hasMixinType(mo,StandardDdlLexicon.TYPE_STATEMENT_OPTION)) {
				statementOptions.add(mo);
			}
		}
		return statementOptions;
	}
	
	/**
	 * Get the statementOption with the provided name
	 * @param name the name
	 * @return the StatementOption, null if not found
	 */
	private ModelObject getStatementOption(String name) {
		ModelObject result = null;
		List<ModelObject> statementOptions = getStatementOptions();
		
		for(ModelObject mObj : statementOptions) {
			String mObjName = null;
			try {
				mObjName = mObj.name();
			} catch (ModelerException e) {
	            //KLog.getLogger().error(ex.getLocalizedMessage(), ex);
			}
			if(mObjName!=null && mObjName.equals(name)) {
				result = mObj;
				break;
			}
		}		
		return result;
	}
	
	@SuppressWarnings("javadoc")
	@Override
	public Object getOptionValue(String name) {
		Object optionValue = null;
		try {
			List<ModelObject> statementOptions = getStatementOptions();
			for(ModelObject modelObj : statementOptions) {
				if(modelObj.name().equals(name)) {
					optionValue = modelObj.value(StandardDdlLexicon.VALUE);
				}
			}
		} catch (Exception ex) {
            //KLog.getLogger().error(ex.getLocalizedMessage(), ex);
		}
		return optionValue;
	}
	
	@SuppressWarnings("javadoc")
	@Override
	public boolean setOptionValue(String name, Object value, Object... additionalValues ) {
		boolean wasSet = false;
		try {
			// Get the statement options with supplied name
			ModelObject statementOption = getStatementOption(name);
			// If no statement option, create one
			if(statementOption==null) {
				this.modelObj.addChild(name);
				setChildMixinType(name,StandardDdlLexicon.TYPE_STATEMENT_OPTION);
				// Get created statement option
				statementOption = getStatementOption(name);
			}
			// Set statement option value
			if(statementOption!=null) {
				statementOption.setProperty(StandardDdlLexicon.VALUE, value, additionalValues);
				wasSet=true;
			}
		} catch (Exception ex) {
			System.out.println();
            //KLog.getLogger().error(ex.getLocalizedMessage(), ex);
		}
		return wasSet;
	}
	
	@SuppressWarnings("javadoc")
	@Override
	public boolean unsetOptionValue(String name) {
		boolean wasSet = false;
		try {
			this.modelObj.removeChild(name);
			wasSet = true;
		} catch (Exception ex) {
			System.out.println();
			//KLog.getLogger().error(ex.getLocalizedMessage(), ex);
		}
		return wasSet;
	}
	
	/**
	 * Set the mixinType of the specified child
	 * @param name the child name
	 * @param mixinType the mixinType
	 * @throws ModelerException the exception
	 */
	private void setChildMixinType(String name, String mixinType) throws ModelerException {
		ModelObject[] moChildren = null;
		try {
			moChildren = this.modelObj.children();
		} catch (Exception ex) {
            //KLog.getLogger().error(ex.getLocalizedMessage(), ex);
		}
		if(moChildren!=null) {
			for(ModelObject mo : moChildren) {
				if(mo.name().equals(name)) {
					mo.setMixinTypes(mixinType);
				}
			}
		}
	}
	
	@SuppressWarnings("javadoc")
	@Override
	public Object getPropertyValue(String name) {
		Object propValue = null;
		
		try {
			propValue = modelObj.value(name);
		} catch (Exception ex) {
            //KLog.getLogger().error(ex.getLocalizedMessage(), ex);
		}
		return propValue;
	}
	
	@SuppressWarnings("javadoc")
	@Override
	public Object[] getPropertyValues(String name) {
		Object[] propValues = null;
		
		try {
			propValues = modelObj.values(name);
		} catch (Exception ex) {
            //KLog.getLogger().error(ex.getLocalizedMessage(), ex);
		}
		return propValues;
	}
	
	@SuppressWarnings("javadoc")
	@Override
	public boolean setPropertyValue(String name, Object value, Object... additionalValues) {
		boolean wasSet = false;
		try {
			if(modelObj.hasProperty(name)) {
				modelObj.setProperty(name, value, additionalValues);
				wasSet = true;
			}
		} catch (Exception ex) {
            //KLog.getLogger().error(ex.getLocalizedMessage(), ex);
		}
		return wasSet;
	}

	/* (non-Javadoc)
	 * @see org.komodo.relational.model2.IObjectDelegate#isExtensionProperty(java.lang.String)
	 */
	@SuppressWarnings("javadoc")
	@Override
	public boolean isExtensionProperty(String propName) {
		boolean isExtension = false;
		if(isPrefixNamespaced(propName) || isUriNamespaced(propName)) {
			isExtension = true;
		}
		return isExtension;
	}

	@SuppressWarnings("javadoc")
	@Override
	public Map<String, String> getExtensionProperties() {
		Map<String,String> extensionProps = new HashMap<String,String>();
		try {
			List<ModelObject> statementOptions = getStatementOptions();
			for(ModelObject modelObj : statementOptions) {
				// Get name, replacing any jsr-283 encodings
				String optionName = replaceJsr283Chars(modelObj.name());
				if(isPrefixNamespaced(optionName) || isUriNamespaced(optionName)) {
					extensionProps.put(optionName, (String)modelObj.value(StandardDdlLexicon.VALUE));
				}
			}
		} catch (Exception ex) {
            //KLog.getLogger().error(ex.getLocalizedMessage(), ex);
		}
		return extensionProps;
	}
	
	/*
	 * Replace any jsr283 chars in the supplied string with the corresponding standard character
	 */
	private String replaceJsr283Chars(String inStr) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < inStr.length(); i++){
		    char c = inStr.charAt(i); 
		    if(c==colon) {
		    	sb.append(':');
		    } else if(c==asterisk){
		    	sb.append('*');
		    } else if(c==fwdSlash){
		    	sb.append('/');
		    } else if(c==leftBracket){
		    	sb.append('[');
		    } else if(c==rightBracket){
		    	sb.append(']');
		    } else if(c==pipe){
		    	sb.append('|');
		    } else {
		    	sb.append(c);
		    }
		}
		return sb.toString();
	}
	
	private boolean hasMixinType(ModelObject modelObject, String type) {
		boolean hasMixin = false;
		String[] mixinTypes = null;
		try {
			mixinTypes = modelObject.mixinTypes();
		} catch (Exception ex) {
            //KLog.getLogger().error(ex.getLocalizedMessage(), ex);
		}
		if(mixinTypes==null) return hasMixin;
		
		for( String mType : mixinTypes) {
			if(mType.equals(type)) {
				hasMixin = true;
				break;
			}
		}
		return hasMixin;
	}
	
    /**
	 * Determine if the property name has a leading namespace prefix
	 * @param propName the extension property name, including namespace
	 * @return 'true' if a namespace is present, 'false' if not.
	 */
	private boolean isPrefixNamespaced(String propName) {    
		boolean isPrefixNamespaced = false;
		if(propName!=null && !hasOpenCloseBraces(propName) && propName.indexOf(':') != -1) {
			isPrefixNamespaced = true;
		}
		return isPrefixNamespaced;
	}
	
    /**
	 * Determine if the property name has a leading namespace uri
	 * @param propName the extension property name, including namespace uri
	 * @return 'true' if a namespace uri is present, 'false' if not.
	 */
	private boolean isUriNamespaced(String propName) {
		boolean isUriNamespaced = false;
		if(propName!=null && hasOpenCloseBraces(propName)) {
			isUriNamespaced = true;
		}
		return isUriNamespaced;
	}
	
	/**
	 * Determine if the supplied property name has open and closed braces
	 * @param propName the extension property name
	 * @return 'true' if both open and closed braces are found
	 */
	private boolean hasOpenCloseBraces(String propName) {
		boolean hasBoth = false;
		if( propName!=null && propName.indexOf('{')!=-1 && propName.indexOf('}')!=-1 ) {
			hasBoth = true;
		}
		return hasBoth;
	}

}
