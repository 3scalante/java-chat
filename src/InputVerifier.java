

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JLabel;

public class InputVerifier implements Runnable{
	Gui gui;
	PrintStream saida;
	String nickname;
	
	InputVerifier(Gui gui, PrintStream saida, String nickname){
		this.gui = gui;
		this.saida = saida;
		this.nickname = nickname;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Verifier");
		while (true) {
			System.out.print("");
			if(gui.sent) {
				String m = nickname +"> " + gui.textArea.getText();
				saida.println(m);
				//saida.println(nickname + ": " + teclado.nextLine());
				gui.chatRoom("",m, JLabel.RIGHT);
				gui.textArea.setText("");
				gui.sent = false;
			}
		}
	}

}
