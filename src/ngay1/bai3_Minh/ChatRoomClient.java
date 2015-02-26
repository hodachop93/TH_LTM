package ngay1.bai3_Minh;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Area;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.*;
public class ChatRoomClient extends JFrame implements ActionListener{
	private static final int port = 2500;
	private JTextArea AreaChat;
	private JTextField msg;
	private JTextArea AreaJoiners;
	private JButton btnSend;
	
	public String NickName;
	public Socket soc;
	public DataInputStream dis;
	public DataOutputStream dos;
	public ChatRoomClient(String name) {
		this.NickName = name;
		this.setBounds(100, 100, 480, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		
		JLabel lr=new JLabel("Chat room! Hello "+this.NickName);
		lr.setBounds(20, 10, 300, 25);
		this.add(lr);
		
		AreaChat = new JTextArea();
		AreaChat.setBounds(20, 50, 300, 250);
		this.add(AreaChat);
		AreaChat.setEditable(false);
		
		JLabel lsd=new JLabel("Send");
		lsd.setBounds(20, 325, 50, 25);
		this.add(lsd);
		
		msg = new JTextField();
		msg.setBounds(100, 325, 200, 25);
		msg.addActionListener(this);
		this.add(msg);
		
		AreaJoiners = new JTextArea();
		AreaJoiners.setBounds(330, 50, 120, 250);
		AreaJoiners.setEditable(false);
		this.add(AreaJoiners);
		
		btnSend = new JButton("Send");
		btnSend.setBounds(320, 325, 100, 25);
		this.add(btnSend);
		btnSend.addActionListener(this);
		this.setVisible(true);
		try{
			soc = new Socket("localhost", port);
			dis = new DataInputStream(soc.getInputStream());
			dos = new DataOutputStream(soc.getOutputStream());
			new ThreadHandler(this).start();
			dos.writeUTF("Joined," + NickName);
		} catch (Exception e){}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (!msg.getText().equals("")){
			AreaChat.setText(AreaChat.getText() + "\n" + NickName + ">" + msg.getText());
			try{
				dos.writeUTF("Msg," + msg.getText());
			}
			catch (Exception ex){
				System.err.println(ex.getMessage());
				this.dispose();
				new LoginFrame();
			}
			msg.setText("");
		}
	}
	class ThreadHandler extends Thread{
		ChatRoomClient crc;
		public ThreadHandler(ChatRoomClient _crc) {
			// TODO Auto-generated constructor stub
			this.crc = _crc;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			String xauNhan;
			try{
				while (true){
					xauNhan = dis.readUTF();
					String dinhDanh = xauNhan.substring(0, xauNhan.indexOf(","));
					String message = xauNhan.substring(xauNhan.indexOf(",")+1);
					if (dinhDanh.equals("Msg"))
						AreaChat.setText(AreaChat.getText() + "\n" + message);
					else if (dinhDanh.equals("Joined"))
						AreaJoiners.setText(message);
					else
						soc.close();
				}
			}
			catch (Exception ex){
				this.crc.dispose();
				new LoginFrame();
			}
		}
	}

	
}
