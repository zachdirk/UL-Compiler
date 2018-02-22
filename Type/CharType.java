package Type;
import AST.*;
public class CharType extends Type {
	public CharType(){
	
	}
	public CharType (int lineNumber, int offset){
		this.lineNumber = lineNumber;
		this.offset = offset;
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
