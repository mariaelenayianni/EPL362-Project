package wsclient;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import ws.MySQLAccess;
import ws.Receptionist;

/**
 * This class represents a receptionist client object
 * @author Styliana Kouva, MariaElena Yianni, Marileni Angelidou, Evangelos Papadopoulos
 *
 */
public class ReceptionistClient {
	private Receptionist ls;

	/**
	 * Constructor
	 * @throws MalformedURLException
	 */
	public ReceptionistClient() throws MalformedURLException {

		URL url = new URL("http://localhost:9999/ws/Receptionist?wsdl");

		QName qname = new QName("http://ws/", "ReceptionistImplService");

		Service service = Service.create(url, qname);

		ls = service.getPort(Receptionist.class);

	}

	/**
	 * This function requests the server to get appointment IDs
	 * @return
	 * @throws Exception
	 */
	public ws.xml getAppointmentID() throws Exception {
		return ls.getAppointmentID();
	}
	
	/**
	 * This function requests the server to get the attendance
	 * @param a_ID
	 * @throws Exception
	 */
	public void came(int a_ID) throws Exception {
		 ls.came(a_ID);
	}
	/**
	 * This function requests the server to insert a recommendation
	 * @param name
	 * @throws Exception
	 */
	public void insertRecommendation(String name) throws Exception {
		 ls.insertRecommendation(name);
	}
	/**
	 * This function requests the server to get transactions
	 * @param cID
	 * @return
	 * @throws Exception
	 */
	public ws.xml viewTransactions(int cID) throws Exception {
		return ls.viewTransactions(cID);
	}
	/**
	 * This function requests the server to get clients
	 * @return
	 * @throws Exception
	 */
	public ws.xml cclient() throws Exception {
		return ls.cclient();
	}
	/**
	 * This function requests the server to get lawyer
	 * @return
	 * @throws Exception
	 */
	public ws.xml clawyer() throws Exception {
		return ls.clawyer();
	}
	
	/**
	 * This function requests the server to get branches
	 * @return
	 * @throws Exception
	 */
	public ws.xml cbranch() throws Exception {
		return ls.cbranch();
	}
	
	/**
	 * This function requests the server to get previous attendance
	 * @param cID
	 * @return
	 * @throws Exception
	 */
	public ws.xml showPreviousAttendance(int cID) throws Exception {
		return ls.showPreviousAttendance(cID);
	}
	
	/**
	 * This function requests the server to get attendance
	 * @return
	 * @throws Exception
	 */
	public ws.xml viewAppointment() throws Exception {
		return ls.showAttendance();
	}
	
	/**
	 * 
	 * This function requests the server to update an appointment
	 * @return
	 * @throws Exception
	 */
	public ws.xml updateAppointment() throws Exception {
		return ls.updateAppointment();
	}
	
	
	/**
	 * This function requests the server to insert an appointment
	 * @param lawyerID
	 * @param clientID
	 * @param branch
	 * @param datee
	 * @param timee
	 * @param dropin
	 * @throws Exception
	 */
	public void addAppointment(String lawyerID,String clientID,String branch,String datee,String timee,int dropin) throws Exception{
		ls.addAppointment(lawyerID,clientID,branch,datee,timee,dropin);
	}
}