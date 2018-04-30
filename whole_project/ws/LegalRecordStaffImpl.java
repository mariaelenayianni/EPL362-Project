package ws;

import java.sql.Statement;

import javax.jws.WebService;

//Service Implementation
@WebService(endpointInterface = "ws.LegalRecordStaff")
public class LegalRecordStaffImpl implements LegalRecordStaff {

	@Override
	public String getHelloWorldAsString(String name) {
		return "Hello World JAX-WS " + name;
	}

	public String sumof(int a, int b) {
		int sum = a + b;
		String s = String.valueOf(sum);
		return "Sum: " + s;
	}

	public String db() throws Exception {
		MySQLAccess dao = new MySQLAccess();
		dao.readDataBase();
		return "hi";
	}

	@Override
	public String addclient(int c_ID, String name, String lastname, String email, String personalinfo, int riskint,
			int illegalint, int changedint, int readonlyint, int recommendationsint) throws Exception {

		MySQLAccess dao = new MySQLAccess();
		dao.readDataBase();
		dao.writeclient(c_ID, name, lastname, email, personalinfo, riskint, illegalint, changedint, readonlyint,
				recommendationsint);

		return "Success update client";

	}

	@Override
	public xml takeAction() throws Exception {
		MySQLAccess dao = new MySQLAccess();
		dao.readDataBase();

		return dao.showrequest();

	}

	@Override
	public String UpdateRequest(int rID) throws Exception {
		MySQLAccess dao = new MySQLAccess();
		dao.readDataBase();
		dao.UpdateRequest(rID);

		return "Success update request";
	}

	@Override
	public String DeclineRequest(int rID) throws Exception {
		MySQLAccess dao = new MySQLAccess();
		dao.readDataBase();
		dao.DeclineRequest(rID);

		return "Success decline request";
	}

	@Override
	public xml cclient() throws Exception {
		MySQLAccess dao = new MySQLAccess();
		dao.readDataBase();
		return dao.retrieveClients();

	}

	@Override
	public xml clawyer() throws Exception {
		MySQLAccess dao = new MySQLAccess();
		dao.readDataBase();
		return dao.retrieveLawyer();

	}

	@Override
	public String insertWarning(String cID, String lID, String description) throws Exception {
		MySQLAccess dao = new MySQLAccess();
		dao.readDataBase();
		dao.insertWarning(cID, lID, description);

		return "Success decline request";

	}

	@Override
	public String DeleteClient(int cID) throws Exception {
		MySQLAccess dao = new MySQLAccess();
		dao.readDataBase();
		dao.DeleteClient(cID);

		return "Success decline request";

	}
	
	
	@Override
	public xml retrieveStrategies() throws Exception {
		MySQLAccess dao = new MySQLAccess();
        dao.readDataBase();
        xml results=dao.retrieveStrategies();
        System.out.println("Success retrieving strategies");
        return results;
	
	}
	
	
	public String insertDis(int cID, int strategyID)throws Exception{
		MySQLAccess dao = new MySQLAccess();
		dao.readDataBase();
		dao.insertDis(cID, strategyID);

		return "Success insert disagreement";

	}
	
	

	@Override
	public xml getClients() throws Exception {
		MySQLAccess dao = new MySQLAccess();
		dao.readDataBase();

		return dao.retrieveClientsIllegal();

	}
	
	@Override
	public String makeillegal(int cID) throws Exception {
		MySQLAccess dao = new MySQLAccess();
		dao.readDataBase();
		dao.makeillegal(cID);

		return "Success make illeal";

	}
	
	@Override
	public String insertStrategy(String name, String sideEffect) throws Exception {
		MySQLAccess dao = new MySQLAccess();
		dao.readDataBase();
		dao.insertStrategy(name, sideEffect);

		return "Success insert strategy";

	}

	@Override
	public String insertLegalOpinion(String description) throws Exception {
		MySQLAccess dao = new MySQLAccess();
		dao.readDataBase();
		dao.insertLegalOpinion(description);

		return "Success insert legal opinion";

	}
	
	@Override
	public xml retrieveClientFields(int cID) throws Exception {
		MySQLAccess dao = new MySQLAccess();
        dao.readDataBase();
        xml results=dao.retrieveClientFields(cID);
        System.out.println("Success retrieving client fields");
        return results;
	
	}
	
	@Override
	public xml viewTransactions(int cID) throws Exception {
		MySQLAccess dao = new MySQLAccess();
        dao.readDataBase();
        xml results=dao.viewTransactions(cID);
        return results;
	
	}
}
