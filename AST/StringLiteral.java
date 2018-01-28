package AST;
public class StringLiteral extends Expression{
	String s;
	public StringLiteral(String s){
		this.s = s;
	}
	public String toString(){
		return s;
	}
	public void accept(Visitor v){
		v.visit(this);
	}
}
