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
	}

	void accept (Visitor v){
		v.visit(this);
	}

}
