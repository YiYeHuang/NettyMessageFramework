package clientprotocol.inbound;

import clientprotocol.Message;

import java.io.UnsupportedEncodingException;

public class AuthOk extends Message{
    public AuthOk(
            int id,
            String content) throws UnsupportedEncodingException {
        super('A', id, content);
    }
}
