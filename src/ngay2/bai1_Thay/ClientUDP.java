package ngay2.bai1_Thay;


import java.net.*;
import java.util.*;

public class ClientUDP {
	public static void main(String [] args) throws Exception{
		InetAddress ip = InetAddress.getByName("localhost");
		DatagramSocket Socket = new DatagramSocket();
		Vector<String> list= new Vector<String>();
		new nhan(Socket, list).start();
		while (list.size()<100){
			Scanner sc = new Scanner(System.in);
			String st = sc.next();
			list.add(st);
			st=list.size()+"@"+st+"@";
			//tao la thu gui
			DatagramPacket pac = new DatagramPacket(st.getBytes(), st.getBytes().length, ip, 5000);
			//gui
			Socket.send(pac);
		}
	}
}

class nhan extends Thread{
	DatagramSocket Socket;
	Vector<String> list;
	byte rest[] =new byte[1024];
	public nhan(DatagramSocket Socket,Vector<String> list){
		this.Socket = Socket;
		this.list = list;
	}
	public void run(){
			while(true){
				try{
				//tao la thu nhan
				DatagramPacket pacre= new DatagramPacket(rest, rest.length);
				Socket.receive(pacre);
				String stre=new String(pacre.getData());
				String temp[]=stre.split("@");
				if (temp[1].compareTo("OK")==0){
					//System.out.println(temp[0]);
					int num=Integer.parseInt(temp[0])-1;
					System.out.println(list.get(num)+" is Ok!");
				}
				//else System.out.println("Error");
			}catch(Exception e){}
		}
	}
}
