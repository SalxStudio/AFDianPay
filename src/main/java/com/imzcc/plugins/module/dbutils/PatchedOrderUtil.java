package com.imzcc.plugins.module.dbutils;

import com.imzcc.plugins.module.jooq.Tables;
import com.imzcc.plugins.module.jooq.tables.records.AfdianOrderRecord;
import com.imzcc.plugins.module.jooq.tables.records.AfdianPatchedRecord;
import org.jooq.Record1;
import org.jooq.Result;
import org.jooq.SelectJoinStep;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PatchedOrderUtil extends DBDslContext {

    public static void ensureTableExist() {
        dslContext.createTableIfNotExists(Tables.AFDIAN_PATCHED)
                .columns(Tables.AFDIAN_PATCHED.fields())
                .execute();
    }

    public static List<AfdianOrderRecord> selectUnPatchedOrder() {
        SelectJoinStep<Record1<String>> patchedOrder = dslContext.select(Tables.AFDIAN_PATCHED.OUT_TRADE_NO).from(Tables.AFDIAN_PATCHED);
        AfdianOrderRecord[] records = dslContext.selectFrom(Tables.AFDIAN_ORDER)
                .where(Tables.AFDIAN_ORDER.OUT_TRADE_NO.notIn(patchedOrder))
                .fetchArray();
        return Arrays.stream(records).collect(Collectors.toList());
    }

    public static boolean save(AfdianPatchedRecord patchedRecord) {
        AfdianPatchedRecord newRecord = dslContext.newRecord(Tables.AFDIAN_PATCHED);
        newRecord.from(patchedRecord);
        return newRecord.insert() > 0;
    }

    public static boolean exist(AfdianOrderRecord order) {
        Result<AfdianPatchedRecord> fetch = dslContext.selectFrom(Tables.AFDIAN_PATCHED)
                .where(Tables.AFDIAN_ORDER.OUT_TRADE_NO.eq(order.getOutTradeNo()))
                .fetch();
        return fetch.isEmpty();
    }
}
