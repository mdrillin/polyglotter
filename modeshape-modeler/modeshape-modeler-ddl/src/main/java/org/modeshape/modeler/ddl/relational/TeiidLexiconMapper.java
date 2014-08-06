package org.modeshape.modeler.ddl.relational;

import org.modeshape.modeler.ddl.relational.RelationalConstants;

/**
 * TeiidLexiconMapper
 * gets corresponding relational type for supplied lexicon
 */
public class TeiidLexiconMapper {
	
	/**
	 * Maps the DDL lexicon to the matching Relational Object Type
	 * @param ddlType the ddl type
	 * @param constraintType for constraints, need this to narrow type
	 * @return the Relational Type
	 */
	public static RelationalConstants.Type getRelationalType(String ddlType, String constraintType) {
		RelationalConstants.Type relType = null;
		if(  ddlType.equals(TeiidDdlLexicon.CreateTable.TABLE_STATEMENT) || ddlType.equals(TeiidDdlLexicon.CreateTable.VIEW_STATEMENT) ) {
			relType = RelationalConstants.Type.TABLE;
		} else if( ddlType.equals(TeiidDdlLexicon.CreateTable.TABLE_ELEMENT) || ddlType.equals(StandardDdlLexicon.TYPE_COLUMN_DEFINITION)) {
			relType = RelationalConstants.Type.COLUMN;
		} else if( ddlType.equals(TeiidDdlLexicon.CreateProcedure.PROCEDURE_STATEMENT) || ddlType.equals(TeiidDdlLexicon.CreateProcedure.FUNCTION_STATEMENT)) {
			relType = RelationalConstants.Type.PROCEDURE;
		} else if( ddlType.equals(TeiidDdlLexicon.Constraint.TABLE_ELEMENT) ) {
			if(constraintType.equals("PRIMARY KEY")) {
				relType = RelationalConstants.Type.PRIMARY_KEY;
			} else if(constraintType.equals("FOREIGN KEY")) {
				relType = RelationalConstants.Type.FOREIGN_KEY;
			} else if(constraintType.equals("UNIQUE")) {
				relType = RelationalConstants.Type.UNIQUE_CONSTRAINT;
			} else if(constraintType.equals("ACCESSPATTERN")) {
				relType = RelationalConstants.Type.ACCESS_PATTERN;
			} else if(constraintType.equals("INDEX")) {
				relType = RelationalConstants.Type.INDEX;
			} else {
				relType = RelationalConstants.Type.PRIMARY_KEY;
			}
		} else if( ddlType.equals(TeiidDdlLexicon.Constraint.FOREIGN_KEY_CONSTRAINT) ) {
			relType = RelationalConstants.Type.FOREIGN_KEY;
		} else if( ddlType.equals(TeiidDdlLexicon.Constraint.INDEX_CONSTRAINT) ) {
			relType = RelationalConstants.Type.INDEX;
		} else if( ddlType.equals(TeiidDdlLexicon.CreateTable.TABLE_ELEMENT) ) {
			relType = RelationalConstants.Type.COLUMN;
		} else if( ddlType.equals(TeiidDdlLexicon.CreateProcedure.PARAMETER) ) {
			relType = RelationalConstants.Type.PARAMETER;
		} else if( ddlType.equals(TeiidDdlLexicon.CreateProcedure.RESULT_COLUMNS) ) {
			relType = RelationalConstants.Type.RESULT_SET;
		} else if( ddlType.equals(TeiidDdlLexicon.CreateProcedure.RESULT_COLUMN) ) {
			relType = RelationalConstants.Type.COLUMN;
		} else if( ddlType.equals(TeiidDdlLexicon.CreateProcedure.RESULT_DATA_TYPE) ) {
			relType = RelationalConstants.Type.RESULT_SET;
		}
		return relType;
	}
	
}
