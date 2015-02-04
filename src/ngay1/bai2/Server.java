package ngay1.bai2;

import java.util.*;
import java.net.*;
import java.io.*;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Server {

	public static void main(String [] args) throws IOException
	{
		ServerSocket server=new ServerSocket(2500);
		while (true){
			Socket socket=server.accept();
			(new ThreadXuly(socket)).start();

		}
	}
}
class ThreadXuly extends Thread{
	Socket socket;
	public ThreadXuly(Socket s){
		this.socket=s;
	}
	public void run(){
		try{
			DataInputStream din = new DataInputStream(socket.getInputStream());
			String chuoi = din.readUTF();
			ScriptEngineManager manager = new ScriptEngineManager();
			Object result = "";
			try {
				result = manager.getEngineByName("js").eval(chuoi);
				chuoi= result.toString();
			} catch (ScriptException e) {
				chuoi = "Loi cu phap";
			}        
		   
			DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
			dout.writeUTF(chuoi);
			socket.close();
		}catch(IOException e){}
	}
}