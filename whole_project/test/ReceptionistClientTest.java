package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import wsclient.LegalRecordStaffClient;
import wsclient.legalStaffClient;
import wsclient.ReceptionistClient;

public class ReceptionistClientTest {
	/**
	 * Test method for
	 * {@link JunitTest.ReceptionistClient#ViewTransactions( int )}.
	 * @throws Exception 
	 */
	@Test
	public final void testViewTransactions() throws Exception {
		try {
			
			ReceptionistClient ls = new ReceptionistClient();
			int c_ID=4;
			assertTrue(ls.viewTransactions(c_ID) instanceof ws.xml);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Test method for
	 * {@link JunitTest.ReceptionistClient#cclient()}.
	 * @throws Exception 
	 */
	@Test
	public final void cclienttest() throws Exception {
		try {
			ReceptionistClient ls = new ReceptionistClient();
			assertTrue(ls.cclient().t instanceof List);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Test method for
	 * {@link JunitTest.ReceptionistClient#clawyer()}.
	 * @throws Exception 
	 */
	@Test
	public final void clawyertest() throws Exception {
		try {
			ReceptionistClient ls = new ReceptionistClient();
			assertTrue(ls.cclient().t.get(0) instanceof String[]);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
