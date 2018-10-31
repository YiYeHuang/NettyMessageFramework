package clientprotocol.inbound;

import clientprotocol.Message;

import java.io.UnsupportedEncodingException;

public class CloseAck extends Message {
    public CloseAck(
            int id,
            String content) throws UnsupportedEncodingException {
        super('K', id, content);
    }
}
