package AST;
import java.util.Vector;

public class FunctionBody extends ASTNode {
	
	Vector<VariableDeclaration> varDecls;
	Vector<Statement> statements;

	public FunctionBody(){
		varDecls = new Vector<VariableDeclaration>();
		statements = new Vector<Statement>();
	}

	public void addVarDecl(VariableDeclaration vd){
		varDecls.addElement(vd);	
	}
	
	public void addStatement(Statement s){
		statements.addElement(s);
	}

	public VariableDeclaration getVarDecl(int index){
		return (VariableDeclaration)varDecls.get(index);
	}

	public Statement getStatement(int index){
		return (Statement)statements.get(index);
	}

	public int numStatements(){
		return statements.size();
	}

	public int numVarDecls(){
		return varDecls.size();
	}

	public void accept(Visitor v){
		v.visit(this);
	}

}
