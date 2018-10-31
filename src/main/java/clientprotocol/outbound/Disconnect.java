package clientprotocol.outbound;

import clientprotocol.Message;

import java.io.UnsupportedEncodingException;

public class Disconnect extends Message {
    public Disconnect(
            int id,
            String content) throws UnsupportedEncodingException {
        super('D', id, content);
    }
}
