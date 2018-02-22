package Type;
import AST.*;
public class StringType extends Type {
	public StringType (){
		
	}
	public StringType (int lineNumber, int offset){
		this.lineNumber = lineNumber;
		this.offset = offset;
	}
	public String toString() {
		return "string";
	}
	public boolean equals (Object o){
		return (o instanceof StringType);
	}
	public void accept (Visitor v){
		v.visit(this);
	}
}
