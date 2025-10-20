package com.JavaDojo2025.CustomManage.repository;

import com.JavaDojo2025.CustomManage.entity.CustomInfo;
import com.JavaDojo2025.CustomManage.entity.CustomInfoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomInfoRepository extends JpaRepository<CustomInfo, CustomInfoId> {
    //Optional<CustomInfo> findByShopIdAndCif(Integer shopId, Integer cif);
    // 保留自动生成的方法
    Optional<CustomInfo> findByShopIdAndCif(Integer shopId, Integer cif);
    
    // 添加安全查询方法
    @Query("SELECT c FROM CustomInfo c WHERE c.shopId = :shopId AND c.cif = :cif")
    List<CustomInfo> findCustomerList(@Param("shopId") Integer shopId, 
                                     @Param("cif") Integer cif);
    
    // 检查存在性
    @Query("SELECT COUNT(c) > 0 FROM CustomInfo c WHERE c.shopId = :shopId AND c.cif = :cif")
    boolean customerExists(@Param("shopId") Integer shopId, 
                          @Param("cif") Integer cif);
}
