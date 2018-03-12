package AST;
import Type.*;
import IR.Temp.*;
import Visitor.*;
public class FunctionDeclaration extends ASTNode {
	
	public Type t;
	public Identifier id;
	public FormalParameterList fpl;

	public FunctionDeclaration(Type t, Identifier id, FormalParameterList fpl){
		this.t = t;
		this.id = id;
		this.fpl = fpl;
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


}
