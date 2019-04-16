import java.net.*;

class UDPServer
{
	public static void main(String args[])
		throws Exception
	{
		DatagramSocket ds = new DatagramSocket(2020);

		byte b[] = new byte[1024];
	
		DatagramPacket dp = new DatagramPacket(b, b.length);
		ds.receive(dp);

		String str = new String(b);
		System.out.println("data is"+str);
		
		ds.close();
	}
}