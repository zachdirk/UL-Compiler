package Type;
import AST.*;
public class CharType extends Type {
	public CharType(){
	
	}
	public String toString() {
		return "char";
	}
	public boolean equals (Object o) {
		return (o instanceof CharType);
	}
	public void accept (Visitor v) {
		v.visit(this);
	}
}
