package com.techacademy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable; // 追加
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.entity.User;
import com.techacademy.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService service;

    /** 一覧画面を表示 */
    @GetMapping("/list")
    public String getList(Model model) {
        // 全件検索結果をModelに登録
        model.addAttribute("userlist", service.getUserList());
        // user/list.htmlに画面遷移
        return "user/list";
    }

    /** User登録画面を表示 */
    @GetMapping("/register")
    public String getRegister(@ModelAttribute User user) {
        // User登録画面に遷移
        return "user/register";
    }

    /** User登録処理 */
    @PostMapping("/register")
    public String postRegister(User user) {
        // User登録
        service.saveUser(user);
        // 一覧画面にリダイレクト
        return "redirect:/user/list";
    }

    // ----- 追加:ここから -----
    /** User更新画面を表示 */
    @GetMapping("/update/{id}/")
    public String getUser(@PathVariable("id") Integer id, Model model) {
        // Modelに登録
        model.addAttribute("user", service.getUser(id));
        // User更新画面に遷移
        return "user/update";
    }

    /** User更新処理 */
    @PostMapping("/update/{id}/")
    public String postUser(User user) {
        // User登録
        service.saveUser(user);
        // 一覧画面にリダイレクト
        return "redirect:/user/list";
    }
    // ----- 追加:ここまで -----
}