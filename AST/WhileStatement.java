package AST;
import Type.*;
import IR.Temp.*;
import Visitor.*;
public class WhileStatement extends Statement{
	
	public Expression condition;
	public Block b;
	
	public WhileStatement(Expression e, Block b){
		condition = e;
		this.b = b;
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
