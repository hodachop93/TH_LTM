package ngay2.bai2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ServerUDP {
	public final static int port = 2000;
	public static void main(String[] args) throws IOException{
		DatagramSocket serverSocket = new DatagramSocket(port);
		byte[] receiveData = new byte[1024];
		byte[] sendData = new byte[1024];
		while (true){
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			//Bat dau nhan du lieu vao packet, receiveData dong vai tro nhu 1 buffer input
			serverSocket.receive(receivePacket);
			
			//Lay dia chi ip cua may da gui de lieu den Datagram
			InetAddress IPAddress = receivePacket.getAddress();
			
			//Nhan du lieu gui den
			String chuoi = new String(receivePacket.getData()).substring(0, receivePacket.getLength());
			int port = receivePacket.getPort();
			
			ScriptEngineManager manager = new ScriptEngineManager();
			Object result = "";
			try {
				result = manager.getEngineByName("js").eval(chuoi);
				chuoi= result.toString();
			} catch (ScriptException e) {
				chuoi = "Loi cu phap";
			} 
			sendData = chuoi.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, chuoi.length(), IPAddress, port);
			System.out.println(chuoi);
			
			serverSocket.send(sendPacket);
		}
	}
}
