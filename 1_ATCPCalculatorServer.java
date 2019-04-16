/**
 author avinash
*/

import java.net.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;

public class TCPCalculatorServer {

	public static void main(String[] args) {
		
		try {

			ServerSocket server = new ServerSocket(8888);

			System.out.println("Server Started....\n");

			ExecutorService pool = Executors.newFixedThreadPool(20);
			
			while(true) {
				pool.execute(new Calculator(server.accept()));
			}						

		} catch (Exception e) {
			System.out.println("Exception Occurred : " + e);
		}
	}
	 private static class Calculator implements Runnable {
	        private Socket client;
	        

	        Calculator(Socket socket) {
	            this.client = socket;
	        }

	        @Override
	        public void run() {
			System.out.println("\nConnected: " + client);
			try {
				String operation;
				double num1, num2, result = 0.0;

								OutputStream os = client.getOutputStream();
				InputStream is = client.getInputStream();

				DataInputStream dis = new DataInputStream(is);
				DataOutputStream dos = new DataOutputStream(os);

				while (true) {
					StringTokenizer st = new StringTokenizer(dis.readUTF());

					num1 = Double.parseDouble(st.nextToken());
					num2 = Double.parseDouble(st.nextToken());
					operation = st.nextToken();

					System.out.println("Server: Received Operands\n num1=" + num1 + "\tnum2=" + num2);
					switch (operation) {
					case "+":
						result = num1 + num2;
						break;
					case "-":
						result = num1 - num2;
						break;
					case "*":
						result = num1 * num2;
						break;
					case "/":
						result = num1 / num2;
						break;
					}
					System.out.println("Server: Sending Result: " + result);
					dos.writeUTF(Double.toString(result));
				}
	            } catch (Exception e) {
	                System.out.println("Error:" + client);
	            } finally {
	                try { client.close(); } catch (IOException e) {}
	                System.out.println("Closed: " + client);
	            }
	        }
	    }

}
