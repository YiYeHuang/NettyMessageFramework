package client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import protocol.Message;

import java.nio.charset.Charset;

public class ClientEncoder extends MessageToByteEncoder<Message> {

	@Override
	protected void encode(ChannelHandlerContext channelHandlerContext, Message myMessage, ByteBuf byteBuf) throws Exception {
		int length = myMessage.getLength();
		int id = myMessage.getId();
		String content = myMessage.getContent();

		byteBuf.writeInt(length);
		byteBuf.writeInt(id);
		byteBuf.writeBytes(content.getBytes(Charset.forName("UTF-8")));
	}
}