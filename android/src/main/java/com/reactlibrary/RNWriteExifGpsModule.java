
package com.reactlibrary;

import java.util.Arrays;
import java.io.File;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import android.media.ExifInterface;
import android.widget.Toast;
import android.util.Log;
import android.media.MediaMetadataRetriever;

import com.reactlibrary.Mp4MetadataWriter;

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
  public void writeExifGps(String fileAbsolutePath, double latitude, double longtitude, double altitude) {
    try {
      if (this.isVideoFile(fileAbsolutePath)) {
          this.addGeoTagsToVideo(fileAbsolutePath, latitude, longtitude, altitude);
          return;
      }

      ExifInterface exif = new ExifInterface(fileAbsolutePath);
      exif.setAttribute(ExifInterface.TAG_GPS_LATITUDE, convert(latitude));
      exif.setAttribute(ExifInterface.TAG_GPS_LATITUDE_REF, latitude < 0 ? "S" : "N");
      exif.setAttribute(ExifInterface.TAG_GPS_LONGITUDE, convert(longtitude));
      exif.setAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF, longtitude < 0 ? "W" : "E");
      exif.setAttribute(ExifInterface.TAG_GPS_ALTITUDE, Double.toString(altitude));
      exif.saveAttributes();
    } catch (IOException e) {}
  }

  private boolean isVideoFile(String fileAbsolutePath) {
    String[] videoFormats = {"mov", "mp4", "mpeg4"};
    String[] pathParts = fileAbsolutePath.split("\\.", 0);
    return Arrays.asList(videoFormats).contains(pathParts[pathParts.length - 1].toLowerCase());
  }

  private void addGeoTagsToVideo(String fileAbsolutePath, double lat, double lon, double ele) {
    try {
//        Toast.makeText(getCurrentActivity(), fileAbsolutePath + "", Toast.LENGTH_LONG).show();
//        Toast.makeText(getCurrentActivity(), lat + "", Toast.LENGTH_LONG).show();
//        Toast.makeText(getCurrentActivity(), lon + "", Toast.LENGTH_LONG).show();
//        Toast.makeText(getCurrentActivity(), ele + "", Toast.LENGTH_LONG).show();
        new Mp4MetadataWriter().writeMetadata(fileAbsolutePath, lat, lon, ele);
//        this.readMetaData(fileAbsolutePath);
    } catch (Exception e) {
        Log.e("video", "Exception : " + e.getMessage());
    }
  }

  public void readMetaData(String fileAbsolutePath) {
//    File sdcard = Environment.getExternalStorageDirectory();
    File  file = new File(fileAbsolutePath);

    if (file.exists()) {
      Log.i("video", ".mp4 file Exist");

      //Added in API level 10
      MediaMetadataRetriever retriever = new MediaMetadataRetriever();
      try {
        retriever.setDataSource(file.getAbsolutePath());
        for (int i = 0; i < 1000; i++){
          //only Metadata != null is printed!
          if(retriever.extractMetadata(i)!=null) {
            Toast.makeText(getCurrentActivity(), retriever.extractMetadata(i), Toast.LENGTH_SHORT).show();
//            Log.i(TAG, "Metadata :: " + retriever.extractMetadata(i));
          }
        }
      } catch (Exception e) {
        Log.e("video", "Exception : " + e.getMessage());
      }
    } else {
      Log.e("video", ".mp4 file doesn´t exist.");
    }
  }

  private void getVideoMetadata() {

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
