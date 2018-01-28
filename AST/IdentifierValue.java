package AST;
public class IdentifierValue extends Expression {
	String id;
	public IdentifierValue(String s){
		id = s;
	}
	public String toString(){
		return id;
	}
	public void accept(Visitor v){
		v.visit(this);
	}
}
