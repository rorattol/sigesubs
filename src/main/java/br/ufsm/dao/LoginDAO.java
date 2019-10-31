package br.ufsm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    String sql = "";
    PreparedStatement pre;
    ResultSet rs;
    boolean retorno = false;

    public boolean autenticarUsuario(String login, String senha) {

        try (Connection conn = new ConectDB_postgres().getConexao()) {

            sql = "SELECT * FROM usuario WHERE senha_usuario = ? and login_usuario = ?;";
            pre = conn.prepareStatement(sql);
            pre.setString(1, senha);
            pre.setString(2, login);

            rs = pre.executeQuery();
            while (rs.next()) {
                retorno = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return retorno;
    }



}
