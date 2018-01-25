package AST;
import Type.*;
public class Identifier extends ASTNode {
	
	String id;

	public Identifier(String id){
		this.id = id;
	}

	public String toString(){
		return id;
	}

	public void accept (Visitor v)
	{
		v.visit(this);
	}

}
