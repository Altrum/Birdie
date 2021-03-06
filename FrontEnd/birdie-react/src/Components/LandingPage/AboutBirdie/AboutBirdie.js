import React, { Component } from 'react';
import {Divider, Header} from 'semantic-ui-react';

class AboutBirdie extends Component {
  render() {
    return (
        <div className="blackout centerize">
            <Header
                as='h1'
                content='Birdie'
                inverted
                style={{
                    fontWeight: 'strong',
                    marginBottom: 0,
                    marginTop: 0
                }}
            />
            <Header
                as='h3'
                content='Invest Intelligently'
                inverted
                style={{
                    fontWeight: 'normal',
                    marginTop: 1
                }}
            />
            <Divider/>
        </div>
    )
  }
}

export default AboutBirdie;
