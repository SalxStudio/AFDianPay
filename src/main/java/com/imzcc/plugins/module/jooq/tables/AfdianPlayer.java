/*
 * This file is generated by jOOQ.
 */
package com.imzcc.plugins.module.jooq.tables;


import com.imzcc.plugins.module.jooq.DefaultSchema;
import com.imzcc.plugins.module.jooq.Keys;
import com.imzcc.plugins.module.jooq.tables.records.AfdianPlayerRecord;

import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function3;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row3;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AfdianPlayer extends TableImpl<AfdianPlayerRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>Afdian_Player</code>
     */
    public static final AfdianPlayer AFDIAN_PLAYER = new AfdianPlayer();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AfdianPlayerRecord> getRecordType() {
        return AfdianPlayerRecord.class;
    }

    /**
     * The column <code>Afdian_Player.uuid</code>.
     */
    public final TableField<AfdianPlayerRecord, String> UUID = createField(DSL.name("uuid"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>Afdian_Player.player_name</code>.
     */
    public final TableField<AfdianPlayerRecord, String> PLAYER_NAME = createField(DSL.name("player_name"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>Afdian_Player.user_id</code>.
     */
    public final TableField<AfdianPlayerRecord, String> USER_ID = createField(DSL.name("user_id"), SQLDataType.VARCHAR(255), this, "");

    private AfdianPlayer(Name alias, Table<AfdianPlayerRecord> aliased) {
        this(alias, aliased, null);
    }

    private AfdianPlayer(Name alias, Table<AfdianPlayerRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>Afdian_Player</code> table reference
     */
    public AfdianPlayer(String alias) {
        this(DSL.name(alias), AFDIAN_PLAYER);
    }

    /**
     * Create an aliased <code>Afdian_Player</code> table reference
     */
    public AfdianPlayer(Name alias) {
        this(alias, AFDIAN_PLAYER);
    }

    /**
     * Create a <code>Afdian_Player</code> table reference
     */
    public AfdianPlayer() {
        this(DSL.name("Afdian_Player"), null);
    }

    public <O extends Record> AfdianPlayer(Table<O> child, ForeignKey<O, AfdianPlayerRecord> key) {
        super(child, key, AFDIAN_PLAYER);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public UniqueKey<AfdianPlayerRecord> getPrimaryKey() {
        return Keys.AFDIAN_PLAYER__PK_AFDIAN_PLAYER;
    }

    @Override
    public AfdianPlayer as(String alias) {
        return new AfdianPlayer(DSL.name(alias), this);
    }

    @Override
    public AfdianPlayer as(Name alias) {
        return new AfdianPlayer(alias, this);
    }

    @Override
    public AfdianPlayer as(Table<?> alias) {
        return new AfdianPlayer(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public AfdianPlayer rename(String name) {
        return new AfdianPlayer(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public AfdianPlayer rename(Name name) {
        return new AfdianPlayer(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public AfdianPlayer rename(Table<?> name) {
        return new AfdianPlayer(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<String, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function3<? super String, ? super String, ? super String, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function3<? super String, ? super String, ? super String, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
