package com.imzcc.plugins.module.dbutils;

import com.imzcc.plugins.module.jooq.Tables;
import com.imzcc.plugins.module.jooq.tables.records.AfdianPlayerRecord;

import java.util.UUID;


public class PlayerUtil extends DBDslContext {
    public static boolean save(AfdianPlayerRecord playerRecord) {

        AfdianPlayerRecord newRecord = dslContext.newRecord(Tables.AFDIAN_PLAYER);
        newRecord.from(playerRecord);
        return newRecord.insert() > 1;
    }

    public static boolean update(AfdianPlayerRecord dbPlayer) {
        AfdianPlayerRecord newRecord = dslContext.newRecord(Tables.AFDIAN_PLAYER);
        newRecord.from(dbPlayer);
        return newRecord.update() > 1;
    }

    public static boolean upsert(AfdianPlayerRecord dbPlayer) {
        int row = dslContext.insertInto(Tables.AFDIAN_PLAYER, Tables.AFDIAN_PLAYER.fields())
                .values(dbPlayer)
                .onDuplicateKeyUpdate()
                .set(Tables.AFDIAN_PLAYER.USER_ID, dbPlayer.getUserId())
                .execute();
        return row > 1;
    }

    public static AfdianPlayerRecord findByUuid(UUID uuid) {
        return dslContext.selectFrom(Tables.AFDIAN_PLAYER)
                .where(Tables.AFDIAN_PLAYER.UUID.eq(uuid.toString()))
                .fetchOne();
    }

    public static AfdianPlayerRecord findByUserId(String userId) {
        return dslContext.selectFrom(Tables.AFDIAN_PLAYER)
                .where(Tables.AFDIAN_PLAYER.USER_ID.eq(userId))
                .fetchOne();
    }

    public static void ensureTableExist() {
        dslContext.createTableIfNotExists(Tables.AFDIAN_PLAYER)
                .columns(Tables.AFDIAN_PLAYER.fields())
                .execute();
    }

    public static AfdianPlayerRecord findByName(String playerName) {
        return dslContext.selectFrom(Tables.AFDIAN_PLAYER)
                .where(Tables.AFDIAN_PLAYER.PLAYER_NAME.eq(playerName))
                .fetchOne();

    }
}
