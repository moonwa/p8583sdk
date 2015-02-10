package com.denovo.p8583server.handlers.jsonmodel;



import java.util.List;

/**
 * Created by Administrator on 2015/1/28.
 */
public class PayDetail {
    private int total;
    private List<HistoryMoneyOrderInfoResultBody> rows;

    public void setTotal(int total) {
        this.total = total;
    }

    public void setRows(List<HistoryMoneyOrderInfoResultBody> rows) {
        this.rows = rows;
    }

    public List<HistoryMoneyOrderInfoResultBody> getRows() {

        return rows;
    }

    public int getTotal() {

        return total;
    }
}
