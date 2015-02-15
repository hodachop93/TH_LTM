package ngay2.bai1_3;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServerUDP {
	public static String doiHoaThuong(String s) {
		String doi = "";
		for (int i = 0; i < s.length(); i++) {
			String tam = s.charAt(i) + "";
			if (s.charAt(i) >= 97 && s.charAt(i) <= 122) {
				doi += tam.toUpperCase();
			} else if (s.charAt(i) >= 65 && s.charAt(i) <= 97) {
				doi += tam.toLowerCase();
			} else {
				doi += tam;
			}
		}
		return doi;
	}

	public static int demChu(String s) {
		String[] tam = s.split(" ");
		int soChuCai = tam.length;
		for (int i = 0; i < tam.length; i++) {
			if (tam[i].equals("")) {
				soChuCai--;
			}
		}
		return soChuCai;
	}

	public static void main(String[] args) {
		try {
			DatagramSocket serverSocket = new DatagramSocket(1993);
			byte[] receiveData = new byte[1024];
			byte[] sendData = new byte[1024];

			while (true) {
				DatagramPacket receivePacket = new DatagramPacket(receiveData,
						receiveData.length);
				serverSocket.receive(receivePacket);
				String chuoinhan = new String(receivePacket.getData())
						.substring(0, receivePacket.getLength());

				InetAddress IP = receivePacket.getAddress();
				int port = receivePacket.getPort();

				sendData = ("hoa" + chuoinhan.toUpperCase())
						.getBytes();
				DatagramPacket sendPacketChuoiHoa = new DatagramPacket(
						sendData, sendData.length, IP, port);
				serverSocket.send(sendPacketChuoiHoa);

				sendData = ("thuong" + chuoinhan.toLowerCase())
						.getBytes();
				DatagramPacket sendPacketChuoiThuong = new DatagramPacket(
						sendData, sendData.length, IP, port);
				serverSocket.send(sendPacketChuoiThuong);

				sendData = ("HOATHUONG" + doiHoaThuong(chuoinhan))
						.getBytes();
				DatagramPacket sendPacketChuoiHoaThuong = new DatagramPacket(
						sendData, sendData.length, IP, port);
				serverSocket.send(sendPacketChuoiHoaThuong);

				sendData = ("dem" + demChu(chuoinhan)).getBytes();
				DatagramPacket sendPacketSoChuCai = new DatagramPacket(
						sendData, sendData.length, IP, port);
				serverSocket.send(sendPacketSoChuCai);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}