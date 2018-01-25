package AST;
import Type.*;
public class FormalParameter extends ASTNode {
	Type t;
	Identifier id;
	public FormalParameter(Type t, Identifier id){
		this.t = t;
		this.id = id;
	}
	
	void accept (Visitor v){
		v.visit(this);
	}

	public String toString (){
		String s;
		s = t.toString() + " " + id.toString();
		return(s);
	}

}
