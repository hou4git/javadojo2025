package com.JavaDojo2025.CustomManage.controller;

import com.JavaDojo2025.CustomManage.entity.CustomInfo;
import com.JavaDojo2025.CustomManage.service.CustomInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
public class CustomInfoController {
    
    @Autowired
    private CustomInfoService customInfoService;
    
    @GetMapping("/")
    public String index() {
        return "index";
    }
    
    @GetMapping("/input")
    public String inputForm(Model model) {
        model.addAttribute("customInfo", new CustomInfo());
        return "input";
    }
    
    @PostMapping("/input")
    public String submitForm(@ModelAttribute CustomInfo customInfo, Model model) {
        try {
            customInfoService.save(customInfo);
            model.addAttribute("message", "データが正常に保存されました。");
            model.addAttribute("customInfo", new CustomInfo());
        } catch (Exception e) {
            model.addAttribute("error", "データの保存中にエラーが発生しました: " + e.getMessage());
        }
        return "input";
    }
    
    @GetMapping("/search")
    public String searchForm(Model model) {
        // 初期表示時は検索フラグをfalseに設定（エラーメッセージ非表示）
        model.addAttribute("searchPerformed", false);
        return "search";
    }
    
    @PostMapping("/search")
    public String searchResult(@RequestParam Integer shopId, 
                             @RequestParam Integer cif, 
                             Model model) {
        // パラメータの検証
        if (shopId == null || cif == null) {
            model.addAttribute("searchPerformed", false);
            model.addAttribute("systemError", true);
            model.addAttribute("errorMessage", "店舗IDとCIF番号の両方を入力してください。");
            return "search";
        }
        
        if (shopId <= 0 || cif <= 0) {
            model.addAttribute("searchPerformed", false);
            model.addAttribute("systemError", true);
            model.addAttribute("errorMessage", "店舗IDとCIF番号は1以上の数値を入力してください。");
            return "search";
        }
        try {
            // 検索実行フラグを設定
            model.addAttribute("searchPerformed", true);
            model.addAttribute("searchShopId", shopId);
            model.addAttribute("searchCif", cif);
            
            Optional<CustomInfo> customInfo = customInfoService.findByShopIdAndCif(shopId, cif);
            
            if (customInfo.isPresent()) {
                // データが見つかった場合：結果画面に遷移
                model.addAttribute("customInfo", customInfo.get());
                return "result";
            } else {
                // データが見つからない場合：検索画面にエラーメッセージを表示
                model.addAttribute("notFoundError", true);
                model.addAttribute("errorMessage", 
                    String.format("ShopID: %d，CIF: %d record not found.from CustomInfoController-no.82-err", shopId, cif));
                //model.addAttribute("searchCompleted", true);  // エラーフラグではない
                //model.addAttribute("infoMessage",  // エラーメッセージではない
                //    String.format("ShopID: %d,CIF: %d に該当するデータは登録されていません。", shopId, cif));
                return "search";
            }
        } catch (NumberFormatException e) {
            // 数値変換エラー
            model.addAttribute("searchPerformed", true);
            model.addAttribute("searchShopId", shopId);
            model.addAttribute("searchCif", cif);
            model.addAttribute("systemError", true);
            model.addAttribute("errorMessage", "入力された値が不正です。数値を入力してください。");
            return "search";
            
        } catch (Exception e) {
            // その他のシステムエラー
            model.addAttribute("searchPerformed", true);
            model.addAttribute("searchShopId", shopId);
            model.addAttribute("searchCif", cif);
            model.addAttribute("systemError", true);
            model.addAttribute("errorMessage", "検索中にエラーが発生しました。しばらくしてから再度お試しください。");
            
            // ログ出力（デバッグ用）
            System.err.println("Search error: " + e.getMessage());
            e.printStackTrace();
            
            return "search";
        }
    }
    // グローバル例外ハンドラー
    //@ExceptionHandler(MissingServletRequestParameterException.class)
    //public String handleMissingParams(MissingServletRequestParameterException ex, Model model) {
    //    model.addAttribute("searchPerformed", false);
    //    model.addAttribute("systemError", true);
    //    model.addAttribute("errorMessage", "必要なパラメータが不足しています。");
    //    return "search";
    //}    
    //@ExceptionHandler(Exception.class)
    //public String handleGeneralError(Exception ex, Model model) {
    //    model.addAttribute("searchPerformed", false);
    //    model.addAttribute("systemError", true);
    //    model.addAttribute("errorMessage", "予期しないエラーが発生しました。管理者にお問い合わせください。");
    //    
    //    // ログ出力
    //    System.err.println("Unexpected error: " + ex.getMessage());
    //    ex.printStackTrace();
    //    
    //    return "search";
    //}
}
