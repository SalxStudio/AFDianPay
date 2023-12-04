/*
 * This file is generated by jOOQ.
 */
package com.imzcc.plugins.module.jooq.tables.records;


import com.imzcc.plugins.module.jooq.tables.AfdianPlayer;
import com.imzcc.plugins.module.jooq.tables.interfaces.IAfdianPlayer;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AfdianPlayerRecord extends UpdatableRecordImpl<AfdianPlayerRecord> implements Record3<String, String, String>, IAfdianPlayer {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>Afdian_Player.uuid</code>.
     */
    @Override
    public void setUuid(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>Afdian_Player.uuid</code>.
     */
    @Override
    public String getUuid() {
        return (String) get(0);
    }

    /**
     * Setter for <code>Afdian_Player.player_name</code>.
     */
    @Override
    public void setPlayerName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>Afdian_Player.player_name</code>.
     */
    @Override
    public String getPlayerName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>Afdian_Player.user_id</code>.
     */
    @Override
    public void setUserId(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>Afdian_Player.user_id</code>.
     */
    @Override
    public String getUserId() {
        return (String) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<String, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<String, String, String> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return AfdianPlayer.AFDIAN_PLAYER.UUID;
    }

    @Override
    public Field<String> field2() {
        return AfdianPlayer.AFDIAN_PLAYER.PLAYER_NAME;
    }

    @Override
    public Field<String> field3() {
        return AfdianPlayer.AFDIAN_PLAYER.USER_ID;
    }

    @Override
    public String component1() {
        return getUuid();
    }

    @Override
    public String component2() {
        return getPlayerName();
    }

    @Override
    public String component3() {
        return getUserId();
    }

    @Override
    public String value1() {
        return getUuid();
    }

    @Override
    public String value2() {
        return getPlayerName();
    }

    @Override
    public String value3() {
        return getUserId();
    }

    @Override
    public AfdianPlayerRecord value1(String value) {
        setUuid(value);
        return this;
    }

    @Override
    public AfdianPlayerRecord value2(String value) {
        setPlayerName(value);
        return this;
    }

    @Override
    public AfdianPlayerRecord value3(String value) {
        setUserId(value);
        return this;
    }

    @Override
    public AfdianPlayerRecord values(String value1, String value2, String value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IAfdianPlayer from) {
        setUuid(from.getUuid());
        setPlayerName(from.getPlayerName());
        setUserId(from.getUserId());
    }

    @Override
    public <E extends IAfdianPlayer> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached AfdianPlayerRecord
     */
    public AfdianPlayerRecord() {
        super(AfdianPlayer.AFDIAN_PLAYER);
    }

    /**
     * Create a detached, initialised AfdianPlayerRecord
     */
    public AfdianPlayerRecord(String uuid, String playerName, String userId) {
        super(AfdianPlayer.AFDIAN_PLAYER);

        setUuid(uuid);
        setPlayerName(playerName);
        setUserId(userId);
    }

    /**
     * Create a detached, initialised AfdianPlayerRecord
     */
    public AfdianPlayerRecord(com.imzcc.plugins.module.jooq.tables.pojos.AfdianPlayer value) {
        super(AfdianPlayer.AFDIAN_PLAYER);

        if (value != null) {
            setUuid(value.getUuid());
            setPlayerName(value.getPlayerName());
            setUserId(value.getUserId());
        }
    }
}
