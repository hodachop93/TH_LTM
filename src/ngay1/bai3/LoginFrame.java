package ngay1.bai3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LoginFrame {
	public JFrame frame;
	public LoginFrame(String ms){
		frame = new JFrame("Login");
		frame.setSize(400 ,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		
		JLabel lname=new JLabel("Name");
		lname.setBounds(50, 50, 50, 50);
		frame.add(lname);
		
		final JTextField Name=new JTextField("");
		Name.setBounds(120, 50, 200, 50);
		frame.add(Name);
		
		final JLabel msg=new JLabel("Msg:"+ms);
		msg.setBounds(120, 200, 200, 50);
		frame.add(msg);
		
		JButton OK=new JButton("Login");
		OK.setBounds(120, 120, 200, 50);
		OK.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!Name.getText().equals("")){
					new ChatRoom(Name.getText());
					frame.dispose();
				}
				else msg.setText("Msg: Please input your name!");	
			}
		});
		frame.add(OK);
		
		frame.setVisible(true);
	}
	public static void main(String[] args)
	{
		new LoginFrame("");
	}
}