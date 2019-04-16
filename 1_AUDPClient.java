import java.net.*; 
import java.io.*;

class UDPClient
{
	public static void main(String args[])
		throws Exception
	{
		DatagramSocket ds = new DatagramSocket(4040);

		BufferedReader br = new BufferedReader
   			           (new InputStreamReader(System.in));

		System.out.print("Enter IP Address:");
		String ip = br.readLine();

		System.out.print("Enter Port:");
		int port = Integer.parseInt(br.readLine());

		System.out.print("Enter Data:");
		String data = br.readLine();

		InetAddress addr = InetAddress.getByName(ip);

		byte b[] = data.getBytes();
	
		DatagramPacket dp = new DatagramPacket
			    (b, b.length, addr, port);

		ds.send(dp);
		
		System.out.println("Data send");
		ds.close();
	}
}