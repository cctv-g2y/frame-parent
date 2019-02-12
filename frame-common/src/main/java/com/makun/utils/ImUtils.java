package com.makun.utils;

import io.netty.channel.ChannelHandlerContext;

import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * @说明：[Im通信工具类] @author: makun
 */
public class ImUtils {

    /**
     * 
     * 说明：[] byte数组转换成16进制字符串
     * 
     * @Title: bytesToHexString
     * @author wangzhichao
     * @param src
     * @date: 2018年4月26日 上午11:18:15
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 
     * 说明：[] 取IP地址及端口
     * 
     * @Title: getIpAndProt
     * @author wangzhichao
     * @param socketaddress
     * @date: 2018年4月26日 上午11:18:15
     */
    public static String getIpAndProt(InetSocketAddress socketaddress) {
        String address = "";
        if (address != null) {
            address = getIp(socketaddress) + ":" + socketaddress.getPort();
        }
        return address;
    }

    /**
     * 
     * 说明：[] 获取IP地址
     * 
     * @Title: getIp
     * @author wangzhichao
     * @param socketaddress
     * @date: 2018年4月26日 上午11:18:15
     */
    public static String getIp(InetSocketAddress socketaddress) {
        String ip = "";
        if (socketaddress != null) {
            InetAddress address = socketaddress.getAddress();
            ip = (address == null) ? socketaddress.getHostName() : address.getHostAddress();
        }
        return ip;
    }

    /**
     * 
     * 说明：[] 获取Remote地址
     * 
     * @Title: getRemoteAddress
     * @author wangzhichao
     * @param ctx
     * @date: 2018年4月26日 上午11:18:15
     */
    public static String getRemoteAddress(ChannelHandlerContext ctx) {
        InetSocketAddress remote = (InetSocketAddress) ctx.channel().remoteAddress();
        return getIpAndProt(remote);
    }

    /**
     * 
     * 说明：[] 获取Local地址
     * 
     * @Title: getLocalAddress
     * @author wangzhichao
     * @param ctx
     * @date: 2018年4月26日 上午11:18:15
     */
    public static String getLocalAddress(ChannelHandlerContext ctx) {
        InetSocketAddress local = (InetSocketAddress) ctx.channel().localAddress();
        return getIpAndProt(local);
    }

}