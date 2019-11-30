package br.ufsm.controller;


import br.ufsm.dao.EstoqueSetorDAO;
import br.ufsm.dao.MaterialDAO;
import br.ufsm.dao.SetorDAO;
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
public class TransferirMaterialController {

    @RequestMapping("transferirMaterial")
    String index(Model model){
        model.addAttribute("unidades", new SetorDAO().getSetor());
        return "views/transferirMaterial";
    }

    @GetMapping("transfMat")
    String transferMateriais(@RequestParam("id") int idUnidade , Model model){
        model.addAttribute("materiais", new MaterialDAO().getMateriais());
        model.addAttribute("idUnidade" ,idUnidade);
      //  model.addAttribute("unidade", new SetorDAO().getSetor(idUnidade));
        model.addAttribute("estoque", new EstoqueSetorDAO().getEstoqueSetor(idUnidade));

        return "views/transferirUnidade";
    }

    @PostMapping("transferir")
    String tranferir(@RequestParam List<Integer> idMaterial, @RequestParam List<Integer> quantidade, @RequestParam int idSetorDestino, Model model) {

        Map<Integer, Integer> materiais = new HashMap<>();
        for(int i = 0; i < idMaterial.size(); i++) {
            if(quantidade.get(i) > 0) {
                materiais.put(idMaterial.get(i), quantidade.get(i));
            }
        }

        new MaterialDAO().movimentar(materiais, idSetorDestino);
        new MaterialDAO().transferir(materiais, idSetorDestino);

        model.addAttribute("sucesso", "Transferencia realizada com sucesso");

        return "menuAdmin";
    }

    @PostMapping("transferirSolicitacao")
    String tranferirSol(@RequestParam List<Integer> idMaterial, @RequestParam List<Integer> quantidade,
                     @RequestParam int idSetorDestino, @RequestParam int idSol, Model model) {

        Map<Integer, Integer> materiais = new HashMap<>();
        for(int i = 0; i < idMaterial.size(); i++) {
            if(quantidade.get(i) > 0) {
                materiais.put(idMaterial.get(i), quantidade.get(i));
            }
        }
        new MaterialDAO().movimentarSolicitacao(materiais, idSetorDestino, idSol);
        new MaterialDAO().transferirSolicitacao(materiais, idSetorDestino, idSol);

        model.addAttribute("sucesso", "Transferencia realizada com sucesso");
        model.addAttribute("unidades", new SetorDAO().getSetor());
        return "menuAdmin";
    }
}
