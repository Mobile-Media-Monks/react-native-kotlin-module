import React from 'react';
import {View} from 'react-native';
import CustomView from '../components/CustomView/CustomView';

const ScreenCustom = () => {
  return (
    <View style={{flex: 1, justifyContent: 'center', alignItems: 'center'}}>
      <CustomView
        color="blue"
        style={{
          width: 200,
          height: 200,
          backgroundColor: '#f4a1a12c',
        }}
      />
    </View>
  );
};

export default ScreenCustom;
