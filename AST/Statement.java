package AST;
public abstract class Statement extends ASTNode {
	public abstract void accept (Visitor v);
}
