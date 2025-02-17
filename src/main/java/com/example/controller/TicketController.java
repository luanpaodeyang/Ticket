package com.example.controller;

import com.example.ConcertService;
import com.example.UserService;
import com.example.model.customer;
import com.example.model.home;
import com.example.model.user;
import com.example.repository.ConcertRepository;
import com.example.repository.CustomerRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
@Controller
public class TicketController {
    @Autowired
    private ConcertService concertService;
    @Autowired
    private ConcertRepository concertRepository;
    @Autowired
    private CustomerRepository customerRepository;


    @GetMapping("/index")
    public String showTicketPage(Model model) {
        List<home> homes = concertRepository.findAll();
        model.addAttribute("homes", homes);
        // 实现演唱会售票系统的逻辑
        return "ticket-page";
    }




    @GetMapping("/index/show-list")
    public String showHomeList(Model model) {
        // 查询表 home 的所有内容
        List<home> homes = concertService.getAllConcerts();
        // 将查询结果放入 Model 中
        model.addAttribute("homes", homes);
        // 返回 Thymeleaf 模板的名称
        return "show-list";
    }


    @GetMapping("/index/show-list/add-ticket")
    public String showAddTicketForm(Model model) {
        //显示添加票务的表单页面
        model.addAttribute("home", new home());
        return "add-ticket";
    }
    @PostMapping("/add")
    public String addConcert(@ModelAttribute home home) {
        //处理实际的添加操作
        concertService.saveConcert(home);
        return "redirect:/index/success";
    }


    @GetMapping("/index/success")
    public String showSuccessPage() {
        //执行成功，跳转到success
        return "success";
    }


    @GetMapping("/index/show-list/delete-ticket")
    public String showDeleteTicketPage(Model model) {
        //跳转到delete-ticket
        // 查询表 home 的所有内容
        model.addAttribute("homes", concertService.getAllConcerts());
        return "/delete-ticket";
    }
    @PostMapping("/delete")
    public String deleteConcertById(@RequestParam Long deleteId) {
        // 处理删除请求
        concertService.deleteConcertById(deleteId);
        return "redirect:/index/success";
    }




    @GetMapping("/index/show-list/revise-ticket")
    public String showReviseTicketForm(Model model) {
        //跳转到revise-ticket

        // 显示表 home 的所有内容
        model.addAttribute("homes", concertService.getAllConcerts());
        //显示用序号查到的源数据
        model.addAttribute("reviseTicket", new home());
        //绑定修改后的数据
        model.addAttribute("updatedTicket", new home());
        return "revise-ticket";
    }

    @PostMapping("/revise")
    public String findTicketById(@ModelAttribute home reviseTicket, Model model) {
        // 用序号查找票品信息
        home existingTicket = concertService.findTicketById(reviseTicket.getId());
        // 显示表 home 的所有内容
        model.addAttribute("homes", concertService.getAllConcerts());
        // 将原数据显示在修改表单中
        model.addAttribute("reviseTicket", existingTicket);
        model.addAttribute("updatedTicket", existingTicket);

        return "revise-ticket";
    }

    @PostMapping("/update")
    public String updateTicket(@ModelAttribute home updatedTicket) {
        // 更新数据库中的数据
        concertService.updateTicket(updatedTicket);
        return "redirect:/index/success";
    }





    @GetMapping("/index/sell-ticket")
    public String showSellTicket(Model model) {
        //跳转到sell-ticket

        // 显示表 home 的所有内容
        List<home> homes = concertService.getAllConcerts();
        model.addAttribute("homes", homes);
        return "sell-ticket";
    }

    @GetMapping("/index/sell-ticket/customer-information/{homeId}")
    public String sellTicketForm(Model model, @PathVariable Long homeId) {
        //显示点击出售后的表单
        home home = concertService.findTicketById(homeId);
        List<String> showNames;
        showNames = concertService.getAllShowNames();

        model.addAttribute("home", home);
        model.addAttribute("customer", new customer());
        model.addAttribute("showNames", showNames);

        return "customer-information";
    }

    @PostMapping("/index/sell-ticket/customer-information/success2/{homeId}")
    public String confirmSell(@PathVariable Long homeId,@ModelAttribute customer customer ) {

        // 获取演出名称
        String showName = customer.getShowName();
        // 确认购买，将数据存到数据库
        concertService.sellTicket(homeId,customer);

        // 重定向到指定页面
        return "redirect:/index/sell-ticket/customer-information/success2" ;
    }

    @GetMapping("/index/sell-ticket/customer-information/success2")
    public String showSuccess2Page() {
        //出售成功，跳转到success2
        return "success2";
    }





    @GetMapping("/index/sell-ticket/show-customer-information")
    public String showCustomerInformationPage(Model model) {
        //跳转到show-customer-information

        // 显示表customer 的所有数据
        List<customer> customers = concertService.getCustomerInformation();
        model.addAttribute("customers", customers);
        return "show-customer-information";
    }







    @GetMapping("/login")
    public String showLoginPage() {
        //显示login.html
        return "login";

    }
    @GetMapping("/success3")
    public String showSuccess3Page() {
        //success3.html
        return "success3";

    }

    @Autowired
    private UserRepository userRepository;
    @PostMapping("/login")
    public String login(String username, String password, HttpSession session, Model model) {
        // 查询数据库中的用户信息
        user user = userRepository.findByUsernameAndPassword(username, password);

        if (user != null) {
            // 登录成功，将用户信息存储在Session中
            session.setAttribute("user", user);
            return "redirect:/index"; // 重定向到index页面
        } else {
            // 登录失败，返回登录页面并显示错误消息
            model.addAttribute("ERROR", "用户名或密码无效");
            return "login";
        }
    }


    @RequestMapping("/checklogin")
    public String showDashboard(HttpSession session, Model model) {
        // 检查用户是否已登录
        user user = (user) session.getAttribute("user");
        if (user != null) {
            // 用户已登录，显示仪表板页面
            model.addAttribute("user", user);
            return "redirect:/index";
        } else {
            // 用户未登录，重定向到登录页面
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 使Session失效，即注销用户
        session.invalidate();
        // 重定向到登录页面
        return "redirect:/login";
    }

    @GetMapping("/add-user")
    public String showAddUserPage(Model model) {
        //显示add-user

        model.addAttribute("user", new user());
        return "add-user";
    }
    @Autowired
    private UserService userService;
    @PostMapping("/add2")
    public String addUser(@ModelAttribute user user, @RequestParam String invitationCode) {
        // 验证邀请码
        if ("1314520".equals(invitationCode)) {
            //邀请码正确，处理实际的注册操作
            userService.saveUser(user);
            return "redirect:/login"; // 注册成功后重定向到登录页面
        } else {
            // 邀请码错误，返回错误信息
            return "redirect:/success3";
        }

    }



}
