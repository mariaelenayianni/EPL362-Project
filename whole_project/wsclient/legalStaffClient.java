package wsclient;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import ws.legalStaff;

/**
 * This class represents an object of legal staff client
 * @author Styliana Kouva, MariaElena Yianni, Marileni Angelidou, Evangelos Papadopoulos
 *
 */
public class legalStaffClient{
	private legalStaff ls;
	
	/**
	 * Constructor
	 * @throws MalformedURLException
	 */
	public legalStaffClient() throws MalformedURLException {
		
		URL url = new URL("http://localhost:4433/ws/legalStaff?wsdl");
	
        QName qname = new QName("http://ws/", "legalStaffImplService");

        Service service = Service.create(url, qname);

        ls = service.getPort(legalStaff.class);
	
	}
	
	/**
	 * This function requests the server to get appointments
	 * @param lawyerID
	 * @return
	 * @throws Exception
	 */
	public ws.xml getAppointments(String lawyerID) throws Exception {
		ws.xml result=ls.getAppointments(lawyerID);
		return result;
	}
	/**
	 * This function requests the server to get transactions
	 * @param eventID
	 * @return
	 * @throws Exception
	 */
	public ws.xml getTransactions(int eventID) throws Exception {
		ws.xml result=ls.getTransactions(eventID);
		return result;
	}
	/**
	 * This function requests the server to get risk
	 * @param clientID
	 * @return
	 * @throws Exception
	 */
	public ws.xml risk(int clientID) throws Exception {
		ws.xml result=ls.risk(clientID);
		return result;
	}
	/**
	 * This function requests the server to get clients
	 * @param lawyerID
	 * @return
	 * @throws Exception
	 */
	public ws.xml RetrieveClients(String lawyerID) throws Exception {
		ws.xml result=ls.RetrieveClients(lawyerID);
		return result;
	}
	
	/**
	 * This function requests the server to insert new case
	 * @param newCase
	 * @param strategy
	 * @param legalOpinion
	 * @param details
	 * @param disputes
	 * @param complete
	 * @param apID
	 * @return
	 * @throws Exception
	 */
	public boolean setNewCase(String newCase, int strategy, int legalOpinion, String details, String disputes, int complete, int apID) throws Exception {
		System.out.println(ls.setNewCase(newCase, strategy, legalOpinion, details, disputes, complete, apID));
		return true;
	}
	
	/**
	 * This function requests the server to insert new comment
	 * @param clientID
	 * @param lawyerID
	 * @param comment
	 * @throws Exception
	 */
	public void setNewComment(int clientID, String lawyerID, String comment) throws Exception {
		System.out.println("client:  "+clientID+ "lawyerID:  "+lawyerID+"comment:  "+comment);
	ls.setNewComment(clientID, lawyerID, comment);
	}
	
	/**
	 * This function requests the server to insert new change request
	 * @param clientID
	 * @param field
	 * @param value
	 * @throws Exception
	 */
	public void setNewChangeRequest(int clientID, String field, String value) throws Exception{
		System.out.println(ls.setNewChangeRequest(clientID, field, value));
	}
	
	/**
	 * This function requests the server to get strategies
	 * @return
	 * @throws Exception
	 */
	public ws.xml retrieveStrategies() throws Exception {
		 return ls.retrieveStrategies();
		
	}
	
	/**
	 * This function requests the server to get legal opinions
	 * @return
	 * @throws Exception
	 */
	public ws.xml retrieveLegalOpinions() throws Exception{
		return ls.retrieveLegalOpinions();
	}
	
	/**
	 * This function requests the server to get disagreements
	 * @param clientID
	 * @param strategyID
	 * @return
	 * @throws Exception
	 */
	public String getDisagreements(int clientID, int strategyID) throws Exception{
		return ls.getDisagreements(clientID,strategyID);
	}
	
	/**
	 * This function requests the server to insert an overruled warning
	 * @param lawyerID
	 * @param clientID
	 * @param description
	 * @param overruled
	 * @throws Exception
	 */
	public void insertOverruledWarning(String lawyerID,int clientID,String description,int overruled) throws Exception {
		this.ls.insertOverruledWarning(lawyerID,clientID,description,overruled);
	}
	/**
	 * This function requests the server to update an overruled warning
	 * @param wID
	 * @return
	 * @throws Exception
	 */
	public boolean updateOverruledWarning(int wID) throws Exception{
		this.ls.updateOverruledWarning(wID);
		return true;
	}
	
	
	/**
	 * This function requests the server to get previous details 
	 * @param lawyerID
	 * @param clientID
	 * @return
	 * @throws Exception
	 */
	public ws.xml getPreviousDetails(String lawyerID, int clientID) throws Exception {
		return ls.getPreviousDetails(lawyerID, clientID);
	}
	
	/**
	 * This function requests the server to get warnings
	 * @param lawyerID
	 * @return
	 * @throws Exception
	 */
	public ws.xml showWarnings(String lawyerID) throws Exception{
		return ls.showWarnings(lawyerID);
	}
	/**
	 * This function requests the server to get transactions
	 * @param aID
	 * @return
	 * @throws Exception
	 */
	public ws.xml getTransactionforUpdate(int aID) throws Exception{
		return ls.getTransactionforupdate(aID);
	}
	/**
	 * This function requests the server to update transaction
	 * @param newCase
	 * @param strategy
	 * @param legalOpinion
	 * @param details
	 * @param disputes
	 * @param complete
	 * @param apID
	 * @throws Exception
	 */
	public void UpdateTransactions(String newCase, int strategy, int legalOpinion, String details, String disputes, int complete, int apID) throws Exception{
		ls.UpdateTransactions(newCase,strategy,legalOpinion,details,disputes,complete,apID);
	}

    
}