package br.ufsm.controller;

import br.ufsm.dao.LoginDAO;
import br.ufsm.dao.SetorDAO;
import br.ufsm.dao.UsuarioDAO;
import br.ufsm.model.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpSession;


@Controller
public class LoginController{
@GetMapping ("/")
String index(){
    return "index";
}

    @PostMapping("login")
    String logar(Usuario usuario, HttpSession sessao, Model model){

        boolean autenticado = new LoginDAO().autenticarUsuario(usuario.getLoginUsuario(), usuario.getSenhaUsuario());
        System.out.println(usuario.getLoginUsuario() + " - " + usuario.getSenhaUsuario());

        if(autenticado){
            usuario = new UsuarioDAO().read(usuario.getLoginUsuario(), usuario.getSenhaUsuario());
            var unidades = new SetorDAO().getSetor();

            sessao.setAttribute("logado", usuario);
            model.addAttribute("logado", usuario);
            model.addAttribute("unidades", unidades);

            if(usuario.getTipoUsuario().getIdTipoUsuario() == 1){  //usuario comum
                System.out.println(usuario.getTipoUsuario().getIdTipoUsuario());
                System.out.println("menuUsuario");
                return "menuUsuario";
            }
            else{ //usuario admin
                System.out.println("menuAdmin");
                return "menuAdmin";
            }
        }
        else{
            model.addAttribute("mensagem", "usuario ou senha incorretos");
            return "index";
        }
    }


    @GetMapping("logout")
    String deslogar(Usuario usuario, HttpSession sessao){
        sessao.removeAttribute("logado");

        System.out.println("deslogado");
        return "index";
    }
}