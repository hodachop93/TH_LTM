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
			DatagramPacket receivePacket = new DatagramPacket(receiveData, 2);
			//Bat dau nhan du lieu vao packet, receiveData dong vai tro nhu 1 buffer input
			serverSocket.receive(receivePacket);
			
			//Lay dia chi ip cua may da gui de lieu den Datagram
			InetAddress IPAddress = receivePacket.getAddress();
			
			//Nhan du lieu gui den
			String sentence = new String(receivePacket.getData()).substring(0, receivePacket.getLength());
			int port = receivePacket.getPort();

			//Xu ly du lieu gui den --> Chuoi hoa
			String chuoiHoa = Process.toUpperCase(sentence);
			System.out.println(chuoiHoa);
			sendData = chuoiHoa.getBytes();
				//Gui di 1 packet
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);

			serverSocket.send(sendPacket);
		
			
						
			// --> Chuoi thuong
			String chuoiThuong = Process.toLowerCase(sentence);
			sendData = chuoiThuong.getBytes();
			DatagramPacket sendPacket1 = new DatagramPacket(sendData, sendData.length, IPAddress, port);

			serverSocket.send(sendPacket1);
			
			//--> Chuoi vua hoa vua thuong
			String chuoiHoaThuong = Process.toUpper_LowerCase(sentence);
			sendData = chuoiHoaThuong.getBytes();
			DatagramPacket sendPacket2 = new DatagramPacket(sendData, sendData.length, IPAddress, port);
			System.out.println(sendPacket2.getLength());
			serverSocket.send(sendPacket2);
			
			// Dem so tu trong chuoi
			int length = Process.getLength(sentence);
			String stringLength = Integer.toString(length);
			System.out.println(stringLength);
			sendData = stringLength.getBytes();
			DatagramPacket sendPacket3 = new DatagramPacket(sendData, sendData.length, IPAddress, port);

			serverSocket.send(sendPacket3);
		
			
			
			
		}
	}
}
class Process {
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
}