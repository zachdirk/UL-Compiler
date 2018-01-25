package Type;
import AST.*;
public class ArrayType extends Type {

	Type t;
	IntegerLiteral i;

	public ArrayType (Type t, IntegerLiteral i) {
		this.t = t;
		this.i = i;
	}
	public String toString() {
		return t + "[" + i + "]";
	}
	public boolean equals (Object o) {
		return (o instanceof ArrayType);
	}
	public void accept (Visitor v){
		v.visit(this);
	}
}
