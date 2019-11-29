package br.ufsm.controller;

import br.ufsm.dao.EstoqueSetorDAO;
import br.ufsm.model.EstoqueSetor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EstoqueLocalController {

    @RequestMapping("/estoqueAlmox")
    String index() {
        return "views/adicionarEstoqueAlmox";
    }

    @GetMapping("/estoqueUnidade")
    String estoque(@RequestParam("id") int idUnidade , Model model) {
        model.addAttribute("materiais", new EstoqueSetorDAO().getEstoqueSetorAtual(idUnidade));

        return "views/estoqueUnidade";
    }
}