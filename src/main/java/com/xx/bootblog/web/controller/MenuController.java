package com.xx.bootblog.web.controller;

import com.xx.bootblog.domain.Menu;
import com.xx.bootblog.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MenuController {

    @Autowired
    MenuService menuService;

    @RequestMapping("getTree")
    @ResponseBody
    public List<Menu> getTree(){
        return menuService.getTree();
    }

}
