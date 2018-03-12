package AST;
import Type.*;
import IR.Temp.*;
import Visitor.*;
public class AddExpression extends Expression {
	public Expression expr1;
	public Expression expr2;

	public AddExpression (Expression e1, Expression e2){
		expr1 = e1;
		expr2 = e2;
		this.lineNumber = expr1.lineNumber;
		this.offset = expr1.offset;
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
		return (expr1.toString() + "+" + expr2.toString());
	}
}
