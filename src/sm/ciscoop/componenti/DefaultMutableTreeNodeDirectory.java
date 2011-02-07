package sm.ciscoop.componenti;

import javax.swing.tree.DefaultMutableTreeNode;

public class DefaultMutableTreeNodeDirectory extends DefaultMutableTreeNode {
	private static final long serialVersionUID = 1L;

	public DefaultMutableTreeNodeDirectory() {
		super();
	}

	public DefaultMutableTreeNodeDirectory(Object userObject) {
		super(userObject, true);
	}


	@Override
	public boolean isLeaf() {
		return false;
	}

	@Override
	public boolean getAllowsChildren() {
		return true;
	}
}
