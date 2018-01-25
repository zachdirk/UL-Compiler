package AST;
public class PrintStatement extends Statement{
	
	Expression e;

	public PrintStatement(Expression e){
		this.e = e;
	}
	
	public String toString(){
		return "print(" + e + ")";
	}

	public void accept(Visitor v){
		v.visit(this);
	}
}
