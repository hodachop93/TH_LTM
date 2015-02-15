package ngay2.bai1_3;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClientUDP {
	public static void main(String[] args) {
		try {
			Scanner keyboard = new Scanner(System.in);
			System.out.print("Nhap chuoi: ");
			String chuoi = keyboard.nextLine();
			byte[] sendData = chuoi.getBytes();

			DatagramSocket socket = new DatagramSocket();
			InetAddress IP = InetAddress.getByName("localhost");

			DatagramPacket sendPacket = new DatagramPacket(sendData,
					sendData.length, IP, 1993);
			socket.send(sendPacket);

			byte[] receiveData = new byte[1024];
			DatagramPacket receivePacket = new DatagramPacket(receiveData,
					receiveData.length);
			String chuoihoa = "";
			String chuoithuong = "";
			String chuoihoathuong = "";
			String sochucai = "";
			while (true) {
				socket.receive(receivePacket);
				String chuoinhan = new String(receivePacket.getData())
						.substring(0, receivePacket.getLength());
				if (chuoinhan.startsWith("hoa")) {
					chuoihoa = chuoinhan.substring(3, chuoinhan.length());
					System.out.println("Chuoi hoa da nhan: " + chuoihoa);
				} else if (chuoinhan.startsWith("thuong")) {
					chuoithuong = chuoinhan.substring(6, chuoinhan.length());
					System.out.println("Chuoi thuong da nhan: " + chuoithuong);
				} else if (chuoinhan.startsWith("HOATHUONG")) {
					chuoihoathuong = chuoinhan.substring(9, chuoinhan.length());
					System.out.println("Chuoi vua hoa vua thuong da nhan: "
							+ chuoihoathuong);
				} else if (chuoinhan.startsWith("dem")) {
					sochucai = chuoinhan.substring(3, chuoinhan.length());
					System.out.println("So tu cua chuoi: " + sochucai);
				} else {					
					socket.close();
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}