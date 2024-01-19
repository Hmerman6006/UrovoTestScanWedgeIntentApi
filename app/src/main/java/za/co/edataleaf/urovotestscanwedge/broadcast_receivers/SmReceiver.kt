package za.co.edataleaf.urovotestscanwedge.broadcast_receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import za.co.edataleaf.urovotestscanwedge.OnReceiverListenerInterface
import za.co.edataleaf.urovotestscanwedge.scanmanager.SmInterface
import za.co.edataleaf.urovotestscanwedge.scanwedge.SwInterface

class SmReceiver: BroadcastReceiver() {
    private var listener: OnReceiverListenerInterface? = null

    fun setOnReceiverListenerInterface(context: Context) {
        this.listener = context as OnReceiverListenerInterface
    }

    private fun setActive(a: Boolean) {
        this.listener?.receiverActive = a
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("SmReceiver", "running")

        Log.d("SmReceiver", "ACTION: ${intent?.action}")
        val scanData =
            intent?.getStringExtra(SmInterface.BARCODE_STRING_TAG)

        Log.d("onReceive", scanData.toString())

        this.listener?.apply {
            onReceivingScannerStatusBroadcast("SCANNING")
            if (scanData != null) {
                onReceivingScannerBarcodeBroadcast(scanData)
            }
        }
    }
}