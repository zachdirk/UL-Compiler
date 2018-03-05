package AST;
import Type.*;
import Temp.*;
import Visitor.*;
public class PrintLnStatement extends Statement{
	
	public Expression e;

	public PrintLnStatement(Expression e){
		this.e = e;
		this.lineNumber = e.lineNumber;
		this.offset = e.offset;
	}	

	public String toString(){
		return "println(" + e + ")";
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
