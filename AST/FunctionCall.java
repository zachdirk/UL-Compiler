package AST;
import Type.*;
import java.util.Vector;
import IR.Temp.*;
import Visitor.*;
public class FunctionCall extends Expression{
	public Identifier id;
	public Vector<Expression> v;

	public FunctionCall(Identifier id, Vector<Expression> v){
		this.id = id;
		this.v = v;
		this.lineNumber = id.lineNumber;
		this.offset = id.offset;
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
