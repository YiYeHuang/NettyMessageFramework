package server;

import sun.management.ThreadInfoCompositeData;

public class Launcher {
	public static void main(String[] args) throws InterruptedException {
		MessageServer server = new MessageServer();
		server.start();

		Thread.sleep(10000l);

		server.stop();
	}
}
