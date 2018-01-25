package Type;
import AST.*;
public class VoidType extends Type {
	public VoidType (){

	}
	public String toString() {
		return "void";
	}
	public boolean equals (Object o){
		return (o instanceof VoidType);
	}
	public void accept (Visitor v){
		v.visit(this);
	}
}
