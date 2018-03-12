package Type;
import AST.*;
import Visitor.*;
import IR.Temp.*;
public class ArrayType extends Type {

	public Type t;
	public IntegerLiteral i;
	public ArrayType (){
	
	}
	public ArrayType (Type t, IntegerLiteral i){
		this.t = t;
		this.i = i;
		this.lineNumber = t.lineNumber;
		this.offset = t.offset;
	}
	public String toString() {
		return t + "[" + i + "]";
	}
	public boolean equals (Object o) {
		return (o instanceof ArrayType);
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
