package AST;
import Type.*;
import Environment.*;
public class TypeCheckVisitor implements Visitor{

	Environment<String, FunctionDecl> fEnv;
	Environment<String, Type> vEnv;

	String currentFunction;
	Type currentFunctionType;

	public TypeCheckVisitor(){
		fEnv = new Environment<String, FunctionDecl>();
		vEnv = new Environment<String, Type>() vEnv;
		currentFunction = "";
		currentFunctionType = null;
	}

	public Type visit(AddExpression e){

	}
	public Type visit(ArrayType t){
		return t;
	}
	public Type visit(ArrayAssignment a){
		return null;
	}
	public Type visit(ArrayReference a){

	}
	public Type visit(AssignmentStatement a){
		return null;
	}
	public Type visit(Block b){
		return null;'
	}
	public Type visit(BooleanLiteral e){
		return new BooleanType;
	}
	public Type visit(BooleanType t){
		return t;
	}
	public Type visit(CharacterLiteral e){
		return new CharType;
	}
	public Type visit(CharType t){
		return t;
	}
	public Type visit(EqualityExpression e){
		return new BooleanType;
	}
	public Type visit(FloatLiteral e){
		return new FloatType;
	}
	public Type visit(FloatType t){
		return t;
	}
	public Type visit(FormalParameter p){

	}
	public Type visit(FormalParameterList p){
		return null;
	}
	public Type visit(FunctionBody f){
		return null;
	}
	public Type visit(FunctionCall f){

	}
	public Type visit(FunctionDeclaration f){

	}
	public Type visit(Function f){
		
	}
	public Type visit(Identifier id){
	
	}
	public Type visit(IdentifierValue id){
		return null;
	}
	public Type visit(IfStatement s){
		return null;
	}
	public Type visit(IntegerLiteral i){
		return new IntegerType;
	}
	public Type visit(IntegerType t){
		return t;
	}
	public Type visit(LessThanExpression e){

	}
	public Type visit(MultExpression e){

	}
	public Type visit(ParenExpression e){

	}
	public Type visit(PrintStatement s){
		return null;
	}
	public Type visit(PrintLnStatement s){
		return null;
	}
	public Type visit(Program p){
		return null;
	}
	public Type visit(ReturnStatement s){

	}
	public Type visit(StringLiteral s){
		return new StringType;
	}
	public Type visit(SubtractExpression e){

	}
	public Type visit(StringType t){
		return t;
	}
	public Type visit(VariableDeclaration s){

	}
	public Type visit(VoidType t){
		return t;
	}
	public Type visit(WhileStatement s){
		return null;
	}

}
