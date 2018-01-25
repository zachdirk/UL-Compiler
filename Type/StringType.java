package Type;
import AST.*;
public class StringType extends Type {
	public StringType (){
		
	}
	public String toString() {
		return "String";
	}
	public boolean equals (Object o){
		return (o instanceof StringType);
	}
	public void accept (Visitor v){
		v.visit(this);
	}
}
