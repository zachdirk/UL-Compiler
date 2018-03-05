package AST;
import Type.*;
import Temp.*;
import Visitor.*;
public class StringLiteral extends Expression{
	public String s;
	public int lineNumber;
	public int offset;

	public StringLiteral(String s, int lineNumber, int offset){
		this.s = s;
		this.lineNumber = lineNumber;
		this.offset = offset;
	}
	public String toString(){
		return s;
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
