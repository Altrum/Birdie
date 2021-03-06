package io.hoth.birdie.Controllers;

import com.binance.api.client.domain.account.NewOrderResponse;
import io.hoth.birdie.Payload.ApiResponse;
import io.hoth.birdie.Services.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/trade")
public class TradeController {

    @Autowired
    TradeService tradeService;
    // ?type={type}&symbol={symbol}&amt={amount}")
    @PostMapping(value = "/market")
    public NewOrderResponse placeMarketOrder(@RequestParam(value = "type") String type,
                                             @RequestParam(value = "symbol") String symbol,
                                             @RequestParam(value = "amt") String amount) {

        if (type.equals("Buy"))
            return tradeService.marketBuy(symbol, amount);
        else if (type.equals("Sell"))
            return tradeService.marketSell(symbol, amount);
        else
            return new NewOrderResponse();

    }



    // ?type={type}&symbol={symbol}&amt={amount}&price={price}
    @PostMapping(value = "/limit")
    public NewOrderResponse placeLimitOrder(@RequestParam(value = "type") String type,
                                            @RequestParam(value = "symbol") String symbol,
                                            @RequestParam(value = "amt") String amount,
                                            @RequestParam(value = "price") String price) {

        if (type.equals("Buy"))
            return tradeService.limitBuy(symbol, amount, price);
        else if (type.equals("Sell"))
            return tradeService.limitSell(symbol, amount, price);
        else
            return new NewOrderResponse();

    }



    // ?symbol={symbol}&la={limitAmount}&lp={limitPrice}&sa={stopAmount}&sp={stopPrice}&threshold={threshold}
    @PostMapping(value = "/both")
    public ResponseEntity placeBoth(@RequestParam(value = "symbol") String symbol,
                                    @RequestParam(value = "la") String limitAmount,
                                    @RequestParam(value = "lp") String limitPrice,
                                    @RequestParam(value = "sa") String stopAmount,
                                    @RequestParam(value = "sp") String stopPrice,
                                    @RequestParam(value = "threshold") String threshold) {

        if (!tradeService.bothOrders(symbol, limitAmount, limitPrice, stopAmount, stopPrice, threshold))
            return new ResponseEntity(HttpStatus.BAD_REQUEST);


        // TODO: PERHAPS NOT THE RIGHT RESPONSE

        return new ResponseEntity(HttpStatus.OK);

        //wss://stream.binance.com:9443/ws/ethbtc@trade
    }


    // ?symbol={symbol}
    @PostMapping(value = "/cancelAll")
    public ResponseEntity cancelAllOrders(@RequestParam(value = "symbol") String symbol) {
        tradeService.cancelAll(symbol);

        return new ResponseEntity(HttpStatus.OK);
    }


    // ?symbol={symbol}&amt={amount}&price={price}&threshold={threshold}
    @PostMapping(value = "/stopLossLimit")
    public NewOrderResponse placeStopLossLimit(@RequestParam(value = "symbol") String symbol,
                                          @RequestParam(value = "amt") String amount,
                                          @RequestParam(value = "price") String price,
                                          @RequestParam(value = "trigger") String trigger) {

        return tradeService.stopLossLimit(symbol,amount,price,trigger);

    }

    // ?symbol={symbol}&amt={amount}&price={price}
    @PostMapping(value = "/stopLoss")
    public NewOrderResponse placeStopLossLimit(@RequestParam(value = "symbol") String symbol,
                                               @RequestParam(value = "amt") String amount,
                                               @RequestParam(value = "price") String price) {

        return tradeService.stopLoss(symbol,amount,price);

    }


    // ?symbol={symbol}
    @GetMapping(value = "/openOrders")
    public ResponseEntity getOpenOrders(@RequestParam(value = "symbol") String symbol) {

        return new ResponseEntity<Object>(tradeService.openOrders(symbol), HttpStatus.OK);
    }


        // TODO: Cancel and Modify

}
