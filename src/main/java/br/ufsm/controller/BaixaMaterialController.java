package br.ufsm.controller;

import br.ufsm.dao.EstoqueSetorDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaixaMaterialController {

    @RequestMapping("/darBaixa")
    String index(Model model){
        model.addAttribute("materiais", new EstoqueSetorDAO().getEstoqueSetorAtual(1));
        return "views/darBaixaMateriais";
    }
}
