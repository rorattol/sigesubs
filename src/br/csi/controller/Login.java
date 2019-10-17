package br.csi.controller;

import br.csi.dao.LoginDAO;
import br.csi.dao.UsuarioDAO;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/login")
public class Login extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login"); //nome do name='login'
        String senha = req.getParameter("senha");

        boolean autenticado = new LoginDAO().autenticarUsuario(login, senha);
        System.out.println(login + " - "+ senha);

        RequestDispatcher disp;

        if(autenticado){
            HttpSession sessao = req.getSession();
            sessao.setAttribute("logado", new UsuarioDAO().read(login, senha));
            disp = req.getRequestDispatcher("/WEB-INF/views/reservarMesa.jsp");
            disp.forward(req, resp);
        }
        else{
            req.setAttribute("mensagem", "usuario ou senha incorretos");
            disp = req.getRequestDispatcher("loginCliente.jsp");
            disp.forward(req, resp);

        }
    }
}