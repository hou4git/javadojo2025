package com.JavaDojo2025.CustomManage.entity;

import java.io.Serializable;
import java.util.Objects;

public class CustomInfoId implements Serializable {
    private Integer shopId;
    private Integer cif;
    
    public CustomInfoId() {}
    
    public CustomInfoId(Integer shopId, Integer cif) {
        this.shopId = shopId;
        this.cif = cif;
    }
    
    // ゲッター・セッター
    public Integer getShopId() { return shopId; }
    public void setShopId(Integer shopId) { this.shopId = shopId; }
    
    public Integer getCif() { return cif; }
    public void setCif(Integer cif) { this.cif = cif; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomInfoId that = (CustomInfoId) o;
        return Objects.equals(shopId, that.shopId) && Objects.equals(cif, that.cif);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(shopId, cif);
    }
}
