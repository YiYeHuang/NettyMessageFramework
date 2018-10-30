package server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

import java.util.Date;

public class MessageHandler extends ChannelHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		ByteBuf in = (ByteBuf) msg;
		try {
			System.out.println("server get :" + in.toString(CharsetUtil.UTF_8));

			ChannelFuture channelFuture = ctx.writeAndFlush(Unpooled.copiedBuffer(("server send time: " + new Date()).getBytes()));
			channelFuture.addListener(ChannelFutureListener.CLOSE);
		} finally {
			ReferenceCountUtil.release(msg);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
