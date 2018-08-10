

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.Random;
import java.util.Scanner;

import javax.swing.*;

public class Gui extends JFrame implements ActionListener{
	
	
	int n = 0;
	JLabel [] tenMessages = new JLabel [15];
	Messenger myMessenger;
	JTextField [] info = new JTextField [2];
	public JTextField textArea = new JTextField();
	public JButton send= new JButton(">");
	
	public boolean sent = false;
			
	Gui(Messenger myMessenger){
		
		for(int i = 0; i<15; i++) {
			tenMessages[i] = new JLabel();
		}
		
		this.myMessenger = myMessenger;
		
		firstScreen();
		
		this.setSize(400, 650);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void firstScreen() {
		// TODO Auto-generated method stub
		
		
		Container window = getContentPane();
		window.setBackground(Color.WHITE);
	    window.setLayout(null);
	    
	    JButton createServer = new JButton("Create Server");
	    createServer.addActionListener(this);
	    createServer.setBounds(90, 200, 200, 75);
	    
	    JButton connect = new JButton("Connect");
	    connect.addActionListener(this);
	    connect.setBounds(90, 300, 200, 75);
	    
	    window.add(createServer);
	    window.add(connect);
		
	}

	private void connectScreen() {
		// TODO Auto-generated method stub
		Container window = getContentPane();
		window.removeAll();
		
		window.setSize(400,650);
		
		Container list = new JPanel(new GridLayout(3,1));
		list.setBackground(Color.WHITE);
		
		window.add(list);
		list.setBounds(10, 200, 360, 200);
		
		Container nick = new JPanel(new GridLayout(2,1));
		nick.setBackground(Color.WHITE);
		
		JLabel nickLabel = new JLabel("NICK:");
		nick.add(nickLabel);
		JTextField nickField = new JTextField();
		nick.add(nickField);
		info[0] = nickField;
		
		Container server = new JPanel(new GridLayout(2,1));
		server.setBackground(Color.WHITE);
		
		JLabel serverLabel = new JLabel("SERVER:");
		server.add(serverLabel);
		JTextField serverField = new JTextField("127.0.0.1");
		server.add(serverField);
		info[1] = serverField;
		
		JButton NEXT = new JButton("NEXT");
		NEXT.addActionListener(this);
		
		nickLabel.setHorizontalAlignment(JLabel.CENTER);
		serverLabel.setHorizontalAlignment(JLabel.CENTER);
		
		list.add(nick);
		list.add(server);
		list.add(NEXT);
		
		window.validate();
		
		
	}

	private void createServerScreen() {
		// TODO Auto-generated method stub
		myMessenger.createServer();
		Container window = getContentPane();
		window.removeAll();
		window.setSize(400,650);
		
		
		
	}
	
	public void print(String s) {
		
		JLabel A = new JLabel(s);
		
		Container window = getContentPane();
		
		window.repaint();
		
		/*Container messages = new JPanel();
		
		messages.setBounds(10, 25, 380, 565);
		
		
		
		window.add(messages);
		*/
		
		A.setAlignmentX(CENTER_ALIGNMENT);
		A.setAlignmentY(CENTER_ALIGNMENT);
		
		window.setLayout(new GridLayout(10, 1));
	    window.add(A);
	    A.setHorizontalAlignment(JLabel.CENTER);
		
		window.repaint();
		window.validate();
		
	
	}
	
	public void chatRoom(String nick, String s, int align) {
		// TODO Auto-generated method stub
		send.addActionListener(this);
		JLabel aux;
		textArea.setBounds(10, 540, 330, 50);
		send.setBounds(335, 540, 50, 50);
		
		System.out.println("Recebemos a mensage: "+s);
		
		Container window = getContentPane();
		window.add(send);
		
		/*Container messages = new JPanel();
		
		messages.setBounds(10, 25, 380, 565);
		
		
		
		window.add(messages);
		*/
		aux = new JLabel(tenMessages[0].getText());
		aux.setHorizontalAlignment(tenMessages[0].getHorizontalAlignment());
		
		tenMessages[0] = new JLabel(s);
		tenMessages[0].setHorizontalAlignment(align);
		
		//tenMessages[9] = "";
		
		
		JLabel aux2;
		
		for(int i = 1; i<15; i++) {
			
			aux2 = new JLabel(tenMessages[i].getText());
			aux2.setHorizontalAlignment(tenMessages[i].getHorizontalAlignment());
			
			tenMessages[i] = new JLabel(aux.getText());
			tenMessages[i].setHorizontalAlignment(aux.getHorizontalAlignment());
			
			aux = new JLabel(aux2.getText());
			aux.setHorizontalAlignment(aux2.getHorizontalAlignment());
			
			
		}
		
		for(int i = 0; i<10; i++) {
			
			System.out.println(i +":" + tenMessages[i].getText());
			
			
			
		}
		
		Container messages = new JPanel(new GridLayout(10,1));
		messages.setBounds(10, 10, 370, 530);
		
		for(int i = 9; i>=0; i--) {
			
			messages.add(tenMessages[i]);
		}
		
		
		
	    window.add(messages);
	    window.add(textArea);
	    
	    
	    
		
		window.validate();
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton x = (JButton) e.getSource();

		String action = x.getText();
		
		if(action.equals("Create Server")) 
			createServerScreen();
			
		if(action.equals("Connect"))
			connectScreen();
		
		if(action.equals("NEXT")) {
			if(!info[0].getText().equals("") && !info[1].getText().equals("")) {
				Container window = getContentPane();
				window.removeAll();
				myMessenger.createClient(info[0].getText(), info[1].getText(), 12345);
			}
		}
		if(action.equals(">")) {
			System.out.println("evento");
			Scanner teclado = new Scanner(System.in);
			teclado.reset();
			sent=true;
		}
		
		
	}


	

	
	
	

}
