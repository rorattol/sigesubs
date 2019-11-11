package br.ufsm.dao;

import br.ufsm.model.Setor;
import br.ufsm.model.Solicitacao;
import br.ufsm.model.Usuario;

import java.sql.*;
import java.util.ArrayList;

public class SolicitacaoDAO {

    String sql = "";
    PreparedStatement pre;
    ResultSet rs;
    boolean retorno = false;


    public ArrayList<Solicitacao> getHistoricoSolicitacoes(int id) {
        ArrayList<Solicitacao> solicitacoes = new ArrayList<>();

        try (Connection conn = new ConectDB_postgres().getConexao()) {
            sql = "SELECT sol.observacao, sol.data_solicitacao, sol.status_solicitacao, " +
                    "s.nome_setor, u.nome_usuario " +
                    "FROM solicitacao sol, setor s, usuario u " +
                    "WHERE u.cod_setor =  s.cod_setor AND sol.usuario_responsavel = u.id_usuario " +
                    "AND sol.id_solicitacao = ?;";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            rs = pre.executeQuery();

            while (rs.next()) {
                Solicitacao solic = new Solicitacao();

                Setor setor = new Setor();
                setor.setNomeSetor(rs.getString("nome_setor"));
                solic.setSetorSolicitante(setor);
                Usuario usu = new Usuario();
                usu.setNomeUsuario(rs.getString("nome_usuario"));
                solic.setUsuarioSolicitante(usu);
                solic.setObservacao(rs.getString("observacao"));
                solic.setDataSolicitacao(rs.getDate("data_solicitacao"));
                solic.setStatusSolicitacao(rs.getString("status_solicitacao"));

                solicitacoes.add(solic);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return solicitacoes;
    }

    public ArrayList<Solicitacao> getHistoricoSolicitacoes() {
        ArrayList<Solicitacao> solicitacoes = new ArrayList<>();

        try (Connection conn = new ConectDB_postgres().getConexao()) {

            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(
                    "SELECT sol.id_solicitacao, sol.data_solicitacao, sol.status_solicitacao, " +
                            "s.nome_setor, u.id_usuario, u.nome_usuario " +
                            "FROM solicitacao sol, setor s, usuario u " +
                            "WHERE u.cod_setor =  s.cod_setor AND sol.usuario_responsavel = u.id_usuario " +
                            "AND sol.status_solicitacao != 'pendente';");

            while (rs.next()) {
                Solicitacao solic = new Solicitacao();

                Setor setor = new Setor();
                setor.setNomeSetor(rs.getString("nome_setor"));
                solic.setSetorSolicitante(setor);
                Usuario usu = new Usuario();
                usu.setIdUsuario(rs.getInt("id_usuario"));
                usu.setNomeUsuario(rs.getString("nome_usuario"));
                solic.setUsuarioSolicitante(usu);
                solic.setId(rs.getInt("id_solicitacao"));
                solic.setDataSolicitacao(rs.getDate("data_solicitacao"));
                solic.setStatusSolicitacao(rs.getString("status_solicitacao"));

                solicitacoes.add(solic);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return solicitacoes;
    }

    public ArrayList<Solicitacao> getSolicitacoesTodas() {
        ArrayList<Solicitacao> solicitacoes = new ArrayList<>();

        try (Connection conn = new ConectDB_postgres().getConexao()) {

            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(
                "SELECT sol.id_solicitacao, sol.data_solicitacao, sol.observacao, sol.status_solicitacao, s.cod_setor, " +
                      "s.nome_setor, u.id_usuario, u.nome_usuario " +
                       "FROM solicitacao sol, setor s, usuario u " +
                        "WHERE u.cod_setor =  s.cod_setor AND sol.usuario_responsavel = u.id_usuario " +
                        "AND sol.status_solicitacao = 'pendente';");

            while (rs.next()) {
                Solicitacao solic = new Solicitacao();

                Setor setor = new Setor();
                setor.setIdSetor(rs.getInt("cod_setor"));
                setor.setNomeSetor(rs.getString("nome_setor"));
                solic.setSetorSolicitante(setor);
                Usuario usu = new Usuario();
                usu.setIdUsuario(rs.getInt("id_usuario"));
                usu.setNomeUsuario(rs.getString("nome_usuario"));
                solic.setUsuarioSolicitante(usu);
                solic.setId(rs.getInt("id_solicitacao"));
                solic.setDataSolicitacao(rs.getDate("data_solicitacao"));
                solic.setObservacao(rs.getString("observacao"));
                solic.setStatusSolicitacao(rs.getString("status_solicitacao"));

                solicitacoes.add(solic);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return solicitacoes;
    }

    public ArrayList<Solicitacao> getSolicitacoes(int id) {
        ArrayList<Solicitacao> solicitacoes = new ArrayList<>();

        try (Connection conn = new ConectDB_postgres().getConexao()) {

            sql = "SELECT sol.data_solicitacao, sol.observacao, sol.status_solicitacao, s.cod_setor, s.nome_setor," +
                    "      sol.id_solicitacao, u.id_usuario, u.nome_usuario " +
                    "FROM solicitacao sol, setor s, usuario u " +
                    "WHERE u.cod_setor =  s.cod_setor AND sol.usuario_responsavel = u.id_usuario " +
                    "AND sol.id_solicitacao = ?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            rs = pre.executeQuery();

            while (rs.next()) {
                Solicitacao solic = new Solicitacao();

                Setor setor = new Setor();
                setor.setIdSetor(rs.getInt("cod_setor"));
                setor.setNomeSetor(rs.getString("nome_setor"));
                solic.setSetorSolicitante(setor);
                Usuario usu = new Usuario();
                usu.setIdUsuario(rs.getInt("id_usuario"));
                usu.setNomeUsuario(rs.getString("nome_usuario"));
                solic.setUsuarioSolicitante(usu);
                solic.setId(rs.getInt("id_solicitacao"));
                solic.setDataSolicitacao(rs.getDate("data_solicitacao"));
                solic.setObservacao(rs.getString("observacao"));
                solic.setStatusSolicitacao(rs.getString("status_solicitacao"));

                solicitacoes.add(solic);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return solicitacoes;
    }
}
