package br.ufsm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SolicitacaoController {

    @RequestMapping("/gerenciarSolicitacao")
    String index() {
        return "views/gerenciarSolicitacao";
    }

    @RequestMapping("detalhesSolicitacao")
    String detalhesSol() {
        return "views/solicitacoesPendentes";
    }

    @RequestMapping("fazerSolicitacao")
    String realizaSol(){
        return "views/fazerSolicitacao";
    }

    @RequestMapping("avaliarSolicitacao")
    String avaliarSol(Model model){

        return "views/ajustarSolicitacao";

//        boolean retorno = true;
//
//        if (retorno) {
//            model.addAttribute("sucesso", "Usuario Cadastrado com sucesso");
//            return "views/gerenciarSolicitacao";
//        } else {
//            model.addAttribute("erro", "Não foi possivel cadastrar");
//            return "views/gerenciarSolicitacao";
//        }
    }
}