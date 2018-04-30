package ws;

import java.time.LocalDate;

import javax.jws.WebService;

//Service Implementation
/**
 * This class is the Managment Implementation.
 * 
 * @author Styliana Kouva, Marileni Angelidou, Aggelos Papadopoulos, MariaElena Yianni
 *
 *	
 */
@WebService(endpointInterface = "ws.Management")
public class ManagementImpl implements Management {

	/**
	 * This function returns the string with the name
	 * 
	 * @param name
	 * @return
	 */
	@Override
	public String getHelloWorldAsString(String name) {
		return "Hello World JAX-WS " + name;
	}

	/**
	 * 
	 *	This function returns the concatenation of two integer values
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public String sumof(int a, int b) {
		int sum = a + b;
		String s = String.valueOf(sum);
		return "Sum: " + s;
	}

	/**
	 * 
	 * 	This function reads the database, creates the connection
	 * 
	 * @return
	 * @throws Exception
	 */
	public String db() throws Exception {
		MySQLAccess dao = new MySQLAccess();
		dao.readDataBase();
		return "hi";
	}

	/**
	 * 
	 * This function returns the weekly reports of the head office
	 * management.
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	public xml weeklyreportm() throws Exception {
		MySQLAccess dao = new MySQLAccess();

		dao.readDataBase();
		return dao.weeklyreportma();

	}

	/**
	 * 
	 * This function returns the report for Monday to the client
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	public xml dailyreportMonday() throws Exception {
		MySQLAccess dao = new MySQLAccess();

		dao.readDataBase();
		return dao.dailyreportMonday();

	}
	/**
	 * 
	 * This function returns the report for Tuesday to the client
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	public xml dailyreportTuesday() throws Exception {
		MySQLAccess dao = new MySQLAccess();

		dao.readDataBase();
		return dao.dailyreportTuesday();

	}
	
	/**
	 * 
	 * This function returns the report for Wednesday to the client
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	public xml dailyreportWednesday() throws Exception {
		MySQLAccess dao = new MySQLAccess();

		dao.readDataBase();
		return dao.dailyreportWednesday();

	}
	/**
	 * 
	 * This function returns the report for Thursday to the client
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	public xml dailyreportThursday() throws Exception {
		MySQLAccess dao = new MySQLAccess();

		dao.readDataBase();
		return dao.dailyreportThursday();

	}
	/**
	 * 
	 * This function returns the report for Friday to the client
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	public xml dailyreportFriday() throws Exception {
		MySQLAccess dao = new MySQLAccess();

		dao.readDataBase();
		return dao.dailyreportFriday();

	}
	/**
	 * 
	 * This function returns the report for Saturday to the client
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	public xml dailyreportSaturday() throws Exception {
		MySQLAccess dao = new MySQLAccess();

		dao.readDataBase();
		return dao.dailyreportSaturday();

	}
	/**
	 * 
	 * This function returns the report for Sunday to the client
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	public xml dailyreportSunday() throws Exception {
		MySQLAccess dao = new MySQLAccess();

		dao.readDataBase();
		return dao.dailyreportSunday();

	}
	
	/**
	 * 
	 * This function returns the sum of cases of type1 to the client weekly
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	public xml dailyreportCaseType1() throws Exception {
		MySQLAccess dao = new MySQLAccess();

		dao.readDataBase();
		return dao.dailyreportCaseType1();

	}
	
	/**
	 * 
	 * This function returns the sum of cases of type2 to the client weekly
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	public xml dailyreportCaseType2() throws Exception {
		MySQLAccess dao = new MySQLAccess();

		dao.readDataBase();
		return dao.dailyreportCaseType2();

	}
	
	/**
	 * 
	 * This function returns the sum of cases of type3 to the client weekly
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	public xml dailyreportCaseType3() throws Exception {
		MySQLAccess dao = new MySQLAccess();

		dao.readDataBase();
		return dao.dailyreportCaseType3();

	}
	
	/**
	 * 
	 * This function returns the sum of cases of type4 to the client weekly
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	public xml dailyreportCaseType4() throws Exception {
		MySQLAccess dao = new MySQLAccess();

		dao.readDataBase();
		return dao.dailyreportCaseType4();

	}
	
	/**
	 * 
	 * This function returns the sum of cases of type5 to the client weekly
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	public xml dailyreportCaseType5() throws Exception {
		MySQLAccess dao = new MySQLAccess();

		dao.readDataBase();
		return dao.dailyreportCaseType5();

	}
	
	/**
	 * 
	 * This function returns the sum of legal opinions for each branch to the client weekly
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	public xml dailyreportLegalBranch() throws Exception {
		MySQLAccess dao = new MySQLAccess();

		dao.readDataBase();
		return dao.dailyreportLegalBranch();

	}
}