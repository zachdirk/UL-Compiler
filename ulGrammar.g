grammar ulGrammar;
@header
{
	import AST.*;
	import Type.*;
	import java.util.Vector;
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
		: t0=intType {t = t0;}
		| t1=floatType {t = t1;}
		| t2=charType {t = t2;}
		| t3=stringType {t = t3;}
		| t4=booleanType {t = t4;}
		| t5=voidType {t = t5;}
		;

intType returns [IntegerType t]
		: i=INTTYPE {t = new IntegerType($i.line, $i.pos);}
		;

floatType returns [FloatType t]
		: f=FLOATTYPE {t = new FloatType($f.line, $f.pos);} 
		;

charType returns [CharType t]
		: c=CHARTYPE {t = new CharType($c.line, $c.pos);} 
		;

booleanType returns [BooleanType t]
		: b=BOOLEANTYPE {t = new BooleanType($b.line, $b.pos);} 
		;

stringType returns [StringType t]
		: s=STRINGTYPE {t = new StringType($s.line, $s.pos);} 
		;

voidType returns [VoidType t]
		: v=VOIDTYPE {t = new VoidType($v.line, $v.pos);} 
		;

arrayType returns [Type t]
		: t2=type '[' i=intLiteral ']' {t = new ArrayType(t2, i);}
		;

statement returns [Statement s] options {backtrack=true;} 
		: s0=ifStmt {s=s0;}
		| s1=whileStmt {s=s1;}
		| s2=printStmt {s=s2;}
		| s3=printlnStmt {s=s3;}
		| s4=returnStmt {s=s4;}
		| s5=assignStmt {s=s5;}
		| s6=arrAssignStmt {s=s6;}
		| s7=exprStmt {s=s7;}
		| ';'
		; 

ifStmt returns [IfStatement s] options {backtrack=true;}
		: IF '(' e1=expr ')' b1=block ELSE b2=block {s = new IfStatement(e1,b1,b2);}
		| IF '(' e1=expr ')' b1=block {s = new IfStatement(e1,b1,null);}
		;

whileStmt returns [WhileStatement w]
		: WHILE '(' e=expr ')' b=block {w = new WhileStatement(e, b);}
		;

printStmt returns [PrintStatement p]	
		: PRINT e=expr ';' {p = new PrintStatement(e);}
		;

printlnStmt returns [PrintLnStatement p]
		: PRINTLN e=expr ';' {p = new PrintLnStatement(e);}
		;

returnStmt returns [ReturnStatement r]	
		: ret=RETURN e=expr? ';' {r = new ReturnStatement(e, $ret.line, $ret.pos);}
		;

assignStmt returns [AssignmentStatement a]	
		: i=id '=' e=expr ';' {a = new AssignmentStatement(i, e);}
		;
arrAssignStmt returns [ArrayAssignment a]	
		: i=id '[' e1=expr ']' '=' e2=expr ';' {a = new ArrayAssignment(i, e1, e2);}
		;

exprStmt returns [Statement s]
		: e1=expr ';' {s = new ExpressionStatement(e1);}
		;

varDecl	returns [VariableDeclaration vd] 
		: t=compoundType i=id ';' {vd = new VariableDeclaration(t, i);}
		;

block returns [Block b]
@init{
	b = new Block();
}
		: '{' (s=statement {b.addElement(s);})* '}'
		;

expr returns [Expression e]
@init {
	Expression it = null;
}
@after {
	e = it;
}

		: e1=ltExpr {it = e1;} ('==' e2=ltExpr {it = new EqualityExpression(it, e2);} )* 
		;

ltExpr returns [Expression e]
@init {
	Expression it = null;
}
@after { 
	e = it;
}

		: e1=asExpr {it = e1;} ('<' e2=asExpr {it = new LessThanExpression(it, e2);} )*
		;

asExpr returns [Expression e]	
@init {
	Expression it = null;
}
@after {
	e = it;
}
		: e1=multExpr {it = e1;} (c=('+'|'-') e2=multExpr {if ($c.text.charAt(0) == '+') it = new AddExpression(it, e2); else it = new SubtractExpression(it, e2);})*
		;
multExpr returns [Expression e]
@init {
	Expression it = null;
}	
@after {
	e = it;
}

		: e1=atom {it = e1;} ('*' e2=atom {it = new MultExpression(it, e2);})*
		; 

atom returns [Expression e]
		: e1=idVal {e = e1;}
		| e2=arrayReference {e = e2;}
		| e3=functionCall {e = e3;}
		| e4=literal {e = e4;}
		| e5=parenExpression {e = e5;}
		;

arrayReference returns [ArrayReference e]
		: i=id '[' e2=expr ']' {e = new ArrayReference(i, e2);}
		;

functionCall returns [FunctionCall f]
		: i=id '(' v=exprList ')' {f = new FunctionCall(i, v);}
		;

parenExpression	returns [ParenExpression p]
		: '(' e=expr ')' {p = new ParenExpression(e);}
		;

exprList returns [Vector<Expression> v]
@init {
	v = new Vector<Expression>();
}
	 	: e=expr {v.add(e);} (e2=exprMore {v.add(e2);})*
		|
		;

exprMore returns [Expression e]	
		: ',' e1=expr {e = e1;}
		;

literal	returns [Expression e]
		: e1=strLiteral {e = e1;}
		| e2=intLiteral {e = e2;}
		| e3=floatLiteral {e = e3;}
		| e4=charLiteral {e = e4;}
		| e5=booleanLiteral {e = e5;}
		;

id returns [Identifier i]
		: i2=ID {i = new Identifier($i2.text, $i2.line, $i2.pos);}
		;

idVal returns [Expression e]
		: e2=ID {e = new IdentifierValue($e2.text, $e2.line, $e2.pos);}
		;

intLiteral returns [IntegerLiteral l]
		: i=INTCONSTANT {l = new IntegerLiteral(Integer.parseInt($i.text), $i.line, $i.pos);}
		;

strLiteral returns [StringLiteral l]
		: s=STRINGCONSTANT {l = new StringLiteral($s.text, $s.line, $s.pos);}
		;

floatLiteral returns [FloatLiteral l]
		: f=FLOATCONSTANT {l = new FloatLiteral(Double.parseDouble($f.text), $f.line, $f.pos);}
		;

charLiteral returns [CharacterLiteral l]
		: c=CHARCONSTANT {l = new CharacterLiteral($c.text, $c.line, $c.pos);}
		;

booleanLiteral returns [BooleanLiteral l]
		: b=TRUE {l = new BooleanLiteral(true, $b.line, $b.pos);}
		| b=FALSE {l = new BooleanLiteral(false, $b.line, $b.pos);}
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
