package wsclient;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import ws.LegalRecordStaff;
import static org.junit.Assert.*;

/**
 * This class represents an object of legal record staff client
 * @author Styliana Kouva, MariaElena Yianni, Marileni Angelidou, Evangelos Papadopoulos
 *
 */
public class LegalRecordStaffClient {
	private LegalRecordStaff ls;

	/**
	 * Constructor
	 * @throws MalformedURLException
	 */
	public LegalRecordStaffClient() throws MalformedURLException {

		URL url = new URL("http://localhost:8080/ws/LegalRecordStaff?wsdl");

		QName qname = new QName("http://ws/", "LegalRecordStaffImplService");

		Service service = Service.create(url, qname);

		ls = service.getPort(LegalRecordStaff.class);

	}

	/**
	 * This function requests the server to add a client
	 * @param c_ID
	 * @param name
	 * @param lastname
	 * @param email
	 * @param personalinfo
	 * @param riskint
	 * @param illegalint
	 * @param changedint
	 * @param readonlyint
	 * @param recommendationsint
	 * @return
	 * @throws Exception
	 */
	public boolean addclient(int c_ID, String name, String lastname, String email, String personalinfo, int riskint,
			int illegalint, int changedint, int readonlyint, int recommendationsint) throws Exception {
		
		ls.addclient(c_ID, name, lastname, email, personalinfo, riskint, illegalint, changedint,
				readonlyint, recommendationsint);
			return true;
		
	}

	
	/**
	 * This function requests the server to take action
	 * @return
	 * @throws Exception
	 */
	public ws.xml takeAction() throws Exception {
		return ls.takeAction();
			
	}
	/**
	 * This function requests the server to get clients
	 * @return
	 * @throws Exception
	 */
	public ws.xml getClients() throws Exception {
		return ls.getClients();
			
	}
	
	/**
	 * This function requests the server to update request
	 * @param rID
	 * @return
	 * @throws Exception
	 */
	public boolean UpdateRequest(int rID) throws Exception {
		ls.UpdateRequest(rID);
		return true;
	}
	
	
	/**
	 * This function requests the server to decline request
	 * @param rID
	 * @throws Exception
	 */
	public void DeclineRequest(int rID) throws Exception {
		ls.DeclineRequest(rID);
	}
	
	/**
	 * This function requests the server for lawyers
	 * @return
	 * @throws Exception
	 */
	public ws.xml clawyer() throws Exception {
		return ls.clawyer();
	}
	
	/**
	 * This function requests the server for clients
	 * @return
	 * @throws Exception
	 */
	public ws.xml cclient() throws Exception {
		return ls.cclient();
	}
	
	/**
	 * This function requests the server to insert warning
	 * @param cID
	 * @param lID
	 * @param description
	 * @throws Exception
	 */
	public void insertWarning(String cID, String lID, String description) throws Exception {
		ls.insertWarning(cID, lID, description);
	}
	
	/**
	 * This function requests the server to delete client
	 * @param cID
	 * @throws Exception
	 */
	public void DeleteClient(int cID) throws Exception {
		ls.DeleteClient(cID);
	}
	
	/**
	 * This function requests the server to view strategy
	 * @return
	 * @throws Exception
	 */
	public ws.xml viewStrategy() throws Exception {
		return ls.retrieveStrategies();
	}
	
	/**
	 * This function requests the server to insert dissagreemet
	 * @param cID
	 * @param strategyID
	 * @throws Exception
	 */
	public void insertDis(int cID, int strategyID) throws Exception {
		ls.insertDis(cID,strategyID);
	}
	
	/**
	 * This function requests the server to make illegal
	 * @param cID
	 * @throws Exception
	 */
	public void makeillegal(int cID) throws Exception {
		ls.makeillegal(cID);
	}
	
	/**
	 * This function requests the server to insert strategy
	 * @param name
	 * @param sideEffect
	 * @throws Exception
	 */
	public void insertStrategy(String name, String sideEffect) throws Exception {
		ls.insertStrategy(name,sideEffect);
	}
	
	/**
	 * This function requests the server to insert legal opinion
	 * @param description
	 * @throws Exception
	 */
	public void insertLegalOpinion(String description) throws Exception {
		ls.insertLegalOpinion(description);
	}
	
	/**
	 * This function requests the server to view client fields
	 * @param cID
	 * @return
	 * @throws Exception
	 */
	public ws.xml viewClientFields(int cID) throws Exception {
		return ls.retrieveClientFields(cID);
	}
	
	/**
	 * This function requests the server to view transactions
	 * @param cID
	 * @return
	 * @throws Exception
	 */
	public ws.xml viewTransactions(int cID) throws Exception {
		return ls.viewTransactions(cID);
	}
}