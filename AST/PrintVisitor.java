package AST;
import java.util.Vector;
import Type.*;
public class PrintVisitor implements Visitor {
	
	private int indent;
	
	public PrintVisitor(){
		indent = 0;
	}
	
	private String tabs(){
		String s = "";
		for (int i = 0; i < indent; i++){
			s = s + "    ";
		}
		return(s);
	}	
	public void visit(AddExpression e){
		System.out.print(e.expr1 + "+" + e.expr2);	
	}
	public void visit(ArrayType t){
		System.out.print(t);	
	}
	public void visit(ArrayAssignment a){
		System.out.print("\n" + tabs());		
		a.id.accept(this);
		System.out.print("[");
		a.index.accept(this);
		System.out.print("]=");
		a.e.accept(this);
		System.out.print(";");
	}
	public void visit(ArrayReference a){
		System.out.print(a);
	}
	public void visit(AssignmentStatement a){
		System.out.print("\n" + tabs());
		a.id.accept(this);
		System.out.print("=");
		a.expr.accept(this);
		System.out.print(";");
	}
	public void visit(Block b){
		System.out.print(tabs() + "{");
		indent++;
		Statement s = null;
		for (int i = 0; i < b.size(); i++){
			s = b.elementAt(i);
			s.accept(this);
		}
		indent--;
		System.out.print("\n" + tabs() + "}");
	}
	public void visit(BooleanLiteral e){
		System.out.print(e);
	}
	public void visit(BooleanType t){
		System.out.print(t);
	}
	public void visit(CharacterLiteral e){
		System.out.print(e);
	}
	public void visit(CharType t){
		System.out.print(t);
	}
	public void visit(EqualityExpression e){
		System.out.print(e.expr1 + "==" + e.expr2);
	}
	public void visit(ExpressionStatement e){
		System.out.print("\n" + tabs());		
		e.e.accept(this);
		System.out.print(";");
	}
	public void visit(FloatLiteral e){
		System.out.print(e);
	}
	public void visit(FloatType t){
		System.out.print(t);
	}
	public void visit(FormalParameter p){
		p.t.accept(this);
		System.out.print(" ");
		p.id.accept(this);
	}
	public void visit(FormalParameterList p){
		System.out.print("(");
		Vector params = p.parameterList;
		FormalParameter fp;
		if (params.size() > 0){
			fp = (FormalParameter)params.get(0);
			fp.accept(this);
		}
		for (int i = 1; i < params.size(); i++){
			System.out.print(", ");
			fp = (FormalParameter)params.get(i);
			fp.accept(this);
		}
		System.out.print(")");
	}
	public void visit(FunctionBody f){
		System.out.print("\n{");
		indent++;
		Vector v = f.varDecls;
		if (v.size() > 0){
			VariableDeclaration vd = (VariableDeclaration)v.get(0);
			vd.accept(this);
		}
		for (int i = 1; i < v.size(); i++){
			VariableDeclaration vd = (VariableDeclaration)v.get(i);
			vd.accept(this);
		}
		if (v.size() > 0)		
			System.out.println(); //if there were vardecls we have to separate them from statements
		v = f.statements;
		if (v.size() > 0){
			Statement s = (Statement)v.get(0);
			s.accept(this);
		}
		for (int i = 1; i < v.size(); i++){
			Statement s = (Statement)v.get(i);
			s.accept(this);
		} 
		System.out.println("\n}");
		indent--;	
	}
	public void visit(FunctionCall f){
		f.id.accept(this);
		System.out.print("(");
		if (f.v.size() > 0) {
			Expression e = (Expression)f.v.get(0);
			e.accept(this);
		}
		for (int i = 1; i < f.v.size(); i++){
			System.out.print(",");
			Expression e = (Expression)f.v.get(i);
			e.accept(this);
			
		}
		System.out.print(")");
	}
	public void visit(FunctionDeclaration f){
		f.t.accept(this);
		System.out.print(" ");
		f.id.accept(this);
		System.out.print(" ");
		f.fpl.accept(this);	
	}
	public void visit(Function f){
		f.fd.accept(this);
		f.fb.accept(this);
	}
	public void visit(Identifier id){
		System.out.print(id.id);
	}
	public void visit(IdentifierValue id){
		System.out.print(id.id);
	}
	public void visit(IfStatement s){
		System.out.print("\n" + tabs() + "if (");
		s.condition.accept(this);
		System.out.print(")\n");
		s.b1.accept(this);
		if (s.b2 != null){
			System.out.print("\n" + tabs() + "else\n");
			s.b2.accept(this);
		}
	}
	public void visit(IntegerLiteral i){
		System.out.print(i);
	}
	public void visit(IntegerType t){
		System.out.print(t);
	}
	public void visit(LessThanExpression e){
		System.out.print(e.expr1 + "<" + e.expr2);
	}
	public void visit(MultExpression e){
		System.out.print(e.expr1 + "*" + e.expr2);
	}
	public void visit(ParenExpression e){
		System.out.print("(" + e.expr + ")");
	}
	public void visit(PrintStatement s){
		System.out.print("\n" + tabs() + "print ");
		s.e.accept(this);
		System.out.print(";");
	}
	public void visit(PrintLnStatement s){
		System.out.print("\n" + tabs() + "println ");
		s.e.accept(this);
		System.out.print(";");
	}
	public void visit(Program p){
		Vector v = p.functionList;
		Function f;		
		if (v.size() > 0){
			f = (Function)v.get(0);
			f.accept(this);
		}
		for (int i = 1; i < v.size(); i++){
			System.out.println();
			f = (Function)v.get(i);			
			f.accept(this);
		}
	}
	public void visit(ReturnStatement s){
		System.out.print("\n" + tabs() + "return");
		if (s.e != null){
			System.out.print(" ");
			s.e.accept(this);
		}
		System.out.print(";");
	}
	public void visit(StringLiteral s){
		System.out.print(s);
	}
	public void visit(SubtractExpression e){
		System.out.print(e.expr1 + "-" + e.expr2);
	}
	public void visit(StringType t){
		System.out.print(t);
	}
	public void visit(VariableDeclaration s){
		System.out.print("\n" + tabs());
		s.t.accept(this);
		System.out.print(" ");
		s.id.accept(this);
		System.out.print(";");
		
	}
	public void visit(VoidType t){
		System.out.print(t);
	}
	public void visit(WhileStatement s){
		System.out.print("\n" + tabs() + "while (");
		s.condition.accept(this);
		System.out.print(")\n");
		s.b.accept(this);
	}
}
