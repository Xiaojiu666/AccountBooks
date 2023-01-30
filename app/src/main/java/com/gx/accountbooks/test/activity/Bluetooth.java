package com.gx.accountbooks.test.activity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gx.accountbooks.R;
import com.gx.utils.log.LogUtil;
import com.tencent.mars.xlog.Log;

import java.util.List;
import java.util.Set;

public class Bluetooth extends AppCompatActivity {

    public static final String TAG = "BluetoothActivity";
    private static final int REQUEST_ENABLE_BT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        isLocationOpen(getBaseContext());
        // Register for broadcasts when a device is discovered.
        // 只会查一次
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
//        bluetoothAdapter.listenUsingRfcommWithServiceRecord()
        if (bluetoothAdapter == null) {
            Log.e(TAG, "Device doesn't support Bluetooth ");
        } else {
            if (!bluetoothAdapter.isEnabled()) {
                Log.e(TAG, "bluetoothAdapter.isEnabled()");
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }

            Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();

            if (pairedDevices.size() > 0) {
                // There are paired devices. Get the name and address of each paired device.
                for (BluetoothDevice device : pairedDevices) {
                    String deviceName = device.getName();
                    String deviceHardwareAddress = device.getAddress(); // MAC address
                    Log.e(TAG, "old deviceName " + deviceName + "deviceHardwareAddress " + deviceHardwareAddress);
                }
            }
            // 低功耗，只会走一次
            boolean b = bluetoothAdapter.startDiscovery();
            Log.e(TAG, "startDiscovery " + b);
            bluetoothAdapter.getBluetoothLeScanner().startScan(new ScanCallback() {
                @Override
                public void onScanResult(int callbackType, ScanResult result) {
                    super.onScanResult(callbackType, result);
//                    result.getDevice().connectGatt(getBaseContext(), true, new BluetoothGattCallback() {
//                        @Override
//                        public void onPhyUpdate(BluetoothGatt gatt, int txPhy, int rxPhy, int status) {
//                            super.onPhyUpdate(gatt, txPhy, rxPhy, status);
//                        }
//
//                        @Override
//                        public void onPhyRead(BluetoothGatt gatt, int txPhy, int rxPhy, int status) {
//                            super.onPhyRead(gatt, txPhy, rxPhy, status);
//                        }
//
//                        @Override
//                        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
//                            super.onConnectionStateChange(gatt, status, newState);
//                        }
//
//                        @Override
//                        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
//                            super.onServicesDiscovered(gatt, status);
//                        }
//
//                        @Override
//                        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
//                            super.onCharacteristicRead(gatt, characteristic, status);
//                        }
//
//                        @Override
//                        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
//                            super.onCharacteristicWrite(gatt, characteristic, status);
//                        }
//
//                        @Override
//                        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
//                            super.onCharacteristicChanged(gatt, characteristic);
//                        }
//
//                        @Override
//                        public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
//                            super.onDescriptorRead(gatt, descriptor, status);
//                        }
//
//                        @Override
//                        public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
//                            super.onDescriptorWrite(gatt, descriptor, status);
//                        }
//
//                        @Override
//                        public void onReliableWriteCompleted(BluetoothGatt gatt, int status) {
//                            super.onReliableWriteCompleted(gatt, status);
//                        }
//
//                        @Override
//                        public void onReadRemoteRssi(BluetoothGatt gatt, int rssi, int status) {
//                            super.onReadRemoteRssi(gatt, rssi, status);
//                        }
//
//                        @Override
//                        public void onMtuChanged(BluetoothGatt gatt, int mtu, int status) {
//                            super.onMtuChanged(gatt, mtu, status);
//                        }
//                    });
                    Log.e(TAG,"results" + result.toString());
                }

                @Override
                public void onBatchScanResults(List<ScanResult> results) {
                    super.onBatchScanResults(results);
                    Log.e(TAG,"results" + results.size());
                }

                @Override
                public void onScanFailed(int errorCode) {
                    super.onScanFailed(errorCode);
                }
            });

        }
        // 只会查一次
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(receiver, filter);
    }

    public static boolean isLocationOpen(final Context context){
            LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    //gps定位
            boolean isGpsProvider = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    //网络定位
            boolean isNetWorkProvider = manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            return isGpsProvider|| isNetWorkProvider;
    }

    // Create a BroadcastReceiver for ACTION_FOUND.
    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Discovery has found a device. Get the BluetoothDevice
                // object and its info from the Intent.
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String deviceName = device.getName();
                String deviceHardwareAddress = device.getAddress(); // MAC address
                Log.d(TAG, "new deviceName " + deviceName + "deviceHardwareAddress " + deviceHardwareAddress);
                Log.d(TAG, "new device " + device );
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Don't forget to unregister the ACTION_FOUND receiver.
        unregisterReceiver(receiver);
    }
}
