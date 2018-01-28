package AST;
public class IfStatement extends Statement{
	Expression condition;
	Block b1;
	Block b2;
	public IfStatement(Expression e,Block b1,Block b2){
		condition = e;
		this.b1=b1;
		this.b2=b2;
	}
	public void accept(Visitor v){
		v.visit(this);
	}
}
