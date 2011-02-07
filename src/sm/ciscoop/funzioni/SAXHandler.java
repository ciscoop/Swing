package sm.ciscoop.funzioni;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import sm.ciscoop.componenti.DefaultMutableTreeNodeDirectory;
import sm.ciscoop.componenti.DefaultMutableTreeNodeFoglia;
import sm.ciscoop.componenti.WebServiceInfo;

public class SAXHandler extends DefaultHandler {
	private JTree c_tree = null;
	private DefaultTreeModel c_modello = null;
	private DefaultMutableTreeNode c_radice = null;
	private Map<String, DefaultMutableTreeNode> c_mappaApplicativi = null;
	
	private Stack<String> c_stack = null;
	private String c_currentTag = null;
	private String c_currentApplicativo = null;
	private WebServiceInfo c_currentServizio = null;
	
	public SAXHandler(JTree tree) {
		c_mappaApplicativi = new HashMap<String, DefaultMutableTreeNode>();
		c_stack = new Stack<String>();
		c_tree = tree;
		c_modello = (DefaultTreeModel) c_tree.getModel();
		c_radice = (DefaultMutableTreeNode) c_modello.getRoot();
	}

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
	}

	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
		refreshTree();
		c_tree = null;
		c_modello = null;
		c_radice = null;
	}
	
	private void refreshTree() {
		c_modello.setRoot(c_radice);
		c_tree.setModel(c_modello);
	}


	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		
		c_stack.push(qName);
		c_currentTag = qName;
		
		if(qName.equals("webservice")) {
			c_currentApplicativo = attributes.getValue("applicativo");
			if( c_mappaApplicativi.get(c_currentApplicativo) == null ) {
				inserisciNodoApplicativo(c_currentApplicativo);
			}
			c_currentServizio = new WebServiceInfo();
			c_currentServizio.setNomeApplicativo(c_currentApplicativo);
		}

	}

	private void inserisciNodoApplicativo(String szApplicativo) {
		DefaultMutableTreeNodeDirectory nodoApplicativo = new DefaultMutableTreeNodeDirectory(szApplicativo);
		c_radice.add(nodoApplicativo);
		c_mappaApplicativi.put(szApplicativo, nodoApplicativo);
	}


	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		
		if(qName.equals("webservice")) {
			inserisciNodoWebService(c_currentServizio);
			c_currentServizio = null;
			c_currentApplicativo = null;
		}
		
		c_stack.pop();
		if( ! c_stack.empty() ) {
			c_currentTag = c_stack.peek();
		}
	}
	
	

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		
		if( c_currentTag.equals("nome") ) {
			String szNome = String.valueOf(ch, start, length);
			c_currentServizio.setNomeServizio(szNome);
		}
		else if( c_currentTag.equals("wsdl") ) {
			String szWsdl = String.valueOf(ch, start, length);
			c_currentServizio.setWsdl(szWsdl);
		}
	}


	private void inserisciNodoWebService(WebServiceInfo servizio) {		
		DefaultMutableTreeNodeFoglia nodoServizio = new DefaultMutableTreeNodeFoglia(servizio);
		DefaultMutableTreeNode nodoApplicativo = c_mappaApplicativi.get(servizio.getNomeApplicativo());
		nodoApplicativo.add(nodoServizio);
	}
	
}
