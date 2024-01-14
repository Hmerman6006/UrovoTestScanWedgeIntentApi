package za.co.edataleaf.urovotestscanwedge.scanwedge

class SwInterface() {

    companion object {
        const val API_ACTION = "com.ubx.datawedge.api.ACTION"

        /**
         * Intent result codes deliver status information about ScanWedge API commands to help developers determine the flow of data and the function of their business logic.
         */
        const val API_RESULT_ACTION = "com.ubx.datawedge.api.RESULT_ACTION"

        /**
         * Used to create a copy of an existing ScanWedge Profile including all settings.
         */
        const val ACTION_EXTRA_CLONE_PROFILE = "CLONE_PROFILE"

        /**
         * Used to create a new ScanWedge Profile on the device. If a Profile of the same name already exists on the device, the intent will fail.
         * To create a Profile and configure its settings parameters, see [.ACTION_EXTRA_SET_CONFIG].
         * WARNING:  recommends that Profile names be unique, contain alpha-numeric characters, and be short in length to avoid possible import/export profile issues.
         */
        const val ACTION_EXTRA_CREATE_PROFILE = "CREATE_PROFILE"

        /**
         * Used to delete an existing deletable Profile, no including the "Default" Profile.
         * WARNING: Supports use of the wildcard character (“*”), which deletes all deletable Profiles from the configuration, no including the "Default" Profile.
         */
        const val ACTION_EXTRA_DELETE_PROFILE = "DELETE_PROFILE"

        /**
         * Used to import a ScanWedge Profile and/or Config settings files.
         * A Profile is a single group of settings that control how ScanWedge will behave with one or more specific applications and devices.
         * A Config file can contain numerous Profiles, and stores all other ScanWedge settings, including its status (enabled/disabled) and other configurable parameters.
         */
        const val ACTION_EXTRA_IMPORT_CONFIG = "IMPORT_CONFIG"
        const val ACTION_EXTRA_FILE_LIST = "FILE_LIST"

        /**
         * Boolean value used to enable (true) or disable (false) ScanWedge on the device.
         */
        const val ACTION_EXTRA_ENABLE_SCANWEDGE = "ENABLE_SCANWEDGE"

        /**
         * Gets the name of the Profile currently in use by ScanWedge
         */
        const val ACTION_EXTRA_GET_ACTIVE_PROFILE = "GET_ACTIVE_PROFILE"

        /**
         * Gets the [.BUNDLE_EXTRA_PARAM_LIST] settings in the specified Profile, returned as a set of value pairs or a category config bundle.
         */
        const val ACTION_EXTRA_GET_CONFIG = "GET_CONFIG"

        /**
         * Returns the status of ScanWedge as "enabled" or "disabled" as a string extra.
         */
        const val ACTION_EXTRA_GET_SCANWEDGE_STATUS = "GET_SCANWEDGE_STATUS"

        /**
         * Gets a list of ScanWedge Profiles on the device.
         */
        const val ACTION_EXTRA_GET_PROFILES_LIST = "GET_PROFILES_LIST"

        /**
         * Returns the status of the scanner currently selected by ScanWedge as the default.
         */
        const val ACTION_EXTRA_GET_SCANNER_STATUS = "GET_SCANNER_STATUS"

        /**
         * Used to rename an existing ScanWedge Profile.
         */
        const val ACTION_EXTRA_RENAME_PROFILE = "RENAME_PROFILE"

        /**
         * Used to reset the default Profile to factory-default settings.
         */
        const val ACTION_EXTRA_RESET_DEFAULT_PROFILE = "RESET_DEFAULT_PROFILE"

        /**
         * Resets all user-configured settings and restores ScanWedge to its factory-default settings. This action cannot be undone.
         */
        const val ACTION_EXTRA_RESTORE_CONFIG = "RESTORE_CONFIG"
        const val ACTION_EXTRA_RESULT_CATEGORY = "RESULT_CATEGORY"
        const val ACTION_EXTRA_SCANNER_INPUT_PLUGIN = "SCANNER_INPUT_PLUGIN"
        const val ACTION_EXTRA_SEND_RESULT = "SEND_RESULT"

        /**
         * Used to create, update or replace a ScanWedge Profile and its settings, and can configure multiple options with a single intent action.
         * SET_CONFIG implements nested bundles, where a [.BUNDLE_EXTRA_PARAM_LIST] (parameter list) bundle can be nested within
         * its corresponding [.BUNDLE_EXTRA_CATEGORY_CONFIG] (option based on input, data processing, utilities, or output) bundle,
         * which can then be nested within the main SET_CONFIG bundle. Multiple [.BUNDLE_EXTRA_CATEGORY_CONFIG] bundles can be nested within the SET_CONFIG bundle.
         */
        const val ACTION_EXTRA_SET_CONFIG = "SET_CONFIG"
        const val ACTION_EXTRA_SET_DEFAULT_PROFILE = "SET_DEFAULT_PROFILE"
        const val ACTION_EXTRA_SET_DISABLED_APP_LIST = "SET_DISABLED_APP_LIST"
        const val ACTION_EXTRA_SOFT_SCAN_TRIGGER = "SOFT_SCAN_TRIGGER"

        /**
         * Temporarily update the settings of the active profile during runtime by passing one or more barcode, scanner and/or reader parameters as intent extras.
         */
        const val ACTION_EXTRA_SWITCH_SCANNER_PARAMS = "SWITCH_SCANNER_PARAMS"

        /**
         * Used to switch the application's association to the specified Profile.
         * The specified Profile must not already be associated with another application;
         * an application cannot be associated with more than one Profile.
         * CAUTION: Switching to a disabled Profile is prevented if the GET_IGNORE_DISABLED_PROFILES API returns a value of "true."
         */
        const val ACTION_EXTRA_SWITCH_TO_PROFILE = "SWITCH_TO_PROFILE"
        const val ACTION_EXTRA_UNREGISTER_FOR_NOTIFICATION = "UNREGISTER_FOR_NOTIFICATION"
        const val ACTION_GET_SCANNER_STATUS = "ACTION_GET_SCANNER_STATUS"
        const val ACTION_RESET_DEFAULT_PROFILE = "ACTION_RESETDEFAULTPROFILE"
        const val ACTION_SCANNER_INPUT_PLUGIN = "ACTION_SCANNERINPUTPLUGIN"
        const val ACTION_SET_DEFAULT_PROFILE = "ACTION_SETDEFAULTPROFILE"
        const val ACTION_SOFT_SCAN_TRIGGER = "ACTION_SOFTSCANTRIGGER"
        const val ACTION_SWITCH_TO_PROFILE = "ACTION_SWITCHTOPROFILE"

        const val EXTRA_GET_VERSION_INFO = "GET_VERSION_INFO"
        const val RESULT_VERSION_INFO = "VERSION_INFO"
        const val PROFILE_CONFIG_MODE_CREATE_IF_NOT_EXIST = "CREATE_IF_NOT_EXIST"
        const val PROFILE_CONFIG_MODE_OVERWRITE = "OVERWRITE"
        const val PROFILE_CONFIG_MODE_REMOVE = "REMOVE"
        const val PROFILE_CONFIG_MODE_UPDATE = "UPDATE"

        /**
         * Name of the received intent command
         */
        const val RESULT_ACTION_EXTRA_COMMAND = "COMMAND"
        const val RESULT_ACTION_EXTRA_COMMAND_IDENTIFIER = "COMMAND_IDENTIFIER"

        const val EXTRA_GET_ACTIVE_PROFILE = "GET_ACTIVE_PROFILE"
        const val EXTRA_GET_SCANWEDGE_STATUS = "GET_SCANWEDGE_STATUS"
        const val EXTRA_GET_SCANNER_STATUS = "GET_SCANNER_STATUS"
        const val EXTRA_GET_PROFILES_LIST = "GET_PROFILES_LIST"
        const val RESULT_ACTIVE_PROFILE = "ACTIVE_PROFILE"
        const val RESULT_SCANWEDGE_STATUS = "SCANWEDGE_STATUS"
        const val RESULT_SCANNER_STATUS = "SCANNER_STATUS"
        const val RESULT_PROFILES_LIST = "PROFILES_LIST"

        const val RESULT_ACTION_EXTRA_GET_ACTIVE_PROFILE = "GET_ACTIVE_PROFILE"
        const val RESULT_ACTION_EXTRA_GET_CONFIG = "GET_CONFIG"
        const val RESULT_ACTION_EXTRA_GET_SCANWEDGE_STATUS = "GET_SCANWEDGE_STATUS"
        const val RESULT_ACTION_EXTRA_GET_PROFILES_LIST = "GET_PROFILES_LIST"
        const val RESULT_ACTION_EXTRA_GET_SCANNER_STATUS = "GET_SCANNER_STATUS"
        const val RESULT_ACTION_EXTRA_GET_VERSION_INFO = "GET_VERSION_INFO"

        const val BUNDLE_EXTRA_RESULT_CODE = "RESULT_CODE"
        const val BUNDLE_EXTRA_ACTIVITY_LIST = "ACTIVITY_LIST"
        const val BUNDLE_EXTRA_APP_LIST = "APP_LIST"
        const val BUNDLE_EXTRA_BARCODE_SCANNING_VERSION = "BARCODE_SCANNING"
        const val BUNDLE_EXTRA_CONFIG_MODE = "CONFIG_MODE"
        const val BUNDLE_EXTRA_SCANWEDGE_VERSION = "SCANWEDGE"
        const val BUNDLE_EXTRA_DECODER_LIBRARY_VERSION = "DECODER_LIBRARY"
        const val BUNDLE_EXTRA_OUTPUT_PLUGIN_NAME = "OUTPUT_PLUGIN_NAME"
        const val BUNDLE_EXTRA_PACKAGE_NAME = "PACKAGE_NAME"
        const val BUNDLE_EXTRA_PARAM_LIST = "PARAM_LIST"

        /**
         * A bundle array (nested within the main bundle) that contains settings of each category
         * [.BUNDLE_EXTRA_CATEGORY_NAME]
         */
        const val BUNDLE_EXTRA_CATEGORY_CONFIG = "CATEGORY_CONFIG"
        const val BUNDLE_EXTRA_CATEGORY_NAME = "CATEGORY_NAME"
        const val BUNDLE_EXTRA_PROFILE_ENABLED = "PROFILE_ENABLED"
        const val BUNDLE_EXTRA_PROFILE_NAME = "PROFILE_NAME"
        const val BUNDLE_EXTRA_RESET_CONFIG = "RESET_CONFIG"
        const val BUNDLE_EXTRA_SCANNER_FIRMWARE_VERSION = "SCANNER_FIRMWARE"
        const val BUNDLE_EXTRA_SIMULSCAN_VERSION = "SIMULSCAN"
        const val API_SCANWEDGE_STATUS_DISABLED = "DISABLED"
        const val API_SCANWEDGE_STATUS_ENABLED = "ENABLED"
        const val API_DISABLE_PLUGIN = "DISABLE_PLUGIN"
        const val API_ENABLE_PLUGIN = "ENABLE_PLUGIN"
        const val API_START_SCANNING = "START_SCANNING"
        const val API_STOP_SCANNING = "STOP_SCANNING"

        const val RESULT_ACTION_EXTRA_RESULT = "RESULT"

        /**
         * Result Code can contain any of the fields below depending on the API in use:
         */
        const val RESULT_ACTION_EXTRA_RESULT_FAILURE = "FAILURE"
        const val RESULT_ACTION_EXTRA_RESULT_INFO = "RESULT_INFO"
        const val RESULT_ACTION_EXTRA_RESULT_SUCCESS = "SUCCESS"
        const val RESULT_ACTION_LABEL_RESULT_CODE = "RESULT_CODE"
        const val RESULT_ACTION_LABEL_RESULT_CODE_PREVIOUS_DEFAULT_PROFILE =
            "PREVIOUS_DEFAULT_PROFILE"
        const val RESULT_ACTION_LABEL_RESULT_CODE_PREVIOUS_PROFILE = "PREVIOUS_PROFILE"
        const val RESULT_ACTION_LABEL_RESULT_CODE_PROFILE_NAME = "PROFILE_NAME"
        const val RESULT_ACTION_LABEL_RESULT_CODE_SOURCE_PROFILE_NAME = "SOURCE_PROFILE_NAME"
        const val RESULT_ACTION_RESULT_CODE_APP_ALREADY_ASSOCIATED = "APP_ALREADY_ASSOCIATED"
        const val RESULT_ACTION_RESULT_CODE_APP_ALREADY_IN_DISABLED_LIST =
            "APP_ALREADY_IN_DISABLED_LIST"
        const val RESULT_ACTION_RESULT_CODE_APP_NOT_IN_DISABLED_LIST = "APP_NOT_IN_DISABLED_LIST"
        const val RESULT_ACTION_RESULT_CODE_BUNDLE_EMPTY = "BUNDLE_EMPTY"
        const val RESULT_ACTION_RESULT_CODE_SCANWEDGE_ALREADY_DISABLED =
            "SCANWEDGE_ALREADY_DISABLED"
        const val RESULT_ACTION_RESULT_CODE_SCANWEDGE_ALREADY_ENABLED = "SCANWEDGE_ALREADY_ENABLED"
        const val RESULT_ACTION_RESULT_CODE_SCANWEDGE_DISABLED = "SCANWEDGE_DISABLED"
        const val RESULT_ACTION_RESULT_CODE_INPUT_NOT_ENABLED = "INPUT_NOT_ENABLED"
        const val RESULT_ACTION_RESULT_CODE_NO_ACTIVE_PROFILE = "NO_ACTIVE_PROFILE"
        const val RESULT_ACTION_RESULT_CODE_OPERATION_NOT_ALLOWED = "OPERATION_NOT_ALLOWED"
        const val RESULT_ACTION_RESULT_CODE_PACKAGE_OR_ACTIVITY_INVALID =
            "INVALID PACKAGE OR ACTIVITY"
        const val RESULT_ACTION_RESULT_CODE_PARAMETER_INVALID = "PARAMETER_INVALID"
        const val RESULT_ACTION_RESULT_CODE_PARAMETER_NOT_SUPPORTED = "PARAMETER_NOT_SUPPORTED"
        const val RESULT_ACTION_RESULT_CODE_CATEGORY_BUNDLE_INVALID = "CATEGORY_BUNDLE_INVALID"
        const val RESULT_ACTION_RESULT_CODE_PLUGIN_DISABLED = "PLUGIN_DISABLED"
        const val RESULT_ACTION_RESULT_CODE_CATEGORY_NOT_SUPPORTED = "CATEGORY_NOT_SUPPORTED"
        const val RESULT_ACTION_RESULT_CODE_PROFILE_ALREADY_EXISTS = "PROFILE_ALREADY_EXISTS"
        const val RESULT_ACTION_RESULT_CODE_PROFILE_ALREADY_SET = "PROFILE_ALREADY_SET"
        const val RESULT_ACTION_RESULT_CODE_PROFILE_DISABLED = "PROFILE_DISABLED"
        const val RESULT_ACTION_RESULT_CODE_PROFILE_HAS_APP_ASSOCIATION =
            "PROFILE_HAS_APP_ASSOCIATION"
        const val RESULT_ACTION_RESULT_CODE_PROFILE_NAME_EMPTY = "PROFILE_NAME_EMPTY"
        const val RESULT_ACTION_RESULT_CODE_PROFILE_NOT_FOUND = "PROFILE_NOT_FOUND"
        const val RESULT_ACTION_RESULT_CODE_SCANNER_ALREADY_DISABLED = "SCANNER_ALREADY_DISABLED"
        const val RESULT_ACTION_RESULT_CODE_SCANNER_ALREADY_ENABLED = "SCANNER_ALREADY_ENABLED"
        const val RESULT_ACTION_RESULT_CODE_SCANNER_DISABLED = "SCANNER_DISABLED"
        const val RESULT_ACTION_RESULT_CODE_SCANNER_DISABLE_FAILED = "SCANNER_DISABLE_FAILED"
        const val RESULT_ACTION_RESULT_CODE_SCANNER_ENABLE_FAILED = "SCANNER_ENABLE_FAILED"
        const val RESULT_ACTION_RESULT_CODE_SCANNER_NOT_CONNECTED = "SCANNER_NOT_CONNECTED"
        const val RESULT_ACTION_RESULT_CODE_UNKNOWN = "UNKNOWN"
        const val RESULT_ACTION_RESULT_CODE_VALUE_INVALID = "VALUE_INVALID"
        const val RESULT_ACTION_RESULT_CODE_VALUE_NOT_SUPPORTED = "VALUE_NOT_SUPPORTED"
    }
}