package AST;
import Type.*;
import IR.Temp.*;
import Visitor.*;
public class ArrayAssignment extends Statement {
	
	public Identifier id;
	public Expression index;
	public Expression e;

	public ArrayAssignment(Identifier id, Expression index, Expression e){
		this.id = id;
		this.index = index;
		this.e = e;
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
