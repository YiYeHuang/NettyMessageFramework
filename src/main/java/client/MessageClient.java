package client;

import client.codec.ClientDecoder;
import client.codec.ClientEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import clientprotocol.Message;

import java.io.UnsupportedEncodingException;

public class MessageClient {

    private EventLoopGroup clientGroup;
    private Bootstrap boot;
    private ChannelFuture ioc ;

    private final String host;
    private final int port;

    public MessageClient(String host, int port) {
        this.host = host;
        this.port = port;
        init();
    }

    public void connect() {
        try {
            ioc = boot.connect(host,port).sync();
            System.out.println("Connected to " + host + ":" + port);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public ChannelFuture getChannelFuture(){
        if(ioc == null){
            this.connect();
        }
        if(!ioc.channel().isActive()){
            this.connect();
        }

        return ioc;
    }

    public void close() throws InterruptedException {
        ioc.channel().closeFuture().sync();
        clientGroup.shutdownGracefully();
        System.out.println("Shutting down client");
    }

    public void sendRequest(Message outbound) throws UnsupportedEncodingException {
        getChannelFuture();
        ioc.channel().writeAndFlush(outbound);
    }

    private void init() {
        clientGroup = new NioEventLoopGroup();

        boot = new Bootstrap();
        boot.group(clientGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipe = ch.pipeline();
                        pipe.addLast(new ClientDecoder())
                                .addLast(new ClientEncoder())
                                .addLast(new ClientMessageHandler());
                    }
                });
    }

}
