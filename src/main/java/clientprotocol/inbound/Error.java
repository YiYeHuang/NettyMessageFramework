package clientprotocol.inbound;

import clientprotocol.Message;

import java.io.UnsupportedEncodingException;

public class Error extends Message {
    public Error(
            int id,
            String content) throws UnsupportedEncodingException {
        super('E', id, content);
    }
}
