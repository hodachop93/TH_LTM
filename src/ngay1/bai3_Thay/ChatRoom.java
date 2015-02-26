package ngay1.bai3_Thay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.*;

public class ChatRoom {
	public JFrame frame;
	public JTextArea Room;
	public JTextField msg;
	public JTextArea Joiners;
	
	public String NickName;
	public Socket soc;
	public DataInputStream dis;
	public DataOutputStream dos;
	
	public ChatRoom(String NickName){
		this.NickName = NickName;
		this.frame = new JFrame("Chat Room!");
		this.frame.setSize(480 ,400);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setLayout(null);
		
		JLabel lr=new JLabel("Chat room! Hello "+this.NickName);
		lr.setBounds(20, 10, 300, 25);
		this.frame.add(lr);
		
		this.Room=new JTextArea("");
		this.Room.setBounds(20, 50, 300, 250);
		this.Room.setEditable(false);
		this.frame.add(Room);
		
		JLabel lsd=new JLabel("Send");
		lsd.setBounds(20, 325, 50, 25);
		this.frame.add(lsd);
		
		this.msg=new JTextField("");
		this.msg.setBounds(100, 325, 200, 25);
		msg.addActionListener(new SendActionListener(this));
		this.frame.add(msg);
		
		JButton OK=new JButton("Send");
		OK.setBounds(320, 325, 80, 25);
		OK.addActionListener(new SendActionListener(this));
		this.frame.add(OK);
		
		JLabel lj=new JLabel("Joiners");
		lj.setBounds(620, 10, 50, 50);
		this.frame.add(lj);
		
		this.Joiners = new JTextArea("");
		this.Joiners.setBounds(330, 50, 120, 250);
		this.Joiners.setEditable(false);
		this.frame.add(Joiners);
		
		frame.setVisible(true);
		
		try{
			soc = new Socket("localhost", 5000);
			this.dis = new DataInputStream(soc.getInputStream());
			this.dos = new DataOutputStream(soc.getOutputStream());
			new ThreadedHandler(this).start();
			this.dos.writeUTF("Joint,"+this.NickName);
		}catch(IOException e){this.frame.dispose();}
	}
	public class SendActionListener implements ActionListener{
		ChatRoom cr;
		public SendActionListener(ChatRoom cr){
			this.cr = cr;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (!cr.msg.getText().equals("")){
				cr.Room.setText(cr.NickName+"> "+cr.msg.getText()+"\n"+cr.Room.getText());
				try{
						this.cr.dos.writeUTF("Msg,"+cr.msg.getText());
				}catch(IOException e1){cr.frame.dispose();new LoginFrame("");}
		
				cr.msg.setText("");
			}
		}
	}
	public class ThreadedHandler extends Thread{
		ChatRoom cr;
		public ThreadedHandler(ChatRoom cr){
			this.cr=cr;
		}
		public void run(){
			String ch="";
			try{
				while (true){
					ch = dis.readUTF();
					String cmd=ch.substring(0, ch.indexOf(","));
					String msg=ch.substring(ch.indexOf(",")+1);
					if (cmd.equals("Msg")) 
						this.cr.Room.setText(msg+"\n"+cr.Room.getText());
					else
						this.cr.soc.close();
				}
			}catch(IOException e){cr.frame.dispose();new LoginFrame("");}
		}
	}
}
