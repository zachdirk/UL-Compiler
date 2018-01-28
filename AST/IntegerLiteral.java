package AST;
public class IntegerLiteral extends Expression{
	int i;
	public IntegerLiteral(int i){
		this.i = i;
	}
	public String toString(){
		return "" + i;
	}
	public void accept(Visitor v){
		v.visit(this);
	}
}
