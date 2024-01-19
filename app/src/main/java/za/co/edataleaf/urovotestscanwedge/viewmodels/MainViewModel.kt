package za.co.edataleaf.urovotestscanwedge.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val _scannerStatus: MutableStateFlow<String> = MutableStateFlow("STARTING")
    val scannerStatus = _scannerStatus.asStateFlow()

    private val _barcode: Channel<String> = Channel()
    val barcodeResult = _barcode.receiveAsFlow()

    fun setBarcode(barcodeResult:String) {
        viewModelScope.launch {
            Log.d("vm barcode", barcodeResult)
            _barcode.send(barcodeResult)
        }
    }

    fun setStatus(status:String) {
        Log.d("vm status", status)
        _scannerStatus.value = status
    }
}