package ngay1.bai3_1;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame implements ActionListener{
	
	public LoginFrame(){
		super("Login");
		setBounds(100, 100, 400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(3,2));
		JLabel lblName = new JLabel("Name: ");
		add(lblName);
		JTextField txtName = new JTextField();
		add(txtName);
		add(new JLabel());
		JButton btnLogin = new JButton("Login");
		add(btnLogin);
		JLabel txtMsg = new JLabel("Message");
		add(txtMsg);
		JLabel txtError = new JLabel();
		add(txtError);
		setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new LoginFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
