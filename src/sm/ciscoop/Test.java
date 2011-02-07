package sm.ciscoop;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import sm.ciscoop.gui.SingletonGUI;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		scegliLookAndFeel();

		SwingUtilities.invokeLater( new Runnable() {
			@Override
			public void run() {
				SingletonGUI.getInstance();
			}
		});
	}

	private static void scegliLookAndFeel() {
        try {
        	try {
        	    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        	        if ("Nimbus".equals(info.getName())) {
        	            UIManager.setLookAndFeel(info.getClassName());
        	            break;
        	        }
        	    }
        	} 
        	catch (Exception e) {
        	}
		} 
		catch (Exception e) {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

}
