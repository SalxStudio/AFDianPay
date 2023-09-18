package com.imzcc.plugins.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jooq.DSLContext;

import java.util.List;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {
    private static final String tableName = "order";
    private String outTradeNo;
    private String customOrderID;
    private String userID;
    private String userPrivateID;
    private String planID;
    private long month;
    private String totalAmount;
    private String showAmount;
    private long status;
    private String remark;
    private String redeemID;
    private long productType;
    private String discount;
    private List<SkuDetail> skuDetail;
    private String addressPerson;
    private String addressPhone;
    private String addressAddress;

    /**
     * 检查订单是否重复
     *
     * @param dslContext
     * @return
     */
    public synchronized boolean exist(DSLContext dslContext) {
        return dslContext.fetchExists(dslContext.select().from(table(tableName)).where(field("out_trade_no").eq(outTradeNo)));
    }
}
