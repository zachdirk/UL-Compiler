package AST;
import Type.*;
public class FormalParameter extends ASTNode {
	Type t;
	Identifier id;
	public FormalParameter(Type t, Identifier id){
		this.t = t;
		this.id = id;
		this.lineNumber = id.lineNumber;
		this.offset = id.offset;
	}
	
	public Type accept(Visitor v){
		return(v.visit(this));
	}

	public String toString (){
		String s;
		s = t.toString() + " " + id.toString();
		return(s);
	}

}
