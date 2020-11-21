package com.tida.manual.nio;/**
 * Created by Administrator on 2020/11/16.
 * Description ${TEXT}
 */

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName LoadWebPageUseSelector
 * @Description TODO
 * @Author Administrator
 * @Date 2020/11/16 2:00
 * @Version 1.0
 **/
public class LoadWebPageUseSelector {

    //通过Selector同时下载多个网页的内容
    public void load(Set<URL> urls) throws IOException{
        Map<SocketAddress,String> socketMapping=urlToSocketAddress(urls);

        //1.创建Selector
        Selector selector = Selector.open();

        //2.将套接字Channel注册到Selector上

        for(SocketAddress address:socketMapping.keySet()){
            register(selector,address);
        }
        int finished=0;
        int total = socketMapping.size();

        ByteBuffer buffer = ByteBuffer.allocate(32 * 1024);
        int len = -1;
        while(finished < total){
            // 3. 调用select方法进行通道选择，该方法会阻塞，直到至少有一个他们所感兴趣的事件发生，然后可以通过selectedKeys获取被选中的通道的对象集合
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while(iterator.hasNext()){
                SelectionKey key = iterator.next();
                iterator.remove();
                if(key.isValid() && key.isConnectable()){
                    SocketChannel channel = (SocketChannel) key.channel();
                    // 4. 如果连接成功，则发送HTTP请求；失败则取消该连接；
                    boolean success = channel.finishConnect();
                    if(!success){
                        finished++;
                        key.cancel();
                    }else {
                        InetSocketAddress address = (InetSocketAddress) channel.getRemoteAddress();
                        String path = socketMapping.get(address);
                        String request = "GET" + path + "HTTP/1.0\r\n\r\nHost:" + address.getHostString() + "\r\n\r\n";
                        ByteBuffer header = ByteBuffer.wrap(request.getBytes("UTF-8"));
                        channel.write(header);
                    }
                }else if(key.isValid() && key.isReadable()){
                    // 5. 当channel处于可读时则读取channel的数据并写入文件
                    SocketChannel channel = (SocketChannel) key.channel();
                    InetSocketAddress address = (InetSocketAddress) channel.getRemoteAddress();
                    String filename = address.getHostName()+".txt";
                    FileChannel destChannel = FileChannel.open(Paths.get(filename), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
                    buffer.clear();

                    // 6. 当返回0时表示本次没有数据可读不需要操作；如果为-1则表示所有数据亿级读取完毕，可以关闭；
                    while ((len = channel.read(buffer)) > 0 || buffer.position() != 0) {
                        buffer.flip();
                        destChannel.write(buffer);
                        buffer.compact();
                    }

                    if (len == -1) {
                        finished++;
                        key.cancel();
                    }
                }
            }
        }

    }
    private void register(Selector selector,SocketAddress address) throws IOException {
        SocketChannel channel = SocketChannel.open();

        //设置为阻塞模式
        channel.configureBlocking(false);
        channel.connect(address);
        //注册时需要指定感兴趣的事件类型
        channel.register(selector, SelectionKey.OP_ACCEPT | SelectionKey.OP_READ);
    }

    private Map<SocketAddress,String> urlToSocketAddress(Set<URL> urls){
        Map<SocketAddress, String> mapping = new HashMap<>();
        for (URL url : urls) {
            int port = url.getPort() != -1 ? url.getPort() : url.getDefaultPort();
            SocketAddress address = new InetSocketAddress(url.getHost(), port);
            String path = url.getPath();
            if (url.getQuery() != null) {
                path = path + "?" + url.getQuery();
            }
            mapping.put(address, path);
        }
        return mapping;
    }
}

