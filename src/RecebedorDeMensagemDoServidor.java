import java.io.InputStream;
import java.util.Scanner;

import javax.swing.JLabel;

class RecebedorDeMensagemDoServidor implements Runnable {

	private InputStream servidor;
	public Gui gui;
	public String nick;
	
	public RecebedorDeMensagemDoServidor(Gui gui, InputStream servidor) {
		this.gui = gui;
		this.servidor = servidor;
	}

	public void run() {
		try(Scanner s = new Scanner(this.servidor)) {
			while (s.hasNextLine()) {
				String  m = s.nextLine();
				System.out.println(m);
				gui.chatRoom("",m, JLabel.LEFT);
				//gui.chatRoom("",s.nextLine());
			}
		}
	}
}
