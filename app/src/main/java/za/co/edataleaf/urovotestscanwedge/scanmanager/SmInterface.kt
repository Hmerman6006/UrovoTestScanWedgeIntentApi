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

        /**
         * Check if hard button scanner is pushed
         *
         * @return Boolean
         */
        @JvmStatic
        fun isKeyScanning(keyCode: Int): Boolean {
            return keyCode >= SCAN_KEYCODE[0] && keyCode <= SCAN_KEYCODE[SCAN_KEYCODE.size - 1]
        }

        /**
         * Setup Scanner
         *
         * @return Boolean
         */
        @JvmStatic
        fun openScanner(scanManager: ScanManager): Boolean {
            return try {
                var state = scanManager.scannerState
                if(!state) {
                    state = scanManager.openScanner()
                }
                scanManager.enableAllSymbologies(true)

                state && (scanManager.outputMode == 0 || scanManager.switchOutputMode(0))
            } catch (e: java.lang.RuntimeException) {
                false
            } catch (e: Exception) {
                false
            }
        }

        /**
         * ScanManager.startDecode
         */
        @JvmStatic
        fun startDecode(scanManager: ScanManager) {
            try {
                if (!scanManager.scannerState) {
                    return
                }

                if (scanManager.triggerLockState) {
                    return
                }
                scanManager.startDecode()
            } catch (e: java.lang.RuntimeException) {
                return
            } catch (e: Exception) {
                return
            }
        }

        /**
         * ScanManager.stopDecode
         */
        @JvmStatic
        fun stopDecode(scanManager: ScanManager) {
            try {

                if (!scanManager.scannerState) {

                    return
                }
                scanManager.stopDecode()
            } catch (e: java.lang.RuntimeException) {
                return
            } catch (e: Exception) {
                return
            }
        }

        /**
         * ScanManager.closeScanner
         *
         * @return Boolean
         */
        @JvmStatic
        fun closeScanner(scanManager: ScanManager): Boolean {
            return try {
                scanManager.stopDecode()

                scanManager.closeScanner()
            } catch (e: java.lang.RuntimeException) {
                false
            } catch (e: Exception) {
                false
            }
        }

    }
}