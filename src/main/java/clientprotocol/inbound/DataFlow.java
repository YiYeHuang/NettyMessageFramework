package clientprotocol.inbound;

import clientprotocol.Message;

import java.io.UnsupportedEncodingException;

public class DataFlow extends Message {
    public DataFlow(
            int id,
            String content) throws UnsupportedEncodingException {
        super('D', id, content);
    }
}
