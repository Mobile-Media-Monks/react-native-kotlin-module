import React from 'react';
import ScreenCustom from './screens/screen-custom';
import ScreenMethod from './screens/screen-method';
import ScreenYoutube from './screens/screen-youtube';
import {Button, Easing, StyleSheet, Text, View} from 'react-native';
import {SafeAreaProvider} from 'react-native-safe-area-context';
import {NavigationContainer, useNavigation} from '@react-navigation/native';
import {
  NativeStackNavigationProp,
  createNativeStackNavigator,
} from '@react-navigation/native-stack';

const styles = StyleSheet.create({
  title: {
    fontSize: 20,
    fontWeight: 'bold',
    textAlign: 'center',
    marginBottom: 20,
  },
});

type RootStackParamList = {
  Home: undefined;
  Custom: undefined;
  Method: undefined;
  Youtube: undefined;
};

const routes: {name: keyof RootStackParamList}[] = [
  {
    name: 'Custom',
  },
  {
    name: 'Method',
  },
  {
    name: 'Youtube',
  },
];

function HomeScreen() {
  const navigation =
    useNavigation<NativeStackNavigationProp<RootStackParamList>>();

  return (
    <View style={{flex: 1, alignItems: 'center', justifyContent: 'center'}}>
      <Text style={styles.title}>Native Module & Native UI Components</Text>

      {routes.map(route => (
        <View key={route.name} style={{marginVertical: 20}}>
          <Button
            key={route.name}
            title={route.name}
            onPress={() => {
              navigation.navigate(route.name);
            }}
          />
        </View>
      ))}
    </View>
  );
}

const Stack = createNativeStackNavigator();

const timingConfig = {
  animation: 'timing',
  config: {
    duration: 200,
    easing: Easing.linear,
  },
};

const options = {
  gestureEnabled: true,
  transitionSpec: {
    open: timingConfig,
    close: timingConfig,
  },
};

function App(): JSX.Element {
  return (
    <SafeAreaProvider>
      <NavigationContainer>
        <Stack.Navigator>
          <Stack.Screen name="Home" options={options} component={HomeScreen} />
          <Stack.Screen name="Custom" component={ScreenCustom} />
          <Stack.Screen name="Method" component={ScreenMethod} />
          <Stack.Screen name="Youtube" component={ScreenYoutube} />
        </Stack.Navigator>
      </NavigationContainer>
    </SafeAreaProvider>
  );
}

export default App;
