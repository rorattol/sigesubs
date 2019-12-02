package br.ufsm.controller;

import br.ufsm.dao.EstoqueSetorDAO;
import br.ufsm.dao.MaterialDAO;
import br.ufsm.dao.SolicitacaoDAO;
import br.ufsm.model.Solicitacao;
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
    String detalhesSol(@RequestParam("id") int idSolic, Model model) {
        model.addAttribute("infoSolic", new SolicitacaoDAO().read(idSolic));
        model.addAttribute("materiais", new SolicitacaoDAO().getMateriaisSolicitacao(idSolic));
        model.addAttribute("idSolicitacao", idSolic);

        return "views/solicitacoesPendentes";
    }

    @GetMapping("fazerSolicitacao")
    String realizaSol(Model model, @RequestParam("id") int id) {
        model.addAttribute("materiais", new EstoqueSetorDAO().getEstoqueSetor(id));
        return "views/fazerSolicitacao";
    }

    @RequestMapping("avaliarSolicitacao")
    String avaliarSol(@RequestParam("op") String opcao, @RequestParam("obs") String observacao,
                      @RequestParam String idSol, @RequestParam("idUnidade") int idSetor, Model model) {

        Solicitacao sol = new Solicitacao();
        sol.setId(Integer.valueOf(idSol));
        if (observacao.trim().equals("") || observacao == null) {
            sol.setObservacao("Sem observações");
        } else {
            sol.setObservacao(observacao);
        }

        if (opcao.equals("1")) { //REJEITAR
            sol.setStatusSolicitacao("Negado");
            boolean resp = new SolicitacaoDAO().udpate(sol);
            if (resp == true) {
                model.addAttribute("sucesso", "Avaliação realizada com sucesso. Você rejeitou a solicitação");
            } else {
                model.addAttribute("erro", "Alguma coisa correu errado. Tente novamente");
            }
            model.addAttribute("solicitacoes", new SolicitacaoDAO().getSolicitacoesTodas());
            return "views/gerenciarSolicitacao";
        }

        else if (opcao.equals("2")) { //ACEITAR COM AJUSTE
            sol.setStatusSolicitacao("Aceito com Ajuste");
            new SolicitacaoDAO().udpate(sol);
            boolean resp = new SolicitacaoDAO().udpate(sol);
            if (resp == true) {
                model.addAttribute("materiais", new SolicitacaoDAO().getMateriaisSolicitacao(Integer.valueOf(idSol)));
                model.addAttribute("idSolicitacao", idSol);
                model.addAttribute("idSetor", idSetor);
                return "views/ajustarSolicitacao";
            } else {
                model.addAttribute("erro", "Alguma coisa correu errado. Tente novamente");
                return "views/gerenciarSolicitacao";
            }

        } else if (opcao.equals("3")) { //ACEITAR
            sol.setStatusSolicitacao("Aceito");
            boolean resp = new SolicitacaoDAO().udpate(sol);
            boolean query = new MaterialDAO().transferirSolicitacao(Integer.valueOf(idSol), idSetor);
            if (resp == true || query == true) {

                model.addAttribute("sucesso", "Avaliação realizada com sucesso. Você rejeitou a solicitação");
            } else {
                model.addAttribute("erro", "Alguma coisa correu errado. Tente novamente");
            }
            model.addAttribute("solicitacoes", new SolicitacaoDAO().getSolicitacoesTodas());
            return "views/gerenciarSolicitacao";
        } else {
            model.addAttribute("solicitacoes", new SolicitacaoDAO().getSolicitacoesTodas());
            model.addAttribute("erro", "Erro ao realizar solicitação. Tente novamente.");
            return "views/gerenciarSolicitacao";
        }
    }


    @PostMapping("solicitar")
    String solictacao(@RequestParam List<Integer> idMaterial, @RequestParam List<Integer> quantidade,
                      @RequestParam("id") int idOrigem, Model model) {

        Map<Integer, Integer> materiais = new HashMap<>();
        for (int i = 0; i < idMaterial.size(); i++) {
            if (quantidade.get(i) > 0) {
                materiais.put(idMaterial.get(i), quantidade.get(i));
            }
        }

        new SolicitacaoDAO().solicitarMaterial(materiais, idOrigem);

        boolean resp = true;
        if (resp == true) {
            model.addAttribute("sucesso", "Solicitação enviada para avaliação.");
        } else {
            model.addAttribute("erro", "Alguma coisa correu errado. Tente novamente.");
        }
        model.addAttribute("estoque", new EstoqueSetorDAO().getEstoqueSetorAtual(idOrigem));
        model.addAttribute("idUnidade", idOrigem);
        return "menuUsuario";
    }
}