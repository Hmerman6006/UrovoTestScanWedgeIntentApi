package za.co.edataleaf.urovotestscanwedge

import android.content.Context
import android.content.IntentFilter
import android.device.ScanManager.ACTION_DECODE
import android.device.scanner.configuration.PropertyID
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
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
import za.co.edataleaf.urovotestscanwedge.scanmanager.SmInterface
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

        if(env.mScanManager.scannerState) viewModel.setStatus(getString(R.string.ready))
    }

    private val env = Env()

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

        env.initScanEnv(this, getString(R.string.activity_intent_filter_main_scan))

        setContent {
            UrovoTestScanWedgeTheme {

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

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        Log.d("onKeyUp", keyCode.toString())
        if(SmInterface.isKeyScanning(keyCode = keyCode)) {
            viewModel.setStatus(getString(R.string.ready))
        }
        return super.onKeyUp(keyCode, event)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        Log.d("onKeyDown", keyCode.toString())
        if(SmInterface.isKeyScanning(keyCode = keyCode)) {
            viewModel.setStatus(getString(R.string.scanning))
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onResume() {
        super.onResume()

        if(env.hasScanManager() && env.hasSmReceiver() && SmInterface.openScanner(env.mScanManager)) {
            Log.d("onResume","register")
            Log.d("openScanner2","${env.mScanManager.outputMode}")
            viewModel.setStatus(getString(R.string.starting_ellipses))

            val filter = IntentFilter()
            val idBuf = intArrayOf(
                PropertyID.WEDGE_INTENT_ACTION_NAME,
                PropertyID.WEDGE_INTENT_DATA_STRING_TAG
            )
            val valueBuf = env.mScanManager.getParameterString(idBuf)
            if (valueBuf != null && valueBuf[0] != null && valueBuf[0] != "") {
                filter.addAction(valueBuf[0])
            } else {
                filter.addAction(ACTION_DECODE)
            }

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                this@MainActivity.registerReceiver(env.smReceiver, filter, Context.RECEIVER_EXPORTED)
            } else {
                this@MainActivity.registerReceiver(env.smReceiver, filter)

            }

            if(env.mScanManager.scannerState) viewModel.setStatus(getString(R.string.ready))
//            SmInterface.startDecode(env.mScanManager)
        }

//            SwHelper.registerScannerReceiver(this, receiver = env.swReceiver)
    }

    override fun onPause() {
        super.onPause()
        if(env.hasScanManager()) {
            Log.d("onPause","stop")
            SmInterface.stopDecode(env.mScanManager)

            if(!env.mScanManager.scannerState) viewModel.setStatus(getString(R.string.stopped))
//            SwHelper.unRegisterScannerReceiver(this, receiver = env.swReceiver)
        }

        if(env.hasSmReceiver()) {
            Log.d("onPause","unregisterReceiver")
            this@MainActivity.unregisterReceiver(env.smReceiver)
//            SwHelper.unRegisterScannerReceiver(this, receiver = env.swReceiver)
        }
    }

//    override fun onStart() {
//        super.onStart()
//        SwHelper.registerScannerReceiver(this, receiver = env.swReceiver)
//    }
//
//    override fun onStop() {
//        super.onStop()
//        SwHelper.unRegisterScannerReceiver(this, receiver = env.swReceiver)
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