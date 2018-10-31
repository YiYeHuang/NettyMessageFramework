package clientprotocol.outbound;

import clientprotocol.Message;

import java.io.UnsupportedEncodingException;

public class Query extends Message {
    public Query(
            int id,
            String content) throws UnsupportedEncodingException {
        super('Q', id, content);
    }
}
