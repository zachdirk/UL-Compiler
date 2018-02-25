package AST;
import Type.*;
public class Identifier extends ASTNode {
	
	String id;
		
	public Identifier(String id, int lineNumber, int offset){
		this.id = id;
		this.lineNumber = lineNumber;
		this.offset = offset;
	}

	public String toString(){
		return id;
	}

	public boolean idEquals(Identifier other){
		System.out.println(this.id + " ?= " + other.id + " = " + this.id.equals(other.id));
		return (this.id.equals(other.id));
	}

	public Type accept(Visitor v){
		return(v.visit(this));
	}


}
