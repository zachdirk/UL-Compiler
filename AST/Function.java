package AST;
import Type.*;
public class Function extends ASTNode {

	FunctionBody fb;
	FunctionDeclaration fd;

	public Function(FunctionDeclaration fd, FunctionBody fb){
		this.fb = fb;
		this.fd = fd;
	}

	public Type accept(Visitor v){
		return(v.visit(this));
	}


}
