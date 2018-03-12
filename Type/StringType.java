package Type;
import AST.*;
import Visitor.*;
import IR.Temp.*;
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
