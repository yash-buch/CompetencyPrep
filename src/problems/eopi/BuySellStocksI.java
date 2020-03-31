package problems.eopi;

import org.junit.Test;

public class BuySellStocksI {
    private final int BUY = 0;
    private final int SELL = 1;

    public int maxProfit(int[] stockValues) {
        int action = BUY;
        int profit = 0;
        for(int i = 0; i < stockValues.length-1; i++) {
            switch (action) {
                case BUY: {
                    if(stockValues[i] < stockValues[i+1]) {
                        profit -= stockValues[i];
                        action = (action + 1) % 2;
                    }
                    break;
                }
                case SELL: {
                    if(stockValues[i] > stockValues[i+1]) {
                        profit += stockValues[i];
                        action = (action + 1) % 2;
                    }
                    break;
                }
            }
        }
        if(action == SELL) {
            profit += stockValues[stockValues.length-1];
        }
        return profit;
    }

    @Test
    public void test_maxProfit() {
        BuySellStocksI obj = new BuySellStocksI();
        int[] stockValues = new int[]{6,6,6};
        int profit = obj.maxProfit(stockValues);
        assert (0 == profit);
    }
}
