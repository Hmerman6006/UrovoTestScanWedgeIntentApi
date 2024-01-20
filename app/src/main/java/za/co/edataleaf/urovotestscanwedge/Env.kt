package za.co.edataleaf.urovotestscanwedge

import android.os.Build

class Env {
    companion object {

        var manufacturer: String = Build.MANUFACTURER
        var model: String = Build.MODEL
    }
}