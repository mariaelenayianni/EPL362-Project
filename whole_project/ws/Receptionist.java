package ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

//Service Endpoint Interface
@WebService
@SOAPBinding(style = Style.RPC)
public interface Receptionist {

	@WebMethod
	String getHelloWorldAsString(String name);

	@WebMethod
	String sumof(int a, int b);

	@WebMethod
	String db() throws Exception;

	@WebMethod
	xml getAppointmentID() throws Exception;

	@WebMethod
	String came(int a_ID) throws Exception;

	@WebMethod
	String insertRecommendation(String name) throws Exception;

	@WebMethod
	xml viewTransactions(int cID) throws Exception;

	@WebMethod
	xml cclient() throws Exception;

	@WebMethod
	xml clawyer() throws Exception;

	@WebMethod
	xml cbranch() throws Exception;
	
	@WebMethod
	xml showPreviousAttendance(int cID) throws Exception;

	@WebMethod
	xml showAttendance() throws Exception;

	@WebMethod
	xml updateAppointment() throws Exception;
	
	@WebMethod
	String addAppointment(String lawyerID,String clientID,String branch,String datee,String timee,int dropin) throws Exception;

}
