package AST;
import Type.*;
public class VariableDeclaration extends ASTNode {

	Type t;
	Identifier id;

	public VariableDeclaration (Type t, Identifier id){
		this.t = t;
		this.id = id;
	}
	
	public void accept (Visitor v){
		v.visit(this);
	}

	public String toString(){
		String s = t.toString() + " " + id.toString() + ";";
		return s;
	}
}

