package AST;
import Type.*;
public class ArrayReference extends Expression{
	Identifier id;
	Expression e;
	public ArrayReference(Identifier id, Expression e){
		this.id = id;
		this.e = e;
		this.lineNumber = id.lineNumber;
		this.offset = id.offset;
	}

	public Type accept(Visitor v){
		return(v.visit(this));
	}
}
