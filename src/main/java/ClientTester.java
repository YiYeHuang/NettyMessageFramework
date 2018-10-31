import client.MessageClient;
import clientprotocol.inbound.CloseAck;
import clientprotocol.outbound.Authentication;
import clientprotocol.outbound.Disconnect;
import clientprotocol.outbound.Query;
import clientprotocol.outbound.Unsupported;

import java.io.UnsupportedEncodingException;

public class ClientTester {

	public static void main(String[] args) throws InterruptedException, UnsupportedEncodingException {
		MessageClient client = new MessageClient("127.0.0.1", 8867);

		client.sendRequest(new Authentication(1, "salt","user", "password"));
		client.sendRequest(new Query(2, "select * from table1"));
		client.sendRequest(new Unsupported(3, "error"));
		client.sendRequest(new Disconnect(4, "close"));

		client.close();
	}

}
