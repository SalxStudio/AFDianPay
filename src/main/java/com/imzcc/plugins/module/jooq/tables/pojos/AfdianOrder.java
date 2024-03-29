/*
 * This file is generated by jOOQ.
 */
package com.imzcc.plugins.module.jooq.tables.pojos;


import com.imzcc.plugins.module.jooq.tables.interfaces.IAfdianOrder;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AfdianOrder implements IAfdianOrder {

    private static final long serialVersionUID = 1L;

    private String outTradeNo;
    private String customOrderId;
    private String userId;
    private String userPrivateId;
    private String planId;
    private Long month;
    private String totalAmount;
    private String showAmount;
    private Long status;
    private String remark;
    private String redeemId;
    private Long productType;
    private String discount;
    private String addressPerson;
    private String addressPhone;
    private String addressAddress;

    public AfdianOrder() {}

    public AfdianOrder(IAfdianOrder value) {
        this.outTradeNo = value.getOutTradeNo();
        this.customOrderId = value.getCustomOrderId();
        this.userId = value.getUserId();
        this.userPrivateId = value.getUserPrivateId();
        this.planId = value.getPlanId();
        this.month = value.getMonth();
        this.totalAmount = value.getTotalAmount();
        this.showAmount = value.getShowAmount();
        this.status = value.getStatus();
        this.remark = value.getRemark();
        this.redeemId = value.getRedeemId();
        this.productType = value.getProductType();
        this.discount = value.getDiscount();
        this.addressPerson = value.getAddressPerson();
        this.addressPhone = value.getAddressPhone();
        this.addressAddress = value.getAddressAddress();
    }

    public AfdianOrder(
        String outTradeNo,
        String customOrderId,
        String userId,
        String userPrivateId,
        String planId,
        Long month,
        String totalAmount,
        String showAmount,
        Long status,
        String remark,
        String redeemId,
        Long productType,
        String discount,
        String addressPerson,
        String addressPhone,
        String addressAddress
    ) {
        this.outTradeNo = outTradeNo;
        this.customOrderId = customOrderId;
        this.userId = userId;
        this.userPrivateId = userPrivateId;
        this.planId = planId;
        this.month = month;
        this.totalAmount = totalAmount;
        this.showAmount = showAmount;
        this.status = status;
        this.remark = remark;
        this.redeemId = redeemId;
        this.productType = productType;
        this.discount = discount;
        this.addressPerson = addressPerson;
        this.addressPhone = addressPhone;
        this.addressAddress = addressAddress;
    }

    /**
     * Getter for <code>Afdian_Order.out_trade_no</code>.
     */
    @Override
    public String getOutTradeNo() {
        return this.outTradeNo;
    }

    /**
     * Setter for <code>Afdian_Order.out_trade_no</code>.
     */
    @Override
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    /**
     * Getter for <code>Afdian_Order.custom_order_id</code>.
     */
    @Override
    public String getCustomOrderId() {
        return this.customOrderId;
    }

    /**
     * Setter for <code>Afdian_Order.custom_order_id</code>.
     */
    @Override
    public void setCustomOrderId(String customOrderId) {
        this.customOrderId = customOrderId;
    }

    /**
     * Getter for <code>Afdian_Order.user_id</code>.
     */
    @Override
    public String getUserId() {
        return this.userId;
    }

    /**
     * Setter for <code>Afdian_Order.user_id</code>.
     */
    @Override
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Getter for <code>Afdian_Order.user_private_id</code>.
     */
    @Override
    public String getUserPrivateId() {
        return this.userPrivateId;
    }

    /**
     * Setter for <code>Afdian_Order.user_private_id</code>.
     */
    @Override
    public void setUserPrivateId(String userPrivateId) {
        this.userPrivateId = userPrivateId;
    }

    /**
     * Getter for <code>Afdian_Order.plan_id</code>.
     */
    @Override
    public String getPlanId() {
        return this.planId;
    }

    /**
     * Setter for <code>Afdian_Order.plan_id</code>.
     */
    @Override
    public void setPlanId(String planId) {
        this.planId = planId;
    }

    /**
     * Getter for <code>Afdian_Order.month</code>.
     */
    @Override
    public Long getMonth() {
        return this.month;
    }

    /**
     * Setter for <code>Afdian_Order.month</code>.
     */
    @Override
    public void setMonth(Long month) {
        this.month = month;
    }

    /**
     * Getter for <code>Afdian_Order.total_amount</code>.
     */
    @Override
    public String getTotalAmount() {
        return this.totalAmount;
    }

    /**
     * Setter for <code>Afdian_Order.total_amount</code>.
     */
    @Override
    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * Getter for <code>Afdian_Order.show_amount</code>.
     */
    @Override
    public String getShowAmount() {
        return this.showAmount;
    }

    /**
     * Setter for <code>Afdian_Order.show_amount</code>.
     */
    @Override
    public void setShowAmount(String showAmount) {
        this.showAmount = showAmount;
    }

    /**
     * Getter for <code>Afdian_Order.status</code>.
     */
    @Override
    public Long getStatus() {
        return this.status;
    }

    /**
     * Setter for <code>Afdian_Order.status</code>.
     */
    @Override
    public void setStatus(Long status) {
        this.status = status;
    }

    /**
     * Getter for <code>Afdian_Order.remark</code>.
     */
    @Override
    public String getRemark() {
        return this.remark;
    }

    /**
     * Setter for <code>Afdian_Order.remark</code>.
     */
    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * Getter for <code>Afdian_Order.redeem_id</code>.
     */
    @Override
    public String getRedeemId() {
        return this.redeemId;
    }

    /**
     * Setter for <code>Afdian_Order.redeem_id</code>.
     */
    @Override
    public void setRedeemId(String redeemId) {
        this.redeemId = redeemId;
    }

    /**
     * Getter for <code>Afdian_Order.product_type</code>.
     */
    @Override
    public Long getProductType() {
        return this.productType;
    }

    /**
     * Setter for <code>Afdian_Order.product_type</code>.
     */
    @Override
    public void setProductType(Long productType) {
        this.productType = productType;
    }

    /**
     * Getter for <code>Afdian_Order.discount</code>.
     */
    @Override
    public String getDiscount() {
        return this.discount;
    }

    /**
     * Setter for <code>Afdian_Order.discount</code>.
     */
    @Override
    public void setDiscount(String discount) {
        this.discount = discount;
    }

    /**
     * Getter for <code>Afdian_Order.address_person</code>.
     */
    @Override
    public String getAddressPerson() {
        return this.addressPerson;
    }

    /**
     * Setter for <code>Afdian_Order.address_person</code>.
     */
    @Override
    public void setAddressPerson(String addressPerson) {
        this.addressPerson = addressPerson;
    }

    /**
     * Getter for <code>Afdian_Order.address_phone</code>.
     */
    @Override
    public String getAddressPhone() {
        return this.addressPhone;
    }

    /**
     * Setter for <code>Afdian_Order.address_phone</code>.
     */
    @Override
    public void setAddressPhone(String addressPhone) {
        this.addressPhone = addressPhone;
    }

    /**
     * Getter for <code>Afdian_Order.address_address</code>.
     */
    @Override
    public String getAddressAddress() {
        return this.addressAddress;
    }

    /**
     * Setter for <code>Afdian_Order.address_address</code>.
     */
    @Override
    public void setAddressAddress(String addressAddress) {
        this.addressAddress = addressAddress;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final AfdianOrder other = (AfdianOrder) obj;
        if (this.outTradeNo == null) {
            if (other.outTradeNo != null)
                return false;
        }
        else if (!this.outTradeNo.equals(other.outTradeNo))
            return false;
        if (this.customOrderId == null) {
            if (other.customOrderId != null)
                return false;
        }
        else if (!this.customOrderId.equals(other.customOrderId))
            return false;
        if (this.userId == null) {
            if (other.userId != null)
                return false;
        }
        else if (!this.userId.equals(other.userId))
            return false;
        if (this.userPrivateId == null) {
            if (other.userPrivateId != null)
                return false;
        }
        else if (!this.userPrivateId.equals(other.userPrivateId))
            return false;
        if (this.planId == null) {
            if (other.planId != null)
                return false;
        }
        else if (!this.planId.equals(other.planId))
            return false;
        if (this.month == null) {
            if (other.month != null)
                return false;
        }
        else if (!this.month.equals(other.month))
            return false;
        if (this.totalAmount == null) {
            if (other.totalAmount != null)
                return false;
        }
        else if (!this.totalAmount.equals(other.totalAmount))
            return false;
        if (this.showAmount == null) {
            if (other.showAmount != null)
                return false;
        }
        else if (!this.showAmount.equals(other.showAmount))
            return false;
        if (this.status == null) {
            if (other.status != null)
                return false;
        }
        else if (!this.status.equals(other.status))
            return false;
        if (this.remark == null) {
            if (other.remark != null)
                return false;
        }
        else if (!this.remark.equals(other.remark))
            return false;
        if (this.redeemId == null) {
            if (other.redeemId != null)
                return false;
        }
        else if (!this.redeemId.equals(other.redeemId))
            return false;
        if (this.productType == null) {
            if (other.productType != null)
                return false;
        }
        else if (!this.productType.equals(other.productType))
            return false;
        if (this.discount == null) {
            if (other.discount != null)
                return false;
        }
        else if (!this.discount.equals(other.discount))
            return false;
        if (this.addressPerson == null) {
            if (other.addressPerson != null)
                return false;
        }
        else if (!this.addressPerson.equals(other.addressPerson))
            return false;
        if (this.addressPhone == null) {
            if (other.addressPhone != null)
                return false;
        }
        else if (!this.addressPhone.equals(other.addressPhone))
            return false;
        if (this.addressAddress == null) {
            if (other.addressAddress != null)
                return false;
        }
        else if (!this.addressAddress.equals(other.addressAddress))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.outTradeNo == null) ? 0 : this.outTradeNo.hashCode());
        result = prime * result + ((this.customOrderId == null) ? 0 : this.customOrderId.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.userPrivateId == null) ? 0 : this.userPrivateId.hashCode());
        result = prime * result + ((this.planId == null) ? 0 : this.planId.hashCode());
        result = prime * result + ((this.month == null) ? 0 : this.month.hashCode());
        result = prime * result + ((this.totalAmount == null) ? 0 : this.totalAmount.hashCode());
        result = prime * result + ((this.showAmount == null) ? 0 : this.showAmount.hashCode());
        result = prime * result + ((this.status == null) ? 0 : this.status.hashCode());
        result = prime * result + ((this.remark == null) ? 0 : this.remark.hashCode());
        result = prime * result + ((this.redeemId == null) ? 0 : this.redeemId.hashCode());
        result = prime * result + ((this.productType == null) ? 0 : this.productType.hashCode());
        result = prime * result + ((this.discount == null) ? 0 : this.discount.hashCode());
        result = prime * result + ((this.addressPerson == null) ? 0 : this.addressPerson.hashCode());
        result = prime * result + ((this.addressPhone == null) ? 0 : this.addressPhone.hashCode());
        result = prime * result + ((this.addressAddress == null) ? 0 : this.addressAddress.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("AfdianOrder (");

        sb.append(outTradeNo);
        sb.append(", ").append(customOrderId);
        sb.append(", ").append(userId);
        sb.append(", ").append(userPrivateId);
        sb.append(", ").append(planId);
        sb.append(", ").append(month);
        sb.append(", ").append(totalAmount);
        sb.append(", ").append(showAmount);
        sb.append(", ").append(status);
        sb.append(", ").append(remark);
        sb.append(", ").append(redeemId);
        sb.append(", ").append(productType);
        sb.append(", ").append(discount);
        sb.append(", ").append(addressPerson);
        sb.append(", ").append(addressPhone);
        sb.append(", ").append(addressAddress);

        sb.append(")");
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IAfdianOrder from) {
        setOutTradeNo(from.getOutTradeNo());
        setCustomOrderId(from.getCustomOrderId());
        setUserId(from.getUserId());
        setUserPrivateId(from.getUserPrivateId());
        setPlanId(from.getPlanId());
        setMonth(from.getMonth());
        setTotalAmount(from.getTotalAmount());
        setShowAmount(from.getShowAmount());
        setStatus(from.getStatus());
        setRemark(from.getRemark());
        setRedeemId(from.getRedeemId());
        setProductType(from.getProductType());
        setDiscount(from.getDiscount());
        setAddressPerson(from.getAddressPerson());
        setAddressPhone(from.getAddressPhone());
        setAddressAddress(from.getAddressAddress());
    }

    @Override
    public <E extends IAfdianOrder> E into(E into) {
        into.from(this);
        return into;
    }
}
