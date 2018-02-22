package AST;
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
	public void accept(Visitor v){
		v.visit(this);
	}
}
