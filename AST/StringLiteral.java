package AST;
import Type.*;
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

	public Type accept(Visitor v){
		return(v.visit(this));
	}


}
