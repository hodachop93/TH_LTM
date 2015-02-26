package ngay1.bai3_Minh;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame implements ActionListener{
	JTextField txtName;
	JLabel txtError;
	public LoginFrame(){
		super("Login");
		setBounds(100, 100, 400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(3,2));
		JLabel lblName = new JLabel("Name: ");
		add(lblName);
		txtName = new JTextField();
		add(txtName);
		txtName.addActionListener(this);
		add(new JLabel());
		JButton btnLogin = new JButton("Login");
		add(btnLogin);
		btnLogin.addActionListener(this);
		JLabel txtMsg = new JLabel("Message");
		add(txtMsg);
		txtError = new JLabel();
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
		if (!txtName.getText().equals("")){
			new ChatRoomClient(txtName.getText());
			this.dispose();
		}
		else{
			txtError.setText("Please: Enter your name!!!");
		}
	}

}
