package ws;

import java.sql.Statement;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.sun.javafx.css.Declaration;

//Service Endpoint Interface
@WebService
@SOAPBinding(style = Style.RPC)
public interface LegalRecordStaff {

	@WebMethod
	String getHelloWorldAsString(String name);

	@WebMethod
	String sumof(int a, int b);

	@WebMethod
	String db() throws Exception;

	@WebMethod
	String addclient(int c_ID, String name, String lastname, String email, String personalinfo, int riskint,
			int illegalint, int changedint, int readonlyint, int recommendationsint) throws Exception;

	@WebMethod
	xml takeAction() throws Exception;
	
	@WebMethod
	String UpdateRequest(int rID)throws Exception;
	
	@WebMethod
	String DeclineRequest(int rID)throws Exception;
	
	@WebMethod
	xml clawyer()throws Exception;
	
	@WebMethod
	xml cclient()throws Exception;
	
	
	@WebMethod
	String insertWarning(String cID, String lID, String description)throws Exception;
	
	@WebMethod
	String DeleteClient(int cID)throws Exception;
	
	@WebMethod
	public xml retrieveStrategies() throws Exception;
	
	@WebMethod
	String insertDis(int cID, int strategyID)throws Exception;
	
	@WebMethod
	xml getClients() throws Exception;
	
	@WebMethod
	String makeillegal(int cID)throws Exception;
	
	@WebMethod
	String insertStrategy(String name, String sideEffect)throws Exception;

	@WebMethod
	String insertLegalOpinion(String description)throws Exception;

	@WebMethod
	xml retrieveClientFields(int cID) throws Exception;
	
	@WebMethod
	xml viewTransactions(int cID) throws Exception;
}

