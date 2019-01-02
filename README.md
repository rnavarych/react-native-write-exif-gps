
# react-native-write-exif-gps

## Getting started

`$ npm install react-native-write-exif-gps --save`

### Mostly automatic installation

`$ react-native link react-native-write-exif-gps`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-write-exif-gps` and add `RNWriteExifGps.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNWriteExifGps.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNWriteExifGpsPackage;` to the imports at the top of the file
  - Add `new RNWriteExifGpsPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-write-exif-gps'
  	project(':react-native-write-exif-gps').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-write-exif-gps/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-write-exif-gps')
  	```

#### Windows
[Read it! :D](https://github.com/ReactWindows/react-native)

1. In Visual Studio add the `RNWriteExifGps.sln` in `node_modules/react-native-write-exif-gps/windows/RNWriteExifGps.sln` folder to their solution, reference from their app.
2. Open up your `MainPage.cs` app
  - Add `using Write.Exif.Gps.RNWriteExifGps;` to the usings at the top of the file
  - Add `new RNWriteExifGpsPackage()` to the `List<IReactPackage>` returned by the `Packages` method


## Usage
```javascript
import RNWriteExifGps from 'react-native-write-exif-gps';

// TODO: What to do with the module?
RNWriteExifGps;
```
  