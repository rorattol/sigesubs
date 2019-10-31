package br.ufsm.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TransferirMaterialController {

    @RequestMapping("transferirMaterial")
    String index(){
        return "views/transferirMaterial";
    }

    @RequestMapping("transfMat")
    String tranferMateriais(Model model){

        return "views/trasferirUnidade";
    }
}
