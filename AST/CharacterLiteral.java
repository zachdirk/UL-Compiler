package AST;
public class CharacterLiteral extends Expression {
	char c;
	public CharacterLiteral(String s){
		c = s.charAt(1);
	}
	public String toString(){
		return "'" + c + "'";
	}
	public void accept(Visitor v){
		v.visit(this);
	}
}
