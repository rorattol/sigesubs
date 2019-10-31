package br.ufsm.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class BaixaMaterialController {

    @RequestMapping("darBaixa")
    String index(){
        return "views/darBaixaMateriais";
    }
}
