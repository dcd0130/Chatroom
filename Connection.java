/**
 * This is the separate thread that services each

 * incoming echo client request.
 *
 * @author Greg Gagne 
 * Denise Dingsleder
 */
//Chatroom
import java.net.*;
import java.util.Vector;
import java.io.*;

public class Connection implements Runnable
{
	private Socket	client;
	private Vector<String> messageQueue;
	private static Handler handler = new Handler();

	public Connection(Socket client, Vector<String> messageQueue) {
		this.client = client;
		this.messageQueue = messageQueue; 

	}

	/**
	 * This method runs in a separate thread.
	 */	
	public void run() { 
		try {
			handler.process(client, messageQueue);
		}
		catch (java.io.IOException ioe) {
			System.err.println(ioe);
		}
	}
}

