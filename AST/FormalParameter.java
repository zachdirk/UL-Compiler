package AST;
import Type.*;
import Temp.*;
import Visitor.*;
public class FormalParameter extends ASTNode {
	public Type t;
	public Identifier id;
	public FormalParameter(Type t, Identifier id){
		this.t = t;
		this.id = id;
		this.lineNumber = id.lineNumber;
		this.offset = id.offset;
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

	public String toString (){
		String s;
		s = t.toString() + " " + id.toString();
		return(s);
	}

}
