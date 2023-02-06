# Tracking Demo app

The Demo app consist of 2 columns with buttons. Each button represents one provided TrackingEvent:

+ 1'st column: buttons to track regular events, such as: `AddToCart` event, `StartView` event, etc.
+ 2'nd column: buttons to track the same event, but with extra `customParams` argument in the call.

![tracking-demo-app-screen](README_md/tracking-demo-app-screen.jpg)

## Run application

+ Open the tracking-demo-app in the AndroidStudio.

+ Setup SDK initialization parameters in the [MainActivity.kt](app/src/main/java/com/example/tracking_sdk/MainActivity.kt) file.

+ Run the app. Then open `Run` tab in the AndroidStudio.

+ Tap on buttons to see the log output.
There, you can see the JSON request to see what properties sent to backend.  

    ![tracking-demo-app-log](README_md/tracking-demo-app-log.jpg)
