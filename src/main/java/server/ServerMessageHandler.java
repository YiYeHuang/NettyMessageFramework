package server;

import clientprotocol.inbound.CloseAck;
import clientprotocol.inbound.DataFlow;
import clientprotocol.inbound.Error;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;
import clientprotocol.Message;
import clientprotocol.inbound.AuthOk;

import java.util.Date;

public class ServerMessageHandler extends ChannelHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		Message in = (Message) msg;
		int id = ((Message) msg).getId();
		char type = ((Message) msg).getTypeTag();
		try {
			System.out.println("server get :" + in);
		} finally {
			ReferenceCountUtil.release(msg);
		}

		if (type == 'R') {
			Message message = new AuthOk(id, (new Date()).toString() +" Auth okay 200");
			ctx.writeAndFlush(message);
		}
		else if (type == 'Q') {
			Message message1 = new DataFlow(id, (new Date()).toString() +" data1");
			Message message2 = new DataFlow(id, (new Date()).toString() +" data2");
			Message message3 = new DataFlow(id, (new Date()).toString() +" data3");
			Message message4 = new DataFlow(id, (new Date()).toString() +"");
			ctx.writeAndFlush(message1);
			ctx.writeAndFlush(message2);
			ctx.writeAndFlush(message3);
			ctx.writeAndFlush(message4);
		}
        else if (type == 'D') {
            Message message = new CloseAck(id, (new Date()).toString() +" Close from server");
            ctx.writeAndFlush(message);
        }
		else {
			Message message = new Error(id, (new Date()).toString() +" Error. Request type not supported");
			ctx.writeAndFlush(message);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
