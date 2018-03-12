package AST;
import Type.*;
import IR.Temp.*;
import Visitor.*;
public class ExpressionStatement extends Statement{
	
	public Expression e;
	
	public ExpressionStatement(Expression e){
		this.e = e;
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
