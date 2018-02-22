package AST;
public class CharacterLiteral extends Expression {
	char c;
	int lineNumber;
	int offset;

	public CharacterLiteral(String s, int lineNumber, int offset){
		c = s.charAt(1);
		this.lineNumber = lineNumber;
		this.offset = offset;
	}
	public String toString(){
		return "'" + c + "'";
	}
	public void accept(Visitor v){
		v.visit(this);
	}
}
