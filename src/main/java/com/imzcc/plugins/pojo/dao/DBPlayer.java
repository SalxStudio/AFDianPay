package com.imzcc.plugins.pojo.dao;

import lombok.*;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.impl.SQLDataType;

import java.util.UUID;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DBPlayer {
    private static final String tableName = "player";

    private UUID uuid;
    private String playerName;
    private String userId;


    public boolean save(DSLContext dsl) {
        int execute = dsl.insertInto(table(tableName)).set(field("uuid"), uuid.toString()).set(field("player_name"), playerName).set(field("user_id"), userId).execute();
        return execute > 0;
    }

    public boolean update(DSLContext dsl) {
        int execute = dsl.update(table(tableName)).set(field("user_id"), userId).where(field("uuid").eq(uuid.toString())).execute();
        return execute > 0;
    }

    public static DBPlayer findByUuid(DSLContext dsl, UUID uuid) {
        Record record = dsl.select().from(table(tableName)).where(field("uuid").eq(uuid.toString())).fetchOne();
        if (record == null) {
            return null;
        }
        return new DBPlayer(UUID.fromString(record.get("uuid", String.class)), record.get("player_name", String.class), record.get("user_id", String.class));
    }

    public static DBPlayer findByUserId(DSLContext dsl, String userId) {
        Record record = dsl.select().from(table(tableName)).where(field("user_id").eq(userId)).fetchOne();
        if (record == null) {
            return null;
        }
        return new DBPlayer(UUID.fromString(record.get("uuid", String.class)), record.get("player_name", String.class), record.get("user_id", String.class));
    }

    public static boolean ensureTableExist(DSLContext dsl) {
        int execute = dsl.createTableIfNotExists(table(tableName)).column("uuid", SQLDataType.VARCHAR(255).nullable(false)).column("player_name", SQLDataType.VARCHAR(255).nullable(false)).column("user_id", SQLDataType.VARCHAR(255).nullable(true)).primaryKey("uuid").execute();
        return execute > 0;
    }
}
