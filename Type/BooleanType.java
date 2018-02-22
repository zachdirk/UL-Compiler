package Type;
import AST.*;
public class BooleanType extends Type {
	public BooleanType () {

	}
	public BooleanType (int lineNumber, int offset){
		this.lineNumber = lineNumber;
		this.offset = offset;
	}
	public String toString() {
		return "boolean";
	}
	public boolean equals (Object o) {
		return (o instanceof BooleanType);
	}
	public void accept (Visitor v) {
		v.visit(this);
	}
}
