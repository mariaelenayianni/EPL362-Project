package test;

import static org.junit.Assert.*;
import java.sql.SQLException;
import java.util.List;

import wsclient.*;
import org.junit.Test;

public class LegalStaffClientTest {
	/**
	 * Test method for
	 * {@link JunitTest.LegalStaffClient#getAppointments(String)}.
	 * @throws Exception 
	 */
	//@Test
	public final void getAppointmentstest() throws Exception {
		try {
			String k="stella";
			legalStaffClient ls = new legalStaffClient();
			assertNotNull(ls.getAppointments(k));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Test method for
	 * {@link JunitTest.LegalStaffClient#getAppointments(String)}.
	 * @throws Exception 
	 */
	//@Test
	public final void getAppointmentstest1() throws Exception {
		try {
			String k="lalala";
			legalStaffClient ls = new legalStaffClient();
			assertNull(ls.getAppointments(k));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Test method for
	 * {@link JunitTest.legalStaffClient#risk(int)}.
	 * @throws Exception 
	 */
	//@Test
	public final void risktest() throws Exception {
		try {
			int id = 1;
			legalStaffClient ls = new legalStaffClient();
			assertTrue(ls.risk(id) instanceof ws.xml);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Test method for
	 * {@link JunitTest.legalStaffClient#getTransactions(int)}.
	 * @throws Exception 
	 */
	//@Test
	public final void getTransactiontest() throws Exception {
		try {
			int id = 1;
			legalStaffClient ls = new legalStaffClient();
			assertTrue(ls.getTransactions(id).t instanceof List);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Test method for
	 * {@link JunitTest.legalStaffClient#getDisaggrements()}.
	 * @throws Exception 
	 */
	//@Test
	public final void getDisaggrementstest() throws Exception {
		try {
			int cid = 1;
			int strid=1;
			legalStaffClient ls = new legalStaffClient();
			assertTrue(ls.getDisagreements(cid,strid) instanceof String);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
