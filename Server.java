/**
 * An echo server listening on port 6007. 

 * This server reads from the client
 * and echoes back the result. 
 *
 * This services each request in a separate thread.
 *
 * This conforms to RFC 862 for echo servers.
 *
 * @author - Greg Gagne.
 * Denise Dingsleder
 */

// Chatroom

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.*;

public class  Server
{
	public static final int DEFAULT_PORT = 5555;

	// construct a thread pool for concurrency	
	private static final Executor exec = Executors.newCachedThreadPool();
	
	

	public static void main(String[] args) throws IOException {
		ServerSocket sock = null;
			
		try {
			
			// establish the socket
			sock = new ServerSocket(DEFAULT_PORT);
			
			Vector<String> messageQueue = new Vector<String>();
			ArrayList<BufferedWriter> clients= new ArrayList<BufferedWriter>();
			
			// Broadcast Thread
			Runnable broadcast = new BoradcastThread(messageQueue, clients);
			exec.execute(broadcast);
									

			while (true) {
				
				Socket client = sock.accept();
				
				BufferedWriter fromWriter = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
				BufferedWriter toClient = new BufferedWriter(fromWriter);
		
				clients.add(toClient);
				
				Runnable task = new Connection(client, messageQueue);
				exec.execute(task);

			}
		}
		catch (IOException ioe) { System.err.println(ioe); }
		finally {
			if (sock != null)
				sock.close();
		}
	}
}
