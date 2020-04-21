import React, {Component} from 'react';
import {Text, View} from 'react-native';
import PushNotification from 'react-native-push-notification'
export default class App extends Component {
  constructor(props) {
    super(props);
    this.state = {};
  }

  async componentDidMount() {
    PushNotification.configure({
      // (required) Called when a remote or local notification is opened or received
      onNotification: function(notification) {
        console.log('NOTIFICATION:', notification);
      },
    });
  }

  render() {
    return (
      <View style={{justifyContent: 'center', alignItems: 'center'}}>
        <Text> received push notification </Text>
      </View>
    );
  }
}
