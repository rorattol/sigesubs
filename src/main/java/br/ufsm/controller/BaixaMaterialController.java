package br.ufsm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaixaMaterialController {

    @RequestMapping("/darBaixa")
    String index(){
        return "views/darBaixaMateriais";
    }
}
