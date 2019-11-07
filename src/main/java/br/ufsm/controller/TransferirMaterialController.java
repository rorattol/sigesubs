package br.ufsm.controller;


import br.ufsm.dao.EstoqueSetorDAO;
import br.ufsm.dao.MaterialDAO;
import br.ufsm.dao.SetorDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TransferirMaterialController {

    @RequestMapping("transferirMaterial")
    String index(Model model){
        model.addAttribute("unidades", new SetorDAO().getSetor());
        return "views/transferirMaterial";
    }

    @GetMapping("transfMat")
    String transferMateriais(@RequestParam("id") int idUnidade , Model model){
        model.addAttribute("materiais", new EstoqueSetorDAO().getEstoqueSetor(idUnidade));

        return "views/transferirUnidade";
    }
}
