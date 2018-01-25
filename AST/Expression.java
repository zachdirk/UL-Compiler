package AST;
public abstract class Expression extends ASTNode {
	public abstract void accept (Visitor v);
}
