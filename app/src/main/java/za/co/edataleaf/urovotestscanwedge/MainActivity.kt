package za.co.edataleaf.urovotestscanwedge

import android.content.Intent
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
import za.co.edataleaf.urovotestscanwedge.broadcast_receivers.SwReceiver
import za.co.edataleaf.urovotestscanwedge.scanwedge.SwHelper
import za.co.edataleaf.urovotestscanwedge.scanwedge.SwUtilities
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

    private lateinit var receiver: SwReceiver

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d("scan intent#0", "have intent ${intent?.action}")
        if (intent?.action?.equals(getString(R.string.activity_intent_filter_main_scan)) == true) {
            Log.d("scan intent", "have intent")
            //  Handle scan intent received from ScanWedge, add it to the list of scans
            val scanData =
                intent.getStringExtra(resources.getString(R.string.scanwedge_intent_key_data))
            Log.d("scan intent #1", scanData.toString())
            scanData?.let {
                viewModel.setBarcode(it)
            }
        } else Log.d("MainActivity", "No extra after")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        receiver = SwReceiver()
        SwUtilities.createSwProfile(
            this, this.resources.getString(R.string.activity_intent_filter_main_scan))

        setContent {
            UrovoTestScanWedgeTheme {
                // A surface container using the 'background' color from the theme
                val status by viewModel.scannerStatus.collectAsState()
                val barcode by viewModel.barcodeResult.collectAsState()

                var statusResult by rememberSaveable {
                    mutableStateOf("")
                }

                var barcodeResult by rememberSaveable {
                    mutableStateOf("")
                }

                LaunchedEffect(key1 = status, key2 = barcode) {
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

                    Greeting("STATUS: $status")

                    Spacer(modifier = Modifier.padding(8.dp))

                    Greeting(name = barcodeResult)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        SwHelper.registerScannerReceiver(this, receiver = receiver)
    }

    override fun onStop() {
        super.onStop()
        SwHelper.unRegisterScannerReceiver(this, receiver = receiver)
    }
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