package AST;

import Type.*;
public class ReturnStatement extends Statement{
	Expression e;
	
	public ReturnStatement(Expression e){
		this.e = e;
		this.lineNumber = e.lineNumber;
		this.offset = e.offset;
	}

	public Type accept(Visitor v){
		return(v.visit(this));
	}


}
