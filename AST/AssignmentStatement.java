package AST;
import Type.*;
public class AssignmentStatement extends Statement{
	Identifier id;
	Expression expr;
	
	public AssignmentStatement (Identifier i, Expression e){
		id = i;
		expr = e;
	}
	
	public Type accept(Visitor v){
		return(v.visit(this));
	}
}
