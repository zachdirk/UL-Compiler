package AST;
import Type.*;
import IR.Temp.*;
import Visitor.*;
public class ArrayReference extends Expression{
	public Identifier id;
	public Expression e;
	public ArrayReference(Identifier id, Expression e){
		this.id = id;
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
