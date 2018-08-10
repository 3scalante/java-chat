import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

class TratadorDeMensagemDoCliente implements Runnable {

	private Socket cliente;
	private Servidor servidor;
	private Gui gui;
	
	public TratadorDeMensagemDoCliente(Gui gui, Socket cliente, Servidor servidor) {
		this.cliente = cliente;
		this.servidor = servidor;
		this.gui = gui;
	}

	public void run() {
		try(Scanner s = new Scanner(this.cliente.getInputStream())) {
			while (s.hasNextLine()) {
				servidor.distribuiMensagem(gui, this.cliente, s.nextLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
