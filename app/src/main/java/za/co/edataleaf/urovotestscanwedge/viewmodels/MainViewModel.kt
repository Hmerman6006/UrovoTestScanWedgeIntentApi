package za.co.edataleaf.urovotestscanwedge.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel: ViewModel() {

    private val _scannerStatus: MutableStateFlow<String> = MutableStateFlow("")
    val scannerStatus = _scannerStatus.asStateFlow()

    private val _barcode: MutableStateFlow<String> = MutableStateFlow("")
    val barcodeResult = _barcode.asStateFlow()

    fun setBarcode(barcodeResult:String) {
        Log.d("vm barcode", barcodeResult)
        _barcode.value = barcodeResult
    }

    fun setStatus(status:String) {
        Log.d("vm status", status)
        _scannerStatus.value = status
    }
}