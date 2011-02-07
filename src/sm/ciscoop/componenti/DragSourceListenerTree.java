package sm.ciscoop.componenti;

import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceContext;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;

public class DragSourceListenerTree implements DragSourceListener {

	@Override
	public void dragDropEnd(DragSourceDropEvent dsde) {
		
	}

	@Override
	public void dragEnter(DragSourceDragEvent dsde) {
		DragSourceContext context = dsde.getDragSourceContext();
		context.setCursor(DragSource.DefaultMoveDrop);
	}

	@Override
	public void dragExit(DragSourceEvent dse) {
		DragSourceContext context = dse.getDragSourceContext();
		context.setCursor(DragSource.DefaultMoveNoDrop);
	}

	@Override
	public void dragOver(DragSourceDragEvent dsde) {
		
	}

	@Override
	public void dropActionChanged(DragSourceDragEvent dsde) {
		
	}

}
