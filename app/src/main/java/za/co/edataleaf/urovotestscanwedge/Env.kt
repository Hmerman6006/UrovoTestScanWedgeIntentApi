package za.co.edataleaf.urovotestscanwedge

import android.content.Context
import android.device.ScanManager
import android.os.Build
import za.co.edataleaf.urovotestscanwedge.broadcast_receivers.SmReceiver
import za.co.edataleaf.urovotestscanwedge.broadcast_receivers.SwReceiver

class Env {

    lateinit var swReceiver: SwReceiver
    lateinit var smReceiver: SmReceiver

    lateinit var mScanManager: ScanManager

    companion object {
        var manufacturer: String = Build.MANUFACTURER
        var model: String = Build.MODEL
    }

    fun initScanEnv(ctx: Context, intentActionProps: String) {
        // TODO Check ScanWedge Version somehow or lack thereof
//        if(isUrovo()) {
            mScanManager = ScanManager()

            if(::mScanManager.isInitialized) {
                smReceiver = SmReceiver()
                smReceiver.setOnReceiverListenerInterface(ctx)
            }

//        } else {
//            swReceiver = SwReceiver()
//
//            SwUtilities.createSwProfile(
//                ctx, intentActionProps)
//
//        }
    }

    fun hasScanManager(): Boolean {
        return ::mScanManager.isInitialized
    }

    fun hasSmReceiver(): Boolean {
        return ::smReceiver.isInitialized
    }

    fun hasSwReceiver(): Boolean {
        return ::swReceiver.isInitialized
    }
}