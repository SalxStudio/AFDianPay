/*
 * This file is generated by jOOQ.
 */
package com.imzcc.plugins.module.jooq.tables.interfaces;


import java.io.Serializable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public interface IAfdianOrder extends Serializable {

    /**
     * Setter for <code>Afdian_Order.out_trade_no</code>.
     */
    public void setOutTradeNo(String value);

    /**
     * Getter for <code>Afdian_Order.out_trade_no</code>.
     */
    public String getOutTradeNo();

    /**
     * Setter for <code>Afdian_Order.custom_order_id</code>.
     */
    public void setCustomOrderId(String value);

    /**
     * Getter for <code>Afdian_Order.custom_order_id</code>.
     */
    public String getCustomOrderId();

    /**
     * Setter for <code>Afdian_Order.user_id</code>.
     */
    public void setUserId(String value);

    /**
     * Getter for <code>Afdian_Order.user_id</code>.
     */
    public String getUserId();

    /**
     * Setter for <code>Afdian_Order.user_private_id</code>.
     */
    public void setUserPrivateId(String value);

    /**
     * Getter for <code>Afdian_Order.user_private_id</code>.
     */
    public String getUserPrivateId();

    /**
     * Setter for <code>Afdian_Order.plan_id</code>.
     */
    public void setPlanId(String value);

    /**
     * Getter for <code>Afdian_Order.plan_id</code>.
     */
    public String getPlanId();

    /**
     * Setter for <code>Afdian_Order.month</code>.
     */
    public void setMonth(Long value);

    /**
     * Getter for <code>Afdian_Order.month</code>.
     */
    public Long getMonth();

    /**
     * Setter for <code>Afdian_Order.total_amount</code>.
     */
    public void setTotalAmount(String value);

    /**
     * Getter for <code>Afdian_Order.total_amount</code>.
     */
    public String getTotalAmount();

    /**
     * Setter for <code>Afdian_Order.show_amount</code>.
     */
    public void setShowAmount(String value);

    /**
     * Getter for <code>Afdian_Order.show_amount</code>.
     */
    public String getShowAmount();

    /**
     * Setter for <code>Afdian_Order.status</code>.
     */
    public void setStatus(Long value);

    /**
     * Getter for <code>Afdian_Order.status</code>.
     */
    public Long getStatus();

    /**
     * Setter for <code>Afdian_Order.remark</code>.
     */
    public void setRemark(String value);

    /**
     * Getter for <code>Afdian_Order.remark</code>.
     */
    public String getRemark();

    /**
     * Setter for <code>Afdian_Order.redeem_id</code>.
     */
    public void setRedeemId(String value);

    /**
     * Getter for <code>Afdian_Order.redeem_id</code>.
     */
    public String getRedeemId();

    /**
     * Setter for <code>Afdian_Order.product_type</code>.
     */
    public void setProductType(Long value);

    /**
     * Getter for <code>Afdian_Order.product_type</code>.
     */
    public Long getProductType();

    /**
     * Setter for <code>Afdian_Order.discount</code>.
     */
    public void setDiscount(String value);

    /**
     * Getter for <code>Afdian_Order.discount</code>.
     */
    public String getDiscount();

    /**
     * Setter for <code>Afdian_Order.address_person</code>.
     */
    public void setAddressPerson(String value);

    /**
     * Getter for <code>Afdian_Order.address_person</code>.
     */
    public String getAddressPerson();

    /**
     * Setter for <code>Afdian_Order.address_phone</code>.
     */
    public void setAddressPhone(String value);

    /**
     * Getter for <code>Afdian_Order.address_phone</code>.
     */
    public String getAddressPhone();

    /**
     * Setter for <code>Afdian_Order.address_address</code>.
     */
    public void setAddressAddress(String value);

    /**
     * Getter for <code>Afdian_Order.address_address</code>.
     */
    public String getAddressAddress();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common
     * interface IAfdianOrder
     */
    public void from(IAfdianOrder from);

    /**
     * Copy data into another generated Record/POJO implementing the common
     * interface IAfdianOrder
     */
    public <E extends IAfdianOrder> E into(E into);
}
