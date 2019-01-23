package com.vendingmachine.lotte.lostcity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_connect.*
import android.bluetooth.BluetoothAdapter
import android.util.Log
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import com.vendingmachine.lotte.lostcity.connection.OpenBtSertverThread


class ConnectActivity : AppCompatActivity(),View.OnClickListener {
    private val mBluetoothAdapter :BluetoothAdapter by lazy{
        BluetoothAdapter.getDefaultAdapter()
    }
    companion object {
        val TAG = ConnectActivity::class.java.simpleName!!
        const val REQUEST_ENABLE_BT = 0
        val arrayAdapter  = ArrayList<String>()
        val broadcastReceiver = object: BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                val action = intent?.action
                if(BluetoothDevice.ACTION_FOUND == action){
                    val bluetoothDevice = intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
                    arrayAdapter.add("${bluetoothDevice.name}\n${bluetoothDevice.address}")
                    for(it: String in arrayAdapter){
                        Log.e(TAG, "$it")
                    }
                }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connect)
        btn_submit.setOnClickListener(this)

        if (mBluetoothAdapter == null) {
            // Device does not support Bluetooth
            Log.e(TAG, "Adapter is null")
        } else {
            Log.e(TAG, "Adapter is not null")
        }

        if (!mBluetoothAdapter.isEnabled) {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
        } else{
            regBR()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(broadcastReceiver)
    }

    fun regBR(){
        val intentFilter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        registerReceiver(broadcastReceiver,intentFilter)
        Log.e(TAG, "Registered BR")

        val intent = Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE)
        intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300)
        startActivity(intent)
        Log.e(TAG, "Enabled Discovering")

        OpenBtSertverThread(mBluetoothAdapter, et_name_editor.text.toString()).start()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode){
            REQUEST_ENABLE_BT -> {
                /**
                 * 0: 거부 : RESULT_CANCELED
                 * -1: 허용 : RESULT_OK
                 */
                Log.e(TAG, "Return Request Enabled BT Response : $resultCode")
                if(resultCode == RESULT_OK){
                    regBR()
                }
            }
            else -> {
                Log.e(TAG, "Adapter is not null")
            }
        }
    }

    override fun onClick(view: View?) {

    }

}
