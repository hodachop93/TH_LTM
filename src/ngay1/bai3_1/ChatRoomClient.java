package ngay1.bai3_1;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Area;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.*;
public class ChatRoomClient extends JFrame implements ActionListener{
	private static final int port = 2500;
	public JTextArea AreaChat;
	public JTextField msg;
	public JTextArea AreaJoiners;
	
	public String NickName;
	public Socket soc;
	public DataInputStream dis;
	public DataOutputStream dos;
	public ChatRoomClient(String name) {
		this.NickName = name;
		Container container = this.getContentPane();
		this.setBounds(100, 100, 480, 400);
		
		AreaChat = new JTextArea();
		AreaChat.setBounds(20, 50, 300, 250);
		container.add(AreaChat);
		AreaChat.setEditable(false);
		
		JLabel lsd=new JLabel("Send");
		lsd.setBounds(20, 325, 50, 25);
		container.add(lsd);
		
		msg = new JTextField();
		msg.setBounds(100, 325, 200, 25);
		msg.addActionListener(this);
		container.add(msg);
		
		AreaJoiners = new JTextArea();
		AreaJoiners.setBounds(330, 50, 120, 250);
		AreaJoiners.setEditable(false);
		container.add(AreaJoiners);
		this.setVisible(true);
		try{
			soc = new Socket("localhost", port);
		} catch (Exception e){}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
}
