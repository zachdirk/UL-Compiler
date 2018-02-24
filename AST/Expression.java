package AST;
import Type.*;
public abstract class Expression extends ASTNode {
	public abstract Type accept(Visitor v);
}
