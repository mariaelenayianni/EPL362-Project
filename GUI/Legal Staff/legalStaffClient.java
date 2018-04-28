package wsclient;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import ws.legalStaff;

public class legalStaffClient{
	private legalStaff ls;
	
	public legalStaffClient() throws MalformedURLException {
		
		URL url = new URL("http://localhost:4433/ws/legalStaff?wsdl");
	
        QName qname = new QName("http://ws/", "legalStaffImplService");

        Service service = Service.create(url, qname);

        ls = service.getPort(legalStaff.class);
	
	}
	
	public ws.xml getAppointments(String lawyerID) throws Exception {
		ws.xml result=ls.getAppointments(lawyerID);
		return result;
	}
	public ws.xml getTransactions(int eventID) throws Exception {
		ws.xml result=ls.getTransactions(eventID);
		return result;
	}
	public ws.xml risk(int clientID) throws Exception {
		ws.xml result=ls.risk(clientID);
		return result;
	}
	public ws.xml RetrieveClients(String lawyerID) throws Exception {
		ws.xml result=ls.RetrieveClients(lawyerID);
		return result;
	}
	
	public void setNewCase(String newCase, String strategy, String legalOpinion, String details, String disputes, int complete, int apID) throws Exception {
		System.out.println(ls.setNewCase(newCase, strategy, legalOpinion, details, disputes, complete, apID));
	}
	
	public void setNewComment(int clientID, String lawyerID, String comment) throws Exception {
		System.out.println(ls.setNewComment(clientID, lawyerID, comment));
	}
	
	public void setNewChangeRequest(int clientID, String field, String value) throws Exception{
		System.out.println(ls.setNewChangeRequest(clientID, field, value));
	}
	
	public ws.xml retrieveStrategies() throws Exception {
		 return ls.retrieveStrategies();
		
	}
	
	
	public ws.xml getPreviousDetails(String lawyerID, int clientID) throws Exception {
		return ls.getPreviousDetails(lawyerID, clientID);
	}
	
	public ws.xml showWarnings(String lawyerID) throws Exception{
		return ls.showWarnings(lawyerID);
	}
	

    
}