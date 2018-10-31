package server.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import clientprotocol.Message;

import java.nio.charset.Charset;
import java.util.List;

public class ServerDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
		int length = byteBuf.readInt();
		int id = byteBuf.readInt();
		char type = byteBuf.readChar();
		byte[] body = new byte[length];
		byteBuf.readBytes(body);

		String content = new String(body, Charset.forName("UTF-8"));
		Message myMessage = new Message(type, id, content);
		list.add(myMessage);
	}
}
