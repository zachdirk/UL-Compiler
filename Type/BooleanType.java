package Type;
import AST.*;
import Visitor.*;
import Temp.*;
public class BooleanType extends Type {
	public BooleanType () {

	}
	public BooleanType (int lineNumber, int offset){
		this.lineNumber = lineNumber;
		this.offset = offset;
	}
	public String toString() {
		return "boolean";
	}
	public boolean equals (Object o) {
		return (o instanceof BooleanType);
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
