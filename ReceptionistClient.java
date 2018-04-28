package wsclient;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import ws.MySQLAccess;
import ws.Receptionist;

public class ReceptionistClient {
	private Receptionist ls;

	public ReceptionistClient() throws MalformedURLException {

		URL url = new URL("http://localhost:9000/ws/Receptionist?wsdl");

		QName qname = new QName("http://ws/", "ReceptionistImplService");

		Service service = Service.create(url, qname);

		ls = service.getPort(Receptionist.class);

	}

	public ws.xml getAppointmentID() throws Exception {
		return ls.getAppointmentID();
	}
	
	public void came(int a_ID) throws Exception {
		 ls.came(a_ID);
	}
	public void insertRecommendation(String name) throws Exception {
		 ls.insertRecommendation(name);
	}
	public ws.xml viewTransactions(int cID) throws Exception {
		return ls.viewTransactions(cID);
	}
	public ws.xml cclient() throws Exception {
		return ls.cclient();
	}
	public ws.xml clawyer() throws Exception {
		return ls.clawyer();
	}
	public ws.xml showPreviousAttendance(int cID) throws Exception {
		return ls.showPreviousAttendance(cID);
	}
	
	public ws.xml viewAppointment() throws Exception {
		return ls.showAttendance();
	}
	
	public ws.xml updateAppointment() throws Exception {
		return ls.updateAppointment();
	}
	
	
	public void addAppointment(String lawyerID,String clientID,String branch,String datee,String timee,int dropin) throws Exception{
		ls.addAppointment(lawyerID,clientID,branch,datee,timee,dropin);
	}
}