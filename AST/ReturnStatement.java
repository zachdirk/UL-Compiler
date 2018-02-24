package AST;

import Type.*;
public class ReturnStatement extends Statement{
	Expression e;
	
	public ReturnStatement(Expression e){
		this.e = e;
	}

	public Type accept(Visitor v){
		return(v.visit(this));
	}


}
