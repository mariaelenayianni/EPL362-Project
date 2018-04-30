package test;
 
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;


import wsclient.ManagementClient;

public class ManagementClientTest {
	/**
	 * Test method for
	 * {@link JunitTest.ManagementClient#dailyreportMonday()}.
	 * @throws Exception 
	 */
	//@Test
	public final void dailyreportMondaytest() throws Exception {
		try {
			int id = 100;
			ManagementClient ls = new ManagementClient();
			assertNotNull(ls.dailyreportMonday());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Test method for
	 * {@link JunitTest.ManagementClient#dailyreportTuesday()}.
	 * @throws Exception 
	 */
	//@Test
	public final void dailyreportTuesdaytest() throws Exception {
		try {
			int id = 100;
			ManagementClient ls = new ManagementClient();
			assertNotNull(ls.dailyreportTuesday());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Test method for
	 * {@link JunitTest.ManagementClient#dailyreportWednesday()}.
	 * @throws Exception 
	 */
	//@Test
	public final void dailyreportWednesdaytest() throws Exception {
		try {
			int id = 100;
			ManagementClient ls = new ManagementClient();
			assertNotNull(ls.dailyreportWednesday());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Test method for
	 * {@link JunitTest.ManagementClient#dailyreportThursday()}.
	 * @throws Exception 
	 */
	//@Test
	public final void dailyreportThursdaytest() throws Exception {
		try {
			int id = 100;
			ManagementClient ls = new ManagementClient();
			assertNotNull(ls.dailyreportThursday());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Test method for
	 * {@link JunitTest.ManagementClient#dailyreportFriday()}.
	 * @throws Exception 
	 */
	//@Test
	public final void dailyreportFridaytest() throws Exception {
		try {
			int id = 100;
			ManagementClient ls = new ManagementClient();
			assertNotNull(ls.dailyreportFriday());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
