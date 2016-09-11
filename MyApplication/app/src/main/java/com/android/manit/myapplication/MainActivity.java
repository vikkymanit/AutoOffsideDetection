package com.android.manit.myapplication;

import android.app.Activity;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.List;
import java.util.UUID;

public class MainActivity extends Activity {

    private BluetoothLeService mBluetoothLeService;
    private final static String TAG = MainActivity.class.getSimpleName();
    private String mDeviceAddress = "98:4F:EE:0F:C6:B1";
    private final ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            mBluetoothLeService = ((BluetoothLeService.LocalBinder) service).getService();
            if (!mBluetoothLeService.initialize()) {
                Log.e(TAG, "Unable to initialize Bluetooth");
                finish();
            }
            // Automatically connects to the device upon successful start-up initialization.
            mBluetoothLeService.connect(mDeviceAddress);
//            mBluetoothLeService.disconnect();
//            mBluetoothLeService.close();
            finish();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBluetoothLeService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnGetDict = (Button) findViewById(R.id.btn);
        Intent gattServiceIntent = new Intent(this, BluetoothLeService.class);
        bindService(gattServiceIntent, mServiceConnection, BIND_AUTO_CREATE);


        btnGetDict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//            mBluetoothLeService.connect(mDeviceAddress);
//            mBluetoothLeService.writeCustomCharacteristi  c(0xAA);
//            mBluetoothLeService.disconnect();
//                UUID uuid1 = new UUID()
//                BluetoothGattCharacteristic bluetoothGattCharacteristic =
//                        new BluetoothGattCharacteristic("19B10001-E8F2-537E-4F6C-D104768A1214",BluetoothGattCharacteristic.WRITE_TYPE_NO_RESPONSE
//                        ,BluetoothGattCharacteristic.PERMISSION_WRITE);
//
//                mBluetoothLeService.broadcastUpdate("A");
//
//                List<BluetoothGattService> bluetoothGattServices = mBluetoothLeService.getSupportedGattServices();
//
//                for(BluetoothGattService s : bluetoothGattServices){
//                    List<BluetoothGattCharacteristic> characteristics = s.getCharacteristics();
//                    for (BluetoothGattDescriptor descriptor : characteristics.getDescriptors()) {
//                        //find descriptor UUID that matches Client Characteristic Configuration (0x2902)
//                        // and then call setValue on that descriptor
//
//                        descriptor.setValue( BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
//                        .writeDescriptor(descriptor);
//                    }
//
//                }
//                BluetoothLeService ble = new BluetoothLeService();
//                ble.initialize();
//                ble.connect("D2:S2:123:213:99");
//                ble.broadcastUpdate("a");
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
