package ngay1.bai3_Minh;
import java.io.*;
import java.net.*;
import java.util.*;

public class ChatRoomServer {
	public static final int port = 2500;
	public Vector<ThreadHandler> list = new Vector<ThreadHandler>();
	private ServerSocket serverSocket;
	private Socket connection;
	public ChatRoomServer(){
		try {
			serverSocket = new ServerSocket(port);
			while (true){
				connection = serverSocket.accept();
				new ThreadHandler(this, connection).start();
			}
		} catch (IOException e) {
			System.out.println("loi tai chat room server");
			System.out.println(e.getMessage());
		}
		
	}
	public static void main(String[] args){
		new ChatRoomServer();
	}
	public class ThreadHandler extends Thread{
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
				System.out.println("loi tai thread");
				System.out.println(e.getMessage());
			}
		}
		@Override
		public void run() {
			String xauNhan;
			String dinhDanh, message;
			try{
				xauNhan = dis.readUTF();
				dinhDanh = xauNhan.substring(0, xauNhan.indexOf(","));
				message = xauNhan.substring(xauNhan.indexOf(",")+1);
				if (!dinhDanh.equals("Joined")){
					this.incomingSocket.close();
					return;
				}
				this.name = message;
				this.crsv.list.add(this);
				updateListJoined();
				while (true){
					xauNhan = dis.readUTF();
					dinhDanh = xauNhan.substring(0, xauNhan.indexOf(","));
					message = xauNhan.substring(xauNhan.indexOf(",")+1);
					if (dinhDanh.equals("Msg")){
						for (int i=0; i < this.crsv.list.size(); i++){
							ThreadHandler temp = this.crsv.list.get(i);
							if (temp!=this){
								temp.dos.writeUTF("Msg,"+this.name+">"+message);
							}
						}
					}
					else{
						
						this.crsv.list.remove(this);
						incomingSocket.close();
					}
				}
				
				
			} catch (Exception e){
				this.crsv.list.remove(this);
				updateListJoined();
			}
		}
		
		private void updateListJoined(){
			String listJoiner="";
			for (int i = 0; i < this.crsv.list.size(); i++){
				ThreadHandler temp = this.crsv.list.get(i);
				listJoiner += temp.name + "\n";
			}
			for (int i = 0; i < this.crsv.list.size(); i++){
				ThreadHandler temp = this.crsv.list.get(i);
				try {
					temp.dos.writeUTF("Joined," + listJoiner);
				} catch (IOException e) {
					System.out.println("loi tai update list");
					System.out.println(e.getMessage());
				}
			}
		}
		
	}
}

