/**
 author avinash
*/

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class TCPCalculatorClient {

	public static void main(String[] args) {
		int choice;
		double num1, num2;

		Scanner sc = new Scanner(System.in);

		try {

			Socket client = new Socket("localhost", 8888);
			
			System.out.println("Client Started....");
			
			OutputStream os = client.getOutputStream();
			InputStream is = client.getInputStream();

			DataInputStream dis = new DataInputStream(is);
			DataOutputStream dos = new DataOutputStream(os);

			do {

				System.out.println("1. Addition\n2. Subtraction\n3. Multiplication\n4. Division\n5. Exit");
				System.out.println("Enter your choice: ");
				choice = sc.nextInt();

				System.out.print("Enter First Number: ");
				num1 = sc.nextDouble();
				System.out.print("Enter Second Number: ");
				num2 = sc.nextDouble();

				switch (choice) {
				case 1:
					dos.writeUTF(num1 + " " + num2 + " +");
					break;
				case 2:
					dos.writeUTF(num1 + " " + num2 + " -");
					break;
				case 3:
					dos.writeUTF(num1 + " " + num2 + " *");
					break;
				case 4:
					dos.writeUTF(num1 + " " + num2 + " /");
					break;
				case 5:
					System.out.println("Bye!");
					break;
				default:
					System.out.println("Invalid Choice!");
				}
				System.out.println("Result: " + dis.readUTF());
			} while (choice != 5);
		} catch (Exception e) {
			System.out.println("Exception Occurred : " + e);
		}
	}

}
