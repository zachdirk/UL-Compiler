package AST;
public class StringLiteral extends Expression{
	String s;
	int lineNumber;
	int offset;

	public StringLiteral(String s, int lineNumber, int offset){
		this.s = s;
		this.lineNumber = lineNumber;
		this.offset = offset;
	}
	public String toString(){
		return s;
	}
	public void accept(Visitor v){
		v.visit(this);
	}
}
