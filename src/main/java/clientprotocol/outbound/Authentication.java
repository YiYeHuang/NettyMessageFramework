package clientprotocol.outbound;

import clientprotocol.Message;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Authentication extends Message {
    public Authentication(
            int id,
            String content,
            String userName,
            String Password) throws UnsupportedEncodingException {
        super('R', id,
                Base64.getEncoder().withoutPadding().encodeToString((content + userName + Password).getBytes()));
    }
}
