package Type;
import AST.*;
import Visitor.*;
import IR.Temp.*;
public class CharType extends Type {
	public CharType(){
	
	}
	public CharType (int lineNumber, int offset){
		this.lineNumber = lineNumber;
		this.offset = offset;
	}
	public String toString() {
		return "char";
	}
	public boolean equals (Object o) {
		return (o instanceof CharType);
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
