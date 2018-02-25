package AST;
import Type.*;
public class ParenExpression extends Expression {
	Expression expr;

	public ParenExpression (Expression e){
		expr = e;
		this.lineNumber = expr.lineNumber;
		this.offset = expr.offset;
	}

	public Type accept(Visitor v){
		return(v.visit(this));
	}

	public String toString(){
		return ("(" + expr.toString() + ")");
	}
}
