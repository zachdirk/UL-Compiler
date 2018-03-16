package AST;
import Type.*;
import IR.Temp.*;
import IR.*;
import Visitor.*;
public class Function extends ASTNode {

	public FunctionBody fb;
	public FunctionDeclaration fd;

	public Function(FunctionDeclaration fd, FunctionBody fb){
		this.fb = fb;
		this.fd = fd;
		this.lineNumber = fd.lineNumber;
		this.offset = fd.offset;
	}

	public void acceptPrint(PrintVisitor v){
		v.visit(this);
	}

	public Type acceptSemantic(SemanticVisitor v){
		return(v.visit(this));
	}

	public IRFunction acceptTemp(TempVisitor v){
		return(v.visit(this));
	}


}
