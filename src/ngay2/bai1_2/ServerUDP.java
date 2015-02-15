package ngay2.bai1_2;

import java.net.*;

public class ServerUDP {
	public static void main(String [] args) throws Exception{
		DatagramSocket socket =new DatagramSocket(5000);
		byte[] receiveData = new byte[1024]; 
		while (true){
			DatagramPacket packet = new DatagramPacket(receiveData, 1024);
			socket.receive(packet);
			new xuly(socket,packet).start();
		}
	}
}

class xuly extends Thread{
	DatagramSocket socket;
	DatagramPacket packet;
	public xuly(DatagramSocket socket, DatagramPacket packet){
		this.socket = socket;
		this.packet = packet;
	}
	
	public void run(){		
		try{
			String temp = new String(packet.getData());
			
			String ct[] = temp.split("@");
			System.out.println(ct[0]);
			String rs = ct[1];
			boolean ok=true;
			for (int i=0;i<rs.length()/2;i++)
				if (rs.charAt(i)!=rs.charAt(rs.length()-1-i)){
					ok = false;
					break;
				}
			String re = "Error";
			if (ok) re = "OK";
			re=ct[0]+"@"+re+"@";
			DatagramPacket rp=new DatagramPacket(re.getBytes(), re.getBytes().length, 
						packet.getAddress(), packet.getPort());
			socket.send(rp);
		}catch(Exception e){};
	}
}