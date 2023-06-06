import {NativeModules, Platform} from 'react-native';

const {CustomMethods} = NativeModules;

interface CustomMethodsProps {
  getBatteryLevel: () => Promise<number>;
  getPhoneId: (callback: (id: string) => void) => void;
}

export default Platform.select({
  ios: {getBatteryLevel: () => Promise.resolve(0), getPhoneId: () => {}},
  android: CustomMethods as CustomMethodsProps,
});
