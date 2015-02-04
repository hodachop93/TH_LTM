package ngay2.bai1;

import java.net.*;
public class ServerUDP {
	public final static int port = 8876;
	public static void main(String[] args) throws Exception{
		//Tao thung thu de nhan thu
		DatagramSocket serverSocket = new DatagramSocket(port);
		
		//Tao 2 stream de giao tiep
		byte[] receiveData = new byte[1024];
		byte[] sendData = new byte[1024];
		
		//Tien hanh qua trinh nhan du lieu
		while (true){
			//Tao mot goi buu pham de nhan
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			//Bat dau nhan du lieu vao packet, receiveData dong vai tro nhu 1 buffer input
			serverSocket.receive(receivePacket);
			
			//Lay dia chi ip cua may da gui de lieu den Datagram
			InetAddress IPAddress = receivePacket.getAddress();
			
			//Nhan du lieu gui den
			String sentence = new String(receivePacket.getData()).substring(0, receivePacket.getLength());
			
			int port = receivePacket.getPort();
			Thread.sleep(20*1000);
			String dao = "";
			for (int i = sentence.length() - 1; i >= 0; i--) {
				dao += sentence.charAt(i) + "";
			}
			if(dao.equals(sentence)){
				String capsent = "OK";
				sendData = capsent.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendData,
						capsent.length(), IPAddress, port);
				serverSocket.send(sendPacket);
			}else{
				String capsent = "Error";
				sendData = capsent.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendData,
						capsent.length(), IPAddress, port);
				serverSocket.send(sendPacket);
			}
			
		}
	}
}
