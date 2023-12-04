package com.imzcc.plugins.module.dbutils;

import org.jooq.DSLContext;

public abstract class DBDslContext {
    public static DSLContext dslContext;

    public static void init(DSLContext dslContext) {
        DBDslContext.dslContext = dslContext;
    }
}
