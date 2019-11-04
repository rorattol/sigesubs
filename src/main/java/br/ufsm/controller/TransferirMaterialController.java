package br.ufsm.controller;


import br.ufsm.dao.SetorDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TransferirMaterialController {

    @RequestMapping("transferirMaterial")
    String index(Model model){
        model.addAttribute("unidades", new SetorDAO().getSetor());
        return "views/transferirMaterial";
    }

    @RequestMapping("transfMat")
    String transferMateriais(){

        return "views/transferirUnidade";
    }
}
