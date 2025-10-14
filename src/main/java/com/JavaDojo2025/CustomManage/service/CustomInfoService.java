package com.JavaDojo2025.CustomManage.service;

import com.JavaDojo2025.CustomManage.entity.CustomInfo;
import com.JavaDojo2025.CustomManage.repository.CustomInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CustomInfoService {
    
    @Autowired
    private CustomInfoRepository customInfoRepository;
    
    public CustomInfo save(CustomInfo customInfo) {
        return customInfoRepository.save(customInfo);
    }
    
    public Optional<CustomInfo> findByShopIdAndCif(Integer shopId, Integer cif) {
        return customInfoRepository.findByShopIdAndCif(shopId, cif);
    }
}
