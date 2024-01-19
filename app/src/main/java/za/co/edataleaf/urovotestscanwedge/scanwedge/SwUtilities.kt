package za.co.edataleaf.urovotestscanwedge.scanwedge

import android.content.Context
import android.content.Intent
import android.os.Bundle

/**
 * Urovo ScanWedge profile setup
 *
 * References:
 * PropertyId - https://en.urovo.com/developer/android/device/scanner/configuration/PropertyID.html
 * Trigger modes - ttps://en.urovo.com/developer/android/device/scanner/configuration/Triggering.html
 */
object SwUtilities {
    val code39 = true
    val code128 = true
    val ean8 = true
    val  ean13 = true
    const val PROFILE_NAME = "UrovoTest1Sw"

    @JvmStatic
    fun createSwProfile(context: Context, intentActionProps: String) {
        sendScanWedgeIntentWithExtra(
            context,
            SwInterface.API_ACTION,
            SwInterface.ACTION_EXTRA_CREATE_PROFILE,
            PROFILE_NAME
        )

        //  Requires ScanWedge V2.1.19_20230220

        //  Now configure that created profile to apply to our application
//        val profileConfig = Bundle()
//        profileConfig.putString(SwInterface.BUNDLE_EXTRA_PROFILE_NAME, PROFILE_NAME)
//        profileConfig.putString(SwInterface.BUNDLE_EXTRA_PROFILE_ENABLED, "true") //  Seems these are all strings
//        profileConfig.putString(SwInterface.BUNDLE_EXTRA_CONFIG_MODE, "UPDATE")
//
//        // Reader
//        val readerConfig = Bundle()
//        readerConfig.putString(SwInterface.BUNDLE_EXTRA_CATEGORY_NAME, SwCategory.CAT_READER)
//        readerConfig.putString(SwInterface.BUNDLE_EXTRA_RESET_CONFIG, "true")
//        val readerProps = Bundle()
//        readerProps.putString(SwCategory.Reader.SCANNER_ENABLE, "true")
//        readerProps.putString(SwCategory.Reader.TRIGGERING_MODES, "PULSE")
//        readerProps.putString(SwCategory.Reader.GOOD_READ_BEEP_ENABLE, "1")
//        readerProps.putString(SwCategory.Reader.LABEL_APPEND_ENTER, "0")
////        readerProps.putString("scanner_selection", "auto");  // barcodeProps.putString("configure_all_scanners", "true")
////        readerProps.putString("scanner_input_enabled", "true")
//        readerConfig.putBundle(SwInterface.BUNDLE_EXTRA_PARAM_LIST, readerProps)
//        profileConfig.putBundle(SwInterface.BUNDLE_EXTRA_CATEGORY_CONFIG, readerConfig)
//        val appConfig = Bundle()
//        appConfig.putString(
//            SwInterface.BUNDLE_EXTRA_PACKAGE_NAME,
//            context.packageName
//        ) //  Associate the profile with this app
//        appConfig.putStringArray(SwInterface.BUNDLE_EXTRA_ACTIVITY_LIST, arrayOf("*"))
//        profileConfig.putParcelableArray(SwInterface.BUNDLE_EXTRA_APP_LIST, arrayOf(appConfig))
//        sendScanWedgeIntentWithExtra(context, SwInterface.API_ACTION, SwInterface.ACTION_EXTRA_SET_CONFIG, profileConfig)
////
//        profileConfig.remove(SwInterface.BUNDLE_EXTRA_CATEGORY_CONFIG)
//
//        // Decoding
//        val barcodeConfig = Bundle()
//        barcodeConfig.putString(SwInterface.BUNDLE_EXTRA_CATEGORY_NAME, SwCategory.CAT_DECODER)
//        barcodeConfig.putString(SwInterface.BUNDLE_EXTRA_RESET_CONFIG, "true")
//        val barcodeProps = Bundle()
//        barcodeProps.putString(SwCategory.Decoder.CODE128_ENABLE, code128.toString())
//        barcodeProps.putString(SwCategory.Decoder.CODE39_ENABLE, code39.toString())
//        barcodeProps.putString(SwCategory.Decoder.EAN8_ENABLE, ean8.toString())
//        barcodeProps.putString(SwCategory.Decoder.EAN13_ENABLE, ean13.toString())
//        barcodeConfig.putBundle(SwInterface.BUNDLE_EXTRA_PARAM_LIST, barcodeProps)
//        profileConfig.putBundle(SwInterface.BUNDLE_EXTRA_CATEGORY_CONFIG, barcodeConfig)
//        sendScanWedgeIntentWithExtra(context, SwInterface.API_ACTION, SwInterface.ACTION_EXTRA_SET_CONFIG, profileConfig)
//
//        //  You can only configure one plugin at a time, we have done the barcode input, now do the intent output
//        profileConfig.remove(SwInterface.BUNDLE_EXTRA_CATEGORY_CONFIG)
//
//        // Output
//        val intentConfig = Bundle()
//        intentConfig.putString(SwInterface.BUNDLE_EXTRA_CATEGORY_NAME, SwCategory.CAT_OUTPUT)
//        intentConfig.putString(SwInterface.BUNDLE_EXTRA_RESET_CONFIG, "true")
//        val intentProps = Bundle()
////        intentProps.putString("intent_output_enabled", "true")
//        intentProps.putString(SwCategory.OutPut.WEDGE_INTENT_ENABLE, "true")
//        intentProps.putString(
//            SwCategory.OutPut.WEDGE_INTENT_ACTION_NAME,
//            intentActionProps  //context.resources.getString(R.string.activity_intent_filter_scan)
//        )
//        intentProps.putString(SwCategory.OutPut.WEDGE_INTENT_DELIVERY_MODE, "0")
//        intentProps.putString(SwCategory.OutPut.WEDGE_KEYBOARD_ENABLE, "false")
////        intentProps.putString("intent_delivery", "0") //  StartActivity
//        intentConfig.putBundle(SwInterface.BUNDLE_EXTRA_PARAM_LIST, intentProps)
//        profileConfig.putBundle(SwInterface.BUNDLE_EXTRA_CATEGORY_CONFIG, intentConfig)
//        sendScanWedgeIntentWithExtra(context, SwInterface.API_ACTION, SwInterface.ACTION_EXTRA_SET_CONFIG, profileConfig)
    }

    private fun sendScanWedgeIntentWithExtra(
        context: Context,
        action: String,
        extraKey: String,
        extraValue: String
    ) {
        val swIntent = Intent()
        swIntent.action = action
        swIntent.putExtra(extraKey, extraValue)
        swIntent.putExtra(SwInterface.ACTION_EXTRA_SEND_RESULT, "true")
        context.sendBroadcast(swIntent)
    }

    private fun sendScanWedgeIntentWithExtra(
        context: Context,
        action: String,
        extraKey: String,
        extras: Bundle
    ) {
        val swIntent = Intent()
        swIntent.action = action
        swIntent.putExtra(extraKey, extras)
        swIntent.putExtra(SwInterface.ACTION_EXTRA_SEND_RESULT, "true")
        context.sendBroadcast(swIntent)
    }

}