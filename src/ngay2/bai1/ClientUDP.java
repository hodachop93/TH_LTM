package ngay2.bai1;
import java.util.*;
import java.net.*;
public class ClientUDP {
	public static void main(String args[]) throws Exception {
		Scanner input = new Scanner(System.in);
		DatagramSocket clientSocket = new DatagramSocket();
		InetAddress IPAddress = InetAddress.getByName("localhost");
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];

		String sentence = input.nextLine();
		sendData = sentence.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData,
				sendData.length, IPAddress, 8876);
		clientSocket.send(sendPacket);
		DatagramPacket receivePacket = new DatagramPacket(receiveData,
				receiveData.length);
		clientSocket.receive(receivePacket);
		String modifiedsentence = new String(receivePacket.getData()).substring(0, receivePacket.getLength());
		if (modifiedsentence.startsWith("OK")) {
			System.out.println("Chuoi doi xung: "+sentence);
		} else {

		}
		System.out.println("From Server:" + modifiedsentence);
		clientSocket.close();
	}
}