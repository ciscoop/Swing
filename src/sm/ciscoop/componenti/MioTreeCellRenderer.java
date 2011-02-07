package sm.ciscoop.componenti;

import java.awt.Component;
import java.util.HashSet;
import java.util.Set;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

public class MioTreeCellRenderer extends DefaultTreeCellRenderer {
	private static final long serialVersionUID = 1L;
	private Set<Integer> c_setNotToRefresh = new HashSet<Integer>(); 
	private Icon c_redIcon = null;
	private Icon c_greenIcon = null;

	public MioTreeCellRenderer() {
		c_redIcon = new ImageIcon("resources/redX.png");
		c_greenIcon = new ImageIcon("resources/greenV.png");
	}

	@Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
    	super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
		Integer chiave = new Integer( value.hashCode() );
    	if (leaf ) {
    		setToolTipText("Sono un servizio web");
    	   	DefaultMutableTreeNodeFoglia nodoFoglia = (DefaultMutableTreeNodeFoglia) value;
    	   	WebServiceInfo wsInfo = (WebServiceInfo) nodoFoglia.getUserObject();
    	   	if( wsInfo.isOnline() ) {
    	   		setIcon(c_greenIcon);
    	   	}
    	   	else {
    	   		setIcon(c_redIcon);
    	   	}
    	   	c_setNotToRefresh.add(chiave);
		} 
		
    	return this;
	}
}
