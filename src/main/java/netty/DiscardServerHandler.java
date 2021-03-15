package netty;
import io.netty.buffer.ByteBuf;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author wenda.zhuang
 * @Date 2021/2/26 22:46
 * @Description Handles a server-side channel.
 * @E-mail sis.nonacosa@gmail.com
 */


public class DiscardServerHandler extends ChannelHandlerAdapter { // (1)

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
		System.out.println(msg);
		// Discard the received data silently.
		((ByteBuf) msg).release(); // (3)
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
		// Close the connection when an exception is raised.
		cause.printStackTrace();
		ctx.close();
	}
}
