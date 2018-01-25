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
		: t2=intType {t = new IntegerType();}
		| t2=floatType {t = new FloatType();}
		| t2=charType {t = new CharType();}
		| t2=stringType {t = new StringType();}
		| t2=booleanType {t = new BooleanType();}
		| t2=voidType {t = new VoidType();}
		;

intType 	: INTTYPE 
		;

floatType 	: FLOATTYPE 
		;

charType 	: CHARTYPE 
		;

booleanType 	: BOOLEANTYPE 
		;

stringType 	: STRINGTYPE 
		;

voidType 	: VOIDTYPE 
		;

arrayType returns [Type t]
		: t2=type '[' i=intLiteral ']' {t = new ArrayType(t2, i);}
		;

statement returns [Statement s] options {backtrack=true;} 
		: ifStmt 
		| whileStmt
		| s2=printStmt {s=s2;}
		| s2=printlnStmt {s=s2;}
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

printStmt returns [PrintStatement p]	
		: PRINT e=expr ';' {p = new PrintStatement(e);}
		;


printlnStmt returns [PrintLnStatement p]
		: PRINTLN e=expr ';' {p = new PrintLnStatement(e);}
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

literal		: strLiteral
		| intLiteral
		| floatLiteral
		| charLiteral
		| TRUE
		| FALSE
		;

id returns [Identifier i]
		: i2=ID {i = new Identifier($i2.text);}
		;

intLiteral returns [IntegerLiteral l]
		: i=INTCONSTANT {l = new IntegerLiteral(Integer.parseInt($i.text));}
		;

strLiteral returns [StringLiteral l]
		: s=STRINGCONSTANT {l = new StringLiteral($s.text);}
		;

floatLiteral returns [FloatLiteral l]
		: f=FLOATCONSTANT {l = new FloatLiteral(Double.parseDouble($f.text));}
		;

charLiteral returns [CharacterLiteral l]
		: c=CHARCONSTANT {l = new CharacterLiteral($c.text);}
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
