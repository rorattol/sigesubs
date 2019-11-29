package br.ufsm.dao;

import br.ufsm.model.Setor;
import br.ufsm.model.TipoUsuario;
import br.ufsm.model.Usuario;

import java.sql.*;
import java.util.ArrayList;

public class UsuarioDAO {

    String sql = "";
    PreparedStatement pre;
    ResultSet rs;
    boolean retorno = false;

    public boolean create(Usuario usuario) {
        try (Connection conn = new ConectDB_postgres().getConexao()) {

            sql = "INSERT INTO usuario(nome_usuario, login_usuario, senha_usuario, situacao_usuario, id_tipousuario, cod_setor) " +
                    "VALUES (?, ?, ? , ?, ?, ?);";
            pre = conn.prepareStatement(sql);
            pre.setString(1, usuario.getNomeUsuario());
            pre.setString(2, usuario.getLoginUsuario());
            pre.setString(3, usuario.getSenhaUsuario());
            pre.setBoolean(4, true);
            pre.setInt(5, usuario.getTipoUsuario().getIdTipoUsuario());
            pre.setInt(6, usuario.getSetor().getIdSetor());
            pre.execute();
            retorno = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return retorno;
    }

    public Usuario read(int id) {
        try (Connection conn = new ConectDB_postgres().getConexao()) {

            sql = "select u.id_usuario, u.nome_usuario, u.login_usuario, u.situacao_usuario, " +
                        "tu.id_tipousuario, tu.tipousuario, s.nome_setor, u.cod_setor\n" +
                            "from usuario u, tipo_usuario tu, setor s\n" +
                                "where u.id_tipousuario = tu.id_tipousuario and u.cod_setor = s.cod_setor and u.id_usuario = ?;\n ";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            rs = pre.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setNomeUsuario(rs.getString("nome_usuario"));
                u.setLoginUsuario(rs.getString("login_usuario"));
                u.setSituacao(rs.getBoolean("situacao_usuario"));
                TipoUsuario tu = new TipoUsuario();
                tu.setNomeTipoUsuario(rs.getString("tipousuario"));
                tu.setIdTipoUsuario(rs.getInt("id_tipousuario"));
                u.setTipoUsuario(tu);
                Setor s = new Setor();
                s.setIdSetor(rs.getInt("cod_setor"));
                s.setNomeSetor(rs.getString("nome_setor"));
                u.setSetor(s);
                return u;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Usuario read(String email, String senha) {
        try (Connection conn = new ConectDB_postgres().getConexao()) {

            sql = "SELECT u.*, s.nome_setor, tu.tipousuario " +
                    "FROM usuario u, tipo_usuario tu, setor s\n" +
                    "WHERE u.cod_setor = s.cod_setor " +
                    "AND u.id_tipousuario = tu.id_tipousuario " +
                    "AND login_usuario = ? AND senha_usuario = ?;";
            pre = conn.prepareStatement(sql);
            pre.setString(1, email);
            pre.setString(2, senha);
            rs = pre.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setNomeUsuario(rs.getString("nome_usuario"));
                u.setLoginUsuario(rs.getString("login_usuario"));
                u.setSenhaUsuario(rs.getString("senha_usuario"));
                TipoUsuario tu = new TipoUsuario();
                tu.setIdTipoUsuario(rs.getInt("id_tipousuario"));
                tu.setNomeTipoUsuario(rs.getString("tipousuario"));
                u.setTipoUsuario(tu);
                Setor s = new Setor();
                s.setIdSetor(rs.getInt("cod_setor"));
                s.setNomeSetor(rs.getString("nome_setor"));
                u.setSetor(s);
                return u;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean updateSemSenha(Usuario usuario) {
        try (Connection conn = new ConectDB_postgres().getConexao()) {

            sql = "UPDATE usuario SET nome_usuario = ?, login_usuario = ?, situacao_usuario = ?, id_tipousuario = ?, cod_setor = ? " +
                    "WHERE id_usuario = ?;";
            pre = conn.prepareStatement(sql);
            pre.setString(1, usuario.getNomeUsuario());
            pre.setString(2, usuario.getLoginUsuario());
            pre.setBoolean(3, true);
            pre.setInt(4, usuario.getTipoUsuario().getIdTipoUsuario());
            pre.setInt(5, usuario.getSetor().getIdSetor());
            pre.setInt(6, usuario.getIdUsuario());
            if (pre.executeUpdate() > 0) {
                retorno = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return retorno;
    }

    public boolean updateComSenha(Usuario usuario) {
        try (Connection conn = new ConectDB_postgres().getConexao()) {

            sql = "UPDATE usuario SET nome_usuario = ?, login_usuario = ?, senha_usuario = ?, situacao_usuario = ?, id_tipousuario = ?, cod_setor = ? WHERE id_usuario = ?;";
            pre = conn.prepareStatement(sql);
            pre.setString(1, usuario.getNomeUsuario());
            pre.setString(2, usuario.getLoginUsuario());
            pre.setString(3, usuario.getSenhaUsuario());
            pre.setBoolean(4, true);
            pre.setInt(5, usuario.getTipoUsuario().getIdTipoUsuario());
            pre.setInt(6, usuario.getSetor().getIdSetor());
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
            rs = stmt.executeQuery("select u.id_usuario, u.nome_usuario,  u.senha_usuario, u.login_usuario, " +
                                            "u.situacao_usuario, tu.tipousuario, s.nome_setor\n" +
                                                "from usuario u, tipo_usuario tu, setor s\n" +
                                                    "where u.id_tipousuario = tu.id_tipousuario and u.cod_setor = s.cod_setor;");

            while (rs.next()) {

                Usuario usu = new Usuario();
                usu.setIdUsuario(rs.getInt("id_usuario"));
                usu.setNomeUsuario(rs.getString("nome_usuario"));
                usu.setSenhaUsuario(rs.getString("senha_usuario"));
                usu.setLoginUsuario(rs.getString("login_usuario"));
                usu.setSituacao(rs.getBoolean("situacao_usuario"));
                TipoUsuario tu = new TipoUsuario();
                tu.setNomeTipoUsuario(rs.getString("tipousuario"));
                usu.setTipoUsuario(tu);
                Setor s = new Setor();
                s.setNomeSetor(rs.getString("nome_setor"));
                usu.setSetor(s);
                usuarios.add(usu);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return usuarios;
    }
}