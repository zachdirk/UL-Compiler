grammar ulGrammar;
@header
{
	import AST.*;
	import Type.*;
}
program returns [Program p]
@init
{
	p = new Program();
}
		: (f=function {p.addElement(f);})+ EOF
		;

function returns [Function f]
		: fd=functionDecl fb=functionBody {f = new Function(fd,fb);}
		;

functionDecl returns [FunctionDeclaration fd]
		: t=compoundType i=id '(' fpl=formalParams ')' {fd = new FunctionDeclaration(t, i, fpl);}
		;

formalParams returns [FormalParameterList fpl]
@init {
	fpl = new FormalParameterList();
}
		: f1=formalParameter {fpl.addElement(f1);} (mf=moreFormals {fpl.addElement(mf);})* 
		| 
		;

moreFormals returns [FormalParameter fp]
		: ',' f=formalParameter {fp = f;}
		;

formalParameter returns [FormalParameter fp]
		: t=compoundType i=id {fp = new FormalParameter(t, i);}
		;

functionBody returns [FunctionBody fb]	
@init {
	fb = new FunctionBody();
}
		: '{' (vd=varDecl{fb.addVarDecl(vd);})* (s=statement{fb.addStatement(s);})* '}'
		;



compoundType returns [Type t]
		: t2=type {t = t2;}
		| t2=arrayType {t = t2;}
		;

type returns [Type t]
		: t2=intType {t = t2;}
		| t2=floatType {t = t2;}
		| t2=charType {t = t2;}
		| t2=stringType {t = t2;}
		| t2=booleanType {t = t2;}
		| t2=voidType {t = t2;}
		;

intType returns [Type t]
		: INTTYPE {t = new IntegerType();}
		;

floatType returns [Type t]
		: FLOATTYPE {t = new FloatType();}
		;

charType returns [Type t]
		: CHARTYPE {t = new CharType();}
		;

booleanType returns [Type t]
		: BOOLEANTYPE {t = new BooleanType();}
		;

stringType returns [Type t]
		: STRINGTYPE {t = new StringType();}
		;

voidType returns [Type t]
		: VOIDTYPE {t = new VoidType();}
		;

arrayType returns [Type t]
		: t2=type '[' INTCONSTANT ']' {t = new ArrayType(t2);}
		;

statement returns [Statement s] options {backtrack=true;} 
		: ifStmt
		| whileStmt
		| printStmt
		| printlnStmt
		| returnStmt
		| assignStmt
		| arrAssignStmt
		| exprStmt
		| ';'
		; 

ifStmt options {backtrack=true;}
		: IF '(' expr ')' block ELSE block
		| IF '(' expr ')' block
		;

whileStmt	: WHILE '(' expr ')' block
		;

printStmt	: PRINT expr ';'
		;


printlnStmt	: PRINTLN expr ';'
		;

returnStmt	: RETURN expr? ';'
		;

assignStmt	: id '=' expr ';'
		;
arrAssignStmt	: id '[' expr ']' '=' expr ';'
		;

exprStmt	: expr ';'
		;

varDecl	returns [VariableDeclaration vd] 
		: t=compoundType i=id ';' {vd = new VariableDeclaration(t, i);}
		;

block		: '{' statement* '}'
		;

expr		: ltExpr ('==' ltExpr)*
		;

ltExpr		: asExpr ('<' asExpr)*
		;


asExpr		: multExpr (('+'|'-') multExpr)*
		;

multExpr	: atom ('*' atom)*
		; 

atom 		: id
		| arrayReference 
		| functionCall
		| literal
		| parenExpression
		;	

arrayReference	: id '[' expr ']'
		;

functionCall	: id '(' exprList ')'
		;

parenExpression	: '(' expr ')'
		;

exprList 	: expr exprMore*
		|
		;

exprMore	: ',' expr
		;

literal		: STRINGCONSTANT
		| INTCONSTANT
		| FLOATCONSTANT
		| CHARCONSTANT
		| TRUE
		| FALSE
		;

id returns [Identifier i]
		: i2=ID {i = new Identifier($i2.text);}
		;

STRINGCONSTANT	: '\"' ('a'..'z'|'A'..'Z'|'0'..'9'|' ')* '\"'
		;

INTCONSTANT 	: ('0'..'9')+
		;

FLOATCONSTANT	: ('0'..'9')+'.'('0'..'9')+
		;

CHARCONSTANT	: '\'' ('a'..'z'|'A'..'Z'|'0'..'9') '\''
		;

TRUE		: 'true'
		;

FALSE		: 'false'
		;

IF 		: 'if'
		;

ELSE		: 'else'
		;

WHILE		: 'while'
		;

PRINT 		: 'print'
		;

PRINTLN		: 'println'
		;

RETURN		: 'return'
		;

INTTYPE		: 'int'
		;

FLOATTYPE	: 'float'
		;

CHARTYPE	: 'char'
		;

STRINGTYPE	: 'string'
		;

BOOLEANTYPE	: 'boolean'
		;

VOIDTYPE	: 'void'
		;


ID 		: ('a'..'z'|'A'..'Z'|'_')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
		;

WS		: ( '\t' | ' ' | ('\r' | '\n') )+ { $channel = HIDDEN;}
		;

COMMENT 	: '//' ~('\r' | '\n')* ('\r' | '\n') { $channel = HIDDEN;}
		;
