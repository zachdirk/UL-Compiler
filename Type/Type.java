package Type;
import AST.*;
public abstract class Type{
	public abstract Type accept (Visitor v);
	public abstract boolean equals (Object o);

	public int lineNumber;
	public int offset;

}
