package com.vendingmachine.lotte.lostcity.connection

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothServerSocket

class OpenBtSertverThread( bluetoothAdapter: BluetoothAdapter, roomName: String): Thread() {

    var mServerSocket: BluetoothServerSocket? = null

    init{
        var tmp:BluetoothServerSocket? = null
        try{
            tmp = bluetoothAdapter.listenUsingInsecureRfcommWithServiceRecord(roomName, ConnectionConst.GameUUID)
        }catch(e: Exception){
            e.printStackTrace()
        }
        mServerSocket = tmp
    }

    override fun run() {
        super.run()
    }
}