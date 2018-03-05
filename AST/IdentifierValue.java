package AST;
import Type.*;
import Temp.*;
import Visitor.*;
public class IdentifierValue extends Expression {
	public String id;
	public IdentifierValue(String s, int lineNumber, int offset){
		id = s;
		this.lineNumber = lineNumber;
		this.offset = offset;
	}
	public String toString(){
		return id;
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
