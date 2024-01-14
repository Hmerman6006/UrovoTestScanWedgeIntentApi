package za.co.edataleaf.urovotestscanwedge.broadcast_receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import za.co.edataleaf.urovotestscanwedge.OnReceiverListenerInterface
import za.co.edataleaf.urovotestscanwedge.scanwedge.SwInterface

class SwReceiver : BroadcastReceiver() {
    private val NO_COMMAND = "COMMAND_NADA"
    private var listener: OnReceiverListenerInterface? = null

    fun setOnReceiverListenerInterface(context: Context) {
        this.listener = context as OnReceiverListenerInterface
    }

    private fun setActive(a: Boolean) {
        this.listener?.receiverActive = a
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("SwReceiver", "running")

        Log.d("SwReceiver", "ACTION: ${intent?.action}")
        val command = intent?.extras?.getString(SwInterface.RESULT_ACTION_EXTRA_COMMAND, NO_COMMAND) ?: NO_COMMAND
        this.listener?.apply {
            onReceivingScannerStatusBroadcast("TESTING")
        }

        if(SwInterface.ACTION_EXTRA_GET_SCANNER_STATUS == command) {
            setActive(true)
            val scannerStatus = intent?.extras?.getString(SwInterface.RESULT_ACTION_EXTRA_GET_SCANNER_STATUS)
            Log.d("SwReceiver", "STATUS GET: $scannerStatus")

            if (scannerStatus != null) {
                setActive(true)
                this.listener?.apply {
                    onReceivingScannerStatusBroadcast(scannerStatus)
                }
            }
        } else {
            setActive(false)
        }
    }
}