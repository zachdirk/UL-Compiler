package Type;
import AST.*;
public class VoidType extends Type {
	public VoidType (){

	}
	public VoidType (int lineNumber, int offset){
		this.lineNumber = lineNumber;
		this.offset = offset;
	}
	public String toString() {
		return "void";
	}
	public boolean equals (Object o){
		return (o instanceof VoidType);
	}
	public Type accept (Visitor v){
		return(v.visit(this));
	}
}
