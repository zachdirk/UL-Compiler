package AST;
import IR.Temp.*;
import Visitor.*;
import Type.*;
public class ReturnStatement extends Statement{
	public Expression e;
	
	public ReturnStatement(Expression e){
		this.e = e;
		this.lineNumber = e.lineNumber;
		this.offset = e.offset;
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
