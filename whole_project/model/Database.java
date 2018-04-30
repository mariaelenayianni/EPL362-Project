package model;

import java.sql.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

public class Database {

	private Connection con;
	private Database db;

	public Database() {

	}

	public Connection getConnection() {
		if (con != null)
			return con;
		return null;
	}

	public void connect() throws Exception {
		try {
			String host = "jdbc:mysql://localhost:3306/epl362?autoReconnect=true&useSSL=false";
			String uName = "root";
			String uPass = "epapad0!";
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(host, uName, uPass);
		} catch (ClassNotFoundException e) {
			System.out.println("Class not Found!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"DB Connection Failed:" + e.getMessage());
		}

	}

	public void addAccount(Account account) throws SQLException {
		if (con != null) {
			String username = account.getUsername();
			String password = account.getPassword();
			boolean member = account.isMember();

			if (account instanceof FreeAccount) {
				FreeAccount fa = (FreeAccount) account;
				String firstName = fa.getfName();
				String lastName = fa.getlName();
				String email = fa.getEmail();
				String role = fa.getRole();
				/*
				 * 
				 */
				PreparedStatement insertStatement = con
						.prepareStatement("INSERT INTO lawyer(lawyerID,password,name,lastname,email,role) values(?,?,?,?,?,?)");
				insertStatement.setString(1, username);
				insertStatement.setString(2, password);
				insertStatement.setString(3, firstName);
				insertStatement.setString(4, lastName);
				insertStatement.setString(5, email);
				insertStatement.setString(6, role);
				insertStatement.executeUpdate();

				insertStatement.close();
			}

			if (account instanceof ClubAccount) {

			}

		}

	}

	/*
	 * public int getAccountID(String userName){ int id=0; try {
	 * 
	 * result.absolute(1); id = result.getInt("ID"); return id; } catch
	 * (SQLException e) { System.out.println(e.getMessage());
	 * e.printStackTrace(); }
	 * 
	 * return id; }
	 */

	public boolean isAccountTaken(Account account) {

		// * returns null if account null else returns the Account

		if (con != null) {
			String username = account.getUsername();
			String password = account.getPassword();

			PreparedStatement insertStatement;
			try {
				insertStatement = con
						.prepareStatement("SELECT * FROM client WHERE	clientID=?");
				insertStatement.setString(1, username);
				ResultSet resultSet = insertStatement.executeQuery();
				if (resultSet.next()) {
					String user = resultSet.getString("clientID");
					if (user.equals(account.getUsername())) {

						return true;
					}
				}

				else
					return false;
			} catch (SQLException e) {
				System.out.println("SQL Exception in isAccountTaken()");
				e.printStackTrace();
			}
		}

		return false;
	}

	public boolean isValidLogin(Account account) throws Exception {
		if (con != null) {

			String username = account.getUsername();
			String password = account.getPassword();

			PreparedStatement insertStatement;
			try {
				insertStatement = con
						.prepareStatement("SELECT * FROM lawyer WHERE lawyerID=?");
				insertStatement.setString(1, username);
				ResultSet resultSet = insertStatement.executeQuery();
				if (resultSet.first())
					if (resultSet.getString("lawyerID").equals(
							account.getUsername()))
						if (resultSet.getString("password").equals(
								account.getPassword())) {
							wsclient.globals.lawyerID=resultSet.getString("lawyerID");
							wsclient.globals.role=resultSet.getString("role");
							displayDB();
							return true;
						}else
							return false;
					else
						return false;
				else
					return false;
			} catch (SQLException e) {
				System.out.println("SQL Exception in isAccountTaken()");
				e.printStackTrace();
			}

		}
		return false;
	}

	public void displayDB() throws Exception {
		if (con != null) {
			try {
				Statement statement = con.createStatement();
				ResultSet result = statement
						.executeQuery("SELECT * FROM lawyer");
				int numOfCol = result.getMetaData().getColumnCount();

				while (result.next()) {
					System.out.println(result.getString("lawyerID") + '\t'
							+ result.getString("password") + '\t'
							+ result.getString("email"));
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public void disconnect() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}

	public void save() throws SQLException {

	}

	public void addPerson() {

	}

	public void removePerson(int index) {

	}

}
