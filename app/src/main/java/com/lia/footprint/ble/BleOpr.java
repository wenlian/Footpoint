package com.lia.footprint.ble;

import android.app.Activity;

/**
 * Created by lia on 2016/6/20 0020.
 */
public class BleOpr extends Activity
{
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {// 接收活动结果，响应startActivityForResult()
//        switch (requestCode) {
//            case REQUEST_CONNECT_DEVICE: // 连接结果，由DeviceListActivity设置返回
//                if (resultCode == Activity.RESULT_OK) { // 连接成功，由DeviceListActivity设置返回
//                    // MAC地址，由DeviceListActivity设置返回
//                    String address = data.getExtras().getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
//                    _device = _bluetooth.getRemoteDevice(address);// 得到蓝牙设备句柄
//
//                    try {// 用服务号得到socket
//                        _socket = _device.createRfcommSocketToServiceRecord(UUID.fromString(MY_UUID));
//                    } catch (IOException e) {
//                        Toast.makeText(this, "Connection failed!", Toast.LENGTH_SHORT).show();
//                    }
//
//                    try {
//                        _socket.connect();
//                        Toast.makeText(this, "Connect " + _device.getName() + " succeed!", Toast.LENGTH_SHORT).show();
//                        myButtonConnect.setText("Disconnect");
//                        isBTConnect = true;
//                    } catch (IOException e) {
//                        try {
//                            Toast.makeText(this, "Connection failed!", Toast.LENGTH_SHORT).show();
//                            _socket.close();
//                            _socket = null;
//                            isBTConnect = false;
//                        } catch (IOException ee) {
//                            Toast.makeText(this, "Connection failed!", Toast.LENGTH_SHORT).show();
//                        }
//                        return;
//                    }
//
//                    try {// 打开接收线程
//                        is = _socket.getInputStream(); // 得到蓝牙数据输入流
//                        os = _socket.getOutputStream();// 得到蓝牙数据输出流
//                    } catch (IOException e) {
//                        Toast.makeText(this, "Failed to X_TX data!", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                    if (bThread == false) {
//                        <span style="color: #FF0000;">ReadThread.start();</span>
//                                bThread = true;
//                    } else {
//                        bRun = true;
//                    }
//                }
//                break;
//            default:
//                break;
//        }
//    }

//    Thread ReadThread = new Thread() {// 接收数据线程
//        public void run() {
//            byte[] buffer = new byte[150];
//            bRun = true;
//            while (true) {
//                try {
//                    while (is.available() == 0) {
//                        while (bRun == false) {
//                        }
//                    }
//                    while (true) {
//                        num = is.read(buffer); // 读入数据buffer 打印出来不对
//                        // 必须转换为其它类型才能打印查看
//                        String s = new String(buffer, 0, num);
//                        break;
//                    }
//                    if (is.available() == 0) break; // 短时间没有数据才跳出进行显示
//                }
//                handler.sendMessage(handler.obtainMessage());//
//                // 发送显示消息，进行显示刷新
//            } catch (IOException e) {
//
//            }
//        }
//    }
//};
//
//    protected void onResume() {
//        super.onResume();
//        registerReceiver(mGattUpdataRecivier, makeGattUpdateIntentFilter());
//        if (mBleService != null) {
//            final boolean result = mBleService.connect(mDeviceAddress);
//            Log.i(TAG, "Connect request result=" + result);
//        }
//
//        if (dataThread == null) {
//            dataThread = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        while (mGattCharacteristics == null || mGattCharacteristics.size() == 0) {
//                            Thread.sleep(1000);
//                            System.out.println("thread__waiting_data");
//                        }
//                        //mBleService.setCharacteristicNotification(characteristic, true);
//                        while (true) {
//                            Thread.sleep(1000);
//                            mBleService.readCharacteristic(characteristic);
//                            if (characteristic == null) {
//                                error();
//                            }
//                        }
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            });
//            dataThread.start();
//        }
//    }
}
