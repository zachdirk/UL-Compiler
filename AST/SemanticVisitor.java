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
		currentFunction = "";
		currentFunctionType = null;
	}

	public Type visit(AddExpression e){
		return null;
	}
	public Type visit(ArrayType t){
		return t;
	}
	public Type visit(ArrayAssignment a){
		return null;
	}
	public Type visit(ArrayReference a){
		return null;
	}
	public Type visit(AssignmentStatement a){
		return null;
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
		return new BooleanType();
	}
	public Type visit(ExpressionStatement e){
		return null;
	}
	public Type visit(FloatLiteral e){
		return new FloatType();
	}
	public Type visit(FloatType t){
		return t;
	}
	public Type visit(FormalParameter p){
		return null;
	}
	public Type visit(FormalParameterList p){
		return null;
	}
	public Type visit(FunctionBody f){
		return null;
	}
	public Type visit(FunctionCall f){
		return null;
	}
	public Type visit(FunctionDeclaration f){
		Type t = f.t.accept(this);
		String id = f.id.id;
		currentFunction = id;
		currentFunctionType = t;
		if (fEnv.inCurrentScope(id)){
			throw new SemanticException("Error:" + f.lineNumber + ": Function " + id + " is already in scope.");
		}
		if (id.equals("main")){
			if (!(t instanceof VoidType))
				throw new SemanticException("Error:" + f.lineNumber + ": Main function must have return type void (" + f.lineNumber + ", " + f.offset + ")");
			if (f.fpl.parameterList.size() > 0)
				throw new SemanticException("Error:" + f.lineNumber + ": Main function must not take any arguments (" + f.lineNumber + ", " + f.offset + ")");
		}
		fEnv.add(id, f);
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
		return null;
	}
	public Type visit(IfStatement s){
		return null;
	}
	public Type visit(IntegerLiteral i){
		return new IntegerType();
	}
	public Type visit(IntegerType t){
		return t;
	}
	public Type visit(LessThanExpression e){
		return null;
	}
	public Type visit(MultExpression e){
		return null;
	}
	public Type visit(ParenExpression e){
		return null;
	}
	public Type visit(PrintStatement s){
		return null;
	}
	public Type visit(PrintLnStatement s){
		return null;
	}
	public Type visit(Program p){
		fEnv.beginScope();
		Vector v = p.functionList;
		Function f;
		for (int i = 0; i < v.size(); i++){
			f = (Function)v.get(i);
			f.accept(this);
		}
		if (!fEnv.inCurrentScope("main"))
			throw new SemanticException("Error:0: Program must have a main function.");
		fEnv.endScope();
		return null;
	}
	public Type visit(ReturnStatement s){
		return(s.e.accept(this));
	}
	public Type visit(StringLiteral s){
		return new StringType();
	}
	public Type visit(SubtractExpression e){
		return null;
	}
	public Type visit(StringType t){
		return t;
	}
	public Type visit(VariableDeclaration s){
		return s.t;
	}
	public Type visit(VoidType t){
		return t;
	}
	public Type visit(WhileStatement s){
		return null;
	}

}
