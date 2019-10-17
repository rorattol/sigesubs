package br.csi.dao;

import br.csi.model.Setor;
import br.csi.model.Usuario;

import java.sql.*;
import java.util.ArrayList;

public class UsuarioDAO {

    String sql = "";
    PreparedStatement pre;
    ResultSet rs;
    boolean retorno = false;

    public boolean create(String nomeUsuario, String loginUsuario, String senhaUsuario, boolean situacao, String tipoUsuario, Setor setor) {
        try (Connection conn = new ConectDB_postgres().getConexao()) {

            sql = "INSERT INTO usuario(nome_usuario, login_usuario, senha_usuario, situacao_usuario, id_tipousuario, cod_setor) " +
                    "VALUES (?, ?, ? , ?, ?, ?);";
            pre = conn.prepareStatement(sql);
            pre.setString(1, nomeUsuario);
            pre.setString(2, loginUsuario);
            pre.setString(3, senhaUsuario);
            pre.setBoolean(4, situacao);
            pre.setString(5, tipoUsuario);
         //   pre.setString(6, setor);
            pre.execute();

            retorno = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return retorno;
    }

    public Usuario read(int id) {
        try (Connection conn = new ConectDB_postgres().getConexao()) {

            sql = "SELECT * FROM cliente WHERE id_cliente = ?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            rs = pre.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setLoginUsuario(rs.getString("login_usuario"));
                u.setSenhaUsuario(rs.getString("senha_usuario"));
                u.setSituacao(rs.getBoolean("situacao_usuario"));
                u.setTipoUsuario(rs.getString("id_tipousuario"));
                //u.setSetor(rs.getString("cod_setor"));
                return u;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Usuario read(String email, String senha) {
        try (Connection conn = new ConectDB_postgres().getConexao()) {

            sql = "SELECT * FROM usuario WHERE login_usuario = ? and senha_usuario = ?;";
            pre = conn.prepareStatement(sql);
            pre.setString(1, email);
            pre.setString(2, senha);
            rs = pre.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setNomeUsuario(rs.getString("nome_usuario"));
                u.setLoginUsuario(rs.getString("login_usuario"));
                u.setSenhaUsuario(rs.getString("senha_usuario"));
                return u;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean update(Usuario usuario) {
        try (Connection conn = new ConectDB_postgres().getConexao()) {

            sql = "UPDATE usuario SET nome_usuario = ?, login_usuario = ?, senha_usuario = ?, situacao_usuario = ?, id_tipousuario, cod_setor = ? WHERE id_usuario = ?;";
            pre = conn.prepareStatement(sql);
            pre.setString(1, usuario.getNomeUsuario());
            pre.setString(2, usuario.getLoginUsuario());
            pre.setString(3, usuario.getSenhaUsuario());
            pre.setBoolean(4, usuario.isSituacao());
//            pre.setString(5, usuario.getSetor());
            pre.setInt(6, usuario.getIdUsuario());
            if (pre.executeUpdate() > 0) {
                retorno = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return retorno;
    }

    public boolean delete(int id) {
        try (Connection conn = new ConectDB_postgres().getConexao()) {
            sql = "DELETE FROM usuario WHERE id_usuario = ?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            if (pre.executeUpdate() > 0) {
                retorno = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return retorno;
    }

    public ArrayList<Usuario> getUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<>();

        try (Connection conn = new ConectDB_postgres().getConexao()) {

            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM usuario");

            while (rs.next()) {

                Usuario usu = new Usuario();
                usu.setIdUsuario(rs.getInt("id_usuario"));
                usu.setNomeUsuario(rs.getString("nome_usuario"));
                usu.setLoginUsuario(rs.getString("login_usuario"));
                usu.setSenhaUsuario(rs.getString("senha_usuario"));
                usu.setSituacao(rs.getBoolean("situacao_usuario"));
                usu.setTipoUsuario(rs.getString("id_tipousuario"));
                usuarios.add(usu);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return usuarios;
    }
}