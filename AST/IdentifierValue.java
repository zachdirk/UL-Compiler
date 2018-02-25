package AST;
import Type.*;
public class IdentifierValue extends Expression {
	String id;
	public IdentifierValue(String s, int lineNumber, int offset){
		id = s;
		this.lineNumber = lineNumber;
		this.offset = offset;
	}
	public String toString(){
		return id;
	}

	public Type accept(Visitor v){
		return(v.visit(this));
	}

}
