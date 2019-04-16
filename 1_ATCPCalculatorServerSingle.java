
import java.net.*;

import java.util.StringTokenizer;
import java.io.*;

public class TCPCalculatorServerSingle {

	public static void main(String[] args) {
		String operation;
		double num1, num2, result=0.0;

		try {

			ServerSocket server = new ServerSocket(8888);
			
			System.out.println("Server Started....");
			System.out.println("Waiting for client to connect....");

			Socket client = server.accept();
			OutputStream os = client.getOutputStream();
			InputStream is = client.getInputStream();

			DataInputStream dis = new DataInputStream(is);
			DataOutputStream dos = new DataOutputStream(os);
			
			while(true) {
				StringTokenizer st = new StringTokenizer(dis.readUTF());

				num1 = Double.parseDouble(st.nextToken());
				num2 = Double.parseDouble(st.nextToken());
				operation = st.nextToken();

				System.out.println("Server: Received Operands\n num1="+num1+"\tnum2="+num2);
				switch (operation) {
				case "+":
					result = num1+num2;
					break;
				case "-":
					result = num1-num2;
					break;
				case "*":
					result = num1*num2;
					break;
				case "/":
					result = num1/num2;
					break;
				}
				System.out.println("Server: Sending Result: "+result);
				dos.writeUTF(Double.toString(result));				
				
			}


		} catch (Exception e) {
			System.out.println("Exception Occurred : " + e);
		}
	}

}


