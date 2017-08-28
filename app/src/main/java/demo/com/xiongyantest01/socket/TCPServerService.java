package demo.com.xiongyantest01.socket;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * Created by xiongyan on 2017/8/25.
 */

public class TCPServerService extends Service {
    private boolean mIsSeriviceDestoryed = false;
    private String[] defaultMessages = new String[]{"哈哈", "你叫神马", "公司空调很冷", "感冒了难受", "同时多人聊天", "今天就到这里吧"};

    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("-----------------TCPServerService---onBind--------------");
        return null;
    }

    @Override
    public void onCreate() {
        System.out.println("-----------------TCPServerService---onCreate--------------");
        new Thread(new TcpServer()).start();
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        mIsSeriviceDestoryed = true;
        super.onDestroy();
    }

    private class TcpServer implements Runnable {

        @Override
        public void run() {
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(8686);
            } catch (IOException e) {
                System.out.println("-----------------8688--failed----");
                e.printStackTrace();
            }
            while (!mIsSeriviceDestoryed) {
                try {
                    final Socket client = serverSocket.accept();
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                responseClient(client);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void responseClient(Socket client) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
        out.println("欢迎来到聊天室");
        while (!mIsSeriviceDestoryed) {
            String str = in.readLine();
            System.out.println("------------get----------" + str);
            if (str == null) {
                break;
            }
            int i = new Random().nextInt(defaultMessages.length);
            String msg = defaultMessages[i];
            out.println(msg);
            System.out.println("-------------sen------------" + msg);
        }
        System.out.println("-------------client out------------");
        if (in != null) {
            in.close();
        }
        if (out != null) {
            out.close();
        }
    }

}
