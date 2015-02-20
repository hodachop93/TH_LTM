package ngay2.bai1;

import java.io.IOException;
import java.net.*;
public class ServerUDP {
	public final static int port = 8876;
	public static void main(String[] args) throws Exception {
		byte[] receiveData = new byte[1024];
		DatagramSocket socket = new DatagramSocket(port);
		while (true)
		{
			DatagramPacket packet = new DatagramPacket(receiveData, receiveData.length);
			socket.receive(packet);
			new Process(socket, packet).start();
		}
	}
	
}
class Process extends Thread{
	DatagramSocket socket;
	DatagramPacket packet;
	public Process(DatagramSocket socket, DatagramPacket packet)
	{
		this.socket=socket;
		this.packet=packet;
	}
	public static String toUpperCase(String input){
		String s;
		s=input.toUpperCase();
		return s;
	}
	public static String toLowerCase(String input){
		return input.toLowerCase();
	}
	public static String toUpper_LowerCase(String input){
		String doi = "";
		for (int i = 0; i < input.length(); i++) {
			String tam = input.charAt(i) + "";
			if (input.charAt(i) >= 97 && input.charAt(i) <= 122) {
				doi += tam.toUpperCase();
			} else if (input.charAt(i) >= 65 && input.charAt(i) <= 97) {
				doi += tam.toLowerCase();
			} else {
				doi += tam;
			}
		}
		return doi;
	}
	public static int getLength(String input){
		String[] words = input.split(" ");
		int count = 0;
		for (int i = 0; i < words.length; i++){
			if (!words[i].equals(""))
				count++;
		}
		return count;
	}
	@Override
	public void run() {
		String input = new String(packet.getData());
		input.substring(0, packet.getLength());
		String output = toUpper_LowerCase(input);
		DatagramPacket sendPacket = new DatagramPacket(output.getBytes(), output.length(),
				packet.getAddress(), packet.getPort());
		try {
			socket.send(sendPacket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Gui goi thu 2
		output = toLowerCase(input);
		sendPacket = new DatagramPacket(output.getBytes(), output.length(),
				packet.getAddress(), packet.getPort());
		try{
			socket.send(sendPacket);
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		//Gui goi thu 3
		output = toUpper_LowerCase(input);
		sendPacket = new DatagramPacket(output.getBytes(), output.length(),
				packet.getAddress(), packet.getPort());
		try{
			socket.send(sendPacket);
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		//Gui goi thu 4
		output = getLength(input) + "";
		sendPacket = new DatagramPacket(output.getBytes(), output.length(),
				packet.getAddress(), packet.getPort());
		try{
			socket.send(sendPacket);
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
}