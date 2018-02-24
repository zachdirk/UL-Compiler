package AST;
import Type.*;
public class EqualityExpression extends Expression {
	Expression expr1;
	Expression expr2;

	public EqualityExpression (Expression e1, Expression e2){
		expr1 = e1;
		expr2 = e2;
	}

	public Type accept(Visitor v){
		return(v.visit(this));
	}

	public String toString(){
		return (expr1.toString() + "==" + expr2.toString());
	}
}
