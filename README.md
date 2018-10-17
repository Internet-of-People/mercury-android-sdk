# Mercury Connect SDK

### TODOS
- should it be thread safe?
- should it be full async?
- send message from service to DAppEndpoint without android
- if the service uses :my_dapp_process, then we can only send global broadcasts

### MUSTS

user must put these into AndroidManifest.xml:
```$xml
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS" />

<!-- if needs to check for boot -->
<uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />

<service
    android:name=".mercury.connect.sdk.DAppService"
    android:enabled="true"
    android:permission="android.permission.BIND_JOB_SERVICE"
    android:process=":my_dapp_process"
    android:exported="false" />

<!-- if needs to check for boot -->
<receiver
    android:name=".AndroidOSBroadcastReceiver"
    android:directBootAware="true">
    <intent-filter>
        <action android:name="android.intent.action.BOOT_COMPLETED" />
        <action android:name="android.intent.action.LOCKED_BOOT_COMPLETED" />
    </intent-filter>
</receiver>
```