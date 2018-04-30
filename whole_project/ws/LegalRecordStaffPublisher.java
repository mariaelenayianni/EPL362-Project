package ws;

import javax.xml.ws.Endpoint;
import ws.LegalRecordStaffImpl;

//Endpoint publisher
public class LegalRecordStaffPublisher{
	
	public static void main(String[] args) {
	   Endpoint.publish("http://localhost:8080/ws/LegalRecordStaff", new LegalRecordStaffImpl());
    }

}