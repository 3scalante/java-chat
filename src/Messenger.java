

public class Messenger extends Thread{
	Gui myGui = new Gui(this);
	Messenger(){
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Messenger();
	}
	
	public void createClient(String nick, String host, int porta) {
		Cliente c = new Cliente(myGui, nick, host, porta);
		Thread client = new Thread(c);
		client.start();
	}
	
	
	public void createServer() {
		Servidor s = new Servidor(12345, myGui);
		Thread server = new Thread(s);
		server.start();
	}
}
