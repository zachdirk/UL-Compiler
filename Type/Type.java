package Type;
import AST.*;
public abstract class Type{
	public abstract void accept (Visitor v);
	public abstract boolean equals (Object o);

	public int lineNumber;
	public int offset;

}
