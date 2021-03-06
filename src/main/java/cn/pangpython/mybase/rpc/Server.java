package cn.pangpython.mybase.rpc;

import cn.pangpython.mybase.dbop.UserDaoImpl;
import cn.pangpython.mybase.dbop.User;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        //创建服务端socket 使用8089端口
        ServerSocket ss = new ServerSocket(8089);
        System.out.println("启动服务器....");
        while (true) {
            //开启监听
            Socket s = ss.accept();
            System.out.println("客户端:" + s.getInetAddress().getLocalHost() + "已连接到服务器");
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            System.out.println("正在读取客户端发送来的消息...");
            //读取客户端发送来的消息
            String mess  = "";
            byte[] data = new byte[1024];
            mess = br.readLine();
            System.out.println("客户端发送的信息：" + mess);

//            while ((mess = br.readLine())!= null){
//                System.out.println("客户端发送的信息：" + mess);
//            }
            //根据传过来的userid去数据库查询user,返回
            String userId = mess.substring(mess.indexOf("=") + 1);
            System.out.println("useris:" + userId);
            UserDaoImpl userDaoImpl = new UserDaoImpl();
            int uid = Integer.parseInt(userId);
            User user = userDaoImpl.getUser(uid);
            //返回数据
            if (user != null) {
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                bw.write("<h1>User:" + user.toString() + "</h1>\n");
                bw.flush();
                bw.close();
            }else {
                System.out.println("user is null!");
            }
            br.close();
            s.close();
        }
    }
}
