package com.JavaDojo2025.CustomManage.repository;

import com.JavaDojo2025.CustomManage.entity.CustomInfo;
import com.JavaDojo2025.CustomManage.entity.CustomInfoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CustomInfoRepository extends JpaRepository<CustomInfo, CustomInfoId> {
    Optional<CustomInfo> findByShopIdAndCif(Integer shopId, Integer cif);
}
