package wsclient;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import ws.MySQLAccess;
import ws.Management;

/**
 * This class represents an object of managment client
 * @author Styliana Kouva, MariaElena Yianni, Marileni Angelidou, Evangelos Papadopoulos
 *
 */
public class ManagementClient {
	private Management ls;

	/**
	 * Constructor
	 * @throws MalformedURLException
	 */
	public ManagementClient() throws MalformedURLException {

		URL url = new URL("http://localhost:9000/ws/Management?wsdl");

		QName qname = new QName("http://ws/", "ManagementImplService");

		Service service = Service.create(url, qname);

		ls = service.getPort(Management.class);

	}
	
	/**
	 * This function requests the server for a weakly report
	 * @return
	 * @throws Exception
	 */
	public ws.xml weeklyreportm() throws Exception {
		return ls.weeklyreportm();
	}

	/**
	 * This function requests the server for Monday's daily report 
	 * @return
	 * @throws Exception
	 */
	public ws.xml dailyreportMonday() throws Exception {
		return ls.dailyreportMonday();
	}
	
	/**
	 * This function requests the server for Tuesday's daily report 
	 * @return
	 * @throws Exception
	 */
	public ws.xml dailyreportTuesday() throws Exception {
		return ls.dailyreportTuesday();
	}
	/**
	 * This function requests the server for Wednesday's daily report 
	 * @return
	 * @throws Exception
	 */
	public ws.xml dailyreportWednesday() throws Exception {
		return ls.dailyreportWednesday();
	}
	
	/**
	 * This function requests the server for Thursday's daily report 
	 * @return
	 * @throws Exception
	 */
	public ws.xml dailyreportThursday() throws Exception {
		return ls.dailyreportThursday();
	}
	/**
	 * This function requests the server for Friday's daily report 
	 * @return
	 * @throws Exception
	 */
	public ws.xml dailyreportFriday() throws Exception {
		return ls.dailyreportFriday();
	}
	
	/**
	 * This function requests the server for Saturday's daily report 
	 * @return
	 * @throws Exception
	 */
	public ws.xml dailyreportSaturday() throws Exception {
		return ls.dailyreportSaturday();
	}
	/**
	 * This function requests the server for Sunday's daily report 
	 * @return
	 * @throws Exception
	 */
	public ws.xml dailyreportSunday() throws Exception {
		return ls.dailyreportSunday();
	}
	
	/**
	 * This function requests the server for weakly report for case type 1
	 * @return
	 * @throws Exception
	 */
	public ws.xml dailyreportCaseType1() throws Exception {
		return ls.dailyreportCaseType1();
	}
	
	/**
	 * This function requests the server for weakly report for case type 2
	 * @return
	 * @throws Exception
	 */
	public ws.xml dailyreportCaseType2() throws Exception {
		return ls.dailyreportCaseType2();
	}
	
	/**
	 * This function requests the server for weakly report for case type 3
	 * @return
	 * @throws Exception
	 */
	public ws.xml dailyreportCaseType3() throws Exception {
		return ls.dailyreportCaseType3();
	}
	
	/**
	 * This function requests the server for weakly report for case type 4
	 * @return
	 * @throws Exception
	 */
	public ws.xml dailyreportCaseType4() throws Exception {
		return ls.dailyreportCaseType4();
	}
	
	/**
	 * This function requests the server for weakly report for case type 5
	 * @return
	 * @throws Exception
	 */
	public ws.xml dailyreportCaseType5() throws Exception {
		return ls.dailyreportCaseType5();
	}
	
	/**
	 * This function requests the server for weakly report of Legal Branch
	 * @return
	 * @throws Exception
	 */
	public ws.xml dailyreportLegalBranch() throws Exception {
		return ls.dailyreportLegalBranch();
	}
}