package AST;
import Type.*;
public class WhileStatement extends Statement{
	
	Expression condition;
	Block b;
	
	public WhileStatement(Expression e, Block b){
		condition = e;
		this.b = b;
	}

	public Type accept(Visitor v){
		return(v.visit(this));
	}


}
