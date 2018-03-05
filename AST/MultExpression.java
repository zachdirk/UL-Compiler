package AST;
import Type.*;
import Temp.*;
import Visitor.*;
public class MultExpression extends Expression {
	public Expression expr1;
	public Expression expr2;

	public MultExpression (Expression e1, Expression e2){
		expr1 = e1;
		expr2 = e2;
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

	public String toString(){
		return (expr1.toString() + "*" + expr2.toString());
	}
}
