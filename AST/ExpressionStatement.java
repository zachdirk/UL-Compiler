package AST;
import Type.*;
public class ExpressionStatement extends Statement{
	
	Expression e;
	
	public ExpressionStatement(Expression e){
		this.e = e;
	}

	public Type accept(Visitor v){
		return(v.visit(this));
	}

}
