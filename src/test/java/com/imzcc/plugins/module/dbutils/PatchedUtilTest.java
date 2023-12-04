package com.imzcc.plugins.module.dbutils;

import com.imzcc.plugins.http.AfdianSDK;
import com.imzcc.plugins.module.jooq.tables.records.AfdianOrderRecord;
import com.imzcc.plugins.module.jooq.tables.records.AfdianPlayerRecord;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Set;

public class PatchedUtilTest {

    @Before
    public void setUp() throws Exception {

        AfdianSDK afdianSDK = new AfdianSDK("92529d7c217111ea9c6952540025c377", "ypWHkKc7C9gTxXh8FDt4mYfEQsMauJUr");
        boolean status = afdianSDK.ping();
        assert status : "配置错误";

        // DB表初始化
        Connection connection = DriverManager.getConnection(String.format("jdbc:sqlite://%s", "D:\\WorkSpace\\IDEA\\AFDianPay\\src\\test\\resources\\afdian.db"));
        DBDslContext.dslContext = DSL.using(connection, SQLDialect.SQLITE);
        PlayerUtil.ensureTableExist();
        OrderUtil.ensureTableExist();
        PatchedOrderUtil.ensureTableExist();


        AfdianPlayerRecord playerRecord = new AfdianPlayerRecord("092645c0-8798-34c9-93bc-1e77a0df5e47", "IMZCC", "21b5afc84bdc11eb8ba152540025c377");
        PlayerUtil.upsert(playerRecord);

        //准备数据
        List<AfdianOrderRecord> orderRecords = afdianSDK.queryAllOrders();
        Set<AfdianOrderRecord> newRecords = OrderUtil.selectNewRecord(orderRecords);
        OrderUtil.saveOrders(newRecords);
    }

    @Test
    public void selectUnPatchedOrder() {
        List<AfdianOrderRecord> unPatchedOrders = PatchedOrderUtil.selectUnPatchedOrder();
        System.out.println(unPatchedOrders);
    }
}