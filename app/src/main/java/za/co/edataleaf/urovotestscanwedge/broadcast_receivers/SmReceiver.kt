package za.co.edataleaf.urovotestscanwedge.broadcast_receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import za.co.edataleaf.urovotestscanwedge.OnReceiverListenerInterface
import za.co.edataleaf.urovotestscanwedge.R
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
        val action = intent?.action ?: "NO_ACTION"
        Log.d("SmReceiver", "ACTION: $action")

        if(action == context?.getString(R.string.urovo_sdk_scan_intent)) {
            this.listener?.apply {

                onReceivingScannerStatusBroadcast(context.getString(R.string.decoding))

                val scanData =
                    intent?.getStringExtra(SmInterface.BARCODE_STRING_TAG)

                Log.d("onReceive", scanData.toString())

                if (scanData != null) {
                    onReceivingScannerBarcodeBroadcast(scanData)
                } else {

                    onReceivingScannerStatusBroadcast(context.getString(R.string.decoding_fail))
                }
            }
        } else {

            if (context != null) {
                this.listener?.onReceivingScannerStatusBroadcast(context.getString(R.string.scan_failure))
            }

        }
    }
}