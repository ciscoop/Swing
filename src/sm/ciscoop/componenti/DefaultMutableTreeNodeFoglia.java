package sm.ciscoop.componenti;

import javax.swing.tree.DefaultMutableTreeNode;

public class DefaultMutableTreeNodeFoglia extends DefaultMutableTreeNode {
	private static final long serialVersionUID = 1L;

	public DefaultMutableTreeNodeFoglia() {
		super();
	}

	public DefaultMutableTreeNodeFoglia(Object userObject) {
		super(userObject, true);
	}
	
	@Override
	public boolean isLeaf() {
		return true;
	}

	@Override
	public boolean getAllowsChildren() {
		return false;
	}
	
	
}
