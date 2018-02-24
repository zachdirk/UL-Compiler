package AST;
import Type.*;
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

	public Type accept(Visitor v){
		return(v.visit(this));
	}
}
