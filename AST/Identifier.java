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

	public void accept (Visitor v)
	{
		v.visit(this);
	}

}
