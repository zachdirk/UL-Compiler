package AST;
public class FloatLiteral extends Expression{
	double f;
	public FloatLiteral(double f){
		this.f = f;
	}
	public String toString(){
		return "" + f;
	}
	public void accept(Visitor v){
		v.visit(this);
	}
}
