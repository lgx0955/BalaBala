package cn.figo.mydemo.danmaku;

import android.util.Log;

import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Lakeinchina(lakeinchina@hotmail.com) on 2015/11/26.
 * FloatingBili Project
 *
 * Copyright (C) 2015 Po Hu <lakeinchina@hotmail.com>
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
public class BiliDanmakuClient {
    Gson gson = new Gson();
    private final String TAG = getClass().toString();
    private boolean alive;
    private GetDanmakuThread workThread;
    private Object syncWorkThread;

    public BiliDanmakuClient(int roomId, IIncomingDanmakuCallback callback) {
        syncWorkThread = new Object();
        workThread = new GetDanmakuThread(roomId, callback);
    }

    public void start() {
        synchronized (syncWorkThread) {
            alive = true;
            workThread.start();
        }
    }

    public void stop() {
        synchronized (syncWorkThread) {
            alive = false;
            workThread.close();
        }
    }

    class GetDanmakuThread extends Thread {
        boolean isAlive;
        int roomID;
        IIncomingDanmakuCallback incomingDanmakuCallback;
        Socket client = null;
        DataOutputStream output = null;
        DataInputStream input = null;

        GetDanmakuThread(int roomId, IIncomingDanmakuCallback callback) {
            roomID = roomId;
            isAlive = true;
            incomingDanmakuCallback = callback;
        }

        public void close() {
            isAlive = false;
        }


        @Override
        public void run() {
            byte[] buf = new byte[1024];
            long lastkeepTime = System.currentTimeMillis();
            while (alive) {
                if (client == null) {
                    openSocket();
                }
                try {
                    if (input.available() <= 0) {
                        if (System.currentTimeMillis() - lastkeepTime >= 30000) {
                            try {
                                output.write(new byte[]{0x01, 0x02, 0x00, 0x04});
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            lastkeepTime = System.currentTimeMillis();
                        }
                        sleep(1000);
                        continue;
                    }
                    short type = input.readShort();
                    if (type == -1) {
                        closeSocket();
                        continue;
                    }
                    switch (type) {
                        case 1:
                            int peoplenum = input.readInt();
                            Log.i(TAG, "peoplenum=" + peoplenum);
                            break;
                        case 4:
                            short leftsize = input.readShort();
                            leftsize -= 4;
                            String s;
                            if (leftsize < 1024) {
                                input.read(buf, 0, leftsize);
                                s = new String(buf, 0, leftsize);
                            } else {
                                input.read(buf, 0, leftsize);
                                s = new String(buf, 0, leftsize);
                            }

                            System.out.println("zzzzzz"+s);
                            incomingDanmakuCallback.incomingDanmaku(s);

//                            Danmaku danmaku = new Danmaku();
//                            BaseDanmaku danmaku = mContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL);
//                            danmaku.text = s;
//                            danmaku.padding = 5;
//                            danmaku.priority = 0;  // 可能会被各种过滤器过滤并隐藏显示
//                            danmaku.textColor = Color.RED;
//                            danmaku.textShadowColor = Color.WHITE;
//                            danmaku.borderColor = Color.GREEN;
//
//                            if (danmaku != null) {
////                                SimpleTextDanmaku danmaku = new SimpleTextDanmaku(s, 0);
//                                try {
//                                    incomingDanmakuCallback.incomingDanmaku(danmaku);
//                                } catch (Throwable throwable) {
//                                    throwable.printStackTrace();
//                                }
//                            } else {
////                                Log.e(TAG, "unknowDanmakujson=aaaaaa" + s);
//                            }
                            break;
                        default:
                            Log.e(TAG, "!!!!!!!!!!!!wtftype=" + type);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
            }
            closeSocket();
        }

        private boolean openSocket() {
            try {
                try {
                    client = new Socket("livecmt-1.bilibili.com", 88);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                    client = null;
                    output = null;
                    input = null;
                    alive = false;
                    return false;
                }
                output = new DataOutputStream(client.getOutputStream());
                output.write(new byte[]{0x01, 0x01, 0x00, 0x0c, (byte) (roomID >> 24), (byte) (roomID >> 16), (byte) (roomID >> 8), (byte) roomID,
                        0x00, 0x00, 0x00, 0x00});
                input = new DataInputStream(client.getInputStream());

//                ILoader loader = DanmakuLoaderFactory.create(DanmakuLoaderFactory.TAG_BILI);
//                try {
//                    loader.load(s);
//                } catch (IllegalDataException e) {
//                    e.printStackTrace();
//                }
//                BaseDanmakuParser parser = new BiliDanmukuParser();
//
//                IDataSource<?> dataSource = loader.getDataSource();
//                parser.load(dataSource);
//                svDanmaku.prepare(parser, danmakuContext);


            } catch (IOException e) {
                e.printStackTrace();
                client = null;
                output = null;
                input = null;
                alive = false;
                return false;
            }
            return true;
        }

        private void closeSocket() {
            if (client != null) {
                try {
                    client.close();
                    client = null;
                } catch (IOException ie) {
                    ie.printStackTrace();
                    client = null;
                }
            }
            if (output != null) {
                try {
                    output.close();
                    output = null;
                } catch (IOException ie) {
                    ie.printStackTrace();
                }
            }
            if (input != null) {
                try {
                    input.close();
                    input = null;
                } catch (IOException ie) {
                    ie.printStackTrace();
                }
            }
        }
    }
}