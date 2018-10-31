package server.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import clientprotocol.Message;

import java.nio.charset.Charset;

public class ServerEncoder extends MessageToByteEncoder<Message> {

	@Override
	protected void encode(ChannelHandlerContext channelHandlerContext, Message myMessage, ByteBuf byteBuf) {
		int length = myMessage.getLength();
		int id = myMessage.getId();
		char type = myMessage.getTypeTag();
		String content = myMessage.getContent();

		byteBuf.writeInt(length);
		byteBuf.writeInt(id);
		byteBuf.writeChar(type);
		byteBuf.writeBytes(content.getBytes(Charset.forName("UTF-8")));
	}
}