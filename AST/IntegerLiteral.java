package AST;
import Type.*;
public class IntegerLiteral extends Expression{
	int i;
	int lineNumber;
	int offset;

	public IntegerLiteral(int i, int lineNumber, int offset){
		this.i = i;
		this.lineNumber = lineNumber;
		this.offset = offset;
	}
	public String toString(){
		return "" + i;
	}

	public Type accept(Visitor v){
		return(v.visit(this));
	}

}
