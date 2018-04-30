package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Test;

import wsclient.LegalRecordStaffClient;
import wsclient.legalStaffClient;

public class LegalRecordStaffClientTest {
	/**
	 * Test method for
	 * {@link JunitTest.LegalRecordStaffClient#addclient(String , int , int ,String ,String, int, int, int )}.
	 * @throws Exception 
	 */
	@Test
	public final void testaddclient() throws Exception {
		try {
			
			LegalRecordStaffClient ls = new LegalRecordStaffClient();
			int c_ID=100;
			String name="test";
			String lastname="test1";
			String personalinfo="afefafaf";
			int riskint=3;
			int illegalint=1;
			int changedint=0;
			int readonlyint=1;
			int recommendationsint=0;
			String email="Afefafef";
			assertTrue(ls.addclient(c_ID, name, lastname, email, personalinfo, riskint, illegalint, changedint,readonlyint, recommendationsint));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Test method for
	 * {@link JunitTest.LegalRecordStaffClient#viewStrategy()}.
	 * @throws Exception 
	 */
	@Test
	public final void testviewStrategy() throws Exception {
		try {
			
			LegalRecordStaffClient ls = new LegalRecordStaffClient();
			assertNotNull(ls.viewStrategy());
		} catch (SQLException e) {
			e.printStackTrace();
		}
}
/**
 * Test method for
 * {@link JunitTest.LegalRecordStaffClient#UpdatRequest(int)}.
 * @throws Exception 
 */
@Test
public final void testupdaterequest() throws Exception {
	LegalRecordStaffClient ls = new LegalRecordStaffClient();
	int ID=1;
	assertTrue(ls.UpdateRequest(ID));
}
/**
 * Test method for
 * {@link JunitTest.LegalRecordStaffClient#viewClientFields(int)}.
 * @throws Exception 
 */
@Test
public final void testviewClientFields() throws Exception {
	try {
		LegalRecordStaffClient ls = new LegalRecordStaffClient();
		int c_ID=2;
		assertTrue(ls.viewClientFields(c_ID) instanceof ws.xml);
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
/**
 * Test method for
 * {@link JunitTest.LegalRecordStaffClient#viewClientFields(int)}.
 * @throws Exception 
 */
@Test
public final void test2viewClientFields() throws Exception {
	try {
		LegalRecordStaffClient ls = new LegalRecordStaffClient();
		int c_ID=1000;
		assertNull(ls.viewClientFields(c_ID).t.get(0));
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
}