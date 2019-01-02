
package com.reactlibrary;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import android.media.ExifInterface;

import java.io.IOException;

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
    double longtitude,
    double altitude
  ) {
    try {
      ExifInterface exif = new ExifInterface(fileAbsolutePath);
      exif.setAttribute(ExifInterface.TAG_GPS_LATITUDE, convert(latitude));
      exif.setAttribute(ExifInterface.TAG_GPS_LATITUDE_REF, latitude < 0 ? "S" : "N");
      exif.setAttribute(ExifInterface.TAG_GPS_LONGITUDE, convert(longtitude));
      exif.setAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF, longtitude < 0 ? "W" : "E");
      exif.setAttribute(ExifInterface.TAG_GPS_ALTITUDE, Double.toString(altitude));
      exif.saveAttributes();
    } catch (IOException e) {}
  }

  public String convert(double latitude) {
    StringBuilder sb = new StringBuilder();

    latitude = Math.abs(latitude);
    int degree = (int) latitude;
    latitude *= 60;
    latitude -= (degree * 60.0d);
    int minute = (int) latitude;
    latitude *= 60;
    latitude -= (minute * 60.0d);
    int second = (int) (latitude * 1000.0d);

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