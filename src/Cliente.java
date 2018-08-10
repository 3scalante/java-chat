import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import javax.swing.JLabel;

public class Cliente  implements Runnable{

	public Gui gui;
	private String host, nickname;
	private int porta;

	public Cliente(Gui gui, String nickname, String host, int porta) {
		this.nickname = nickname;
		this.host = host;
		this.porta = porta;
		this.gui = gui;
	}

	public void run() {
		// Cria uma concexão com o servidor
		try {
			Socket cliente = new Socket(this.host, this.porta); 
			 
			PrintStream saida = new PrintStream(cliente.getOutputStream());
			gui.chatRoom(nickname,"O cliente se conectou ao servidor!", JLabel.CENTER);
			gui.setTitle(nickname);
			saida.println(nickname+" se conectou ao chat!");
			Scanner teclado = new Scanner(System.in);
			RecebedorDeMensagemDoServidor r = new RecebedorDeMensagemDoServidor(gui, cliente.getInputStream());
			new Thread(r).start();
			
			InputVerifier i = new InputVerifier(gui, saida, nickname);
			new Thread(i).start();
			
			
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Falha na conex�o.");
			System.exit(-1);
		}
		
	}
	
	/*public static void main(String[] args) {
		new Cliente("Jos�", "127.0.0.1", 12345).executa();
	}*/
}
