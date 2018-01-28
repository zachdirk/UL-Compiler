package AST;
public class MultExpression extends Expression {
	Expression expr1;
	Expression expr2;

	public MultExpression (Expression e1, Expression e2){
		expr1 = e1;
		expr2 = e2;
	}

	public void accept (Visitor v){
		v.visit(this);
	}

	public String toString(){
		return (expr1.toString() + "*" + expr2.toString());
	}
}
