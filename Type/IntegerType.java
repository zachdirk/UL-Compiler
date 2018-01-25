package Type;
import AST.*;
public class IntegerType extends Type{
	public IntegerType(){
	
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
