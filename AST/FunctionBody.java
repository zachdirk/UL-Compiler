package AST;
import java.util.Vector;
import Type.*;
import Temp.*;
import Visitor.*;
public class FunctionBody extends ASTNode {
	
	public Vector<VariableDeclaration> varDecls;
	public Vector<Statement> statements;

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
