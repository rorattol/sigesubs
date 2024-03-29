package br.ufsm.controller;


import br.ufsm.dao.MaterialDAO;
import br.ufsm.model.Material;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MaterialController {

    @RequestMapping("/gerenciarMaterial")
    String index(){
        return "views/materialGerenciar";
    }

    @RequestMapping("/adicionarMaterial")
    String addmat(){
        return "views/materialAdd";
    }

    @GetMapping("updateMat")
    String atualizarUsuario(@RequestParam("id") int idMat , Model model){
        model.addAttribute("material", new MaterialDAO().read(idMat));
        return "views/materialEdit";
    }

    @PostMapping("updateMaterial")
    String atualizaUsuario(Material material, Model model){

        boolean retorno;
        retorno = new MaterialDAO().update(material);

        if (retorno) {
            model.addAttribute("sucesso", "Material atualizado com sucesso.");
            return "views/materialGerenciar";
        } else {
            model.addAttribute("erro", "Não foi possível atualizar o material.");
            return "views/materialGerenciar";
        }
    }

    @PostMapping("deleteMaterial")
    String deletarMaterial(Material material, Model model){

        System.out.println(material.getIdMaterial());
        boolean retorno = new MaterialDAO().delete(material.getIdMaterial());

        if (retorno) {
            model.addAttribute("sucesso", "Material excluído com sucesso.");
            return "views/materialGerenciar";

        } else {
            model.addAttribute("erro", "Não foi possível deletar este material.");
            return "views/materialGerenciar";
        }
    }

    @PostMapping("materialadd")
    String cadastrarMaterial(Material material, Model model){

        boolean retorno = new MaterialDAO().create(material);

        if (retorno) {
            model.addAttribute("sucesso", "Material Cadastrado com sucesso.");
            return "views/materialGerenciar";

        } else {
            model.addAttribute("erro", "Não foi possivel cadastrar esse material.");
            return "views/materialGerenciar";
        }
    }

}
