package AST;
import Type.*;
import IR.Temp.*;
import Visitor.*;
public class VariableDeclaration extends ASTNode {

	public Type t;
	public Identifier id;

	public VariableDeclaration (Type t, Identifier id){
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

	public String toString(){
		String s = t.toString() + " " + id.toString() + ";";
		return s;
	}
}

