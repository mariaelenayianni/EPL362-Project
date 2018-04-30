package ws;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import java.sql.ResultSetMetaData;
import java.sql.Time;
import java.text.SimpleDateFormat;
import org.joda.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Vector;
import java.util.Calendar;
import org.joda.time.DateTimeConstants;

/**
 * 
 * This class contains all the functions with the queries 
 * 
 * @author Styliana Kouva, Marileni Angelidou, Aggelos Papadopoulos, MariaElena Yianni
 *
 */
public class MySQLAccess {
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private ResultSet result = null;
	private ResultSet result1 = null;
	private ResultSet result2 = null;
	private ResultSet result3[]=null;

	/**
	 * 
	 * This functioncreates the connection statement to connect with the database
	 * 
	 * @throws Exception
	 */
	public void readDataBase() throws Exception {
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362?autoReconnect=true&useSSL=false", "root", "epapad0!");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Result set get the result of the SQL query
			// resultSet = statement.executeQuery("select * from feedback.comments");
			// writeResultSet(resultSet);

			// PreparedStatements can use variables and are more efficient
			// preparedStatement = connect
			// .prepareStatement("insert into feedback.comments values (default, ?, ?, ?, ?
			// , ?, ?)");
			// "myuser, webpage, datum, summary, COMMENTS from feedback.comments");
			// Parameters start with 1
			/*
			 * preparedStatement.setString(1, "Test"); preparedStatement.setString(2,
			 * "TestEmail"); preparedStatement.setString(3, "TestWebpage");
			 * preparedStatement.setDate(4, new java.sql.Date(2009, 12, 11));
			 * preparedStatement.setString(5, "TestSummary"); preparedStatement.setString(6,
			 * "TestComment"); preparedStatement.executeUpdate();
			 * 
			 * preparedStatement = connect
			 * .prepareStatement("SELECT myuser, webpage, datum, summary, COMMENTS from feedback.comments"
			 * ); resultSet = preparedStatement.executeQuery(); writeResultSet(resultSet);
			 */
			// Remove again the insert comment
			/*
			 * preparedStatement =
			 * connect.prepareStatement("delete from feedback.comments where myuser= ? ; ");
			 * preparedStatement.setString(1, "Test"); preparedStatement.executeUpdate();
			 * 
			 * resultSet = statement.executeQuery("select * from feedback.comments");
			 * writeMetaData(resultSet);
			 */
		} catch (Exception e) {
			throw e;
		} finally {
			//close();
		}

	}
	
	/**
	 * 
	 * This function add a new client into the database
	 * 
	 * @param c_ID
	 * @param name
	 * @param lastname
	 * @param email
	 * @param personalinfo
	 * @param riskint
	 * @param illegalint
	 * @param changedint
	 * @param readonlyint
	 * @param recommendationsint
	 * @throws Exception
	 */
	public void writeclient(int c_ID, String name, String lastname, String email, String personalinfo, int riskint,
			int illegalint, int changedint, int readonlyint, int recommendationsint) throws Exception {
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362?autoReconnect=true&useSSL=false", "root", "epapad0!");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();

			statement.executeUpdate("INSERT INTO client VALUES (" + c_ID + ",'" + name + "','" + lastname + "','"
					+ email + "','" + personalinfo + "'," + riskint + "," + illegalint + "," + changedint + ","
					+ readonlyint + "," + recommendationsint + ")");

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}
	
	/**
	 * 
	 * This function retrieve the requests that must be accept or decline.
	 * 
	 * @return
	 * @throws Exception
	 */
	public xml showrequest() throws Exception {
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362?autoReconnect=true&useSSL=false", "root", "epapad0!");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();

			String query = "select * from Requests WHERE `done`=0";

			resultSet = statement.executeQuery(query);

			return writeResultSet(resultSet);

		} catch (Exception e) {
			throw e;
		} finally {
			// close();
		}

	}
	
	/**
	 * 
	 * This function prints the results
	 * 
	 * @param resultSet
	 * @throws SQLException
	 */
	private void writeMetaData(ResultSet resultSet) throws SQLException {
		// Now get some metadata from the database
		// Result set get the result of the SQL query

		System.out.println("The columns in the table are: ");

		System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
		for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
			System.out.println("Column " + i + " " + resultSet.getMetaData().getColumnName(i));
		}
	}

	/**
	 * 
	 * This function returns the result set of a query into an xml table
	 * 
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	private xml writeResultSet(ResultSet resultSet) throws SQLException {
		xml table = new xml();
		int nCol = resultSet.getMetaData().getColumnCount();
		table.t = new ArrayList<>();
		while (resultSet.next()) {
			String[] row = new String[nCol];
			for (int iCol = 1; iCol <= nCol; iCol++) {
				Object obj = resultSet.getObject(iCol);
				row[iCol - 1] = (obj == null) ? null : obj.toString();
			}
			table.t.add(row);
		}
		return table;

	}
	
	/**
	 * 
	 * This function combine many results sets and returns it as an xml table
	 * 
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	private xml writeResultSetForMany(ResultSet[] resultSet) throws SQLException {
		xml table = new xml();
		table.t = new ArrayList<>();
		int i=0;
		int nCol=0;
		while (i < resultSet.length) {
			try {
			 nCol = resultSet[i].getMetaData().getColumnCount();
			}catch(Exception e){i++;continue;}
			System.out.println(nCol);
			while (resultSet[i].next()) {
				
				String[] row = new String[nCol];
				for (int iCol = 1; iCol <= nCol; iCol++) {
					Object obj = resultSet[i].getObject(iCol);
					row[iCol-1] = (obj == null) ? null : obj.toString();
				}
				table.t.add(row);
			}
			i++;
			
		}
		return table;
	}
	
	/**
	 * 
	 * This function creates a new case and inserts it into the database.
	 * 
	 * @param newCase
	 * @param strategy
	 * @param legalOpinion
	 * @param details
	 * @param disputes
	 * @param complete
	 * @param apID
	 * @throws SQLException
	 */
	public void writeNewCase(String newCase, int strategy, int legalOpinion, String details, String disputes,
			int complete, int apID) throws SQLException {
		preparedStatement = connect.prepareStatement(
				"INSERT INTO `cases`(`strategyID`, `appointmentID`, `Disputes`, `Updated`, `Details`, `CaseType`,`legalopinionID`) VALUES (?,?,?,?,?,?,?)");

		preparedStatement.setInt(1, strategy);
		preparedStatement.setInt(2, apID);
		preparedStatement.setString(3, disputes);
		preparedStatement.setInt(4, complete);
		preparedStatement.setString(5, details);
		preparedStatement.setString(6, newCase);
		preparedStatement.setInt(7,legalOpinion);
		preparedStatement.executeUpdate();

	}

	/**
	 * 
	 * This function creates a new request to change personal info and inserts it into
	 * the database
	 * 
	 * @param clientID
	 * @param field
	 * @param value
	 * @throws SQLException
	 */
	public void writeNewChangeRequest(int clientID, String field, String value) throws SQLException {

		preparedStatement = connect
				.prepareStatement("INSERT INTO `requests`(`clientID`, `field`, `info`) VALUES (?,?,?)");

		preparedStatement.setInt(1, clientID);
		preparedStatement.setString(2, field);
		preparedStatement.setString(3, value);
		preparedStatement.executeUpdate();

	}
	
	/**
	 * 
	 * This function inserts a new comment into the database
	 * 
	 * @param clientID
	 * @param lawyerID
	 * @param comment
	 * @throws SQLException
	 */
	public void writeNewComment(int clientID, String lawyerID, String comment) throws SQLException {

		System.out.println("client:  "+clientID+ "lawyerID:  "+lawyerID+"comment:  "+comment);
		preparedStatement = connect
				.prepareStatement("INSERT INTO `comments`(`clientID`, `lawyerID`, `comments`) VALUES (?,?,?)");

		preparedStatement.setInt(1, clientID);
		preparedStatement.setString(2, lawyerID);
		preparedStatement.setString(3, comment);
		preparedStatement.executeUpdate();

	}
	
	/**
	 * 
	 * This function inserts a new disagreement into the database
	 * 
	 * @param c_ID
	 * @param strategyID
	 */
	public void insertDis(int c_ID, int strategyID) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362?autoReconnect=true&useSSL=false", "root", "epapad0!");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();

			String query = "INSERT INTO disaggrements VALUES (default," + c_ID + ", " + strategyID + ")";
			statement.executeUpdate(query);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			close();
		}
	}

	/**
	 * 
	 * This function change the illegal field to 1 into the database
	 * 
	 * @param c_ID
	 */
	public void makeillegal(int c_ID) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362?autoReconnect=true&useSSL=false", "root", "epapad0!");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();

			statement.executeUpdate("UPDATE `client` SET `illegal`=1 WHERE `clientID`=" + c_ID);
			System.out.println("irtha sto update:   " + c_ID);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			close();
		}
	}
	
	/**
	 * 
	 * This function inserts a new strategy into the database
	 * 
	 * @param name
	 * @param sideEffect
	 */
	public void insertStrategy(String name, String sideEffect) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362?autoReconnect=true&useSSL=false", "root", "epapad0!");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();

			String query = "INSERT INTO strategy VALUES (default,'" + name + "', '" + sideEffect + "')";
			statement.executeUpdate(query);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * This function inserts the legal opinions into the database.
	 * 
	 * @param description
	 */
	public void insertLegalOpinion(String description) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362?autoReconnect=true&useSSL=false", "root", "epapad0!");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();

			String query = "INSERT INTO legalopinion VALUES (default,'" + description + "')";
			statement.executeUpdate(query);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * 	This function retrieves the fields of the client
	 * 
	 * @param cID
	 * @return
	 * @throws Exception
	 */
	public xml retrieveClientFields(int cID) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		// Setup the connection with the DB
		connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362?autoReconnect=true&useSSL=false", "root", "epapad0!");

		// Statements allow to issue SQL queries to the database
		statement = connect.createStatement();
		resultSet = statement.executeQuery("select * from client where clientID=" + cID);

		return writeResultSet(resultSet);

	}
	
	/**
	 * 
	 * This function retrieves the transactions from the database
	 * 
	 * @param id_client
	 * @return
	 * @throws Exception
	 */
	public xml viewTransactions(int id_client) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		// Setup the connection with the DB
		connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362?autoReconnect=true&useSSL=false", "root", "epapad0!");

		// Statements allow to issue SQL queries to the database
		statement = connect.createStatement();
		String query = "select * from cases, appointment where appointment.appointmentID=cases.appointmentID AND appointment.clientID="
				+ id_client;

		resultSet = statement.executeQuery(query);

		return writeResultSet(resultSet);

	}
	
	/**
	 * 
	 * This function retrieves the appointment id from the database
	 * 
	 * @return
	 * @throws Exception
	 */
	public xml getAppointmentID() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		// Setup the connection with the DB
		connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362?autoReconnect=true&useSSL=false", "root", "epapad0!");

		// Statements allow to issue SQL queries to the database
		statement = connect.createStatement();
		resultSet = statement.executeQuery("select appointmentID from appointment");

		return writeResultSet(resultSet);

	}
	
	/**
	 * 
	 * This function updates the field attendance of the appointment
	 * 
	 * @param a_ID
	 */
	public void came(int a_ID) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362?autoReconnect=true&useSSL=false", "root", "epapad0!");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();

			statement.executeUpdate("UPDATE `appointment` SET `attended`=1 WHERE `appointmentID`=" + a_ID);

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			close();
		}
	}
	
	/**
	 * 
	 * This function inserts the recommendation into the database
	 * 
	 * @param name
	 */
	public void insertRecommendation(String name) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362?autoReconnect=true&useSSL=false", "root", "epapad0!");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();

			String query = "INSERT INTO reccomendations VALUES (default,'" + name + "')";
			statement.executeUpdate(query);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * This function retrieve the previous attendance of a client
	 * 
	 * @param cID
	 * @return
	 * @throws Exception
	 */
	public xml showPreviousAttendance(int cID) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		// Setup the connection with the DB
		connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362?autoReconnect=true&useSSL=false", "root", "epapad0!");

		// Statements allow to issue SQL queries to the database
		statement = connect.createStatement();
		resultSet = statement.executeQuery("select date from appointment where clientID='" + cID + "'");

		return writeResultSet(resultSet);

	}

	/**
	 * 
	 * This function retrieve the IDs where the client has not attended today
	 * 
	 * @return
	 * @throws Exception
	 */
	public xml showAttendance() throws Exception {

		Class.forName("com.mysql.jdbc.Driver");
		// Setup the connection with the DB
		connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362?autoReconnect=true&useSSL=false", "root", "epapad0!");

		// Statements allow to issue SQL queries to the database
		statement = connect.createStatement();
		LocalDate today = LocalDate.now();
		String query = "select appointmentID,clientID,lawyerID from appointment where attended=0 AND date='" + today
				+ "'";

		resultSet = statement.executeQuery(query);

		return writeResultSet(resultSet);

	}

	/**
	 * 
	 * This function retrive the appointment details from the database
	 * 
	 * @return
	 * @throws Exception
	 */
	public xml updateAppointment() throws Exception {

		Class.forName("com.mysql.jdbc.Driver");
		// Setup the connection with the DB
		connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362?autoReconnect=true&useSSL=false", "root", "epapad0!");

		// Statements allow to issue SQL queries to the database
		statement = connect.createStatement();
		LocalDate today = LocalDate.now();

		String query = "select appointment.appointmentID , appointment.clientID , cases.caseID from appointment,cases where appointment.appointmentID=cases.appointmentID "
				+ " AND appointment.attended=1 AND cases.updated=0 AND appointment.date='" + today + "'";

		resultSet = statement.executeQuery(query);

		return writeResultSet(resultSet);

	}

	/**
	 * 
	 * This function adds the appointment into the database
	 * 
	 * @param lawyerID
	 * @param clientID
	 * @param branch
	 * @param datee
	 * @param timee
	 * @param dropin
	 * @throws Exception
	 */
	public void addAppointment(String lawyerID, String clientID, String branch, String datee, String timee, int dropin)
			throws Exception {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = sdf1.parse(datee);
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());

		java.sql.Time sqlTime = java.sql.Time.valueOf(timee);

		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362?autoReconnect=true&useSSL=false", "root", "epapad0!");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();

			String query = "insert into appointment values (default,'" + lawyerID + "'," + Integer.parseInt(clientID)
					+ "," + Integer.parseInt(branch) + "," + 1 + "," + dropin + "," + 0 + ",'" + sqlDate + "','"
					+ sqlTime + "')";
			statement.executeUpdate(query);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * This function retrieves data for the weekly reports
	 * 
	 * @return
	 * @throws Exception
	 */
	public xml weeklyreportma() throws Exception {

		Class.forName("com.mysql.jdbc.Driver");
		// Setup the connection with the DB
		connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362?autoReconnect=true&useSSL=false", "root", "epapad0!");

		// Statements allow to issue SQL queries to the database
		statement = connect.createStatement();

		LocalDate now = new LocalDate();

		LocalDate monday = now.withDayOfWeek(DateTimeConstants.MONDAY);
		LocalDate sunday = now.withDayOfWeek(DateTimeConstants.SUNDAY);

		String query = "select branchID,count(clientID) AS numClient from appointment WHERE date >='" + monday
				+ "'  AND date < '" + sunday + "' GROUP BY branchID";

		resultSet = statement.executeQuery(query);

		return writeResultSet(resultSet);

	}

	/**
	 * 
	 * This function retrieves the data for the Monday report
	 * 
	 * @return
	 * @throws Exception
	 */
	public xml dailyreportMonday() throws Exception {

		Class.forName("com.mysql.jdbc.Driver");
		// Setup the connection with the DB
		connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362?autoReconnect=true&useSSL=false", "root", "epapad0!");

		// Statements allow to issue SQL queries to the database
		statement = connect.createStatement();

		LocalDate today = LocalDate.now();

		String query = "SELECT branchID, COUNT(clientID) FROM appointment WHERE  DATE(Date) = DATE_SUB(CURDATE(), INTERVAL 4 DAY) GROUP BY branchID";

		resultSet = statement.executeQuery(query);

		return writeResultSet(resultSet);

	}

	/**
	 * 
	 * This function retrieves the data for the Tuesday report
	 * 
	 * @return
	 * @throws Exception
	 */
	public xml dailyreportTuesday() throws Exception {

		Class.forName("com.mysql.jdbc.Driver");
		// Setup the connection with the DB
		connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362?autoReconnect=true&useSSL=false", "root", "epapad0!");

		// Statements allow to issue SQL queries to the database
		statement = connect.createStatement();

		LocalDate today = LocalDate.now();

		LocalDate tuesday = today.withDayOfWeek(DateTimeConstants.TUESDAY);

		String query = "SELECT branchID, COUNT(clientID) FROM appointment WHERE  DATE(Date) = DATE_SUB(CURDATE(), INTERVAL 3 DAY) GROUP BY branchID";

		resultSet = statement.executeQuery(query);

		return writeResultSet(resultSet);

	}

	/**
	 * 
	 * This function retrieves the data for the Wednesday report
	 * 
	 * @return
	 * @throws Exception
	 */
	public xml dailyreportWednesday() throws Exception {

		Class.forName("com.mysql.jdbc.Driver");
		// Setup the connection with the DB
		connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362?autoReconnect=true&useSSL=false", "root", "epapad0!");

		// Statements allow to issue SQL queries to the database
		statement = connect.createStatement();

		LocalDate today = LocalDate.now();

		String query = "SELECT branchID, COUNT(clientID) FROM appointment WHERE  DATE(Date) = DATE_SUB(CURDATE(), INTERVAL 2 DAY) GROUP BY branchID";

		resultSet = statement.executeQuery(query);

		return writeResultSet(resultSet);

	}

	/**
	 * 
	 * This function retrieves the data for the Thursday report
	 * 
	 * @return
	 * @throws Exception
	 */
	public xml dailyreportThursday() throws Exception {

		Class.forName("com.mysql.jdbc.Driver");
		// Setup the connection with the DB
		connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362?autoReconnect=true&useSSL=false", "root", "epapad0!");

		// Statements allow to issue SQL queries to the database
		statement = connect.createStatement();

		String query = "SELECT branchID, COUNT(clientID) FROM appointment WHERE  DATE(Date) = DATE_SUB(CURDATE(), INTERVAL 1 DAY) GROUP BY branchID";

		resultSet = statement.executeQuery(query);

		return writeResultSet(resultSet);

	}

	/**
	 * 
	 * This function retrieves the data for the Friday report
	 * 
	 * @return
	 * @throws Exception
	 */
	public xml dailyreportFriday() throws Exception {

		Class.forName("com.mysql.jdbc.Driver");
		// Setup the connection with the DB
		connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362?autoReconnect=true&useSSL=false", "root", "epapad0!");

		// Statements allow to issue SQL queries to the database
		statement = connect.createStatement();

		LocalDate today = LocalDate.now();

		String query = "SELECT branchID, COUNT(clientID) FROM appointment WHERE  DATE(Date) = DATE_SUB(CURDATE(), INTERVAL 0 DAY) GROUP BY branchID";

		resultSet = statement.executeQuery(query);

		return writeResultSet(resultSet);

	}

	/**
	 * 
	 * This function returns the sum of cases weekly of type1 
	 * 
	 * @return
	 * @throws Exception
	 */
	public xml dailyreportCaseType1() throws Exception {

		Class.forName("com.mysql.jdbc.Driver");
		// Setup the connection with the DB
		connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362?autoReconnect=true&useSSL=false", "root", "epapad0!");

		// Statements allow to issue SQL queries to the database
		statement = connect.createStatement();

		LocalDate now = new LocalDate();

		LocalDate monday = now.withDayOfWeek(DateTimeConstants.MONDAY);
		LocalDate sunday = now.withDayOfWeek(DateTimeConstants.SUNDAY);

		String query = "select cases.CaseType,count(appointment.clientID) AS numClient from appointment,cases WHERE ((DATE(Date) = DATE_SUB(CURDATE(), INTERVAL 4 DAY)) OR (DATE(Date) = DATE_SUB(CURDATE(), INTERVAL 3 DAY)) OR (DATE(Date) = DATE_SUB(CURDATE(), INTERVAL 2 DAY)) OR (DATE(Date) = DATE_SUB(CURDATE(), INTERVAL 1 DAY)) OR (DATE(Date) = DATE_SUB(CURDATE(), INTERVAL 0 DAY))) AND cases.CaseType='1' AND appointment.appointmentID=cases.appointmentID";

		resultSet = statement.executeQuery(query);

		return writeResultSet(resultSet);

	}

	/**
	 * 
	 * This function returns the sum of cases weekly of type2 
	 * 
	 * @return
	 * @throws Exception
	 */
	public xml dailyreportCaseType2() throws Exception {

		Class.forName("com.mysql.jdbc.Driver");
		// Setup the connection with the DB
		connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362?autoReconnect=true&useSSL=false", "root", "epapad0!");

		// Statements allow to issue SQL queries to the database
		statement = connect.createStatement();

		LocalDate now = new LocalDate();

		LocalDate monday = now.withDayOfWeek(DateTimeConstants.MONDAY);
		LocalDate sunday = now.withDayOfWeek(DateTimeConstants.SUNDAY);

		String query = "select cases.CaseType,count(appointment.clientID) AS numClient from appointment,cases WHERE ((DATE(Date) = DATE_SUB(CURDATE(), INTERVAL 4 DAY)) OR (DATE(Date) = DATE_SUB(CURDATE(), INTERVAL 3 DAY)) OR (DATE(Date) = DATE_SUB(CURDATE(), INTERVAL 2 DAY)) OR (DATE(Date) = DATE_SUB(CURDATE(), INTERVAL 1 DAY)) OR (DATE(Date) = DATE_SUB(CURDATE(), INTERVAL 0 DAY))) AND cases.CaseType='2' AND appointment.appointmentID=cases.appointmentID";

		resultSet = statement.executeQuery(query);

		return writeResultSet(resultSet);

	}

	/**
	 * 
	 * This function returns the sum of cases weekly of type3
	 * 
	 * @return
	 * @throws Exception
	 */
	public xml dailyreportCaseType3() throws Exception {

		Class.forName("com.mysql.jdbc.Driver");
		// Setup the connection with the DB
		connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362?autoReconnect=true&useSSL=false", "root", "epapad0!");

		// Statements allow to issue SQL queries to the database
		statement = connect.createStatement();

		LocalDate now = new LocalDate();

		LocalDate monday = now.withDayOfWeek(DateTimeConstants.MONDAY);
		LocalDate sunday = now.withDayOfWeek(DateTimeConstants.SUNDAY);

		String query = "select cases.CaseType,count(appointment.clientID) AS numClient from appointment,cases WHERE ((DATE(Date) = DATE_SUB(CURDATE(), INTERVAL 4 DAY)) OR (DATE(Date) = DATE_SUB(CURDATE(), INTERVAL 3 DAY)) OR (DATE(Date) = DATE_SUB(CURDATE(), INTERVAL 2 DAY)) OR (DATE(Date) = DATE_SUB(CURDATE(), INTERVAL 1 DAY)) OR (DATE(Date) = DATE_SUB(CURDATE(), INTERVAL 0 DAY))) AND cases.CaseType='3' AND appointment.appointmentID=cases.appointmentID";

		resultSet = statement.executeQuery(query);

		return writeResultSet(resultSet);

	}

	/**
	 * 
	 * This function returns the sum of cases weekly of type4
	 * 
	 * @return
	 * @throws Exception
	 */
	public xml dailyreportCaseType4() throws Exception {

		Class.forName("com.mysql.jdbc.Driver");
		// Setup the connection with the DB
		connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362?autoReconnect=true&useSSL=false", "root", "epapad0!");

		// Statements allow to issue SQL queries to the database
		statement = connect.createStatement();

		LocalDate now = new LocalDate();

		LocalDate monday = now.withDayOfWeek(DateTimeConstants.MONDAY);
		LocalDate sunday = now.withDayOfWeek(DateTimeConstants.SUNDAY);

		String query = "select cases.CaseType,count(appointment.clientID) AS numClient from appointment,cases WHERE ((DATE(Date) = DATE_SUB(CURDATE(), INTERVAL 4 DAY)) OR (DATE(Date) = DATE_SUB(CURDATE(), INTERVAL 3 DAY)) OR (DATE(Date) = DATE_SUB(CURDATE(), INTERVAL 2 DAY)) OR (DATE(Date) = DATE_SUB(CURDATE(), INTERVAL 1 DAY)) OR (DATE(Date) = DATE_SUB(CURDATE(), INTERVAL 0 DAY))) AND cases.CaseType='4' AND appointment.appointmentID=cases.appointmentID";

		resultSet = statement.executeQuery(query);

		return writeResultSet(resultSet);

	}

	/**
	 * 
	 * This function returns the sum of cases weekly of type5
	 * 
	 * @return
	 * @throws Exception
	 */
	public xml dailyreportCaseType5() throws Exception {

		Class.forName("com.mysql.jdbc.Driver");
		// Setup the connection with the DB
		connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362?autoReconnect=true&useSSL=false", "root", "epapad0!");

		// Statements allow to issue SQL queries to the database
		statement = connect.createStatement();

		LocalDate now = new LocalDate();

		LocalDate monday = now.withDayOfWeek(DateTimeConstants.MONDAY);
		LocalDate sunday = now.withDayOfWeek(DateTimeConstants.SUNDAY);

		String query = "select cases.CaseType,count(appointment.clientID) AS numClient from appointment,cases WHERE ((DATE(Date) = DATE_SUB(CURDATE(), INTERVAL 4 DAY)) OR (DATE(Date) = DATE_SUB(CURDATE(), INTERVAL 3 DAY)) OR (DATE(Date) = DATE_SUB(CURDATE(), INTERVAL 2 DAY)) OR (DATE(Date) = DATE_SUB(CURDATE(), INTERVAL 1 DAY)) OR (DATE(Date) = DATE_SUB(CURDATE(), INTERVAL 0 DAY))) AND cases.CaseType='5' AND appointment.appointmentID=cases.appointmentID";

		resultSet = statement.executeQuery(query);

		return writeResultSet(resultSet);

	}

	/**
	 * 
	 * This function returns the sum of legal opinions weekly for each branch
	 * 
	 * @return
	 * @throws Exception
	 */
	public xml dailyreportLegalBranch() throws Exception {

		Class.forName("com.mysql.jdbc.Driver");
		// Setup the connection with the DB
		connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362?autoReconnect=true&useSSL=false", "root", "epapad0!");

		// Statements allow to issue SQL queries to the database
		statement = connect.createStatement();

		String query = "select appointment.branchID, count(cases.legalopinionID) AS numLegalOpinion from appointment,cases WHERE ((DATE(Date) = DATE_SUB(CURDATE(), INTERVAL 4 DAY)) OR (DATE(Date) = DATE_SUB(CURDATE(), INTERVAL 3 DAY)) OR (DATE(Date) = DATE_SUB(CURDATE(), INTERVAL 2 DAY)) OR (DATE(Date) = DATE_SUB(CURDATE(), INTERVAL 1 DAY)) OR (DATE(Date) = DATE_SUB(CURDATE(), INTERVAL 0 DAY))) AND cases.appointmentID=appointment.appointmentID GROUP BY appointment.branchID";

		resultSet = statement.executeQuery(query);

		return writeResultSet(resultSet);

	}

	/**
	 * 
	 * This function retrieves the data for the Saturday report
	 * 
	 * @return
	 * @throws Exception
	 */
	public xml dailyreportSaturday() throws Exception {

		Class.forName("com.mysql.jdbc.Driver");
		// Setup the connection with the DB
		connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362?autoReconnect=true&useSSL=false", "root", "epapad0!");

		// Statements allow to issue SQL queries to the database
		statement = connect.createStatement();

		LocalDate today = LocalDate.now();

		LocalDate saturday = today.withDayOfWeek(DateTimeConstants.SATURDAY);
		String query = "SELECT DISTINCT branchID, COUNT(clientID) FROM appointment WHERE  DATE(Date) = DATE_SUB(CURDATE(), INTERVAL 3 DAY)";

		resultSet = statement.executeQuery(query);

		return writeResultSet(resultSet);

	}

	/**
	 * 
	 * This function retrieves the data for the Sunday report
	 * 
	 * @return
	 * @throws Exception
	 */
	public xml dailyreportSunday() throws Exception {

		Class.forName("com.mysql.jdbc.Driver");
		// Setup the connection with the DB
		connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362?autoReconnect=true&useSSL=false", "root", "epapad0!");

		// Statements allow to issue SQL queries to the database
		statement = connect.createStatement();

		LocalDate today = LocalDate.now();

		LocalDate sunday = today.withDayOfWeek(DateTimeConstants.SUNDAY);
		String query = "SELECT DISTINCT branchID, COUNT(clientID) FROM appointment WHERE  DATE(Date) = DATE_SUB(CURDATE(), INTERVAL 4 DAY)";

		resultSet = statement.executeQuery(query);

		return writeResultSet(resultSet);

	}

	/**
	 * 
	 * This function returns the sum of clients by branch
	 * 
	 * @return
	 * @throws Exception
	 */
	public xml dailyreport() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		// Setup the connection with the DB
		connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362?autoReconnect=true&useSSL=false", "root", "epapad0!");

		// Statements allow to issue SQL queries to the database
		statement = connect.createStatement();
		String query;
		ResultSet[] result = new ResultSet[5];
		int i = 4;
		while (i >= 0) {
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362?autoReconnect=true&useSSL=false", "root", "epapad0!");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			query = "SELECT branchID, COUNT(clientID) FROM appointment WHERE  DATE(Date) = DATE_SUB(CURDATE(), INTERVAL "
					+ i + " DAY) GROUP BY branchID";
			System.out.println(query);
			result[i] = statement.executeQuery(query);

			i--;
		}

		return writeResultSetDaily(result);

	}
	
	/**
	 * 
	 * This function returns the strategies from the database
	 * 
	 * @return
	 * @throws Exception
	 */
	public xml retrieveStrategies() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362", "root", "epapad0!");
		statement = connect.createStatement();
		resultSet = statement.executeQuery("select * from strategy");

		return writeResultSet(resultSet);

	}
	
	/**
	 * 
	 * This function returns the legal opinions
	 * 
	 * @return
	 * @throws Exception
	 */
	public xml retrieveLegalOpinions() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362", "root", "epapad0!");
		statement = connect.createStatement();
		resultSet = statement.executeQuery("select * from legalopinion");

		return writeResultSet(resultSet);

	}
	
	/**
	 * 
	 * This function returns the disagreements
	 * @param clientID
	 * @param strategyID
	 * @return
	 * @throws Exception
	 */
	public String retrieveDisagreements(int clientID,int strategyID) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362", "root", "epapad0!");
		statement = connect.createStatement();
		resultSet = statement.executeQuery("select dID from disaggrements where clientID="+clientID+" and strategyID="+strategyID);

		xml disagreements=writeResultSet(resultSet);
		if(disagreements.t.isEmpty()) {
			return "ok";
		}else {
			return "notok";
		}
	}
	
	/**
	 * 
	 * This function inserts an overruled warning into the database
	 * 
	 * @param lawyerID
	 * @param clientID
	 * @param description
	 * @param overruled
	 * @throws Exception
	 */
	public void insertOverruledWarning(String lawyerID,int clientID,String description,int overruled) throws Exception {
		preparedStatement = connect
				.prepareStatement("INSERT INTO `warnings`(`lawyerID`, `clientID`, `description`,`overruled`) VALUES (?,?,?,?)");

		preparedStatement.setString(1, lawyerID);
		preparedStatement.setInt(2, clientID);
		preparedStatement.setString(3, description);
		preparedStatement.setInt(4, overruled);
		preparedStatement.executeUpdate();
	}
	
	/**
	 * 
	 * This function updates the warnings to overruled 
	 * 
	 * @param wID
	 * @throws Exception
	 */
	public void updateOverruledWarning(int wID) throws Exception {
		
		preparedStatement = connect
				.prepareStatement("UPDATE `warnings` SET `overruled`=1 where `warnID`="+wID);
		preparedStatement.executeUpdate();
		
	}
	

	
	/**
	 * 
	 * This function returns the previous details of the client and cases
	 * 
	 * @param lawyerID
	 * @param clientID
	 * @return
	 * @throws Exception
	 */
	public xml retrievePreviousDetails(String lawyerID, int clientID) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		// Setup the connection with the DB
		connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362?autoReconnect=true&useSSL=false", "root", "epapad0!");
		// Statements allow to issue SQL queries to the database
		statement = connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		System.out.println(lawyerID + " " + clientID);
		resultSet = statement    
				.executeQuery("SELECT appointmentID FROM appointment WHERE lawyerID=\""+lawyerID+"\" and clientID="+clientID);
		resultSet.last();
		int size=resultSet.getRow();
		System.out.println("size: "+size);
		resultSet.beforeFirst();
		//result.beforeFirst();
		ResultSet[] result=new ResultSet [size];
		int i = 0;
		while (resultSet.next()) {
			System.out.println(i);
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362?autoReconnect=true&useSSL=false", "root", "epapad0!");
			statement = connect.createStatement();
			System.out.println(resultSet.getInt("appointmentID"));
			result[i] = statement.executeQuery("select Details from cases where appointmentID="+ resultSet.getInt("appointmentID"));
			
			i++;
		}

		return writeResultSetForMany(result);

	}
	
	/**
	 * 
	 * This function returns the daily results for the weekly report
	 * 
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	private xml writeResultSetDaily(ResultSet[] resultSet) throws SQLException {
		xml table = new xml();
		table.t = new ArrayList<>();
		int i = 0;
		int count = 1;

		while (resultSet[i].next()) {
			++count;
			// Get data from the current row and use it
		}
		int nCol = count;

		while (i < nCol) {
			String[] row = new String[6];
			table.t.add(row);
			i++;
		}
		i = 0;
		int j = 0;
		while (i < resultSet.length) {
			j = 0;
			int size = table.t.size();
			while (j < size) {
				System.out.println("_____-" + resultSet[i].getObject(0).toString());
				table.t.get(j)[i] = resultSet[i].getObject(1).toString();

				j++;
			}
			i++;

		}
		return table;
	}
	
	/**
	 * 
	 * The function updates the request to done.
	 * 
	 * @param rID
	 * @throws Exception
	 */
	public void DeclineRequest(int rID) throws Exception {
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362?autoReconnect=true&useSSL=false", "root", "epapad0!");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();

			String query = "UPDATE `requests` SET `done`= 1 where `rID`=" + rID;

			try {
				statement.executeUpdate(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}
	
	/**
	 * 
	 * This function update the client filed from the request
	 * 
	 * @param rID
	 * @throws Exception
	 */
	public void UpdateRequest(int rID) throws Exception {
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362?autoReconnect=true&useSSL=false", "root", "epapad0!");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();

			String query = "SELECT * from `Requests` where `rID`=" + rID;

			resultSet = statement.executeQuery(query);
			// ResultSetMetaData metaData = resultSet.getMetaData();
			int wrong = 0;
			String query3 = null;
			int arith;

			while (resultSet.next()) {

				String a = resultSet.getString("rID");
				String b = resultSet.getString("clientID");
				String c = resultSet.getString("field");
				String d = resultSet.getString("info");
				int cID = Integer.parseInt(b);

				System.out.println("rid:  " + a + "clientID:  " + b + "field:  " + c + "info:  " + d);

				if (c.equals("name")) {

					query3 = "UPDATE `client` SET `name`= \"" + d + "\" where `clientID`=" + cID;
				} else if (c.equals("lastname")) {
					query3 = "UPDATE `client` SET `lastname`= '" + d + "' where `clientID`=" + cID;
				} else if (c.equals("email")) {
					query3 = "UPDATE `client` SET `email`= '" + d + "' where `clientID`=" + cID;
				} else if (c.equals("personalinfo")) {

					query3 = "UPDATE `client` SET personalinfo= '" + d + "' where `clientID`=" + cID;
				} else if (c.equals("risk")) {

					arith = Integer.parseInt(d);
					query3 = "UPDATE `client` SET risk= '" + arith + "' where `clientID`=" + cID;
				} else if (c.equals("illegal")) {
					arith = Integer.parseInt(d);
					query3 = "UPDATE `client` SET illegal= '" + arith + "' where `clientID`=" + cID;

				} else if (c.equals("changed")) {
					arith = Integer.parseInt(d);
					query3 = "UPDATE `client` SET changed= '" + arith + "' where `clientID`=" + cID;
				} else if (c.equals("recommendations")) {
					arith = Integer.parseInt(d);
					query3 = "UPDATE `client` SET recommendations= '" + arith + "' where `clientID`=" + cID;
				} else {

					wrong = 1;
				}
				if (wrong == 0) {

					try {
						Class.forName("com.mysql.jdbc.Driver");
						connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362?autoReconnect=true&useSSL=false", "root", "epapad0!");
						statement = connect.createStatement();
						System.out.println(query3);
						statement.executeUpdate(query3);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

			//

		} catch (Exception e) {
			throw e;
		}

		String query3 = "UPDATE `requests` SET `done`= 1 where `rID`=" + rID;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362?autoReconnect=true&useSSL=false", "root", "epapad0!");
			statement = connect.createStatement();

			statement.executeUpdate(query3);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// close();
		}

	}
	
	/**
	 * 
	 * This function returns the appointment IDs
	 * 
	 * @param lawyerID
	 * @return
	 * @throws SQLException
	 */
	public xml getAppointments(String lawyerID) throws SQLException {
		preparedStatement = connect
				.prepareStatement("SELECT * FROM `appointment` WHERE lawyerID=?");
		preparedStatement.setString(1,lawyerID );
		result=preparedStatement.executeQuery();
		int nCol = result.getMetaData().getColumnCount();
		xml table=new xml();
		table.t=new ArrayList<>();
		while( result.next()) {
		    String[] row = new String[nCol];
		    for( int iCol = 1; iCol <= nCol; iCol++ ){
		            Object obj = result.getObject( iCol );
		            row[iCol-1] = (obj == null) ?null:obj.toString();
		    }
		    table.t.add( row );
		}
		return table;
	}
	/**
	 * 
	 * This function returns the risk factor
	 * 
	 * @param clientID
	 * @return
	 * @throws SQLException
	 */
	public xml risk(int clientID) throws SQLException {
		preparedStatement = connect
				.prepareStatement("SELECT name,lastname,risk,clientID FROM client WHERE clientID=?");
		preparedStatement.setInt(1,clientID );
		result=preparedStatement.executeQuery();
		int nCol = result.getMetaData().getColumnCount();
		xml table=new xml();
		table.t=new ArrayList<>();
		while( result.next()) {
		    String[] row = new String[nCol];
		    for( int iCol = 1; iCol <= nCol; iCol++ ){
		            Object obj = result.getObject( iCol );
		            row[iCol-1] = (obj == null) ?null:obj.toString();
		    }
		    table.t.add( row );
		}
		return table;
	}

	/**
	 * 
	 * This function returns the transactions
	 * 
	 * @param eventID
	 * @return
	 * @throws SQLException
	 */
	public xml getTransactions(int eventID) throws SQLException {
		preparedStatement = connect.prepareStatement("SELECT appointmentID,clientID,attended FROM appointment WHERE appointmentID="+eventID);
		result=preparedStatement.executeQuery();
		result=preparedStatement.executeQuery();
		int nCol = result.getMetaData().getColumnCount();
		xml table=new xml();
		table.t=new ArrayList<>();
		while( result.next()) {
		    String[] row = new String[nCol+4];
		    for( int iCol = 1; iCol <= nCol; iCol++ ){
		            Object obj = result.getObject( iCol );
		            row[iCol-1] = (obj == null) ?null:obj.toString();
		                      
		    }
		    preparedStatement = connect.prepareStatement("SELECT name,lastname FROM client WHERE clientID=?");
		    preparedStatement.setString(1,result.getObject(2).toString());
		    result1=preparedStatement.executeQuery();
		    result1.next();
		    Object obj1=result1.getObject(1);
    		Object obj2=result1.getObject(2);
    		row[4] = (obj1 == null) ?null:obj1.toString();
    		row[5]=(obj2 == null) ?null:obj2.toString();
    		preparedStatement = connect.prepareStatement("SELECT Updated FROM cases WHERE appointmentID="+result.getObject(1).toString());
    		
    		result2=preparedStatement.executeQuery();
    		Object obj3=null;
    		if(result2.next()) {
    			obj3=result2.getObject(1);
    		}
    		row[6]=(obj3 == null) ?null:obj3.toString();
    		table.t.add( row );
		}
		    
		
		
		return table;
	}
	
	/**
	 * 
	 * This function returns the client with a certain lawyer
	 * 
	 * @param lawyerID
	 * @return
	 * @throws SQLException
	 */
	public xml RetrieveClients(String lawyerID) throws SQLException {
		preparedStatement = connect.prepareStatement("SELECT clientID FROM appointment WHERE lawyerID=? GROUP BY clientID");
		preparedStatement.setString(1,lawyerID );
		result=preparedStatement.executeQuery();
		int nCol = result.getMetaData().getColumnCount();
		xml table=new xml();
		table.t=new ArrayList<>();
		while( result.next()) {
		    String[] row = new String[nCol];
		    for( int iCol = 1; iCol <= nCol; iCol++ ){
		            Object obj = result.getObject( iCol );
		            row[iCol-1] = (obj == null) ?null:obj.toString();
		    }
		    table.t.add( row );
		}
		return table;
	}
	
	/**
	 * 
	 * This function retrieve the clients
	 * 
	 * @return
	 * @throws Exception
	 */
	public xml retrieveClients() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		// Setup the connection with the DB
		connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362?autoReconnect=true&useSSL=false", "root", "epapad0!");

		// Statements allow to issue SQL queries to the database
		statement = connect.createStatement();
		resultSet = statement.executeQuery("select clientID from client");

		return writeResultSet(resultSet);

	}
	
	/**
	 * 
	 * This function returns the clients with illegal=1
	 * 
	 * @return
	 * @throws Exception
	 */
	public xml retrieveClientsIllegal() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		// Setup the connection with the DB
		connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362?autoReconnect=true&useSSL=false", "root", "epapad0!");

		// Statements allow to issue SQL queries to the database
		statement = connect.createStatement();
		resultSet = statement.executeQuery("select `clientID` from `client` where `illegal`=1");

		return writeResultSet(resultSet);

	}
	
	/**
	 * 
	 * This function returns the lawyers 
	 * 
	 * @return
	 * @throws Exception
	 */
	public xml retrieveLawyer() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		// Setup the connection with the DB
		connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362?autoReconnect=true&useSSL=false", "root", "epapad0!");

		// Statements allow to issue SQL queries to the database
		statement = connect.createStatement();
		resultSet = statement.executeQuery("select lawyerID from lawyer");

		return writeResultSet(resultSet);

	}

	/**
	 * 
	 *  This function returns the branches 
	 * 
	 * @return
	 * @throws Exception
	 */
	public xml retrieveBranch() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		// Setup the connection with the DB
		connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362?autoReconnect=true&useSSL=false", "root", "epapad0!");

		// Statements allow to issue SQL queries to the database
		statement = connect.createStatement();
		resultSet = statement.executeQuery("select branchID from branch");

		return writeResultSet(resultSet);

	}

	/**
	 * 
	 * This function inserts the warning into the database
	 * 
	 * @param cID
	 * @param lID
	 * @param description
	 */
	public void insertWarning(String cID, String lID, String description) {

		try {

			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362?autoReconnect=true&useSSL=false", "root", "epapad0!");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			String query = "INSERT INTO warnings VALUES (default,'" + lID + "', " + cID + ",'" + description + "'," + 0
					+ ")";
			statement.executeUpdate(query);

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			close();
		}

	}
	
	/**
	 * 
	 * This function deletes a client by his id
	 * 
	 * @param cID
	 */
	public void DeleteClient(int cID) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362?autoReconnect=true&useSSL=false", "root", "epapad0!");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();

			String query = "UPDATE `client` SET `readonly` = 1 where `clientID`=" + cID;

			statement.executeUpdate(query);

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	
	/**
	 * 
	 * This function returns the warnings from the database
	 * 
	 * @param lawyerID
	 * @return
	 * @throws Exception
	 */
	public xml retrieveWarnings(String lawyerID) throws Exception{
		
		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362", "root", "epapad0!");
		statement = connect.createStatement();
		resultSet = statement.executeQuery("select description, clientID, warnID from warnings where lawyerID='"+lawyerID+"'");
		
		return writeResultSet(resultSet);
		
	}
	
	
	// You need to close the resultSet
	/**
	 *  This function closes the resultSet
	 */
	private void close() {
		try {
			if (result != null) {
				result.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}

	/**
	 * 
	 * This function returns the transaction to be updated
	 * 
	 * @param eventID
	 * @return
	 * @throws SQLException
	 */
	public xml getTransactionforupdate(int eventID) throws SQLException {
		preparedStatement = connect.prepareStatement("SELECT * FROM cases WHERE appointmentID="+eventID);
		result=preparedStatement.executeQuery();
		result=preparedStatement.executeQuery();
		int nCol = result.getMetaData().getColumnCount();
		xml table=new xml();
		table.t=new ArrayList<>();
		while( result.next()) {
		    String[] row = new String[nCol+4];
		    for( int iCol = 1; iCol <= nCol; iCol++ ){
		            Object obj = result.getObject( iCol );
		            row[iCol-1] = (obj == null) ?null:obj.toString();
		                      
		    }
    		table.t.add( row );
		}
		    
		
		
		return table;
	}

	/**
	 * 
	 * This function updates the transactions
	 * 
	 * @param newCase
	 * @param strategy
	 * @param legalOpinion
	 * @param details
	 * @param disputes
	 * @param complete
	 * @param apID
	 * @throws SQLException
	 */
	public void UpdateTransactions(String newCase, int strategy, int legalOpinion, String details, String disputes,int complete, int apID) throws SQLException {
		preparedStatement = connect.prepareStatement(
				"UPDATE `cases` SET `strategyID`=?,`Disputes`=?, `Updated`=?, `Details`=?, `CaseType`=?,`legalopinionID`=? WHERE `appointmentID`=?");

		preparedStatement.setInt(1, strategy);
		preparedStatement.setString(2, disputes);
		preparedStatement.setInt(3, complete);
		preparedStatement.setString(4, details);
		preparedStatement.setString(5, newCase);
		preparedStatement.setInt(6,legalOpinion);
		preparedStatement.setInt(7, apID);
		preparedStatement.executeUpdate();
		
	}

	

}
