package za.co.edataleaf.urovotestscanwedge.scanmanager

import android.device.ScanManager
import android.util.Log

/**
 * ScanManager Sdk
 *
 * Reference:
 *     https://www.urovo.com/developer/android/device/ScanManager.html
 *     https://github.com/urovosamples/SDK_ReleaseforAndroid/tree/master/Samples/ScanManager
 */
class SmInterface {

    companion object {

        val SCAN_KEYCODE = intArrayOf(520, 521, 522, 523)

        /**
         * Tag set to obtain the output data as a byte array. In the case of concatenated barcodes, the decode data is concatenated and sent out as a single array.
         */
        const val DECODE_DATA_TAG = ScanManager.DECODE_DATA_TAG

        /**
         * Tag set to obtain the label type of the barcode.
         */
        const val BARCODE_TYPE_TAG = ScanManager.BARCODE_TYPE_TAG

        /**
         * Tag set to obtain the label type of the barcode.
         */
        const val BARCODE_STRING_TAG = ScanManager.BARCODE_STRING_TAG

        /**
         * Tag set to obtain the label length of the barcode.
         */
        const val BARCODE_LENGTH_TAG = ScanManager.BARCODE_LENGTH_TAG

        fun openScanner(scanManager: ScanManager): Boolean {
            var state = scanManager.scannerState
            if(!state) {
                state = scanManager.openScanner()
            }
            scanManager.enableAllSymbologies(true)
            Log.d("openScanner","$state || ${scanManager.outputMode}")

            return state && (scanManager.outputMode == 0 || scanManager.switchOutputMode(0))
        }

        /**
         * ScanManager.startDecode
         */
        fun startDecode(scanManager: ScanManager) {
            if (!scanManager.scannerState) {
                return
            }

            if (scanManager.triggerLockState) {
                Log.d("sm","startDecode ignore, Scan lockTrigger state:${scanManager.triggerLockState}")
                return
            }
            scanManager.startDecode()
        }

        /**
         * ScanManager.stopDecode
         */
        fun stopDecode(scanManager: ScanManager) {
            if (!scanManager.scannerState) {

                return
            }
            scanManager.stopDecode()
        }

        /**
         * ScanManager.closeScanner
         *
         * @return
         */
        fun closeScanner(scanManager: ScanManager): Boolean {
            scanManager.stopDecode()

            return scanManager.closeScanner()
        }

    }
}