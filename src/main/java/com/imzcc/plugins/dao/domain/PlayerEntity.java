package com.imzcc.plugins.dao.domain;

import org.jooq.DSLContext;
import org.jooq.Record;

import java.util.UUID;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;

public class PlayerEntity {


    private UUID uuid;
    private String playerName;
    private String userId;

    public PlayerEntity(UUID uuid, String playerName, String userId) {
        this.uuid = uuid;
        this.playerName = playerName;
        this.userId = userId;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void save(DSLContext dsl) {
        dsl.insertInto(table("player"))
                .set(field("uuid"), uuid.toString())
                .set(field("player_name"), playerName)
                .set(field("user_id"), userId)
                .execute();
    }

    public static PlayerEntity findByUuid(DSLContext dsl, UUID uuid) {
        Record record = dsl.select()
                .from(table("player"))
                .where(field("uuid").eq(uuid.toString()))
                .fetchOne();
        if (record == null) {
            return null;
        }
        return new PlayerEntity(
                UUID.fromString(record.get("uuid", String.class)),
                record.get("player_name", String.class),
                record.get("user_id", String.class)
        );
    }
}
