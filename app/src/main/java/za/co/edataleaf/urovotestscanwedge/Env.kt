package za.co.edataleaf.urovotestscanwedge

import android.os.Build

class Env {
    companion object {

        var manufacturer = Build.MANUFACTURER
        var model = Build.MODEL
    }
}