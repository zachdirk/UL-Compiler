package AST;
import Type.*;
import Temp.*;
import Visitor.*;
public abstract class Statement extends ASTNode {
	public abstract void acceptPrint(PrintVisitor v);
	public abstract Type acceptSemantic(SemanticVisitor v);
	public abstract Temp acceptTemp(TempVisitor v);
}
