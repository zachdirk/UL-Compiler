package AST;
public class CharacterLiteral{
	char c;
	public CharacterLiteral(String s){
		c = s.charAt(0);
	}
	public String toString(){
		return "" + c;
	}
}
