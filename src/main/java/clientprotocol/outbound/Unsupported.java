package clientprotocol.outbound;

import clientprotocol.Message;

import java.io.UnsupportedEncodingException;

public class Unsupported extends Message {
    public Unsupported(
            int id,
            String content) throws UnsupportedEncodingException {
        super('Z', id, content);
    }
}
