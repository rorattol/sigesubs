package br.ufsm.controller;

import br.ufsm.dao.SetorDAO;
import br.ufsm.dao.UsuarioDAO;
import br.ufsm.model.Setor;
import br.ufsm.model.TipoUsuario;
import br.ufsm.model.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsuarioController{

    @RequestMapping("menuAdmin")
    String menuA(Model model){
        model.addAttribute("unidades", new SetorDAO().getSetor());
        return "menuAdmin";
    }

    @PostMapping("menuUsuario")
    String menuU(){
        //@RequestParam ("id") int id, Model model
       // model.addAttribute("estoque", new SetorDAO().getSetor());
        return "menuUsuario";
    }

    @RequestMapping("gerenciarUsuario")
    String index(){
        return "views/usuarioGerenciar";
    }

    @RequestMapping("adicionarUsuario")
    String adduser(Model model){
        model.addAttribute("unidades", new SetorDAO().getSetor());
        return "views/usuarioAdd";
    }

    @GetMapping("updateUser")
    String atualizarUsuario(@RequestParam("id") int idUser , Model model){
        model.addAttribute("unidades", new SetorDAO().getSetor());
        model.addAttribute("usuario", new UsuarioDAO().read(idUser));
        return "views/usuarioEdit";
    }

    @RequestMapping("visualizarPerfil")
    String visualizarPerfil(Model model){

        return "views/perfilVisualizar";
    }

    @RequestMapping("editarPerfil")
    String editPerfil(Model model){

        return "views/perfilEditar";
    }



    @PostMapping("updateUsuario")
    String atualizaUsuario(Usuario usuario, int idSetor, int tipoU, Model model){

        TipoUsuario tipoUser = new TipoUsuario();
        Setor s = new Setor();
        tipoUser.setIdTipoUsuario(tipoU);
        s.setIdSetor(idSetor);
        usuario.setSetor(s);
        usuario.setTipoUsuario(tipoUser);

        boolean retorno;
//        if (usuario.getSenhaUsuario() == null){
            retorno = new UsuarioDAO().updateSemSenha(usuario);
//        }
//        else {
//            retorno = new UsuarioDAO().updateComSenha(usuario);
//        }
        if (retorno) {
            model.addAttribute("sucesso", "Usuario Atualizado com sucesso.");
            return "views/usuarioGerenciar";
        } else {
            model.addAttribute("erro", "Não foi possivel atualizar.");
            return "views/usuarioGerenciar";
        }
    }

    @PostMapping("deleteUsuario")
    String deletarUsuario(Usuario usuario, Model model){

        System.out.println(usuario.getIdUsuario());
        boolean retorno = new UsuarioDAO().delete(usuario.getIdUsuario());

        if (retorno) {
            model.addAttribute("sucesso", "Usuario excluido com sucesso.");
            return "views/usuarioGerenciar";

        } else {
            model.addAttribute("erro", "Não foi possivel deletar.");
            return "views/usuarioGerenciar";
        }

}

    @PostMapping("addUsuario")
    String cadastrarUsuario(Usuario usuario, int idSetor, int tipoU, Model model){

        TipoUsuario tipoUser = new TipoUsuario();
        tipoUser.setIdTipoUsuario(tipoU);
        Setor s = new Setor();
        s.setIdSetor(idSetor);
        usuario.setSetor(s);
        usuario.setTipoUsuario(tipoUser);
        boolean retorno = new UsuarioDAO().create(usuario);

        if (retorno) {
            model.addAttribute("sucesso", "Usuario Cadastrado com sucesso.");
            return "views/usuarioGerenciar";

        } else {
            model.addAttribute("erro", "Não foi possivel cadastrar.");
            return "views/usuarioGerenciar";
        }
    }
}
