package za.co.edataleaf.urovotestscanwedge.scanwedge

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import za.co.edataleaf.urovotestscanwedge.broadcast_receivers.SwReceiver

/**
 * Datawedge Zebra Technologies scanner status enum class
 *
 */
enum class ScannerStatus {
    WAITING,
    SCANNING,
    CONNECTED,
    DISCONNECTED,
    IDLE,
    DISABLED,
    UNKNOWN
}

/**
 * Datawedge Zebra Technologies helper
 *
 */
object SwHelper {
    const val PACKAGE_NAME = "za.co.edataleaf.urovotestscanwedge"

    @JvmStatic
    fun getStatus(status: String): ScannerStatus {
        return if(status == "WAITING") {
            ScannerStatus.WAITING
        } else if(status == "SCANNING") {
            ScannerStatus.SCANNING
        } else if(status == "CONNECTED") {
            ScannerStatus.CONNECTED
        } else if(status == "DISCONNECTED") {
            ScannerStatus.DISCONNECTED
        } else if(status == "IDLE") {
            ScannerStatus.IDLE
        } else if(status == "DISABLED") {
            ScannerStatus.DISABLED
        } else {
            ScannerStatus.UNKNOWN
        }
    }

    @JvmStatic
    fun isSwScannerUnavailable(status: String): Boolean {
        return (getStatus(status) != ScannerStatus.UNKNOWN)
    }

    @JvmStatic
    private fun scannerStatus(activityCompat: ComponentActivity) {
        val b = Bundle()
        b.putString(SwInterface.ACTION_SWITCH_TO_PROFILE, SwUtilities.PROFILE_NAME)
        b.putString(SwInterface.ACTION_GET_SCANNER_STATUS, "true")
        val i = Intent()
        i.action = SwInterface.RESULT_ACTION_EXTRA_COMMAND
        i.putExtra(SwInterface.RESULT_ACTION_EXTRA_RESULT, b)
        i.putExtra(SwInterface.ACTION_EXTRA_SEND_RESULT, "true")
        activityCompat.sendBroadcast(i)
    }

    @JvmStatic
    fun registerScannerReceiver(activityCompat: ComponentActivity, receiver: SwReceiver) {
        Log.d("SwReceiver", "register")
        receiver.setOnReceiverListenerInterface(activityCompat)
        //  Register broadcast receiver to listen for responses from DW API
        val intentFilter = IntentFilter()
        intentFilter.addAction(SwInterface.API_RESULT_ACTION)
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            activityCompat.registerReceiver(receiver, intentFilter, Context.RECEIVER_EXPORTED)

        } else {

            activityCompat.registerReceiver(receiver, intentFilter)
        }
        scannerStatus(activityCompat)
    }

    @JvmStatic
    fun unRegisterScannerReceiver(activityCompat: ComponentActivity, receiver: SwReceiver) {
        // To unregister, change only the iPutExtra command
        val bb = Bundle()
        bb.putString(SwInterface.ACTION_SWITCH_TO_PROFILE, SwUtilities.PROFILE_NAME)
//        bb.putString(DwInterface.DATAWEDGE_NOTIFICATION_TYPE, "SCANNER_STATUS")
        val ii = Intent()
        ii.action = SwInterface.ACTION_EXTRA_SEND_RESULT
        ii.putExtra(SwInterface.ACTION_EXTRA_UNREGISTER_FOR_NOTIFICATION, bb)
        activityCompat.sendBroadcast(ii)
        activityCompat.unregisterReceiver(receiver)
    }
}