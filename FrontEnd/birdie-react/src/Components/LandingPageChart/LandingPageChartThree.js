import React, { Component } from 'react';
import TradingViewWidget , { Themes, watchlist } from 'react-tradingview-widget';

class LandingPageChartThree extends Component {
    render() {
        return (
            <div classname= 'LandingPageChart'>
                <TradingViewWidget
                    symbol="BINANCE:BTCUSDT"
                    theme={Themes.DARK}
                    locale="en"
                    timezone="America/Vancouver"
                    width="1180"
                    height="610"
                    details
                    withdateranges="true"
                    watchlist = {[
                        'BINANCE:ETHBTC',
                        'BINANCE:XRPBTC',
                        'BINANCE:LTCBTC',
                        'BINANCE:ADABTC',
                        'BINANCE:NEOBTC',
                        'BINANCE:XLMBTC',
                        'BINANCE:EOSBTC',
                        'BINANCE:DASHBTC',
                        'BINANCE:XMRBTC',
                        'BINANCE:ETCBTC',
                        'BINANCE:TRXBTC',
                        'BINANCE:VENBTC',
                        'BINANCE:LSKBTC',
                        'BINANCE:QTUMBTC',
                        'BINANCE:OMGBTC',
                        'BINANCE:ICXBTC',
                        'BINANCE:NANOBTC',
                        'BINANCE:ZECBTC',
                        'BINANCE:BNBBTC',
                        'BINANCE:STEEMBTC',
                        'BINANCE:XVGBTC',
                        'BINANCE:PPTBTC',
                        'BINANCE:STRATBTC',
                        'BINANCE:WAVESBTC',
                        'BINANCE:SNTBTC',
                        'BINANCE:DGDBTC',
                        'BINANCE:BTSBTC',
                        'BINANCE:AEBTC',
                        'BINANCE:WTCBTC',
                        'BINANCE:ZRXBTC',
                        'BINANCE:HSRBTC',
                        'BINANCE:KMDBTC',
                        'BINANCE:ARKBTC',
                        'BINANCE:GASBTC',
                        'BINANCE:LRCBTC',
                        'BINANCE:ELFBTC',
                        'BINANCE:AIONBTC',
                        'BINANCE:PIVXBTC',
                        'BINANCE:IOSTBTC',
                        'BINANCE:GXSBTC',
                        'BINANCE:LINKBTC',
                        'BINANCE:POWRBTC',
                        'BINANCE:FUNBTC',
                        'BINANCE:XZCBTC',
                        'BINANCE:CNDBTC',
                        'BINANCE:SALTBTC',
                        'BINANCE:REQBTC',
                        'BINANCE:BNTBTC',
                        'BINANCE:ENGBTC',
                        'BINANCE:NEBLBTC',
                        'BINANCE:RDNBTC',
                        'BINANCE:QSPBTC',
                        'BINANCE:POEBTC',
                        'BINANCE:ENJBTC',
                        'BINANCE:STORJBTC',
                        'BINANCE:SUBBTC',
                        'BINANCE:MANABTC',
                        'BINANCE:NAVBTC',
                        'BINANCE:LENDBTC',
                        'BINANCE:ADXBTC',
                        'BINANCE:TNBBTC',
                        'BINANCE:MCOBTC',
                        'BINANCE:RLCBTC',
                        'BINANCE:GVTBTC',
                        'BINANCE:RPXBTC',
                        'BINANCE:VIBEBTC',
                        'BINANCE:AMBBTC',
                        'BINANCE:MTLBTC',
                        'BINANCE:SNMBTC',
                        'BINANCE:APPCBTC',
                        'BINANCE:EDOBTC',
                        'BINANCE:FUELBTC',
                        'BINANCE:ASTBTC',
                        'BINANCE:WABIBTC',
                        'BINANCE:OSTBTC',
                        'BINANCE:BQXBTC',
                        'BINANCE:GTOBTC',
                        'BINANCE:SNGLSBTC',
                        'BINANCE:INSBTC',
                        'BINANCE:NULSBTC',
                        'BINANCE:BRDBTC',
                        'BINANCE:TNTBTC',
                        'BINANCE:VIABTC',
                        'BINANCE:TRIGBTC',
                        'BINANCE:CDTBTC',
                        'BINANCE:WINGSBTC',
                        'BINANCE:CTRBTC',
                        'BINANCE:DNTBTC',
                        'BINANCE:MODBTC',
                        'BINANCE:VIBBTC',
                        'BINANCE:BCPTBTC',
                        'BINANCE:EVXBTC',
                        'BINANCE:MTHBTC',
                        'BINANCE:LUNBTC',
                        'BINANCE:DLTBTC',
                        'BINANCE:BCCBTC',
                        'BINANCE:ARNBTC',
                        'BINANCE:MDABTC',
                        'BINANCE:OAXBTC',
                        'BINANCE:BLZBTC',
                        'BINANCE:BTGBTC',
                        'BINANCE:ICNBTC',
                        'BINANCE:CMTBTC',
                        'BINANCE:KNCBTC',
                        'BINANCE:CHATBTC',
                        'BINANCE:BCDBTC',
                        'BINANCE:BATBTC',
                        'BINANCE:RCNBTC',
                        'BINANCE:IOTABTC',
                        'BINANCE:YOYOBTC'
                    ]}
                />
            </div>
        );
    }
}

export default LandingPageChartThree;