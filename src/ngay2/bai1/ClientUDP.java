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
		System.out.println("Nhap chuoi: ");
		String sentence = input.nextLine();
		sendData = sentence.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData,
				sentence.length(), IPAddress, 8876);
		clientSocket.send(sendPacket);
		DatagramPacket receivePacket = new DatagramPacket(receiveData,
				receiveData.length);
		// Nhan goi gui ve dau tien
		clientSocket.receive(receivePacket);
		String receivedSentence = new String(receivePacket.getData());
		System.out.println("Xau chuoi HOA : " + receivedSentence);
		
		// Nhan goi gui ve thu 2
		
		clientSocket.receive(receivePacket);
		receivedSentence = new String(receivePacket.getData());
		System.out.println("Xau chuoi THUONG : " + receivedSentence);

		// Nhan goi gui ve thu 3
		
		clientSocket.receive(receivePacket);
		receivedSentence = new String(receivePacket.getData());
		System.out.println("Xau chuoi VUA HOA VUA THUONG : " + receivedSentence);

		//// Nhan goi gui ve thu 4
		clientSocket.receive(receivePacket);
		receivedSentence = new String(receivePacket.getData());
		String receivedSentence1 = new String(receivePacket.getData()).substring(0, receivePacket.getLength());
		
		System.out.println("So tu trong xau da gui : " + receivedSentence1);
		
	}
}