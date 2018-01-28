package AST;
public class BooleanLiteral extends Expression{
	boolean b;
	public BooleanLiteral(boolean b){
		this.b = b;
	}
	public String toString(){
		String s = "";
		if (b) 
			s = "true";
		else if (!b)
			s = "false";
		return(s);
	}
	public void accept(Visitor v){
		v.visit(this);
	}
}
