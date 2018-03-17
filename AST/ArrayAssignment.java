package AST;
import Type.*;
public class ArrayAssignment extends Statement {
	
	Identifier id;
	Expression index;
	Expression e;

	public ArrayAssignment(Identifier id, Expression index, Expression e){
		this.id = id;
		this.index = index;
		this.e = e;
		this.lineNumber = id.lineNumber;
		this.offset = id.offset;	
	}

	public Type accept(Visitor v){
		return(v.visit(this));
	}
}
