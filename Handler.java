/**
 * Handler class containing the logic for echoing results back

 * to the client. 
 *
 * This conforms to RFC 862 for echo servers.
 *
 * @author Greg Gagne 
 * Denise Dingsleder
 */
// Chatroom

import java.io.*;
import java.net.*;
import java.util.Vector;

public class Handler 
{
	public static final int BUFFER_SIZE = 256;

	/**
	 * this method is invoked by a separate thread
	 */
	public void process(Socket client, Vector<String> messageQueue) throws java.io.IOException {
		byte[] buffer = new byte[BUFFER_SIZE];
		BufferedReader  fromClient = null;
		DataOutputStream toClient = null;
		InetAddress hostAddress;

		try {
			// READ from Client
			
			System.out.println("I have a connection");
			
			fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
			
			String line;
			
			while((line = fromClient.readLine()) != null){
				// write message to messaageQueue
				messageQueue.add(line);
				
			}
		}
		
		catch (IOException ioe) {
			System.err.println(ioe);
		}
		


		finally {
			// close streams and socket
			if (fromClient != null)
				fromClient.close();
			if (toClient != null)
				toClient.close();
		}
	}
}

