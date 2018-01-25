package AST;
import Type.*;

public interface Visitor{
	public void visit(AddExpression e);
	//public void visit(ArrayAssignment e);
	public void visit(ArrayType t);
	public void visit(AssignmentStatement s);

	//public void visit(Block e);
	//public void visit(BooleanLiteral e);
	public void visit(BooleanType t);
	public void visit(CharType t);
	//public void visit(Declaration e);
	public void visit(EqualityExpression e);
	//public void visit(ExpressionList e);
	//public void visit(ExpressionStatement e);
	//public void visit(FloatLiteral e);
	public void visit(FloatType t);
	public void visit(FormalParameter p);
	public void visit(FormalParameterList p);
	public void visit(FunctionBody f);
	//public void visit(FunctionCall s);
	public void visit(FunctionDeclaration f);
	public void visit(Function f);
	public void visit(Identifier id);
	//public void visit(IdentifierValue id);
	//public void visit(IfStatement s);
	//public void visit(IntegerLiteral i);
	public void visit(IntegerType t);
	public void visit(LessThanExpression e);
	public void visit(MultExpression e);
	public void visit(ParenExpression e);
	public void visit(PrintStatement s);
	public void visit(PrintLnStatement s);
	public void visit(Program p);
	//public void visit(ReturnStatement s);
	//public void visit(StringLiteral s);
	public void visit(StringType t);
	public void visit(SubtractExpression s);
	//public void visit(TypeNode t);
	//public void visit(VariableAssignment s);
	public void visit(VariableDeclaration s);
	//public void visit(VariableDeclarationList s);
	public void visit(VoidType t);
	//public void visit(WhileStatement s);
}
