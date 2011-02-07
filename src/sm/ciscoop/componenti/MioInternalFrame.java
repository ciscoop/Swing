package sm.ciscoop.componenti;

import javax.swing.JInternalFrame;

public class MioInternalFrame extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	
	private static int openFrameCount = 0;
	private static final int xOffset = 30, yOffset = 30;

	public MioInternalFrame(String titolo) {
	    super(titolo,
	          true, //resizable
	          true, //closable
	          true, //maximizable
	          true);//iconifiable
	    
	    ++openFrameCount;
	    setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
	    
		setSize(700, 300);
	}

}
