package com.imzcc.plugins.module.dbutils;

import com.imzcc.plugins.module.jooq.Tables;
import com.imzcc.plugins.module.jooq.tables.records.AfdianOrderRecord;
import org.jooq.Result;

import java.util.*;
import java.util.stream.Collectors;

public class OrderUtil extends DBDslContext {
    public static boolean exist(AfdianOrderRecord order) {
        Result<AfdianOrderRecord> fetch = dslContext.selectFrom(Tables.AFDIAN_ORDER)
                .where(Tables.AFDIAN_ORDER.OUT_TRADE_NO.eq(order.getOutTradeNo()))
                .fetch();
        return fetch.isEmpty();
    }

    public static void ensureTableExist() {
        dslContext.createTableIfNotExists(Tables.AFDIAN_ORDER)
                .columns(Tables.AFDIAN_ORDER.fields())
                .execute();
    }

    public static void saveOrders(Collection<AfdianOrderRecord> orderRecords) {
        dslContext.batchInsert(orderRecords).execute();
    }

    public static Set<AfdianOrderRecord> selectNewRecord(List<AfdianOrderRecord> orderRecords) {
        AfdianOrderRecord[] records = dslContext.selectFrom(Tables.AFDIAN_ORDER).fetchArray();
        Set<AfdianOrderRecord> newRecords = new HashSet<>(orderRecords);
        Set<AfdianOrderRecord> existRecords = Arrays.stream(records).collect(Collectors.toSet());
        newRecords.removeAll(existRecords);
        return newRecords;
    }

    public static AfdianOrderRecord findByOutTradeNo(String orderId) {
        return dslContext.selectFrom(Tables.AFDIAN_ORDER)
                .where(Tables.AFDIAN_ORDER.OUT_TRADE_NO.eq(orderId))
                .fetchOne();
    }
}
