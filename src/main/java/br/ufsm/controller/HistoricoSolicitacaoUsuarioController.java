package br.ufsm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HistoricoSolicitacaoUsuarioController {

    @RequestMapping("/historicoUsuario")
    String index(){
        return "views/historicoSolicitacaoUsuario";
    }

    @RequestMapping("/detalheHistoricoUsuario")
    String detalhesAdmin(){
        return "views/detalhesHistoricoUsuario";
    }

}
