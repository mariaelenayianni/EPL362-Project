package ws;

import java.sql.ResultSet;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
//Service Endpoint Interface
@WebService
@SOAPBinding(style = Style.RPC)

public interface legalStaff {
	@WebMethod xml getAppointments(String lawyerID) throws Exception;
	@WebMethod xml getTransactions(int eventID) throws Exception;
	@WebMethod void UpdateTransactions(String newCase, int strategy, int legalOpinion, String details, String disputes, int complete, int apID) throws Exception;
	@WebMethod xml getTransactionforupdate(int eventID) throws Exception;
	@WebMethod xml risk(int	clientID) throws Exception;
	@WebMethod xml RetrieveClients(String lawyerID) throws Exception;
	@WebMethod String setNewCase(String newCase, int strategy, int legalOpinion, String details, String disputes, int complete,int apID) throws Exception;
	@WebMethod xml retrieveStrategies() throws Exception;
	@WebMethod xml retrieveLegalOpinions() throws Exception;
	@WebMethod String setNewComment(int clientID, String lawyerID, String comment) throws Exception;
	@WebMethod String setNewChangeRequest(int clientID, String field, String value) throws Exception;
	@WebMethod xml getPreviousDetails(String lawyerID, int clientID) throws Exception;
	@WebMethod xml showWarnings(String lawyerID) throws Exception;
	@WebMethod String getDisagreements (int clientID, int strategyID) throws Exception;
	@WebMethod String insertOverruledWarning(String lawyerID,int clientID,String description,int overruled) throws Exception;
	@WebMethod String updateOverruledWarning(int wID) throws Exception;
	
}
