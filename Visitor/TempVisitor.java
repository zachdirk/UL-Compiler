package Visitor;
import java.util.Vector;
import IR.*;
import IR.Temp.*;
import AST.*;
import Type.*;
import Environment.*;
public class TempVisitor{

	ListEnvironment<String, FunctionDeclaration> fEnv;
	ListEnvironment<String, Temp> vEnv;

	String currentSignature;
	String currentFunction;
	Vector<IRInstruction> instr;
	TempList temps;
	int labelCount;

	public TempVisitor(){
		fEnv = new ListEnvironment<String, FunctionDeclaration>();
		vEnv = new ListEnvironment<String, Temp>();
		instr = null;
		temps = null;
	}

	
	
	public Temp visit(AddExpression e){
		Temp lhs = e.expr1.acceptTemp(this);
		Temp rhs = e.expr2.acceptTemp(this);
		Temp dest = temps.getTemp(lhs.type);
		IRInstruction ir = new IRBinaryOp(dest, lhs, rhs, IRBinaryEnum.ADD);
		instr.add(ir);
		return(dest);
	}
	public Temp visit(ArrayType t){
		return null;
	}
	public Temp visit(ArrayAssignment a){
		String name = a.id.id;
		Temp var = vEnv.lookup(name);		
		Temp index = a.index.acceptTemp(this);
		Temp expr = a.e.acceptTemp(this);
		IRInstruction ir = new IRArrayAssignment(var, index, expr);
		instr.add(ir);
		return var;
	}
	public Temp visit(ArrayReference a){
		String name = a.id.id;
		Temp var = vEnv.lookup(name);
		Temp expr = a.e.acceptTemp(this);
		ArrayType at = (ArrayType)var.type;
		Type t = at.t;
		Temp result = temps.getTemp(t);
		IRInstruction ir = new IRArrayReference(result, var, expr);
		instr.add(ir);
		return result;
	}
	public Temp visit(AssignmentStatement a){
		Temp t1 = temps.findTemp(a.id.id);
		Temp t2 = a.expr.acceptTemp(this);
		IRInstruction ir = new IRAssignment(t1, t2);
		instr.add(ir);	
		return t1;
	}
	public Temp visit(Block b){
		Vector<Statement> s = b.statements;
		Statement st = null;		
		for (int i = 0; i < s.size(); i++){
			st = s.get(i);
			st.acceptTemp(this);
		}	
		return(null);
	}
	public Temp visit(BooleanLiteral e){
		Temp tmp = temps.getTemp(new BooleanType());
		IRInstruction ir = new IRExpressionAssignment(tmp, e);
		instr.add(ir);
		return tmp;
	}
	public Temp visit(BooleanType t){
		return(null);
	}
	public Temp visit(CharacterLiteral e){
		Temp tmp = temps.getTemp(new CharType());
		IRInstruction ir = new IRExpressionAssignment(tmp, e);
		instr.add(ir);
		return tmp;
	}
	public Temp visit(CharType t){
		return(null);
	}
	public Temp visit(EqualityExpression e){
		Temp lhs = e.expr1.acceptTemp(this);
		Temp rhs = e.expr2.acceptTemp(this);
		Temp dest = temps.getTemp(new BooleanType());
		IRInstruction ir = new IRBinaryOp(dest, lhs, rhs, IRBinaryEnum.EQUALS);
		instr.add(ir);
		return(dest);
	}
	public Temp visit(ExpressionStatement e){
		Temp tmp = e.e.acceptTemp(this);
		return tmp;
	}
	public Temp visit(FloatLiteral e){
		Temp tmp = temps.getTemp(new FloatType());
		IRInstruction ir = new IRExpressionAssignment(tmp, e);
		instr.add(ir);
		return tmp;
	}
	public Temp visit(FloatType t){
		return(null);
	}
	public Temp visit(FormalParameter p){
		Type t = p.t;
		String name = p.id.id;
		currentSignature = currentSignature + Temp.IRType(t);
		Temp tmp = temps.getTemp(t, TempClass.PARAMETER, name);
		vEnv.add(name, tmp);
		return tmp;
	}
	public Temp visit(FormalParameterList p){
		FormalParameter fp;
		currentSignature = "(";
		for (int i = 0; i < p.parameterList.size(); i++){
			fp = p.parameterList.get(i);
			fp.acceptTemp(this);
		}
		return null;
	}
	public Temp visit(FunctionBody f){
		Vector<VariableDeclaration> v = f.varDecls;
		VariableDeclaration vd = null;		
		for (int i = 0; i < v.size(); i++){
			vd = v.get(i);
			vd.acceptTemp(this);
		}
		Vector<Statement> s = f.statements;
		Statement st = null;		
		for (int i = 0; i < s.size(); i++){
			st = s.get(i);
			st.acceptTemp(this);
		}			
		return null;	
	}
	public Temp visit(FunctionCall f){
		Identifier id = f.id;
		String name = id.id;
		Type funcType = fEnv.lookup(name).t;
		Vector<Expression> v = f.v;
		Vector<Temp> v2 = new Vector<Temp>();
		Expression e;
		Temp tmp;
		for (int i = 0; i < v.size(); i++){
			e = (Expression)v.get(i);
			tmp = e.acceptTemp(this);
			v2.add(tmp);
		}
		Temp ret = null;
		IRInstruction ir = null;
		IRCallStatement ic = new IRCallStatement(name, v2);
		if (!(funcType instanceof VoidType)){
			ret = temps.getTemp(funcType);
			ir = new IRCallAssignment(ret, ic);
		} else {
			ir = (IRInstruction)ic;
		}
		instr.add(ir);
		return ret;
	}
	public Temp visit(FunctionDeclaration f){
		currentFunction = f.id.id;
		Type returnType = f.t;
		String returnString = Temp.IRType(returnType);
		f.fpl.acceptTemp(this);
		currentSignature = currentSignature + ')' + returnString;
		return null;	
	}
	public IRFunction visit(Function f){
		vEnv.beginScope();
		labelCount = 0;
		temps = new TempList();
		instr = new Vector<IRInstruction>();
		f.fd.acceptTemp(this);
		f.fb.acceptTemp(this);
		IRFunction irf = new IRFunction(currentFunction, currentSignature, instr, temps);
		vEnv.endScope();
		return irf;
	}
	public Temp visit(Identifier id){
		Temp tmp = vEnv.lookup(id.id);
		return tmp;
	}
	public Temp visit(IdentifierValue id){
		Temp tmp = vEnv.lookup(id.id);
		return tmp;
	}
	public Temp visit(IfStatement s){
		IRInstruction ir;
		IRLabel l1 = new IRLabel(labelCount++);
		IRLabel l2 = new IRLabel(labelCount++);	
		Temp t = s.condition.acceptTemp(this);
		Temp t2 = temps.getTemp(new BooleanType());
		ir = new IRAssignment(t2, t);	
		instr.add(ir);		
		t = t2;
		ir = new IRUnaryOp(t, t, IRUnaryEnum.BOOLNEGATE);
		instr.add(ir);
		ir = new IRIFStatement(t, l1);
		instr.add(ir);
		s.b1.acceptTemp(this);
		ir = new IRGOTOStatement(l2);
		instr.add(ir);
		instr.add(l1);
		if (s.b2 != null)
			s.b2.acceptTemp(this);
		instr.add(l2);
		return(t);
	}
	public Temp visit(IntegerLiteral i){
		Temp tmp = temps.getTemp(new IntegerType());
		IRInstruction ir = new IRExpressionAssignment(tmp, i);
		instr.add(ir);
		return tmp;
	}
	public Temp visit(IntegerType t){
		return(null);
	}
	public Temp visit(LessThanExpression e){
		Temp lhs = e.expr1.acceptTemp(this);
		Temp rhs = e.expr2.acceptTemp(this);
		Temp dest = temps.getTemp(new BooleanType());
		IRInstruction ir = new IRBinaryOp(dest, lhs, rhs, IRBinaryEnum.LESS);
		instr.add(ir);
		return(dest);
	}
	public Temp visit(MultExpression e){
		Temp lhs = e.expr1.acceptTemp(this);
		Temp rhs = e.expr2.acceptTemp(this);
		Temp dest = temps.getTemp(lhs.type);
		IRInstruction ir = new IRBinaryOp(dest, lhs, rhs, IRBinaryEnum.MULTIPLY);
		instr.add(ir);
		return(dest);
	}
	public Temp visit(ParenExpression e){
		Temp tmp = e.expr.acceptTemp(this);
		return tmp;
	}
	public Temp visit(PrintStatement s){
		Temp tmp = s.e.acceptTemp(this);
		IRInstruction ir = new IRPrintStatement(tmp);
		instr.add(ir);	
		return tmp;
	}
	public Temp visit(PrintLnStatement s){
		Temp tmp = s.e.acceptTemp(this);
		IRInstruction ir = new IRPrintLnStatement(tmp);
		instr.add(ir);	
		return tmp;
	}
	public IRProgram visit(Program p, String name){
		IRProgram irp = new IRProgram(name);
		Vector v = p.functionList;
		Function f;
		//build function environment first
		for (int i = 0; i < v.size(); i++){			
			f = (Function)v.get(i);
			String id = f.fd.id.id;			
			fEnv.add(id, f.fd);
		}
		IRFunction irf;
		for (int i = 0; i < v.size(); i++){
			f = (Function)v.get(i);
			irf = f.acceptTemp(this);
			irp.addElement(irf);
		}
		return irp;
	}
	public Temp visit(ReturnStatement s){
		Temp tmp = s.e.acceptTemp(this);
		IRInstruction ir = new IRReturnStatement(tmp);
		instr.add(ir);
		return tmp;
	}
	public Temp visit(StringLiteral s){
		Temp tmp = temps.getTemp(new StringType());
		IRInstruction ir = new IRExpressionAssignment(tmp, s);
		instr.add(ir);
		return tmp;
	}
	public Temp visit(StringType t){
		return(null);
	}
	public Temp visit(SubtractExpression e){
		Temp lhs = e.expr1.acceptTemp(this);
		Temp rhs = e.expr2.acceptTemp(this);
		Temp dest = temps.getTemp(lhs.type);
		IRInstruction ir = new IRBinaryOp(dest, lhs, rhs, IRBinaryEnum.SUBTRACT);
		instr.add(ir);
		return(dest);
	}
	public Temp visit(VariableDeclaration s){
		Type t = s.t;
		String name = s.id.id;
		Temp tmp = temps.getTemp(t, TempClass.LOCAL, name);
		vEnv.add(name, tmp);
		if (t instanceof ArrayType){
			ArrayType at = (ArrayType)t;
			IRInstruction ir = new IRArrayDeclaration(tmp, at.i);
			instr.add(ir);
		}
		return tmp;
	}
	public Temp visit(VoidType t){
		return(null);
	}
	public Temp visit(WhileStatement s){
		IRInstruction ir;
		IRLabel l1 = new IRLabel(labelCount++);
		IRLabel l2 = new IRLabel(labelCount++);
		instr.add(l1);	
		Temp t = s.condition.acceptTemp(this);
		Temp t2 = temps.getTemp(new BooleanType());
		ir = new IRAssignment(t2, t);
			
		instr.add(ir);		
		t = t2;
		
		ir = new IRUnaryOp(t, t, IRUnaryEnum.BOOLNEGATE);
		instr.add(ir);
		
		ir = new IRIFStatement(t, l2);
		instr.add(ir);
		s.b.acceptTemp(this);
		ir = new IRGOTOStatement(l1);
		instr.add(ir);
		instr.add(l2);
		return(t);
	}
}
