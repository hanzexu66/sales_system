package com.example.could.contorller;

import com.example.could.Service.SalesLogService;
import com.example.could.model.SalesLogViewModel;
import com.example.could.model.T_item;
import com.example.could.model.T_sales_log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/log")
public class SalesLogController {
    @Autowired
    SalesLogService salesLogService;

    @GetMapping("/store")
    public String enterLogPage(Model model, HttpSession session){
        String name = session.getAttribute("store_name").toString();
        List<SalesLogViewModel> salesLogs = salesLogService.getSalesLog(Integer.parseInt(session.getAttribute("id").toString()));
        int logAmount = salesLogs.size();
        int gross = 0;
        int net = 0;
        for (SalesLogViewModel s: salesLogs){
            gross += s.getTotal_price();
            net +=s.getTotal_price() - s.getCost_price() * s.getPurchase_volume();
        }
        model.addAttribute("store_name", name);
        model.addAttribute("logList", salesLogs);
        model.addAttribute("logAmount", logAmount);
        model.addAttribute("gross", gross);
        model.addAttribute("net", net);
        return "store_logpage";
    }

    @GetMapping("/store/all")
    public String searchLogs(String itemName, Model model, HttpSession session){
        log.info("搜索了" + itemName);
        String name = session.getAttribute("store_name").toString();
        List<SalesLogViewModel> salesLogs = salesLogService.searchSalesLog(Integer.parseInt(session.getAttribute("id").toString()), itemName);
        int logAmount = salesLogs.size();
        model.addAttribute("store_name", name);
        model.addAttribute("logList", salesLogs);
        model.addAttribute("logAmount", logAmount);
        model.addAttribute("searchName", itemName);
        return "store_logpage";
    }

    @GetMapping("/admin")
    public String enterAdminLogPage(Model model, HttpSession session){
        String account = session.getAttribute("account").toString();
        List<SalesLogViewModel> salesLogs = salesLogService.getSalesLog(null);
        int logAmount = salesLogs.size();
        model.addAttribute("account", account);
        model.addAttribute("logList", salesLogs);
        model.addAttribute("logAmount", logAmount);
        return "admin_logpage";
    }

    @GetMapping("/admin/all")
    public String searchInAllLogs(String itemName, Model model, HttpSession session){
        log.info("搜索了" + itemName);
        String account = session.getAttribute("account").toString();
        List<SalesLogViewModel> salesLogs = salesLogService.searchSalesLog(null, itemName);
        int logAmount = salesLogs.size();
        model.addAttribute("account", account);
        model.addAttribute("logList", salesLogs);
        model.addAttribute("logAmount", logAmount);
        model.addAttribute("searchName", itemName);
        return "admin_logpage";
    }
}
