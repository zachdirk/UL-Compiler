package AST;
public class Function extends ASTNode {

	FunctionBody fb;
	FunctionDeclaration fd;

	public Function(FunctionDeclaration fd, FunctionBody fb){
		this.fb = fb;
		this.fd = fd;
	}

	void accept (Visitor v){
		v.visit(this);
	}

}
