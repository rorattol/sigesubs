package br.ufsm.controller;

import br.ufsm.dao.SolicitacaoDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SolicitacaoController {

    @RequestMapping("/gerenciarSolicitacao")
    String index(Model model) {
        model.addAttribute("solicitacoes", new SolicitacaoDAO().getSolicitacoesTodas());
        return "views/gerenciarSolicitacao";
    }

    @GetMapping("detalhesSolicitacao")
    String detalhesSol(@RequestParam("id") int idSolic , Model model) {
        model.addAttribute("infoSolic", new SolicitacaoDAO().getSolicitacoes(idSolic));
        model.addAttribute("materiais", new SolicitacaoDAO().getMateriaisSolicitacao(idSolic));     //MATERIAL_SOLICITACAO
        return "views/solicitacoesPendentes";
    }

    @RequestMapping("fazerSolicitacao")
    String realizaSol(){
        return "views/fazerSolicitacao";
    }

    @RequestMapping("avaliarSolicitacao")
    String avaliarSol(@RequestParam("op") int opcao, @RequestParam ("obs") String observacao, Model model){

        if(opcao == 1){ //REJEITAR

        }
        else if(opcao == 2){ //ACEITAR COM AJUSTE
            model.addAttribute(observacao);
            return "views/ajustarSolicitacao";
        }
        else if(opcao == 3){ //ACEITAR

        }
        else{
            model.addAttribute("erro", "Erro ao realizar solicitação. Tente novamente.");
            return "views/gerenciarSolicitacao";
        }

        model.addAttribute("sucesso", "Avaliação realizada com sucesso.");
        return "views/gerenciarSolicitacao";
    }



}