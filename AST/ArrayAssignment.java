package AST;
public class ArrayAssignment extends Statement {
	
	Identifier id;
	Expression index;
	Expression e;

	public ArrayAssignment(Identifier id, Expression index, Expression e){
		this.id = id;
		this.index = index;
		this.e = e;	
	}

	public void accept(Visitor v){
		v.visit(this);
	}
}
