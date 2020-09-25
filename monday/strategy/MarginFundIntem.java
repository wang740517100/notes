package cn.wangkf.monday.strategy;

import java.math.BigDecimal;

/**
 * Created by stanley.wang on 2020/8/3.
 */
public class MarginFundIntem extends FundItem {

    private Integer userId;

    public MarginFundIntem(Integer userId) {
        this.userId = userId;
        this.adjustAmount = BigDecimal.ZERO;
        init();
    }

    public MarginFundIntem(Integer userId, BigDecimal adjustAmount) {
        this.userId = userId;
        this.adjustAmount = adjustAmount;
        init();
    }

    private void init() {
        this.amount = this.calCurrentAmount();
        this.deductAmount = this.calDeductAmount();
        this.preserveAmount = this.calPreserveAmount();
        this.adjustAmount = this.calAdjustAmount();
        this.abandonAmount = this.calAbandonAmount();
        this.finalAmount = this.calFinalAmount();
    }

    @Override
    public BigDecimal calCurrentAmount() {

        // 查询固保金账户的当前余额

        return null;
    }

    @Override
    public BigDecimal calDeductAmount() {
        return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal calPreserveAmount() {
        // 根据退出合作配置里的保留金额

        // 取10000
        return null;
    }

    @Override
    public BigDecimal calAdjustAmount() {
        return this.adjustAmount;
    }

    @Override
    public BigDecimal calAbandonAmount() {
        return BigDecimal.ZERO;
    }

}
