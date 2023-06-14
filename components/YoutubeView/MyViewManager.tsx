import {ViewProps, requireNativeComponent} from 'react-native';

interface MyCustomViewProps extends ViewProps {
  id?: string;
  videoId: string;
}

export default requireNativeComponent<MyCustomViewProps>('MyViewManager');
