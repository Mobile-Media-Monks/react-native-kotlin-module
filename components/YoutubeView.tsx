import {ViewProps, requireNativeComponent, Platform, View} from 'react-native';

interface MyCustomViewProps extends ViewProps {
  id?: string;
}

export default Platform.select({
  ios: View,
  /* @ts-ignore */
  android: requireNativeComponent<MyCustomViewProps>('RNUtubeView'),
});
