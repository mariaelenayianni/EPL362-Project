package ws;


import javax.jws.WebService;

//Service Implementation
@WebService(endpointInterface = "ws.Receptionist")
public class ReceptionistImpl implements Receptionist{

	@Override
	public String getHelloWorldAsString(String name) {
		return "Hello World JAX-WS " + name;
	}
	
	public String sumof(int a,int b) {
		int sum=a+b;
		String s=String.valueOf(sum);
		return "Sum: " + s ;
	}
	
	public String db() throws Exception {
		MySQLAccess dao = new MySQLAccess();
        dao.readDataBase();
        return "hi";
	}

	@Override
	public xml getAppointmentID() throws Exception {
		MySQLAccess dao = new MySQLAccess();
		dao.readDataBase();

		return dao.getAppointmentID();

	}
	
	
	public String came(int a_ID)throws Exception {
		MySQLAccess dao = new MySQLAccess();
		dao.readDataBase();
		dao.came(a_ID);
		return "Successfully updated attendance";

	}
	
	public String insertRecommendation(String name) throws Exception{
		MySQLAccess dao = new MySQLAccess();
		dao.readDataBase();
		dao.insertRecommendation(name);
		return "Successfully insert recommendation";
	}
	
	@Override
	public xml viewTransactions(int cID) throws Exception {
		MySQLAccess dao = new MySQLAccess();
        dao.readDataBase();
        xml results=dao.viewTransactions(cID);
        return results;
	
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
	public xml cbranch() throws Exception {
		MySQLAccess dao = new MySQLAccess();
		dao.readDataBase();
		return dao.retrieveBranch();

	}
	
	@Override
	public xml showPreviousAttendance(int cID) throws Exception{
		MySQLAccess dao = new MySQLAccess();
		dao.readDataBase();
		return dao.showPreviousAttendance(cID);

	}
	
	@Override
	public xml showAttendance() throws Exception{
		MySQLAccess dao = new MySQLAccess();
		dao.readDataBase();
		return dao.showAttendance();

	}
	
	
	@Override
	public xml updateAppointment() throws Exception{
		MySQLAccess dao = new MySQLAccess();
		dao.readDataBase();
		return dao.updateAppointment();

	}
	
	@Override
	public String addAppointment(String lawyerID,String clientID,String branch,String datee,String timee,int dropin) throws Exception{
		MySQLAccess dao = new MySQLAccess();
		dao.readDataBase();
		dao.addAppointment(lawyerID,clientID,branch,datee,timee,dropin);
		
		return "Successfully insert appointment";
	}
	
	
	
}