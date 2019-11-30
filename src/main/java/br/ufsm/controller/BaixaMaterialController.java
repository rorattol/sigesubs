package br.ufsm.controller;

import br.ufsm.dao.EstoqueSetorDAO;
import br.ufsm.dao.MaterialDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BaixaMaterialController {

    @RequestMapping("/darBaixa")
    String index(@RequestParam("id") int id,Model model){
        model.addAttribute("materiais", new EstoqueSetorDAO().getEstoqueSetorAtual(id));
        return "views/darBaixaMateriais";
    }

    @PostMapping("materiaisUsados")
    String darbaixa(@RequestParam List<Integer> idMaterial, @RequestParam List<Integer> quantidade,
                     int idSetor, Model model){

        Map<Integer, Integer> materiais = new HashMap<>();
        for(int i = 0; i < idMaterial.size(); i++) {
            if(quantidade.get(i) > 0) {
                materiais.put(idMaterial.get(i), -quantidade.get(i) );
            }
        }
        materiais.forEach((k, v) -> System.out.println("id: " + k + " quantidade: "+ v));

        new MaterialDAO().darBaixa(materiais, idSetor);

        model.addAttribute("sucesso", "Estoque atual atualizado.");
        model.addAttribute("estoque", new EstoqueSetorDAO().getEstoqueSetorAtual(idSetor));
        return "menuUsuario";
    }
}
