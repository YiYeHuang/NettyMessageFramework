package clientprotocol.inbound;

import clientprotocol.Message;

import java.io.UnsupportedEncodingException;

public class EndOfData extends Message {
    public EndOfData(
            int id,
            String content) throws UnsupportedEncodingException {
        super('N', id, content);
    }
}
