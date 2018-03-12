package Visitor;
import java.util.Vector;
import IR.*;
import IR.Temp.*;
import AST.*;
import Type.*;
import Environment.*;
public class TempVisitor{

	ListEnvironment<String, FunctionDeclaration> fEnv;
	ListEnvironment<String, Temp> vEnv;

	String currentFunction;
	Type currentFunctionType;

	public TempVisitor(){
		fEnv = new ListEnvironment<String, FunctionDeclaration>();
		vEnv = new ListEnvironment<String, Temp>();
		currentFunction = null;
		currentFunctionType = null;
	
	}
		
	public Temp visit(AddExpression e){
		return null;
	}
	public Temp visit(ArrayType t){
		return null;
	}
	public Temp visit(ArrayAssignment a){
		return null;
	}
	public Temp visit(ArrayReference a){
		return null;
	}
	public Temp visit(AssignmentStatement a){
		return null;
	}
	public Temp visit(Block b){
		return null;
	}
	public Temp visit(BooleanLiteral e){
		return null;
	}
	public Temp visit(BooleanType t){
		return null;
	}
	public Temp visit(CharacterLiteral e){
		return null;
	}
	public Temp visit(CharType t){
		return null;
	}
	public Temp visit(EqualityExpression e){
		return null;
	}
	public Temp visit(ExpressionStatement e){
		return null;
	}
	public Temp visit(FloatLiteral e){
		return null;
	}
	public Temp visit(FloatType t){
		return null;
	}
	public Temp visit(FormalParameter p){
		return null;
	}
	public Temp visit(FormalParameterList p){
		return null;
	}
	public Temp visit(FunctionBody f){
		return null;	
	}
	public Temp visit(FunctionCall f){
		return null;
	}
	public Temp visit(FunctionDeclaration f){
		return null;	
	}
	public Temp visit(Function f){
		return null;
	}
	public Temp visit(Identifier id){
		return null;
	}
	public Temp visit(IdentifierValue id){
		return null;
	}
	public Temp visit(IfStatement s){
		return null;
	}
	public Temp visit(IntegerLiteral i){
		return null;
	}
	public Temp visit(IntegerType t){
		return null;
	}
	public Temp visit(LessThanExpression e){
		return null;
	}
	public Temp visit(MultExpression e){
		return null;
	}
	public Temp visit(ParenExpression e){
		return null;
	}
	public Temp visit(PrintStatement s){
		return null;
	}
	public Temp visit(PrintLnStatement s){
		return null;
	}
	public Temp visit(Program p){
		return null;
	}
	public Temp visit(ReturnStatement s){
		return null;
	}
	public Temp visit(StringLiteral s){
		return null;
	}
	public Temp visit(SubtractExpression e){
		return null;
	}
	public Temp visit(StringType t){
		return null;
	}
	public Temp visit(VariableDeclaration s){
		return null;
	}
	public Temp visit(VoidType t){
		return null;
	}
	public Temp visit(WhileStatement s){
		return null;
	}
}
