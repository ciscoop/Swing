package sm.ciscoop.componenti;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class Scheda {
	
	private MioInternalFrame c_mioInternalFrame = null;
	private JTabbedPane c_tabbedPane = null;
	
	
	public Scheda(String titolo) {
		c_mioInternalFrame = new MioInternalFrame(titolo);
		c_tabbedPane = new JTabbedPane();
		
		c_tabbedPane.addTab("Storico", new JScrollPane());
		c_tabbedPane.addTab("Principale", new JScrollPane());
		c_tabbedPane.addTab("Processi", new JScrollPane());
		
	}
	

}
