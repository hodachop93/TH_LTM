package ngay1.bai1;

import java.util.*;
import java.io.*;
import java.net.*;
public class ServerTCP {
	 public final static int chatPort = 5000;
	 public static void main(String[] args) throws Exception{
	   ServerSocket serverSocket = new ServerSocket(chatPort);
	   while (true){
		   Socket sk = serverSocket.accept();
		   new ThreadXuLy(sk).start();
	   }
	  }
}
class ThreadXuLy extends Thread{
	Socket sc;
	public ThreadXuLy(Socket s){
		this.sc = s;
	}
	@Override
	public void run(){
		try{
			DataInputStream din = new DataInputStream(sc.getInputStream());
			DataOutputStream dout = new DataOutputStream(sc.getOutputStream());
			String input = din.readUTF();
			String kq1 = Process.toUpperCase(input);
			String kq2 = Process.toLowerCase(input);
			String kq3 = Process.toUpper_LowerCase(input);
			int kq4 = Process.getLength(input);
			dout.writeUTF(kq1);
			dout.writeUTF(kq2);
			dout.writeUTF(kq3);
			dout.writeInt(kq4);
		}
		catch (Exception e){
			
		}
	}
}
