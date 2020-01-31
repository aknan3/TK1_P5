import java.io.Serializable;

public class Airplane implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//private String name;
	private int number;
	
	public Airplane(int number) {
	//	this.name = name;
		this.number = number;
	}

//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
}
