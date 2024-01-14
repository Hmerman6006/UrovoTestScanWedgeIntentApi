
#Purpose
To test the Urovo ScanWedge Intent Api for creating profiles and easy application shipping.  It follows Zebra's DataWedge conventions, albeit adapted to the ScanWedge available source material.
*[ProfileID](https://en.urovo.com/developer/android/device/scanner/configuration/PropertyID.html#USPS_4STATE_ENABLE)*.
*[ScanWedge Api Github Sample](https://github.com/urovosamples/ScanWedgeAPIsSample/blob/main/src/main/java/com/ubx/scanwedge/intentapi/MainActivity.java)*.

#So far
Tested on Urovo DT50Q, but does not create the profile or gather the intent barcode on scan.

#How it works
The Main Activty's on start method registers a brodcast receiver.  The SW Utility creates the profile and sets the profile params with the Api categories.
