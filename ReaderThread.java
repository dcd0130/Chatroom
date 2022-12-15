/**
 * This thread is passed a socket that it reads from. Whenever it gets input
 * it writes it to the ChatScreen text area using the displayMessage() method.
 */

import java.io.*;
import java.net.*;
import javax.swing.*;

public class ReaderThread implements Runnable
{
	Socket server;
	BufferedReader fromServer;
	ChatScreen screen;

	public ReaderThread(Socket server, ChatScreen screen) {
		this.server = server;
		this.screen = screen;
	}

	public void run() {
		try {
			fromServer = new BufferedReader(new InputStreamReader(server.getInputStream()));

			while (true) {
				String message = fromServer.readLine();
				screen.displayMessage(message);
			
				/**
				String[] parts = message.split(" ");
				
				
				if (parts[0]== "JOIN") {
					screen.displayMessage(parts[1]+ " joined");
					// ("JOIN " + name + "\r\n");
				}
				if(parts[0] == "SEND"){
					screen.displayMessage(parts[1] + ": " + parts[2]);
					// ("SEND " + name + " " + message + "\r\n");
				}
				if(parts[0] == "EXIT"){
					screen.displayMessage(parts[1] + " left");
					// ("EXIT " + name + "\r\n");
					
				}
				**/
			}
		}
		catch (IOException ioe) { System.out.println(ioe); }

	}
}
