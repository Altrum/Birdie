import React, { Component } from 'react'

import WalletCoinTable from './WalletCoinTable/WalletCoinTable';
import LandingPageChartTwo from '../LandingPageChart/LandingPageChartTwo'
import axios from 'axios'
import NavBar from '../NavBar/NavBarWallet'

class Wallet extends Component {

    constructor(props){
        super(props);
        this.state = {
            data : []
        }

    }
    componentWillMount(){
        // GET REQUEST ON STANDBY
        const queryURL = "http://localhost:8080/wallet/balance"
        const token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI1YWYzZjM1ZTVlYjE5NzEwMGNhNDQ3OGUiLCJpYXQiOjE1MjY1NzQzNjUsImV4cCI6MTUyNzE3OTE2NX0.HYr-Rrs7qUMeHf2RxNz3xdrZ360B54xBTBnVKkcFt-Dh49didBOeIpAWfU452kbStbvqFlAgBzJrx-7vtMzoDg"
        axios({
            method: 'get',
            url: queryURL,
            headers: {'Content-Type': 'application/json', 'Authorization': "Bearer " + token},
        }).then((response) => {
            let tempData = response.data;
            this.setState({
                data: tempData
            });
        }).catch((error) => {
            console.log(error);
        });
    }

    render() {
        return (
            <div>
              <NavBar/>
              <div className="righttable">
                <h2>
                  Coin Table
                </h2>
                <WalletCoinTable data={this.state.data}/>
              </div>
              <div className="leftchart">
                <h2>
                  Overall
                </h2>
                <LandingPageChartTwo/>
              </div>
            </div>
        )
    }
}

export default Wallet;
