package Type;
import AST.*;
public class ArrayType extends Type {

	Type t;
	Expression e;

	public ArrayType (Type t) {
		this.t = t;
	}
	public String toString() {
		return "Array of " + t;
	}
	public boolean equals (Object o) {
		return (o instanceof ArrayType);
	}
	public void accept (Visitor v){
		v.visit(this);
	}
}
