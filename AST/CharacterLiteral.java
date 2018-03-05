package AST;
import Type.*;
import Temp.*;
import Visitor.*;
public class CharacterLiteral extends Expression {
	public char c;
	public int lineNumber;
	public int offset;

	public CharacterLiteral(String s, int lineNumber, int offset){
		c = s.charAt(1);
		this.lineNumber = lineNumber;
		this.offset = offset;
	}
	public String toString(){
		return "'" + c + "'";
	}

	public void acceptPrint(PrintVisitor v){
		v.visit(this);
	}

	public Type acceptSemantic(SemanticVisitor v){
		return(v.visit(this));
	}

	public Temp acceptTemp(TempVisitor v){
		return(v.visit(this));
	}
}
