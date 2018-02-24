package AST;
import Type.*;
public class FunctionDeclaration extends ASTNode {
	
	Type t;
	Identifier id;
	FormalParameterList fpl;

	public FunctionDeclaration(Type t, Identifier id, FormalParameterList fpl){
		this.t = t;
		this.id = id;
		this.fpl = fpl;
		this.lineNumber = id.lineNumber;
		this.offset = id.offset;
	}

	public Type accept(Visitor v){
		return(v.visit(this));
	}


}
