package AST;
import Type.*;
import Temp.*;
import Visitor.*;
public class FloatLiteral extends Expression{
	public double f;
	public int lineNumber;
	public int offset;

	public FloatLiteral(double f, int lineNumber, int offset){
		this.f = f;
		this.lineNumber = lineNumber;
		this.offset = offset;
	}
	public String toString(){
		return "" + f;
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
