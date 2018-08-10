import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor implements Runnable{

	private int porta;             // Porta de comunicaÃ§Ã£o
	private List<Socket> clientes; // Lista dos clientes conectados
	public Gui gui;
	
	public Servidor(int porta, Gui myGui) {
		this.porta = porta;
		this.clientes = new ArrayList<>();
		this.gui = myGui;
	}

	public void run() {
		try {
			ServerSocket servidor = new ServerSocket(this.porta);
			System.out.println("Porta " + this.porta + " aberta!");
	
			while (true) {
				// Aceita uma coneção de um novo cliente (accept é BLOQUEANTE)
				Socket cliente = servidor.accept();
				System.out.println("Nova conexão com o cliente " + cliente.getInetAddress().getHostAddress());
	
				// Adiciona o novo cliente a lista de clientes
				this.clientes.add(cliente);
	
				// Cria uma thread responsável por receber as mensagens do novo cliente
				TratadorDeMensagemDoCliente tc = new TratadorDeMensagemDoCliente(gui, cliente, this);
				new Thread(tc).start();
			}
		} catch (IOException e) {
				e.printStackTrace();
				System.err.println("Falha na conexão.");
				System.exit(-1);
		}
	}

	public void distribuiMensagem(Gui gui, Socket clienteQueEnviou, String msg) {
		// Para cada cliente pertencente a lista envia sua mensagem aos outros clientes
		for (Socket cliente : this.clientes) {
			if(!cliente.equals(clienteQueEnviou)){
				try {
					PrintStream ps = new PrintStream(cliente.getOutputStream());
					ps.println(msg);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	


	
}
