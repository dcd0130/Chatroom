import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

public class BoradcastThread implements Runnable{
	
	
	private Vector<String> messageQueue;
	private ArrayList<BufferedWriter> clients;

	public BoradcastThread(Vector<String> messageQueue, ArrayList<BufferedWriter> clients) {
		this.messageQueue = messageQueue;
		this.clients = clients;
		
	}

	public void run() {
		
		while (true) {
            try { Thread.sleep(100); } catch (InterruptedException ignore) { }
            
          
			while(messageQueue.isEmpty() == false) {
				String message = messageQueue.get(0);
				messageQueue.remove(0);
				
				// iterate over clients
				for (int i = 0; i < clients.size(); i++) {
					
					try {
					clients.get(i).write(message + "\r\n");				
					clients.get(i).flush();
					} 
					
					catch (IOException e) {
						e.printStackTrace();
					}		              
	            }	
            }
		}
	}
}
