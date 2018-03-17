package AST;
import Type.*;
import IR.Temp.*;
import Visitor.*;
public class BooleanLiteral extends Expression{
	public boolean b;
	public int lineNumber;
	public int offset;

	public BooleanLiteral(boolean b, int lineNumber, int offset){
		this.b = b;
		this.lineNumber = lineNumber;
		this.offset = offset;
	}
	public String toString(){
		String s = "";
		if (b) 
			s = "TRUE";
		else if (!b)
			s = "FALSE";
		return(s);
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
