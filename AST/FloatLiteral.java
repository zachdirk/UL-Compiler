package AST;
public class FloatLiteral{
	double f;
	public FloatLiteral(double f){
		this.f = f;
	}
	public String toString(){
		return "" + f;
	}
}
