package ws;

import java.time.LocalDate;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

//Service Endpoint Interface
@WebService
@SOAPBinding(style = Style.RPC)
public interface Management {

	@WebMethod
	String getHelloWorldAsString(String name);

	@WebMethod
	String sumof(int a, int b);

	@WebMethod
	String db() throws Exception;

	@WebMethod
	xml weeklyreportm() throws Exception;

	@WebMethod
	xml dailyreportMonday() throws Exception;

	@WebMethod
	xml dailyreportTuesday() throws Exception;

	@WebMethod
	xml dailyreportWednesday() throws Exception;

	@WebMethod
	xml dailyreportThursday() throws Exception;

	@WebMethod
	xml dailyreportFriday() throws Exception;

	@WebMethod
	xml dailyreportSaturday() throws Exception;

	@WebMethod
	xml dailyreportSunday() throws Exception;

	@WebMethod
	xml dailyreportCaseType1() throws Exception;

	@WebMethod
	xml dailyreportCaseType2() throws Exception;

	@WebMethod
	xml dailyreportCaseType3() throws Exception;

	@WebMethod
	xml dailyreportCaseType4() throws Exception;

	@WebMethod
	xml dailyreportCaseType5() throws Exception;

	@WebMethod
	xml dailyreportLegalBranch() throws Exception;
}
