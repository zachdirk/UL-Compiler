package AST;
import Type.*;
public class WhileStatement extends Statement{
	
	Expression condition;
	Block b;
	
	public WhileStatement(Expression e, Block b){
		condition = e;
		this.b = b;
		this.lineNumber = condition.lineNumber;
		this.offset = condition.offset;
	}

	public Type accept(Visitor v){
		return(v.visit(this));
	}


}
