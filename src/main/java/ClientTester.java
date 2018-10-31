import client.MessageClient;

import java.io.UnsupportedEncodingException;

public class ClientTester {

	public static void main(String[] args) throws InterruptedException, UnsupportedEncodingException {
		MessageClient client = new MessageClient("127.0.0.1", 8867);
		client.sendRequest(223);

		Thread.sleep(1000);
		client.close();
	}

}
