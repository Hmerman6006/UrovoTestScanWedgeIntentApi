package za.co.edataleaf.urovotestscanwedge

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.device.ScanManager
import android.device.ScanManager.ACTION_DECODE
import android.device.scanner.configuration.PropertyID
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import za.co.edataleaf.urovotestscanwedge.broadcast_receivers.SmReceiver
import za.co.edataleaf.urovotestscanwedge.broadcast_receivers.SwReceiver
import za.co.edataleaf.urovotestscanwedge.scanmanager.SmInterface
import za.co.edataleaf.urovotestscanwedge.scanwedge.SwHelper
import za.co.edataleaf.urovotestscanwedge.ui.theme.UrovoTestScanWedgeTheme
import za.co.edataleaf.urovotestscanwedge.viewmodels.MainViewModel
import za.co.edataleaf.urovotestscanwedge.viewmodels.MainViewModelFactory


class MainActivity() : ComponentActivity(), OnReceiverListenerInterface {

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(this)
    }

    override fun onReceivingScannerStatusBroadcast(status: String) {
        viewModel.setStatus(status)
    }

    override var receiverActive: Boolean = false

    override fun onReceivingScannerBarcodeBroadcast(barcode: String) {
        viewModel.setBarcode(barcode)

        if(mScanManager.scannerState) viewModel.setStatus("READY")
    }

    private lateinit var swReceiver: SwReceiver
    private lateinit var smReceiver: SmReceiver

    private lateinit var mScanManager: ScanManager

//    override fun onNewIntent(intent: Intent?) {
//        super.onNewIntent(intent)
//        Log.d("scan intent#0", "have intent ${intent?.action}")
//        if (intent?.action?.equals(resources.getString(R.string.activity_main)) == true) {
//            Log.d("scan intent", "have intent")
////            Log.d("BUNDLE_EXTRA_PROFILE_NAME", intent?.hasExtra(SwInterface.BUNDLE_EXTRA_PROFILE_NAME).toString())
////            Log.d("BUNDLE_EXTRA_APP_LIST", intent?.hasExtra(SwInterface.BUNDLE_EXTRA_APP_LIST).toString())
////            Log.d("EXTRA_GET_SCANNER_STATUS", intent?.hasExtra(SwInterface.EXTRA_GET_SCANNER_STATUS).toString())
//            //  Handle scan intent received from ScanWedge, add it to the list of scans
//            val scanData =
//                intent.getStringExtra(SmInterface.BARCODE_STRING_TAG)
//            Log.d("scan intent #1", scanData.toString())
//            scanData?.let {
//                viewModel.setBarcode(it)
//            }
//        } else Log.d("MainActivity", "No extra after")
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mScanManager = ScanManager()
        if(::mScanManager.isInitialized) {
            smReceiver = SmReceiver()
            smReceiver.setOnReceiverListenerInterface(this)
        } else {

            swReceiver = SwReceiver()
//        SwUtilities.createSwProfile(
//            this, this.resources.getString(R.string.activity_intent_filter_main_scan))
        }

        setContent {
            UrovoTestScanWedgeTheme {
                // A surface container using the 'background' color from the theme
                val status by viewModel.scannerStatus.collectAsState()
                val barcode by viewModel.barcodeResult.collectAsState(initial = "")

                var barcodeResult by rememberSaveable {
                    mutableStateOf("")
                }

                var statusResult by rememberSaveable {
                    mutableStateOf("")
                }

                LaunchedEffect(key1 = barcode, key2 = status) {
                    statusResult = status
                    barcodeResult = barcode
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Greeting("MANUFACTURER: ${Env.manufacturer}")
                    Greeting("MODEL: ${Env.model}")

                    Spacer(modifier = Modifier.padding(16.dp))

                    Greeting("STATUS: $statusResult")

                    Spacer(modifier = Modifier.padding(8.dp))

                    Greeting(name = barcodeResult)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if(::mScanManager.isInitialized && ::smReceiver.isInitialized && SmInterface.openScanner(mScanManager)) {
            Log.d("onResume","register")
            Log.d("openScanner2","${mScanManager.outputMode}")
            viewModel.setStatus("SETUP")

            val filter = IntentFilter()
            val idbuf = intArrayOf(
                PropertyID.WEDGE_INTENT_ACTION_NAME,
                PropertyID.WEDGE_INTENT_DATA_STRING_TAG
            )
            val value_buf = mScanManager.getParameterString(idbuf)
            if (value_buf != null && value_buf[0] != null && value_buf[0] != "") {
                filter.addAction(value_buf[0])
            } else {
                filter.addAction(ACTION_DECODE)
            }

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                this@MainActivity.registerReceiver(smReceiver, filter, Context.RECEIVER_EXPORTED)
            } else {
                this@MainActivity.registerReceiver(smReceiver, filter)

            }

            if(mScanManager.scannerState) viewModel.setStatus("READY")
//            SmInterface.startDecode(mScanManager)
        }

//            SwHelper.registerScannerReceiver(this, receiver = receiver)
    }

    override fun onPause() {
        super.onPause()
        if(::mScanManager.isInitialized) {
            Log.d("onPause","stop")
            SmInterface.stopDecode(mScanManager)

            if(!mScanManager.scannerState) viewModel.setStatus("STOP")
//            SwHelper.unRegisterScannerReceiver(this, receiver = receiver)
        }

        if(::smReceiver.isInitialized) {
            Log.d("onPause","unregisterReceiver")
            this@MainActivity.unregisterReceiver(smReceiver)
//            SwHelper.unRegisterScannerReceiver(this, receiver = receiver)
        }
    }

//    override fun onStart() {
//        super.onStart()
//        SwHelper.registerScannerReceiver(this, receiver = receiver)
//    }
//
//    override fun onStop() {
//        super.onStop()
//        SwHelper.unRegisterScannerReceiver(this, receiver = receiver)
//    }
}

@Composable
fun Greeting(name: String) {
    Text(
        text = name,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UrovoTestScanWedgeTheme {
        Greeting("Android")
    }
}