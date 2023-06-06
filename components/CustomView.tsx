import {ViewProps, requireNativeComponent, ViewStyle} from 'react-native';

interface MyCustomViewProps extends ViewProps {
  text?: string;
  style: ViewStyle;
  color?: string;
}

export default requireNativeComponent<MyCustomViewProps>('CustomView');
