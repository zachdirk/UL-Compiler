package AST;
public class BooleanLiteral extends Expression{
	boolean b;
	int lineNumber;
	int offset;

	public BooleanLiteral(boolean b, int lineNumber, int offset){
		this.b = b;
		this.lineNumber = lineNumber;
		this.offset = offset;
	}
	public String toString(){
		String s = "";
		if (b) 
			s = "true";
		else if (!b)
			s = "false";
		return(s);
	}
	public void accept(Visitor v){
		v.visit(this);
	}
}
