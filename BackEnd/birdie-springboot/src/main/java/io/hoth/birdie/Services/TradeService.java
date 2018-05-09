package io.hoth.birdie.Services;

import com.binance.api.client.BinanceApiCallback;
import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.BinanceApiWebSocketClient;
import com.binance.api.client.domain.OrderSide;
import com.binance.api.client.domain.OrderType;
import com.binance.api.client.domain.TimeInForce;
import com.binance.api.client.domain.account.NewOrder;
import com.binance.api.client.domain.account.NewOrderResponse;
import com.binance.api.client.domain.account.Order;
import com.binance.api.client.domain.account.request.CancelOrderRequest;
import com.binance.api.client.domain.account.request.OrderStatusRequest;
import com.binance.api.client.domain.event.AggTradeEvent;
import io.hoth.birdie.Entities.UserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.binance.api.client.domain.OrderType.LIMIT;
import static com.binance.api.client.domain.OrderType.STOP_LOSS;

@Service
public class TradeService {

    // Needs to be here as to not get FINAL warning. Used in setting both
    String originalOrderId;

    // Concurrency Threading TODO: DO I NEED THIS?
    ExecutorService executor = Executors.newSingleThreadExecutor();


    // Returns the Binance account
    private BinanceApiRestClient getCurrentClient() {
        // Grab current user
        UserPrincipal currentUser = (UserPrincipal)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(
                currentUser.getApiKey(),
                currentUser.getSecret()
        );

        return factory.newRestClient();
    }

    // MARKET BUY
    public NewOrderResponse marketBuy(String symbol, String amount) {
        BinanceApiRestClient client = getCurrentClient();

        return client.newOrder(NewOrder.marketBuy(symbol, amount));
    }

    // MARKET SELL
    public NewOrderResponse marketSell(String symbol, String amount) {
        BinanceApiRestClient client = getCurrentClient();

        return client.newOrder(NewOrder.marketSell(symbol, amount));

    }

    // LIMIT BUY
    public NewOrderResponse limitBuy(String symbol, String amount, String price) {
        BinanceApiRestClient client = getCurrentClient();

        return client.newOrder(NewOrder.limitBuy(symbol, TimeInForce.GTC, amount, price));
    }

    // LIMIT SELL
    public NewOrderResponse limitSell(String symbol, String amount, String price) {
        BinanceApiRestClient client = getCurrentClient();

        return client.newOrder(NewOrder.limitSell(symbol, TimeInForce.GTC, amount, price));
    }

    // TODO: TEST
    // STOP LOSS
    public NewOrderResponse stopLoss(String symbol, String amount, String price) {
        BinanceApiRestClient client = getCurrentClient();

        NewOrder newStopLoss = new NewOrder(symbol, OrderSide.SELL, OrderType.STOP_LOSS, TimeInForce.GTC, amount, price);
        return client.newOrder(newStopLoss);
    }


    // TODO: TEST
    // PLACE BOTH
    public boolean bothOrders(String symbol, String limitAmount, String limitPrice,
                           String stopAmount, String stopPrice, String threshold) {

        // Binance REST client
        BinanceApiRestClient client = getCurrentClient();
        // Websocket client
        BinanceApiWebSocketClient wsClient = BinanceApiClientFactory.newInstance().newWebSocketClient();

        // Create order with LIMIT BUY
        originalOrderId = limitBuy(symbol, limitAmount, limitPrice).getClientOrderId();



        // TODO: DO I NEED CONCURRENCY HERE???
        wsClient.onAggTradeEvent(symbol.toLowerCase(), new BinanceApiCallback<AggTradeEvent>() {
            @Override
            public void onResponse(final AggTradeEvent response) {

                // While order is not fulfilled
                Order order = client.getOrderStatus(new OrderStatusRequest(symbol, originalOrderId));
                double price = Double.parseDouble(response.getPrice());
                double thresh = Double.parseDouble(threshold);

                if (price <  thresh && order.getType() == LIMIT) {
                    client.cancelOrder(new CancelOrderRequest(symbol, originalOrderId));
                    originalOrderId = stopLoss(symbol, stopAmount, stopPrice).getClientOrderId();
                }
                else if (price >= thresh && order.getType() == STOP_LOSS) {
                    client.cancelOrder(new CancelOrderRequest(symbol, originalOrderId));
                    originalOrderId = limitBuy(symbol, limitAmount, limitPrice).getClientOrderId();
                }
            }

            @Override
            public void onFailure(final Throwable cause) {
                System.err.println("Web socket failed");
                cause.printStackTrace(System.err);
            }
        });


        return true;
    }


    // TODO: TEST
    public void cancel(String symbol, String orderID) {
        // Binance REST client
        BinanceApiRestClient client = getCurrentClient();
        client.cancelOrder(new CancelOrderRequest(symbol,orderID));

    }

}