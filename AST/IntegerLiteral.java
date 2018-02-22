package AST;
public class IntegerLiteral extends Expression{
	int i;
	int lineNumber;
	int offset;

	public IntegerLiteral(int i, int lineNumber, int offset){
		this.i = i;
		this.lineNumber = lineNumber;
		this.offset = offset;
	}
	public String toString(){
		return "" + i;
	}
	public void accept(Visitor v){
		v.visit(this);
	}
}
