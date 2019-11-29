package br.ufsm.controller;

import br.ufsm.dao.EstoqueSetorDAO;
import br.ufsm.dao.SolicitacaoDAO;
import br.ufsm.model.Solicitacao;
import br.ufsm.model.util.MaterialQuantidade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SolicitacaoController {

    @RequestMapping("/gerenciarSolicitacao")
    String index(Model model) {
        model.addAttribute("solicitacoes", new SolicitacaoDAO().getSolicitacoesTodas());
        return "views/gerenciarSolicitacao";
    }

    @GetMapping("detalhesSolicitacao")
    String detalhesSol(@RequestParam("id") int idSolic , Model model) {
        model.addAttribute("infoSolic", new SolicitacaoDAO().read(idSolic));
       // model.addAttribute("estoqueAtual", new EstoqueSetorDAO().getEstoqueAtual(idSolic));
        model.addAttribute("materiais", new SolicitacaoDAO().getMateriaisSolicitacao(idSolic));

        return "views/solicitacoesPendentes";
    }

    @RequestMapping("fazerSolicitacao")
    String realizaSol(Model model){
        model.addAttribute("materiais", new EstoqueSetorDAO().getEstoqueSetor(1));
        return "views/fazerSolicitacao";
    }

    @RequestMapping("avaliarSolicitacao")
    String avaliarSol(@RequestParam("op") String opcao, @RequestParam ("obs") String observacao, @RequestParam ("id") String id, Model model){


        if(opcao.equals("1")){ //REJEITAR

            Solicitacao sol = new Solicitacao();
            sol.setId(Integer.valueOf(id));
            sol.setStatusSolicitacao("Negado");
            if(observacao.trim().equals("") || observacao == null){
                sol.setObservacao("Sem observações");
            }else {
                sol.setObservacao(observacao);
            }
            boolean resp = new SolicitacaoDAO().udpate(sol);
            if (resp == true){
                model.addAttribute("sucesso", "Avaliação realizada com sucesso. Você rejeitou a solicitação");
            }
            else{
                model.addAttribute("erro", "Alguma coisa correu errado. Tente novamente");
            }
            model.addAttribute("solicitacoes", new SolicitacaoDAO().getSolicitacoesTodas());
            return "views/gerenciarSolicitacao";
        }

        else if(opcao.equals("2")){ //ACEITAR COM AJUSTE
            model.addAttribute(observacao);
            model.addAttribute("materiais", new SolicitacaoDAO().getMateriaisSolicitacao(Integer.valueOf(id)));
            return "views/ajustarSolicitacao";
        }

        else if(opcao.equals("3")){ //ACEITAR
            Solicitacao sol = new Solicitacao();
            sol.setId(Integer.parseInt(id));
            sol.setStatusSolicitacao("Aceito");
            if(observacao.equals(null)){
                sol.setObservacao("Sem observações");
            }else {
                sol.setObservacao(observacao);
            }

            boolean resp = new SolicitacaoDAO().udpate(sol);
            if (resp == true){
                model.addAttribute("sucesso", "Avaliação realizada com sucesso. Você rejeitou a solicitação");
            }
            else{
                model.addAttribute("erro", "Alguma coisa correu errado. Tente novamente");
            }
            model.addAttribute("solicitacoes", new SolicitacaoDAO().getSolicitacoesTodas());
            return "views/gerenciarSolicitacao";
        }

        else{
            model.addAttribute("solicitacoes", new SolicitacaoDAO().getSolicitacoesTodas());
            model.addAttribute("erro", "Erro ao realizar solicitação. Tente novamente.");
            return "views/gerenciarSolicitacao";
        }
    }


    @PostMapping("solicitar")
    String solictacao(@RequestParam List<Integer> idMaterial, @RequestParam List<Integer> quantidade, @RequestParam ("id") int idOrigem, Model model){

        Map<Integer, Integer> materiais = new HashMap<>();
        for(int i = 0; i < idMaterial.size(); i++) {
            if(quantidade.get(i) > 0) {
                materiais.put(idMaterial.get(i), quantidade.get(i));
            }
        }

        new SolicitacaoDAO().solicitarMaterial(materiais, idOrigem);

        boolean resp = true;
        if (resp == true){
            model.addAttribute("sucesso", "Solicitação enviada para avaliação.");
        }
        else{
            model.addAttribute("erro", "Alguma coisa correu errado. Tente novamente.");
        }
        model.addAttribute("estoque", new EstoqueSetorDAO().getEstoqueSetorAtual(idOrigem));
        return "views/fazerSolicitacao";
    }
}