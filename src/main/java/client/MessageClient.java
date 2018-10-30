package client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

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
            System.out.print("Connected to " + host + ":" + port);
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
    }

    public void sendRequest() {

    }

    private void init() {
        clientGroup = new NioEventLoopGroup();

        boot = new Bootstrap();
        boot.group(clientGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline p = ch.pipeline();
                        p.addLast(new InboundMessageHandler());
                    }
                });
    }

}
