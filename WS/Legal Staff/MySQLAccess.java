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

public class MySQLAccess {
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private ResultSet result = null;
	private ResultSet result1 = null;
	private ResultSet result2 = null;
	private ResultSet result3[]=null;

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

	private void writeMetaData(ResultSet resultSet) throws SQLException {
		// Now get some metadata from the database
		// Result set get the result of the SQL query

		System.out.println("The columns in the table are: ");

		System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
		for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
			System.out.println("Column " + i + " " + resultSet.getMetaData().getColumnName(i));
		}
	}

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
	
	public void writeNewCase(String newCase, String strategy, String legalOpinion, String details, String disputes,
			int complete, int apID) throws SQLException {
		preparedStatement = connect.prepareStatement(
				"INSERT INTO `cases`(`strategyID`, `appointmentID`, `Disputes`, `Updated`, `Details`, `CaseType`) VALUES (?,?,?,?,?,?)");

		preparedStatement.setInt(1, 1);
		preparedStatement.setInt(2, apID);
		preparedStatement.setString(3, disputes);
		preparedStatement.setInt(4, complete);
		preparedStatement.setString(5, details);
		preparedStatement.setString(6, newCase);
		preparedStatement.executeUpdate();

	}

	public void writeNewChangeRequest(int clientID, String field, String value) throws SQLException {

		preparedStatement = connect
				.prepareStatement("INSERT INTO `requests`(`clientID`, `field`, `info`) VALUES (?,?,?)");

		preparedStatement.setInt(1, clientID);
		preparedStatement.setString(2, field);
		preparedStatement.setString(3, value);
		preparedStatement.executeUpdate();

	}
	
	public void writeNewComment(int clientID, String lawyerID, String comment) throws SQLException {

		preparedStatement = connect
				.prepareStatement("INSERT INTO `comments`(`clientID`, `lawyerID`, `comments`) VALUES (?,?,?)");

		preparedStatement.setInt(1, clientID);
		preparedStatement.setString(2, lawyerID);
		preparedStatement.setString(3, comment);
		preparedStatement.executeUpdate();

	}
	
	public xml retrieveStrategies() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362", "root", "epapad0!");
		statement = connect.createStatement();
		resultSet = statement.executeQuery("select * from strategy");

		return writeResultSet(resultSet);

	}
	
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
	
	public xml getAppointments(String lawyerID) throws SQLException {
		preparedStatement = connect
				.prepareStatement("SELECT * FROM `appointment` WHERE lawyerID=? GROUP BY eventID");
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
	public xml risk(int clientID) throws SQLException {
		preparedStatement = connect
				.prepareStatement("SELECT name,lastname,risk FROM client WHERE clientID=?");
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

	public xml getTransactions(int eventID) throws SQLException {
		preparedStatement = connect.prepareStatement("SELECT appointmentID,clientID,attended FROM appointment WHERE eventID="+eventID);
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
	
	public xml retrieveWarnings(String lawyerID) throws Exception{
		
		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/epl362", "root", "epapad0!");
		statement = connect.createStatement();
		resultSet = statement.executeQuery("select description, clientID from warnings where lawyerID='"+lawyerID+"'");
		
		return writeResultSet(resultSet);
		
	}
	
	
	// You need to close the resultSet
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

	

}
