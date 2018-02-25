package AST;
import Type.*;
public class PrintLnStatement extends Statement{
	
	Expression e;

	public PrintLnStatement(Expression e){
		this.e = e;
		this.lineNumber = e.lineNumber;
		this.offset = e.offset;
	}	

	public String toString(){
		return "println(" + e + ")";
	}

	public Type accept(Visitor v){
		return(v.visit(this));
	}


}
