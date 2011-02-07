package sm.ciscoop.componenti;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.io.Serializable;

import sm.ciscoop.funzioni.SOAPDispatcher;

/**
 * This class stores all family data that we use in the example. You should note
 * that no error checking is done, but you probably will want to set that up in
 * your scheme especially if you are using beans!
 */
public class WebServiceInfo implements Transferable, Serializable {

	private static final long serialVersionUID = 1L;

	/** Tipologia del dato trasferibile */
	final public static DataFlavor INFO_FLAVOR = new DataFlavor( WebServiceInfo.class, "Informazioni");
	private static DataFlavor flavors[] = { INFO_FLAVOR };

	private SOAPDispatcher c_soapDispatcher = null;
	
	private String c_nome = null;
	private String c_wsdl = null;
	private String c_nomeApplicativo = null;
	private String c_namespace = null;
	private String c_servizio = null;
	private Boolean c_isOnline = null;

	public WebServiceInfo() {

	}

	// --------- Transferable --------------

	public boolean isDataFlavorSupported(DataFlavor df) {
		return df.equals(INFO_FLAVOR);
	}

	/** implements Transferable interface */
	public Object getTransferData(DataFlavor df) throws UnsupportedFlavorException, IOException {
		if (df.equals(INFO_FLAVOR)) {
			return this;
		} 
		else {
			throw new UnsupportedFlavorException(df);
		}
	}

	@Override
	public String toString() {
		return c_nome;
	}

	/** implements Transferable interface */
	public DataFlavor[] getTransferDataFlavors() {
		return flavors;
	}

	public String getNome() {
		return c_nome;
	}
	public void setNomeServizio(String cNome) {
		c_nome = cNome;
	}

	public String getWsdl() {
		return c_wsdl;
	}
	public void setWsdl(String cWsdl) {
		c_wsdl = cWsdl;
	}

	public String getNomeApplicativo() {
		return c_nomeApplicativo;
	}
	public void setNomeApplicativo(String cNomeApplicativo) {
		c_nomeApplicativo = cNomeApplicativo;
	}
	
	public String getNamespace() {
		return c_namespace;
	}
	public void setNamespace(String cNamespace) {
		c_namespace = cNamespace;
	}
	
	public String getServizio() {
		return c_servizio;
	}
	public void setServizio(String cServizio) {
		c_servizio = cServizio;
	}

	public boolean isOnline() {
		if( c_isOnline == null ) {
			c_soapDispatcher = new SOAPDispatcher(c_wsdl);
			try {
				c_soapDispatcher.invokeDynamic();
				c_isOnline = true;
			} 
			catch (Exception e) {
				e.printStackTrace();
				c_isOnline = false;
			}
		}
		return c_isOnline;
	}
}
