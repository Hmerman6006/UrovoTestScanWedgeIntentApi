package za.co.edataleaf.urovotestscanwedge

interface OnReceiverListenerInterface {
    fun onReceivingScannerStatusBroadcast(status: String)

    var receiverActive: Boolean
}