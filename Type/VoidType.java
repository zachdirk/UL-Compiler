package Type;
import AST.*;
import Visitor.*;
import IR.Temp.*;
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
