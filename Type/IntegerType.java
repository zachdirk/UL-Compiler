package Type;
import AST.*;
public class IntegerType extends Type{
	public IntegerType(){
	
	}
	public IntegerType(int lineNumber, int offest){
		this.lineNumber = lineNumber;
		this.offset = offest;
	}
	public String toString(){
		return "int";
	}
	public boolean equals (Object o){
		return (o instanceof IntegerType);
	}
	public void accept (Visitor v){
		v.visit(this);
	}
}
