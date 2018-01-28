package AST;
public class ArrayReference extends Expression{
	Identifier id;
	IntegerLiteral i;
	public ArrayReference(Identifier id, IntegerLiteral i){
		this.id = id;
		this.i = i;
	}
	public String toString(){
		String s = id.toString() + "[" + i.toString() + "]";
		return(s);
	}
	public void accept (Visitor v){
		v.visit(this);
	}
}
