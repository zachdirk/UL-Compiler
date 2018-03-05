package AST;
import Type.*;
import Temp.*;
import Visitor.*;
public class ParenExpression extends Expression {
	public Expression expr;

	public ParenExpression (Expression e){
		expr = e;
		this.lineNumber = expr.lineNumber;
		this.offset = expr.offset;
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
		return ("(" + expr.toString() + ")");
	}
}
