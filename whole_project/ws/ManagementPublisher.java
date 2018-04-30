package ws;

import javax.xml.ws.Endpoint;
import ws.ReceptionistImpl;

//Endpoint publisher
/**
 * 
 * This class opens the connection with the server(mapping)
 * 
 * @author Styliana Kouva, Marileni Angelidou, Aggelos Papadopoulos, MariaElena Yianni
 *
 */
public class ManagementPublisher{
	
	/**
	 * 
	 * opens the connection with the server(mapping)
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
	   Endpoint.publish("http://localhost:9000/ws/Management", new ManagementImpl());
    }

}