package sm.ciscoop.componenti;

import java.awt.datatransfer.Transferable;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

public class TabbedPanePrincipale extends JTabbedPane implements DropTargetListener {

	private static final long serialVersionUID = 1L;
	
	public static final int TAB_WIDTH = 900;
	public static final int TAB_HEIGHT = 700;
	
	private Map<String, MioDesktopPane> c_mappa = new HashMap<String, MioDesktopPane>();
	
	
	public TabbedPanePrincipale() {
		super();
		
		DropTarget dropTarget = new DropTarget(this, this);
		setDropTarget(dropTarget);
	}

	public void aggiungiTab(String nomeTab) {
		aggiungiTab(nomeTab, TAB_WIDTH, TAB_HEIGHT);
	}
	
	public void aggiungiTab(String nomeTab, int larg, int lung) {
		if( c_mappa.get(nomeTab) == null ) {
			MioDesktopPane desktop = new MioDesktopPane();
			desktop.setSize(larg, lung);
			desktop.setVisible(true);
			c_mappa.put(nomeTab, desktop);
			
			super.addTab(nomeTab, desktop);
		}
	}

	
	@Override
	public void dragEnter(DropTargetDragEvent dtde) {
		System.out.println("Entrato un drag");
		
	}

	@Override
	public void dragExit(DropTargetEvent dte) {
		System.out.println("Uscito un drag");
		
	}

	@Override
	public void dragOver(DropTargetDragEvent dtde) {
		System.out.println("Drag over");
	}

	@Override
	public void drop(DropTargetDropEvent dtde) {
		System.out.println("Dropping");
		try {
			if (dtde.isDataFlavorSupported(WebServiceInfo.INFO_FLAVOR)) {
				dtde.acceptDrop(dtde.getDropAction());
				Transferable t = dtde.getTransferable();
				WebServiceInfo wsInfo = (WebServiceInfo) t.getTransferData(WebServiceInfo.INFO_FLAVOR);
				String szNomeApplicativo = wsInfo.getNomeApplicativo();
				//Se esiste non lo aggiunge
				aggiungiTab(szNomeApplicativo);
				//
				aggiungiFrameAndFocus(szNomeApplicativo, wsInfo.getNome());

				dtde.dropComplete(true);
			} 
			else {
				dtde.rejectDrop();
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void aggiungiFrameAndFocus(String nomeTab, String titoloFrame) {
		MioInternalFrame frameInterno = new MioInternalFrame(titoloFrame);
		frameInterno.setVisible(true);
		
		aggiungiFrame(nomeTab, frameInterno, true);
	}
	
	public void aggiungiFrame(String nomeTab, MioInternalFrame frameInterno, boolean focus) {
		MioDesktopPane desktop = null;
		if( (desktop = c_mappa.get(nomeTab)) != null ) {
			desktop.add(frameInterno);
			setSelectedComponent(desktop);
		}
		else {
			JOptionPane.showMessageDialog(null, "Non esiste il tab "+nomeTab, "Attenzione", JOptionPane.WARNING_MESSAGE);
		}
	}

	@Override
	public void dropActionChanged(DropTargetDragEvent dtde) {
		System.out.println("Drop action change");
	}


}
