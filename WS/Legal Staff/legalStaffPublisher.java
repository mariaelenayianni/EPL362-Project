package ws;


import javax.xml.ws.Endpoint;
import ws.legalStaffImpl;

//Endpoint publisher
public class legalStaffPublisher{
	
	public static void main(String[] args) {
	   Endpoint.publish("http://localhost:4433/ws/legalStaff", new legalStaffImpl());
    }

}