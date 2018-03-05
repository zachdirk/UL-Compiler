package AST;
import Type.*;
import Temp.*;
import Visitor.*;
public class Identifier extends ASTNode {
	
	public String id;
		
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

	public void acceptPrint(PrintVisitor v){
		v.visit(this);
	}

	public Type acceptSemantic(SemanticVisitor v){
		return(v.visit(this));
	}

	public Temp acceptTemp(TempVisitor v){
		return(v.visit(this));
	}


}
