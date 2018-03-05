package AST;
import Type.*;
import Temp.*;
import Visitor.*;
public class IfStatement extends Statement{
	public Expression condition;
	public Block b1;
	public Block b2;
	public IfStatement(Expression e,Block b1,Block b2){
		condition = e;
		this.b1=b1;
		this.b2=b2;
		this.lineNumber = condition.lineNumber;
		this.offset = condition.offset;
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
