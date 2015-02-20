package ngay1.bai3_1;
import java.io.*;
import java.net.*;
import java.util.*;

public class ChatRoomServer {
	public static final int port = 2500;
	public Vector<ThreadHandler> list = new Vector<ThreadHandler>();
	public ChatRoomServer(){
		
	}

}
class ThreadHandler extends Thread{
	ChatRoomServer crsv;
	private Socket incomingSocket;
	private DataInputStream dis;
	private DataOutputStream dos;
	private String name;
	public ThreadHandler(ChatRoomServer crsv, Socket incomingSocket){
		this.crsv = crsv;
		this.incomingSocket = incomingSocket;
		try {
			this.dis = new DataInputStream(this.incomingSocket.getInputStream());
			this.dos = new DataOutputStream(this.incomingSocket.getOutputStream());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	@Override
	public void run() {
		String s;
		try{
			s = dis.readUTF();
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
	
}
