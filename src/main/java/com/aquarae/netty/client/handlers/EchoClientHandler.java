package com.aquarae.netty.client.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class EchoClientHandler extends SimpleChannelInboundHandler {
  public EchoClientHandler() { }

  @Override
  public void channelActive(ChannelHandlerContext ctx) {
    ctx.writeAndFlush(Unpooled.copiedBuffer("Hello Netty!".getBytes()));
  }

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
    ByteBuf in = (ByteBuf) msg;
    while (in.isReadable()) { // (1)
      System.out.print((char) in.readByte());
      System.out.flush();
    }
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    // Close the connection when an exception is raised.
    cause.printStackTrace();
    ctx.close();
  }
}