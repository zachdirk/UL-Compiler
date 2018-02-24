package AST;
import Type.*;
public class FloatLiteral extends Expression{
	double f;
	int lineNumber;
	int offset;

	public FloatLiteral(double f, int lineNumber, int offset){
		this.f = f;
		this.lineNumber = lineNumber;
		this.offset = offset;
	}
	public String toString(){
		return "" + f;
	}

	public Type accept(Visitor v){
		return(v.visit(this));
	}

}
