package Type;
import AST.*;
import Visitor.*;
import Temp.*;
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
	public void acceptPrint(PrintVisitor v){
		v.visit(this);
	}
	public Type acceptSemantic(SemanticVisitor v){
		return(v.visit(this));
	}
	public Temp acceptTemp(TempVisitor v){
		return(v.visit(this));
	}
}
