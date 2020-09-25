package cn.wangkf.monday.strategy;

import java.math.BigDecimal;

/**
 * Created by stanley.wang on 2020/8/3.
 */
public abstract class FundItem {

    public BigDecimal amount;

    public BigDecimal deductAmount;

    public BigDecimal preserveAmount;

    public BigDecimal adjustAmount;

    public BigDecimal abandonAmount;

    public BigDecimal finalAmount;


    // 计算当前余额
    abstract BigDecimal calCurrentAmount();

    // 计算扣除赠送金额
    abstract BigDecimal calDeductAmount();

    // 计算保留金额
    abstract BigDecimal calPreserveAmount();

    // 计算调整金额
    abstract BigDecimal calAdjustAmount();

    // 计算协议不退金额
    abstract BigDecimal calAbandonAmount();

    // 计算协议不退金额
    public BigDecimal calFinalAmount() {
        return this.amount
                .subtract(this.deductAmount)
                .subtract(this.preserveAmount)
                .subtract(this.adjustAmount)
                .subtract(this.abandonAmount);
    }
}
