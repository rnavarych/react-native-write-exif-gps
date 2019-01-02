
package com.reactlibrary;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

public class RNWriteExifGpsModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public RNWriteExifGpsModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNWriteExifGps";
  }

  @ReactMethod
  public void writeExifGps(
    String fileAbsolutePath,
    double latitude,
    double longtitude
  ) {
    ExifInterface exif = new ExifInterface(fileAbsolutePath);
    exif.setAttribute(ExifInterface.TAG_GPS_LATITUDE, convert(latitude));
    exif.setAttribute(ExifInterface.TAG_GPS_LATITUDE_REF, lat < 0 ? "S" : "N");
    exif.setAttribute(ExifInterface.TAG_GPS_LONGITUDE, convert(longtitude));
    exif.setAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF, lon < 0 ? "W" : "E");
    exif.saveAttributes();
  }

  public String convert(double latitude) {
    StringBuilder sb = new StringBuilder();

    latitude=Math.abs(latitude);
    int degree = (int) latitude;
    latitude *= 60;
    latitude -= (degree * 60.0d);
    int minute = (int) latitude;
    latitude *= 60;
    latitude -= (minute * 60.0d);
    int second = (int) (latitude*1000.0d);

    sb.setLength(0);
    sb.append(degree);
    sb.append("/1,");
    sb.append(minute);
    sb.append("/1,");
    sb.append(second);
    sb.append("/1000,");

    return sb.toString();
  }
}