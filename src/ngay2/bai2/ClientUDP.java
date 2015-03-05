package ngay2.bai2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClientUDP{
	public static void main(String[] args) throws IOException{
		Scanner input = new Scanner(System.in);
		DatagramSocket clientSocket = new DatagramSocket();
		InetAddress IPAddress = InetAddress.getByName("localhost");
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		
		String sentence = input.nextLine();
		sendData = sentence.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData,
				sentence.length(), IPAddress, 2000);
		clientSocket.send(sendPacket);
		// Nhan goi gui ve 
		DatagramPacket receivePacket = new DatagramPacket(receiveData,
				receiveData.length);
		
		clientSocket.receive(receivePacket);
		String receivedSentence = new String(receivePacket.getData()).substring(0, receivePacket.getLength());
		System.out.println(receivedSentence);
		
	}
}
