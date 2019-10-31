package br.ufsm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HistoricoSolicitacaoAdminController {

    @RequestMapping("/historicoAdmin")
    String index(){
        return "views/historicoSolicitacaoAdmin";
    }

    @RequestMapping("/detalheHistoricoAdmin")
    String detalhesAdmin(){
        return "views/detalhesHistoricoAdmin";
    }
}
