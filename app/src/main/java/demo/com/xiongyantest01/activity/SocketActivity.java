package demo.com.xiongyantest01.activity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import demo.com.xiongyantest01.R;
import demo.com.xiongyantest01.socket.TCPServerService;
import demo.com.xiongyantest01.utils.L;

/**
 * Created by xiongyan on 2017/8/25.
 */

public class SocketActivity extends BaseActivity implements View.OnClickListener {
    private static final int MESSAGE_RECEIVE_NEW_MSG = 1;
    private static final int MESSAGE_SOCKET_CONNECTED = 2;
    private Button mSendButton;
    private EditText mMessageEditText;
    private TextView mMessgaeTextView;
    private Socket mSocketClient;
    private PrintWriter mPrintWriter;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_RECEIVE_NEW_MSG:
                    mMessgaeTextView.setText(mMessgaeTextView.getText() + (String) msg.obj);
                    break;
                case MESSAGE_SOCKET_CONNECTED:
                    mSendButton.setEnabled(true);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected int setLayoutId() {
        return R.layout.socket_activity;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        startTcpService();
        mSendButton = (Button) findViewById(R.id.socket_button);
        mMessageEditText = (EditText) findViewById(R.id.socket_exit);
        mMessgaeTextView = (TextView) findViewById(R.id.socket_text);
        mSendButton.setOnClickListener(this);
        new Thread() {
            @Override
            public void run() {
                linkClient();
            }
        }.start();
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void onDestroy() {
        if (mSocketClient != null) {
            try {
                mSocketClient.shutdownInput();
                mSocketClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onDestroy();
    }

    private void startTcpService() {
        Intent service = new Intent(SocketActivity.this, TCPServerService.class);
        startService(service);

    }

    private void linkClient() {
        Socket socket = null;
        while (socket == null) {
            try {
                socket = new Socket("localhost", 8686);

                mSocketClient = socket;
                mPrintWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                System.out.println("----------------------------link Success---------");
                mHandler.sendEmptyMessage(MESSAGE_SOCKET_CONNECTED);
            } catch (IOException e) {
                SystemClock.sleep(1000);
                e.printStackTrace();
            }
        }

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (!SocketActivity.this.isFinishing()) {
                String msg = br.readLine();
                System.out.println("------------get msg-------------------" + msg);
                if (msg != null) {
                    String time = formatDataTime(System.currentTimeMillis());
                    final String showMsg = "server" + time + ":" + msg + "\n";
                    mHandler.obtainMessage(MESSAGE_RECEIVE_NEW_MSG, showMsg).sendToTarget();
                }
            }
            if (mPrintWriter != null) {
                mPrintWriter.close();
            }
            if (br != null) {
                br.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String formatDataTime(long time) {
        return new SimpleDateFormat("(HH:mm:ss)").format(new Date(time));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.socket_button:
                System.out.println("---------------是否运行---------------" + isServiceRunning(this, "demo.com.xiongyantest01.socket.TCPServerService"));
                final String msg = mMessageEditText.getText().toString();
                if (!TextUtils.isEmpty(msg) && mPrintWriter != null) {
                    mPrintWriter.println(msg);
                    mMessageEditText.setText("");
                    String time = formatDataTime(System.currentTimeMillis());
                    final String showMsg = "self" + time + ":" + msg + "\n";
                    mMessgaeTextView.setText(mMessgaeTextView.getText() + showMsg);
                }
                break;
            default:
                break;
        }
    }


    private String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            L.e("WifiPreference IpAddress", ex.toString());
        }
        return null;
    }

    /**
     * 用来判断服务是否运行.
     *
     * @param context
     * @param className 判断的服务名字
     * @return true 在运行 false 不在运行
     */
    public static boolean isServiceRunning(Context context, String className) {
        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager)
                context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList
                = activityManager.getRunningServices(Integer.MAX_VALUE);
        if (!(serviceList.size() > 0)) {
            return false;
        }
        for (int i = 0; i < serviceList.size(); i++) {
            if (serviceList.get(i).service.getClassName().equals(className) == true) {
                isRunning = true;
                break;
            }
        }
        return isRunning;
    }
}
