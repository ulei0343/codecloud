package club.codecloud.demo.io.netty;

import club.codecloud.base.util.time.DateFormatUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.nio.charset.StandardCharsets;
import java.util.Date;

public class TimeServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] request = new byte[buf.readableBytes()];
        buf.readBytes(request);
        String body = new String(request, StandardCharsets.UTF_8);
        System.out.println("receive order:" + body);
        String currentTime = "Time".equalsIgnoreCase(body) ? DateFormatUtils.formatDate(DateFormatUtils.DATE_TIME_FORMAT, new Date()) : "Error";
        System.out.println("response:" + currentTime);
        ByteBuf response = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.write(response);

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
