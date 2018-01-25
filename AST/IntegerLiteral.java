package AST;
public class IntegerLiteral{
	int i;
	public IntegerLiteral(int i){
		this.i = i;
	}
	public String toString(){
		return "" + i;
	}
}
