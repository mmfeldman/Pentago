package pentago;

public class Pentago {

	public static void main(String[] args) {
        System.out.println("ffff");
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrame_Interface().setVisible(true);
            }
        });

	}
	
	//...what is this?
	public static int[] section(int[] Array){
		return Array;
	}

}