import {useEffect, useRef, useState} from 'react';
import {
  Platform,
  View,
  UIManager,
  findNodeHandle,
  Dimensions,
  Button,
} from 'react-native';
import MyViewManager from './MyViewManager';

const dimensions = Dimensions.get('screen');

const createFragment = (viewId: number) =>
  UIManager.dispatchViewManagerCommand(
    viewId,
    // @ts-ignore
    UIManager.MyViewManager.Commands.create.toString(),
    [viewId],
  );

const YoutubeView = ({id}: {id: string}) => {
  const ref = useRef(null);

  if (Platform.OS === 'ios') {
    return <View />;
  }

  const pauseVideo = () => {
    const viewId = findNodeHandle(ref.current) as number;
    if (viewId != null) {
      console.log('RN pauseAudio ');
      UIManager.dispatchViewManagerCommand(
        viewId,
        // @ts-ignore
        UIManager.MyViewManager.Commands.pause.toString(),
        [viewId],
      );
    }
  };

  const resumeVideo = () => {
    const viewId = findNodeHandle(ref.current) as number;
    if (viewId != null) {
      UIManager.dispatchViewManagerCommand(
        viewId,
        // @ts-ignore
        UIManager.MyViewManager.Commands.resume.toString(),
        [viewId],
      );
    }
  };

  useEffect(() => {
    const viewId = findNodeHandle(ref.current) as number;
    createFragment(viewId);
  }, []);

  return (
    <View>
      <MyViewManager
        ref={ref}
        videoId={id}
        style={{
          height: dimensions.width * 0.56,
          width: dimensions.width,
          backgroundColor: 'red',
        }}
      />

      <View
        style={{
          flexDirection: 'row',
          justifyContent: 'space-evenly',
          marginTop: 20,
        }}>
        <Button title="Pause" onPress={pauseVideo} />
        <Button title="Resume" onPress={resumeVideo} />
      </View>
    </View>
  );
};

export default YoutubeView;
