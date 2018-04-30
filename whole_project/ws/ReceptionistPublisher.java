package ws;

import javax.xml.ws.Endpoint;
import ws.ReceptionistImpl;

//Endpoint publisher
public class ReceptionistPublisher{
	
	public static void main(String[] args) {
	   Endpoint.publish("http://localhost:9999/ws/Receptionist", new ReceptionistImpl());
    }

}