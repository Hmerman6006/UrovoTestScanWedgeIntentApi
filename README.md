# About
Needs ScanManager SDK release `platform_sdk_v4.1.0326.jar` in `libs` directory or for pure intent use without Sdk, ScanWedge version 2.1.19_20230220

# Phase
Epsilon Test

# Purpose
Initially to test the Urovo ScanWedge Intent Api for creating profiles and easy application shipping.  It follows Zebra's DataWedge conventions, albeit adapted to the ScanWedge available source material.<br>
References:  *[ProfileID](https://en.urovo.com/developer/android/device/scanner/configuration/PropertyID.html#USPS_4STATE_ENABLE)*.
*[ScanWedge Api Github Sample](https://github.com/urovosamples/ScanWedgeAPIsSample/blob/main/src/main/java/com/ubx/scanwedge/intentapi/MainActivity.java)*.
<br><br>
Due to high ScanManager version level for pure Intent Api, Sdk route was also followed and implemented.<br>
Sdk references: *[Sdk Release Android](https://github.com/urovosamples/SDK_ReleaseforAndroid/tree/master/Samples/ScanManager)*.
*[Urovo Docs](https://www.urovo.com/developer/android/device/ScanManager.html)*
*[Android on Sdk Best Practice](https://developer.android.com/guide/practices/sdk-best-practices)*

# So far
Tested on Urovo DT50Q with ScanWedge v2.1.9_20210729 and Sdk, it scans.  For Intent Api, ~~it does not create the profile or gather the intent barcode on scan yet~~ need high ScanWedge version enabled Urovo phone.

# How it works
Open App and scan.

## How it is suppose to work
The SW Utility creates the profile and sets the profile params with the Api categories in the activity's `onCreate` method.  The Main Activity's `onStart` method registers a broadcast receiver to listen to scanner status.

# What needs to be done
Event to monitor scanner status with Sdk.  Test pure intent Api.
