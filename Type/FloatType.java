package Type;
import AST.*;
public class FloatType extends Type{
	public FloatType() {

	}
	public FloatType(int lineNumber, int offset){
		this.lineNumber = lineNumber;
		this.offset = offset;
	}
	public String toString() {
		return "float";
	}
	public boolean equals (Object o) {
		return (o instanceof FloatType);
	}
	public Type accept (Visitor v){
		return(v.visit(this));
	}
}
