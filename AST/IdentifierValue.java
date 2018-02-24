package AST;
import Type.*;
public class IdentifierValue extends Expression {
	String id;
	public IdentifierValue(String s){
		id = s;
	}
	public String toString(){
		return id;
	}

	public Type accept(Visitor v){
		return(v.visit(this));
	}

}
