package AST;
import java.util.Vector;
import Type.*;
import Environment.*;
public class SemanticVisitor implements Visitor{

	ListEnvironment<String, FunctionDeclaration> fEnv;
	ListEnvironment<String, Type> vEnv;

	String currentFunction;
	Type currentFunctionType;

	public SemanticVisitor(){
		fEnv = new ListEnvironment<String, FunctionDeclaration>();
		vEnv = new ListEnvironment<String, Type>();
		currentFunction = null;
		currentFunctionType = null;
	}

	public Type visit(AddExpression e){
		Type t1 = e.expr1.accept(this);
		Type t2 = e.expr2.accept(this);
		if (!t1.getClass().equals(t2.getClass()))
			throw new SemanticException("Type mismatch on addition expression: " + t1 + " and " + t2, e.lineNumber, e.offset);
		if (t1 instanceof BooleanType)
			throw new SemanticException("Boolean type is invalid for addition", e.expr1.lineNumber, e.expr1.offset);
		if (t2 instanceof BooleanType)
			throw new SemanticException("Boolean type is invalid for addition", e.expr2.lineNumber, e.expr2.offset);
		return t1;
	}
	public Type visit(ArrayType t){
		return t;
	}
	public Type visit(ArrayAssignment a){
		
		return null;
	}
	public Type visit(ArrayReference a){
		Type t = a.e.accept(this);
		if (!(t instanceof IntegerType))
			throw new SemanticException("Array index has invalid type " + t + ". Index must be of type integer.", a.lineNumber, a.offset);
		return t;
	}
	public Type visit(AssignmentStatement a){
		Type t1 = vEnv.lookup(a.id.id);
		Type t2 = a.expr.accept(this);
		if (!t1.getClass().equals(t2.getClass()))
			throw new SemanticException("Type mismatch, variable \"" + a.id.id + "\" expected type " + t1 + " but got type " + t2, a.lineNumber, a.offset);
		if (t1 instanceof ArrayType){
			ArrayType a1 = (ArrayType)t1;
			ArrayType a2 = (ArrayType)t2;			
			if (a1.i != a2.i)
				throw new SemanticException("Array lengths do not match, expected " + a1.i + " but got " + a2.i, a.lineNumber, a.offset);
		}
		return t1;
	}
	public Type visit(Block b){
		return null;
	}
	public Type visit(BooleanLiteral e){
		return new BooleanType();
	}
	public Type visit(BooleanType t){
		return t;
	}
	public Type visit(CharacterLiteral e){
		return new CharType();
	}
	public Type visit(CharType t){
		return t;
	}
	public Type visit(EqualityExpression e){
		Type t1 = e.expr1.accept(this);
		Type t2 = e.expr2.accept(this);
		if (!t1.getClass().equals(t2.getClass()))
			throw new SemanticException("Type mismatch on equality expression: " + t1 + " and " + t2, e.lineNumber, e.offset);	
		return new BooleanType();
	}
	public Type visit(ExpressionStatement e){
		Type t = e.e.accept(this);
		return t;
	}
	public Type visit(FloatLiteral e){
		return new FloatType();
	}
	public Type visit(FloatType t){
		return t;
	}
	public Type visit(FormalParameter p){
		if (p.t instanceof VoidType)
			throw new SemanticException("Parameter \"" + p.id + "\" has type Void.", p.lineNumber, p.offset);
		if(vEnv.inCurrentScope(p.id.id))
			throw new SemanticException("Parameter \"" + p.id + "\" already exists in scope.", p.lineNumber, p.offset);		
		vEnv.add(p.id.id, p.t);
		return null;
	}
	public Type visit(FormalParameterList p){
		Vector params = p.parameterList;
		FormalParameter fp;
		for (int i = 0; i < params.size(); i++){
			fp = (FormalParameter)params.get(i);
			fp.accept(this);
		}
		return null;
	}
	public Type visit(FunctionBody f){
		Vector v = f.varDecls;
		VariableDeclaration vd;
		for (int i = 0; i < v.size(); i++){
			vd = (VariableDeclaration)v.get(i);
			vd.accept(this);
		}
		Vector sList = f.statements;
		Statement s;
		for (int i = 0; i < sList.size(); i++){
			s = (Statement)sList.get(i);
			s.accept(this);
		}
		return null;
	}
	public Type visit(FunctionCall f){
		Identifier id = f.id;
		Vector v = f.v;
		if (!fEnv.inCurrentScope(id.id))
			throw new SemanticException("No function called \"" + id + "\"", f.lineNumber, f.offset);
		FunctionDeclaration fd = fEnv.lookup(id.id);
		Vector v2 = fd.fpl.parameterList;
		if (v.size() != v2.size())
			throw new SemanticException("Function call to \"" + id + "\" has " + v.size() + " parameters, expected " + v2.size(), f.lineNumber, f.offset);
		for (int i = 0; i < v.size(); i++){
			Type t1 = (((Expression)v.get(i)).accept(this));
			Type t2 = ((FormalParameter)v2.get(i)).t;
			if (!t1.getClass().equals(t2.getClass()))
				throw new SemanticException("Type mismatch invoking function \"" + id + "\", parameter " + (i+1) + " has type " + t1 + ", function expects type " + t2, f.lineNumber, t1.offset);
		}
		
		return fd.t;
	}
	public Type visit(FunctionDeclaration f){
		Type t = f.t;
		String id = f.id.id;
		currentFunction = id;
		currentFunctionType = t;
		if (id.toString().equals("main")){
			if (!(t instanceof VoidType))
				throw new SemanticException("Main function must have return type void.", f.lineNumber, f.offset);
			if (f.fpl.parameterList.size() > 0)
				throw new SemanticException("Main function must not take any arguments.", f.lineNumber, f.offset);
		}
		f.fpl.accept(this);
		return(t);
	}
	public Type visit(Function f){
		vEnv.beginScope();
		Type t = null;
		t = f.fd.accept(this);
		f.fb.accept(this);		
		vEnv.endScope();
		return(t);
	}
	public Type visit(Identifier id){
		return null;
	}
	public Type visit(IdentifierValue id){
		if (!vEnv.inCurrentScope(id.id))
			throw new SemanticException("Variable \"" + id.id + "\" undefined.", id.lineNumber, id.offset);
		return (vEnv.lookup(id.id));
	}
	public Type visit(IfStatement s){
		Type t = s.condition.accept(this);
		if (!(t instanceof BooleanType))
			throw new SemanticException("If statement condition must have type boolean, found " + t, s.lineNumber, s.offset);
		s.b1.accept(this);
		s.b2.accept(this);
		return t;
	}
	public Type visit(IntegerLiteral i){
		return new IntegerType();
	}
	public Type visit(IntegerType t){
		return t;
	}
	public Type visit(LessThanExpression e){
		Type t1 = e.expr1.accept(this);
		Type t2 = e.expr2.accept(this);
		if (!t1.getClass().equals(t2.getClass()))
			throw new SemanticException("Type mismatch on less-than expression: " + t1 + " and " + t2, e.lineNumber, e.offset);	
		return new BooleanType();
	}
	public Type visit(MultExpression e){
		Type t1 = e.expr1.accept(this);
		Type t2 = e.expr2.accept(this);
		if (!t1.getClass().equals(t2.getClass()))
			throw new SemanticException("Type mismatch on multiplication expression: " + t1 + " and " + t2, e.lineNumber, e.offset);
		if (t1 instanceof BooleanType)
			throw new SemanticException("Boolean type is invalid for multiplication", e.expr1.lineNumber, e.expr1.offset);
		if (t2 instanceof BooleanType)
			throw new SemanticException("Boolean type is invalid for multiplication", e.expr2.lineNumber, e.expr2.offset);
		if (t1 instanceof StringType)
			throw new SemanticException("String type is invalid for multiplication", e.expr1.lineNumber, e.expr1.offset);
		if (t2 instanceof StringType)
			throw new SemanticException("String type is invalid for multiplication", e.expr2.lineNumber, e.expr2.offset);
		if (t1 instanceof CharType)
			throw new SemanticException("Char type is invalid for multiplication", e.expr1.lineNumber, e.expr1.offset);
		if (t2 instanceof CharType)
			throw new SemanticException("Char type is invalid for multiplication", e.expr2.lineNumber, e.expr2.offset);
		return t1;
	}
	public Type visit(ParenExpression e){
		Type t = e.expr.accept(this);
		return t;
	}
	public Type visit(PrintStatement s){
		Type t = s.e.accept(this);
		if (t instanceof VoidType)
			throw new SemanticException("Cannot print expression of type Void", s.lineNumber, s.offset);
		return(t);
	}
	public Type visit(PrintLnStatement s){
		Type t = s.e.accept(this);
		if (t instanceof VoidType)
			throw new SemanticException("Cannot print expression of type Void", s.lineNumber, s.offset);
		return(t);
	}
	public Type visit(Program p){
		fEnv.beginScope();
		Vector v = p.functionList;
		Function f;
		//have to do this to make sure we can invoke functions before we declare them
		for (int i = 0; i < v.size(); i++){			
			f = (Function)v.get(i);
			String id = f.fd.id.id;			
			if (fEnv.inCurrentScope(id))
				throw new SemanticException("Function \"" + id + "\" is already in scope.", f.lineNumber, f.offset); 
			fEnv.add(id, f.fd);
		}
		for (int i = 0; i < v.size(); i++){
			f = (Function)v.get(i);
			f.accept(this);
		}
		if (!fEnv.inCurrentScope("main")) 
			throw new SemanticException("Program must have a main function.", 0, 0);
		fEnv.endScope();
		return null;
	}
	public Type visit(ReturnStatement s){
		Type t = s.e.accept(this);
		if (!t.getClass().equals(currentFunctionType))
			throw new SemanticException("Return statement type " + t + " does not match function return type " + currentFunctionType, s.lineNumber, s.offset);
		return(s.e.accept(this));
	}
	public Type visit(StringLiteral s){
		return new StringType();
	}
	public Type visit(SubtractExpression e){
		Type t1 = e.expr1.accept(this);
		Type t2 = e.expr2.accept(this);
		if (!t1.getClass().equals(t2.getClass()))
			throw new SemanticException("Type mismatch on subtraction expression: " + t1 + " and " + t2, e.lineNumber, e.offset);
		if (t1 instanceof BooleanType)
			throw new SemanticException("Boolean type is invalid for subtraction", e.expr1.lineNumber, e.expr1.offset);
		if (t2 instanceof BooleanType)
			throw new SemanticException("Boolean type is invalid for subtraction", e.expr2.lineNumber, e.expr2.offset);
		if (t1 instanceof StringType)
			throw new SemanticException("String type is invalid for subtraction", e.expr1.lineNumber, e.expr1.offset);
		if (t2 instanceof StringType)
			throw new SemanticException("String type is invalid for subtraction", e.expr2.lineNumber, e.expr2.offset);
		return t1;
	}
	public Type visit(StringType t){
		return t;
	}
	public Type visit(VariableDeclaration s){
		if (s.t instanceof VoidType)
			throw new SemanticException("Parameter \"" + s.id + "\" has type Void.", s.lineNumber, s.offset);
		if (vEnv.inCurrentScope(s.id.id))
			throw new SemanticException("Variable \"" + s.id.id + "\" already in scope.", s.lineNumber, s.offset);
		vEnv.add(s.id.id, s.t);
		return s.t;
	}
	public Type visit(VoidType t){
		return t;
	}
	public Type visit(WhileStatement s){
		Type t = s.condition.accept(this);
		if (!(t instanceof BooleanType))
			throw new SemanticException("While statement condition must have type boolean, found " + t, s.lineNumber, s.offset);
		s.b.accept(this);
		return t;
	}

}
