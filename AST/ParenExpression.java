package AST;
import Type.*;
public class ParenExpression extends Expression {
	Expression expr;

	public ParenExpression (Expression e){
		expr = e;
	}

	public Type accept(Visitor v){
		return(v.visit(this));
	}

	public String toString(){
		return ("(" + expr.toString() + ")");
	}
}
