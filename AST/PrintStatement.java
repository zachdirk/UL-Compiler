package AST;
import Type.*;
public class PrintStatement extends Statement{
	
	Expression e;

	public PrintStatement(Expression e){
		this.e = e;
	}
	
	public String toString(){
		return "print(" + e + ")";
	}

	public Type accept(Visitor v){
		return(v.visit(this));
	}

}
