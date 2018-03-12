package AST;
import Type.*;
import IR.Temp.*;
import Visitor.*;
public class AssignmentStatement extends Statement{
	public Identifier id;
	public Expression expr;
	
	public AssignmentStatement (Identifier i, Expression e){
		id = i;
		expr = e;
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
