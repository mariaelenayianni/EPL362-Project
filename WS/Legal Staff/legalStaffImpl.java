package ws;


import javax.jws.WebMethod;
import javax.jws.WebService;

//Service Implementation
@WebService(endpointInterface = "ws.legalStaff")
public class legalStaffImpl implements legalStaff{

	
	
	@Override
	public xml getAppointments(String lawyerID) throws Exception {
		MySQLAccess dao = new MySQLAccess();
        dao.readDataBase();
        xml result=dao.getAppointments(lawyerID);
		return result;
	}
	@Override
	public xml getTransactions(int eventID) throws Exception{
		MySQLAccess dao = new MySQLAccess();
        dao.readDataBase();
        xml result=dao.getTransactions(eventID);
		return result;
		
	}
	@Override
	public xml risk(int clientID) throws Exception{
		MySQLAccess dao = new MySQLAccess();
        dao.readDataBase();
        xml result=dao.risk(clientID);
        
        
        
		return result;
		
	}
	@Override
	public xml RetrieveClients(String lawyerID) throws Exception{
		MySQLAccess dao = new MySQLAccess();
        dao.readDataBase();
        xml result=dao.RetrieveClients(lawyerID);
		return result;
	}
	
	@Override
	public String setNewCase(String newCase, String strategy, String legalOpinion, String details, String disputes, int complete,int apID) throws Exception {
		MySQLAccess dao = new MySQLAccess();
        dao.readDataBase();
        dao.writeNewCase(newCase, strategy, legalOpinion, details, disputes, complete, apID);
		return "Success update cases";
	}
	
	@Override
	public String setNewComment(int clientID, String lawyerID, String comment) throws Exception {
		MySQLAccess dao = new MySQLAccess();
        dao.readDataBase();
        dao.writeNewComment(clientID, lawyerID, comment);
		return "Success update cases";
	}
	
	@Override
	public String setNewChangeRequest(int clientID, String field, String value) throws Exception {
		MySQLAccess dao = new MySQLAccess();
        dao.readDataBase();
        dao.writeNewChangeRequest(clientID, field, value);
		return "Success update cases";
	}
	
	@Override
	public xml retrieveStrategies() throws Exception {
		MySQLAccess dao = new MySQLAccess();
        dao.readDataBase();
        xml results=dao.retrieveStrategies();
        System.out.println("Success retrieving strategies");
        return results;
	
	}
	
	public xml getPreviousDetails(String lawyerID, int clientID) throws Exception {
		MySQLAccess dao = new MySQLAccess();
        dao.readDataBase();
        xml results=dao.retrievePreviousDetails( lawyerID,  clientID);
        System.out.println("Success retrieving previous details");
        return results;
	
	}
	
	@Override 
	public xml showWarnings(String lawyerID) throws Exception{
		MySQLAccess dao = new MySQLAccess();
        dao.readDataBase();
        xml results=dao.retrieveWarnings(lawyerID);
        System.out.println("Success retrieving warnings");
        return results;
		
	}

}