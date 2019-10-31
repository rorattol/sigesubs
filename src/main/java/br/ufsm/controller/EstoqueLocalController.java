package br.ufsm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EstoqueLocalController {

    @RequestMapping("/estoqueAlmox")
    String index(){
        return "views/adicionarEstoqueAlmox";
    }
}
