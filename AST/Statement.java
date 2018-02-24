package AST;
import Type.*;
public abstract class Statement extends ASTNode {
	public abstract Type accept(Visitor v);
}
