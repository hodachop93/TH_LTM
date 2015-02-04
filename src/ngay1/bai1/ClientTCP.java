package ngay1.bai1;

import java.util.*;
import java.io.*;
import java.net.*;

public class ClientTCP {
	public static void main(String [] args) throws IOException
	{
		Scanner sc=new Scanner(System.in);
		System.out.print("Nhap xau s:");
		String s=sc.nextLine();
	
		
		Socket socket=new Socket("Localhost",5000);
		DataInputStream din = new DataInputStream(socket.getInputStream());
		DataOutputStream dout = new DataOutputStream(socket.getOutputStream());		
		dout.writeUTF(s);
		String kq1=din.readUTF();
		String kq2=din.readUTF();
		String kq3=din.readUTF();
		int kq4=din.readInt();
		System.out.print("Chuoi thanh in HOA:\n");
		System.out.print(kq1 +"\n");
		System.out.print("Chuoi thanh in THUONG:\n");
		System.out.print(kq2 +"\n");
		System.out.print("Chuoi thanh vua HOA vua THUONG:\n");
		System.out.print(kq3 + "\n");
		System.out.print("So tu trong chuoi:\n");
		System.out.print(kq4 +"\n");
	}

}
