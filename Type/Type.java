package Type;
import AST.*;
import Visitor.*;
import IR.Temp.*;
public abstract class Type{
	public abstract void acceptPrint(PrintVisitor v);
	public abstract Type acceptSemantic(SemanticVisitor v);
	public abstract Temp acceptTemp(TempVisitor v);
	public abstract boolean equals (Object o);

	public int lineNumber;
	public int offset;

}
