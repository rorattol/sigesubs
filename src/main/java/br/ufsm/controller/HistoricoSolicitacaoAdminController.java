package br.ufsm.controller;

import br.ufsm.dao.SolicitacaoDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HistoricoSolicitacaoAdminController {

    @RequestMapping("/historicoAdmin")
    String index(Model model){
        model.addAttribute("historico", new SolicitacaoDAO().getHistoricoSolicitacoes());
        return "views/historicoSolicitacaoAdmin";
    }

    @GetMapping("/detalheHistoricoAdmin")
    String detalhesAdmin(@RequestParam("id") int idSol,Model model){
        model.addAttribute("historico", new SolicitacaoDAO().getHistoricoSolicitacoes(idSol));
        return "views/detalhesHistoricoAdmin";
    }

    @RequestMapping("/historicoUsuario")
    String index1(Model model){
        model.addAttribute("historico", new SolicitacaoDAO().getHistoricoSolicitacoes());
        return "views/historicoSolicitacaoUsuario";
    }

    @RequestMapping("/detalheHistoricoUsuario")
    String detalhesUsuario(){
        return "views/detalhesHistoricoUsuario";
    }
}
