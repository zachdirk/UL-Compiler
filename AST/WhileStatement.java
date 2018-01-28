package AST;
public class WhileStatement extends Statement{
	
	Expression condition;
	Block b;
	
	public WhileStatement(Expression e, Block b){
		condition = e;
		this.b = b;
	}

	public void accept(Visitor v){
		v.visit(this);
	}
}
