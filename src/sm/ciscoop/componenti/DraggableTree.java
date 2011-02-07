package sm.ciscoop.componenti;

/*
 **  This is version II of DnDJTree. The first version allowed for what I 
 **  thought was a JDK oversight. However, we can set the cursor appropriately,
 **  relative to whether the current cursor location is a valid drop target.
 **
 **  If this is your first time reading the source code. Just ignore the above
 **  comment and ignore the "CHANGED" comments below. Otherwise, the 
 **  "CHANGED" comments will show where the code has changed.
 **
 **  Credit for finding this shortcoming in my code goes Laurent Hubert.
 **  Thanks Laurent.
 **
 **  Rob. [ rkenworthy@hotmail.com ]
 */

import java.awt.Frame;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragGestureRecognizer;
import java.awt.dnd.DragSource;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.event.InputEvent;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class DraggableTree extends JTree implements TreeSelectionListener, DragGestureListener {

	private static final long serialVersionUID = 1L;

	/** Stores the selected node info */
	protected TreePath SelectedTreePath = null;
	protected DefaultMutableTreeNode SelectedNode = null;

	/** Variables needed for DnD */
	private DragSource dragSource = null;

	/**
	 * Constructor
	 * 
	 * @param root
	 *            The root node of the tree
	 * @param parent
	 *            Parent JFrame of the JTree
	 */
	public DraggableTree(DefaultMutableTreeNode root, Frame parent) {
		super(root);

		addTreeSelectionListener(this);

		/*    ********************** CHANGED ********************** */
		dragSource = DragSource.getDefaultDragSource();
		/*    ****************** END OF CHANGE ******************** */

		DragGestureRecognizer dgr = dragSource
				.createDefaultDragGestureRecognizer(this, // DragSource
						DnDConstants.ACTION_COPY_OR_MOVE, // specifies valid
						// actions
						this // DragGestureListener
				);

		/**
		 * Eliminates right mouse clicks as valid actions - useful especially if
		 * you implement a JPopupMenu for the JTree
		 */
		dgr.setSourceActions(dgr.getSourceActions() & ~InputEvent.BUTTON3_MASK);

		/**
		 * First argument: Component to associate the target with Second
		 * argument: DropTargetListener
		 */
		// DropTarget dropTarget = new DropTarget(this, this);

		// unnecessary, but gives FileManager look
		putClientProperty("JTree.lineStyle", "Angled");
	}

	/** Returns The selected node */
	public DefaultMutableTreeNode getSelectedNode() {
		return SelectedNode;
	}

	// /////////////////////// Interface stuff ////////////////////

	/** DragGestureListener interface method */
	public void dragGestureRecognized(DragGestureEvent e) {
		// Get the selected node
		DefaultMutableTreeNode dragNode = getSelectedNode();
		if (dragNode != null && dragNode instanceof DefaultMutableTreeNodeFoglia ) {
			// Get the Transferable Object
			Transferable transferable = (Transferable) dragNode.getUserObject();
			/*    ********************** CHANGED ********************** */

			// Select the appropriate cursor;
//			Cursor cursor = DragSource.DefaultCopyNoDrop;
//			int action = e.getDragAction();
//			if (action == DnDConstants.ACTION_MOVE)
//				cursor = DragSource.DefaultMoveNoDrop;

			// In fact the cursor is set to NoDrop because once an action is
			// rejected
			// by a dropTarget, the dragSourceListener are no more invoked.
			// Setting the cursor to no drop by default is so more logical,
			// because
			// when the drop is accepted by a component, then the cursor is
			// changed by the
			// dropActionChanged of the default DragSource.
			/*    ****************** END OF CHANGE ******************** */

			// begin the drag
			DragSourceListenerTree dsl = new DragSourceListenerTree();
			dragSource.startDrag(e, DragSource.DefaultCopyNoDrop, transferable, dsl);
		}
	}


	/** DropTaregetListener interface method */
	public void dragEnter(DropTargetDragEvent e) {
	}

	/** DropTaregetListener interface method */
	public void dragExit(DropTargetEvent e) {
	}

	// /** DropTaregetListener interface method */
	// public void dragOver(DropTargetDragEvent e) {
	// /* ********************** CHANGED ********************** */
	// //set cursor location. Needed in setCursor method
	// Point cursorLocationBis = e.getLocation();
	// TreePath destinationPath =
	// getPathForLocation(cursorLocationBis.x, cursorLocationBis.y);
	//
	//
	// // if destination path is okay accept drop...
	// if (testDropTarget(destinationPath, SelectedTreePath) == null){
	// e.acceptDrag(DnDConstants.ACTION_COPY_OR_MOVE ) ;
	// }
	// // ...otherwise reject drop
	// else {
	// e.rejectDrag() ;
	// }
	// /* ****************** END OF CHANGE ******************** */
	// }

	// /** DropTaregetListener interface method */
	// public void dropActionChanged(DropTargetDragEvent e) {
	// }

	/** TreeSelectionListener - sets selected node */
	public void valueChanged(TreeSelectionEvent evt) {
		SelectedTreePath = evt.getNewLeadSelectionPath();
		if (SelectedTreePath == null) {
			SelectedNode = null;
			return;
		}
		SelectedNode = (DefaultMutableTreeNode) SelectedTreePath.getLastPathComponent();
	}

	// /** Convenience method to test whether drop location is valid
	// @param destination The destination path
	// @param dropper The path for the node to be dropped
	// @return null if no problems, otherwise an explanation
	// */
	// private String testDropTarget(TreePath destination, TreePath dropper) {
	// //Typical Tests for dropping
	//	 
	// //Test 1.
	// boolean destinationPathIsNull = destination == null;
	// if (destinationPathIsNull)
	// return "Invalid drop location.";
	//
	// //Test 2.
	// PersonNode node = (PersonNode) destination.getLastPathComponent();
	// if ( !node.getAllowsChildren() )
	// return "This node does not allow children";
	//
	// if (destination.equals(dropper))
	// return "Destination cannot be same as source";
	//
	// //Test 3.
	// if ( dropper.isDescendant(destination))
	// return "Destination node cannot be a descendant.";
	//
	// //Test 4.
	// if ( dropper.getParentPath().equals(destination))
	// return "Destination node cannot be a parent.";
	//
	// return null;
	// }

}
