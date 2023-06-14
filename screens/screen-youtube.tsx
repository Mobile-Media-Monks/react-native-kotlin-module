import React from 'react';
import {View} from 'react-native';
import YoutubeView from '../components/YoutubeView/YoutubeView';

const ScreenYoutube = () => {
  return (
    <View style={{justifyContent: 'center', alignItems: 'center'}}>
      <View style={{marginVertical: 20}} />

      <YoutubeView id="mFLiYY0s9z8" />
      <View style={{marginVertical: 20}} />
      <YoutubeView id="Vc75pr0UteQ" />
    </View>
  );
};

export default ScreenYoutube;
