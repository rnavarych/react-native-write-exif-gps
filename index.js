import { NativeModules } from 'react-native';

const { RNWriteExifGps } = NativeModules;

export const writeExifGps = RNWriteExifGps.writeExifGps;
