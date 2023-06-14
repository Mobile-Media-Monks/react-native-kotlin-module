import React from 'react';
import {Button, Text, View} from 'react-native';
import CustomMethod from '../components/CustomMethod';

const ScreenMethod = () => {
  const [phoneId, setPhoneId] = React.useState<string>('');
  const [batteryLevel, setBatteryLevel] = React.useState<number>(0);

  const handlePress = () => {
    CustomMethod?.getBatteryLevel().then((value: number) => {
      setBatteryLevel(value);
    });
    CustomMethod?.getPhoneId(setPhoneId);
  };
  return (
    <View style={{flex: 1, justifyContent: 'center', alignItems: 'center'}}>
      <Text
        style={{
          fontSize: 20,
          fontWeight: 'bold',
        }}>
        Battery Level: {batteryLevel}
      </Text>
      <Text
        style={{
          fontSize: 20,
          fontWeight: 'bold',
          marginVertical: 20,
        }}>
        Phone ID: {phoneId}
      </Text>

      <Button title="Get Battery Level and Phone ID " onPress={handlePress} />
    </View>
  );
};

export default ScreenMethod;
