package AST;
public class SemanticException extends RuntimeException{

	String msg;	
		
	public SemanticException(){

	}
	public SemanticException(String msg){	
		this.msg = msg;	
	}
	public String getMessage(){
		return(msg);
	}
	public String toString(){
		return(msg);
	}
}
