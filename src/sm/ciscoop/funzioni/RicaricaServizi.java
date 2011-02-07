package sm.ciscoop.funzioni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JTree;

import sm.ciscoop.gui.SingletonGUI;

public class RicaricaServizi implements ActionListener, ItemListener {
	private SingletonGUI c_gui = null;
	
	public RicaricaServizi(SingletonGUI gui) {
		c_gui = gui;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JTree tree = c_gui.getTree();
		try {
			Util.azzeraAlbero(tree);
			Util.ricaricaServizi(tree);

		}
		catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		System.out.println("prova");
	}

}
