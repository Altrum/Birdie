package io.hoth.birdie.Controllers;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.account.Trade;
import com.binance.api.client.exception.BinanceApiException;
import io.hoth.birdie.Config.AvailableSymbols;
import io.hoth.birdie.Entities.UserPrincipal;
import io.hoth.birdie.Services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/wallet")
public class WalletController {


    @Autowired
    AvailableSymbols availableSymbols;

    @Autowired
    WalletService walletService;

    @GetMapping(value = "/balance")
    public ResponseEntity getAccountBalance() {
        return walletService.getBalances();
    }

    // TODO: takes too long, and still have to do calculations of profit/loss somehow
    @GetMapping(value = "/alltrades")
    public void getAllTrades() {
        // Grab current user and use the API key and Secret to make new REST calls
        UserPrincipal currentUser = (UserPrincipal)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(
                currentUser.getApiKey(),
                currentUser.getSecret()
        );
        BinanceApiRestClient client = factory.newRestClient();


        List<List<Trade>> allTrades  = new ArrayList<>();               // List of All trades


        // Concurrency Threading
        ExecutorService executor = Executors.newSingleThreadExecutor();


        // GET ALL ACCOUNT DATA FOR ALL SYMBOLS
        // ONLY GETS 500 for each symbol for now
        for (String[] group : availableSymbols.getAllSymbols())
        {
            for (String symbol : group)
            {
                /*
                 * @param symbol symbol to get trades from
                 * @param limit default 500; max 500
                 * @param fromId TradeId to fetch from. Default gets most recent trades.
                 * @param recvWindow
                 * @param timeStamp
                 * @return a list of trades
                 */
                executor.submit(() -> {
                    try {
                        List<Trade> trades = client.getMyTrades(symbol, 500, 0L, 6000000L, System.currentTimeMillis());
                        if (trades.size() > 0)
                            allTrades.add(trades);
                    } catch (BinanceApiException e) {
                        System.out.println(e.getError().getCode());
                        System.out.println(e.getError().getMsg());
                        System.out.println(symbol);
                    }
                });
            }
        }


        // Thread shutdown
        try {
            System.out.println("attempt to shutdown executor");
            executor.shutdown();
            executor.awaitTermination(120, TimeUnit.SECONDS);
        }
        catch (InterruptedException e) {
            System.err.println("tasks interrupted");
        }
        finally {
            if (!executor.isTerminated()) {
                System.err.println("cancel non-finished tasks");
            }
            executor.shutdownNow();
            System.out.println("shutdown finished");
        }

        // TODO: Open datastream and listen for updates, instead of doing it everytime
        // TODO: Maybe have this method be a seeder thing

        System.out.println(allTrades);

    }




}
