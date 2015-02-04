package ngay1.bai2;

import java.util.*;
import java.net.*;
import java.io.*;



public class Client {
	public static void main(String [] args) throws IOException
	{
		Scanner sc=new Scanner(System.in);
		System.out.print("Nhap bieu thuc:");
		String a=sc.nextLine();
		Socket socket=new Socket("Localhost",2500);
		DataInputStream din = new DataInputStream(socket.getInputStream());
		DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
		dout.writeUTF(a);
	    String ketqua=din.readUTF();
		System.out.print(ketqua);
	}
}