package com.JavaDojo2025.CustomManage.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid; // バリデーション使用時
import java.time.LocalDate;

@Entity
@Table(name = "custominfo")
@IdClass(CustomInfoId.class)
public class CustomInfo {
    
    @Id
    @Column(name = "ShopID")
    private Integer shopId;
    
    @Id
    @Column(name = "CIF")
    private Integer cif;
    
    @Column(name = "NameKanji", nullable = false, length = 100)
    private String nameKanji;
    
    @Column(name = "NameKana", nullable = false, length = 100)
    private String nameKana;
    
    @Column(name = "NameRomaji", nullable = false, length = 100)
    private String nameRomaji;
    
    @Column(name = "PostCode", nullable = false, length = 8)
    private String postCode;
    
    @Column(name = "Address1", nullable = false, length = 100)
    private String address1;
    
    @Column(name = "Address2", nullable = false, length = 100)
    private String address2;
    
    @Column(name = "PhoneNum", nullable = false, length = 20)
    private String phoneNum;
    
    @Column(name = "BirthDate")
    private LocalDate birthDate;
    
    // コンストラクタ
    public CustomInfo() {}
    
    // ゲッター・セッター
    public Integer getShopId() { return shopId; }
    public void setShopId(Integer shopId) { this.shopId = shopId; }
    
    public Integer getCif() { return cif; }
    public void setCif(Integer cif) { this.cif = cif; }
    
    public String getNameKanji() { return nameKanji; }
    public void setNameKanji(String nameKanji) { this.nameKanji = nameKanji; }
    
    public String getNameKana() { return nameKana; }
    public void setNameKana(String nameKana) { this.nameKana = nameKana; }
    
    public String getNameRomaji() { return nameRomaji; }
    public void setNameRomaji(String nameRomaji) { this.nameRomaji = nameRomaji; }
    
    public String getPostCode() { return postCode; }
    public void setPostCode(String postCode) { this.postCode = postCode; }
    
    public String getAddress1() { return address1; }
    public void setAddress1(String address1) { this.address1 = address1; }
    
    public String getAddress2() { return address2; }
    public void setAddress2(String address2) { this.address2 = address2; }
    
    public String getPhoneNum() { return phoneNum; }
    public void setPhoneNum(String phoneNum) { this.phoneNum = phoneNum; }
    
    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
}
