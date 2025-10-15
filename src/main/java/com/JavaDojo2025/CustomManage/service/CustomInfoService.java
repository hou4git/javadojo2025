package com.JavaDojo2025.CustomManage.service;

import com.JavaDojo2025.CustomManage.entity.CustomInfo;
import com.JavaDojo2025.CustomManage.repository.CustomInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomInfoService {
    
    @Autowired
    private CustomInfoRepository customInfoRepository;
    
    public CustomInfo save(CustomInfo customInfo) throws DataAccessException {
        try {
            return customInfoRepository.save(customInfo);
        } catch (DataAccessException e) {
            System.err.println("Database save error: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("Unexpected save error: " + e.getMessage());
            throw new RuntimeException("データ保存中にエラーが発生しました", e);
        }
    }
    
    
    public Optional<CustomInfo> findByShopIdAndCif(Integer shopId, Integer cif) {
        try {
            // 使用List查询，永远不会抛异常
            List<CustomInfo> results = customInfoRepository.findCustomerList(shopId, cif);
            return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
        } catch (DataAccessException e) {
            // 记录数据库异常
            System.err.println("Database access error: " + e.getMessage());
            return Optional.empty();
        } catch (Exception e) {
            // 记录其他异常
            System.err.println("Unexpected error: " + e.getMessage());
            return Optional.empty();
        }
    }
    //public Optional<CustomInfo> findByShopIdAndCif(Integer shopId, Integer cif) throws DataAccessException {
    //    try {
    //        if (shopId == null || cif == null) {
    //            return Optional.empty();
    //        }
    //        
    //        // データベース検索実行
    //        Optional<CustomInfo> result = customInfoRepository.findByShopIdAndCif(shopId, cif);
    //        
    //        // ログ出力（デバッグ用）
    //        if (result.isPresent()) {
    //            System.out.println("Found customer: shopId=" + shopId + ", cif=" + cif);
    //        } else {
    //            System.out.println("No customer found: shopId=" + shopId + ", cif=" + cif);
    //        }
    //        
    //        return result;
    //        
    //    } catch (DataAccessException e) {
    //        // データベースアクセスエラーの場合
    //        System.err.println("Database access error: " + e.getMessage());
    //        throw new RuntimeException("データベースアクセスエラーが発生しました", e);
    //    } catch (Exception e) {
    //        // その他の予期しないエラー
    //        System.err.println("Unexpected error in findByShopIdAndCif: " + e.getMessage());
    //        throw new RuntimeException("検索中にエラーが発生しました", e);
    //    }
    //}

}
