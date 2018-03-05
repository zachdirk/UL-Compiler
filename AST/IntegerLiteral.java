package AST;
import Type.*;
import Temp.*;
import Visitor.*;
public class IntegerLiteral extends Expression{
	public int i;
	public int lineNumber;
	public int offset;

	public IntegerLiteral(int i, int lineNumber, int offset){
		this.i = i;
		this.lineNumber = lineNumber;
		this.offset = offset;
	}
	public String toString(){
		return "" + i;
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
