package pentago;

public class Marble {

	private String mode;

	public Marble () {
		this.mode = null;
	}
	
	public Marble (String mode) {
		this.mode = mode;
	}
	
	public void setMode(String mode) {
		this.mode = mode; 
	}
	
	public String getMode() {
		return mode;
	}
}