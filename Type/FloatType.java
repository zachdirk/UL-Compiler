package Type;
import AST.*;
import Visitor.*;
import IR.Temp.*;
public class FloatType extends Type{
	public FloatType() {

	}
	public FloatType(int lineNumber, int offset){
		this.lineNumber = lineNumber;
		this.offset = offset;
	}
	public String toString() {
		return "float";
	}
	public boolean equals (Object o) {
		return (o instanceof FloatType);
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
