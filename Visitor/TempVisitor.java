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

	public TempVisitor(){
		fEnv = new ListEnvironment<String, FunctionDeclaration>();
		vEnv = new ListEnvironment<String, Temp>();
		instr = null;
		temps = null;
	}
	
	private String IRType(Type t){
		String s = "";
		if (t instanceof BooleanType)
			s = "Z";
		if (t instanceof CharType)
			s = "C";
		if (t instanceof IntegerType)
			s = "I";
		if (t instanceof FloatType)
			s = "F";
		if (t instanceof StringType)
			s = "U";
		if (t instanceof VoidType)
			s = "V";
		if (t instanceof ArrayType){
			ArrayType t2 = (ArrayType)t;			
			s = "A" + IRType(t2.t);
		}
		return s;
	}

	public Temp visit(AddExpression e){
		return null;
	}
	public Temp visit(ArrayType t){
		return null;
	}
	public Temp visit(ArrayAssignment a){
		return null;
	}
	public Temp visit(ArrayReference a){
		return null;
	}
	public Temp visit(AssignmentStatement a){
		return null;
	}
	public Temp visit(Block b){
		return null;
	}
	public Temp visit(BooleanLiteral e){
		return null;
	}
	public Temp visit(BooleanType t){
		return null;
	}
	public Temp visit(CharacterLiteral e){
		return null;
	}
	public Temp visit(CharType t){
		return null;
	}
	public Temp visit(EqualityExpression e){
		return null;
	}
	public Temp visit(ExpressionStatement e){
		return null;
	}
	public Temp visit(FloatLiteral e){
		return null;
	}
	public Temp visit(FloatType t){
		return null;
	}
	public Temp visit(FormalParameter p){
		Type t = p.t;
		currentSignature = currentSignature + IRType(t);
		return null;
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
		return null;	
	}
	public Temp visit(FunctionCall f){
		return null;
	}
	public Temp visit(FunctionDeclaration f){
		currentFunction = f.id.id;
		Type returnType = f.t;
		String returnString = IRType(returnType);
		f.fpl.acceptTemp(this);
		currentSignature = currentSignature + ')' + returnString;
		return null;	
	}
	public IRFunction visit(Function f){
		temps = new TempList();
		instr = new Vector<IRInstruction>();
		f.fd.acceptTemp(this);
		//f.fb.acceptTemp(this);
		IRFunction irf = new IRFunction(currentFunction, currentSignature, instr, temps);
		return irf;
	}
	public Temp visit(Identifier id){
		return null;
	}
	public Temp visit(IdentifierValue id){
		return null;
	}
	public Temp visit(IfStatement s){
		return null;
	}
	public Temp visit(IntegerLiteral i){
		return null;
	}
	public Temp visit(IntegerType t){
		return null;
	}
	public Temp visit(LessThanExpression e){
		return null;
	}
	public Temp visit(MultExpression e){
		return null;
	}
	public Temp visit(ParenExpression e){
		return null;
	}
	public Temp visit(PrintStatement s){
		return null;
	}
	public Temp visit(PrintLnStatement s){
		return null;
	}
	public IRProgram visit(Program p, String name){
		IRProgram irp = new IRProgram(name);
		Vector v = p.functionList;
		Function f;
		IRFunction irf;
		for (int i = 0; i < v.size(); i++){
			f = (Function)v.get(i);
			irf = f.acceptTemp(this);
			irp.addElement(irf);
		}
		return irp;
	}
	public Temp visit(ReturnStatement s){
		return null;
	}
	public Temp visit(StringLiteral s){
		return null;
	}
	public Temp visit(SubtractExpression e){
		return null;
	}
	public Temp visit(StringType t){
		return null;
	}
	public Temp visit(VariableDeclaration s){
		return null;
	}
	public Temp visit(VoidType t){
		return null;
	}
	public Temp visit(WhileStatement s){
		return null;
	}
}
