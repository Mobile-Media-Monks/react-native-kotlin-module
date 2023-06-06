import React from 'react';
import {Dimensions, Pressable, SafeAreaView, Text, View} from 'react-native';
import CustomView from './components/CustomView';
import CustomMethod from './components/CustomMethod';

const dimensions = Dimensions.get('window');
const BUTTON_WIDTH = dimensions.width * 0.75;

function App(): JSX.Element {
  const [batteryLevel, setBatteryLevel] = React.useState<number>(0);
  const [phoneId, setPhoneId] = React.useState<string>('');

  const handlePress = () => {
    CustomMethod?.getBatteryLevel().then((value: number) => {
      setBatteryLevel(value);
    });
    CustomMethod?.getPhoneId(setPhoneId);
  };

  return (
    <SafeAreaView>
      <View style={{justifyContent: 'center', alignItems: 'center'}}>
        <CustomView
          color="#F00F0F"
          text={`Texto desde Js \n batteryLevel: ${batteryLevel} \n phoneId: ${phoneId} `}
          style={{width: '100%', height: 200}}
        />

        <Pressable
          onPress={handlePress}
          style={{
            width: BUTTON_WIDTH,
            height: 30,
            alignItems: 'center',
            justifyContent: 'center',
            backgroundColor: '#f9c0c0',
            borderRadius: 10,
          }}>
          <Text style={{color: '#1c1b1b'}}>PRESIONA AQUI</Text>
        </Pressable>
      </View>
    </SafeAreaView>
  );
}

export default App;
