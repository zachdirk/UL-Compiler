package Type;
import AST.*;
public class FloatType extends Type{
	public FloatType() {

	}
	public String toString() {
		return "float";
	}
	public boolean equals (Object o) {
		return (o instanceof FloatType);
	}
	public void accept (Visitor v){
		v.visit(this);
	}
}
