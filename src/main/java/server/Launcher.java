package server;

public class Launcher {
	public static void main(String[] args) throws InterruptedException {
		MessageServer server = MessageServer.getInstance();
		server.start();
	}
}
