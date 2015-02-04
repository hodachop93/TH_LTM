package ngay2.bai1;

import java.net.*;
public class ServerUDP {
	public final static int port = 3000;
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
		}
	}
}
