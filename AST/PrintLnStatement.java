package AST;
public class PrintLnStatement extends Statement{
	
	Expression e;

	public PrintLnStatement(Expression e){
		this.e = e;
	}	

	public String toString(){
		return "println(" + e + ")";
	}

	public void accept(Visitor v){
		v.visit(this);
	}
}
