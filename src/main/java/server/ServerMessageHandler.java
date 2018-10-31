package server;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;
import protocol.Message;

import java.util.Date;

public class ServerMessageHandler extends ChannelHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		Message in = (Message) msg;
		int id = ((Message) msg).getId();
		try {
			// Do something with msg
			System.out.println("server get :" + in);

		} finally {
			ReferenceCountUtil.release(msg);
		}

		String content = (new Date()).toString() + " Processing task: " + id;

		Message ret = new Message(id, content.getBytes("UTF-8").length,content);
		ctx.writeAndFlush(ret);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
