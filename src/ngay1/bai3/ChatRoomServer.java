package ngay1.bai3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;


public class ChatRoomServer {
	public final static int daytimePort = 6000;
	public Vector<ThreadedHandler> cls=new Vector<ThreadedHandler>();
	
	public ChatRoomServer(){
		ServerSocket theServer;
		Socket theConnection;
		try {
			theServer = new ServerSocket(daytimePort);
			while (true) {
				theConnection = theServer.accept();
				System.out.println("Have Connection!");
				new ThreadedHandler(this,theConnection).start();
			}
		}catch (IOException e) {
		System.err.println(e);
		}
	}
	
	public static void main(String[] args) {
		new ChatRoomServer();
	}
	public class ThreadedHandler extends Thread{
		ChatRoomServer crsv;
		public Socket incoming;
		public DataInputStream dis;
		public DataOutputStream dos;
		public String name;
		public ThreadedHandler(ChatRoomServer crsv, Socket i)
		{
			this.crsv=crsv;
			this.incoming=i;
			try{
				this.dis = new DataInputStream(incoming.getInputStream());
				this.dos = new DataOutputStream(incoming.getOutputStream());
			}catch(IOException e){}
		}
		public void run(){
			String ch="";
			try{
				ch = dis.readUTF();
				String cmd=ch.substring(0, ch.indexOf(","));
				String msg=ch.substring(ch.indexOf(",")+1);
				if (!cmd.equals("Joint")) incoming.close();
				System.out.println("Hello "+msg);
				this.name=msg;
				this.crsv.cls.add(this);
				
				while (true)
				{
					ch = dis.readUTF();
					cmd=ch.substring(0, ch.indexOf(","));
					msg=ch.substring(ch.indexOf(",")+1);
					if (cmd.equals("Msg")) {
						for (int i=0;i<this.crsv.cls.size();i++){
							ThreadedHandler temp=this.crsv.cls.get(i);
							if (temp!=this){
								temp.dos.writeUTF("Msg,"+this.name+">>"+msg);
							}
						}
					}
					else{
						incoming.close();
						this.crsv.cls.remove(this);
					}
					
				}
			}catch(IOException e){crsv.cls.remove(this);}
			
		}
	}
}