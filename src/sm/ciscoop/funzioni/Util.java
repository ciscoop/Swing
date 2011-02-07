package sm.ciscoop.funzioni;

import java.io.InputStream;
import java.net.URL;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import sm.ciscoop.gui.SingletonGUI;

public class Util {
	
	public static synchronized void ricaricaServizi(JTree tree) throws Exception{
		URL url = new URL(SingletonGUI.URL_SERVIZI);
		InputStream io = url.openStream();
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
		
		saxParser.parse( io, new SAXHandler(tree) );
	}

	public static void azzeraAlbero(JTree tree) throws Exception {
		DefaultTreeModel modello = (DefaultTreeModel) tree.getModel();
		MutableTreeNode radice = null;
		if( (radice = (MutableTreeNode) modello.getRoot()) == null ) {
			throw new Exception("Errore la radice dell'albero non esiste!");
		}
		int numeroFigli = radice.getChildCount();
		for( int i = 0; i < numeroFigli ; i++ ) {
			//0 perchè l'indice si sposta sempre alla posizione 0 rimuovendo
			radice.remove(0);
		}
		//Per rinfrescare l'albero
		modello.setRoot(radice);
		tree.setModel(modello);
	}
}
