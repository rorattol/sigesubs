package br.ufsm.dao;

import br.ufsm.model.Material;
import br.ufsm.model.Setor;
import br.ufsm.model.Solicitacao;
import br.ufsm.model.Usuario;
import br.ufsm.model.util.MaterialQuantidade;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SolicitacaoDAO {

    String sql = "";
    PreparedStatement pre;
    ResultSet rs;
    boolean retorno = false;

    public boolean udpate(Solicitacao sol){
        try (Connection conn = new ConectDB_postgres().getConexao()) {

            sql = "UPDATE solicitacao SET observacao = ?, status_solicitacao = ? " +
                    "WHERE id_solicitacao = ?;";
            pre = conn.prepareStatement(sql);
            pre.setString(1, sol.getObservacao());
            pre.setString(2, sol.getStatusSolicitacao());
            pre.setInt(3, sol.getId());
            if (pre.executeUpdate() > 0) {
                retorno = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return retorno;
    }

    //busca os materiais que constam em uma determinada solicitacao
    public ArrayList<Material> getMateriaisSolicitacao(int id) {
        ArrayList<Material> materiais = new ArrayList<>();

        try (Connection conn = new ConectDB_postgres().getConexao()) {

            sql = "SELECT m.*, ms.quantidade " +
                    "FROM material_solicitacao ms, material m " +
                    "WHERE ms.id_material = m.id_material " +
                    "AND id_solicitacao = ?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            rs = pre.executeQuery();

            Solicitacao sol = new Solicitacao();
            while (rs.next()) {

                Material mat = new Material();
                mat.setIdMaterial(rs.getInt("id_material"));
                mat.setNomeMaterial(rs.getString("nome_material"));
                mat.setUnidadeMedida(rs.getString("unidade_medida"));
                mat.setQuantidade(rs.getInt("quantidade"));
                sol.adicionarMaterial(mat);

                materiais.add(mat);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return materiais;
    }

    //busca uma solicitacao pelo identicador dela
    public Solicitacao read(int id) {

        try (Connection conn = new ConectDB_postgres().getConexao()) {
            sql = "SELECT sol.id_solicitacao , sol.observacao, sol.data_solicitacao, sol.status_solicitacao, " +
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
                solic.setId(rs.getInt("id_solicitacao"));
                solic.setObservacao(rs.getString("observacao"));
                solic.setDataSolicitacao(rs.getDate("data_solicitacao"));
                solic.setStatusSolicitacao(rs.getString("status_solicitacao"));

                return solic;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    //busca o historico de solicitações que já foram avaliadas
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

    //busca o historico de solicitações que já foram avaliadas pelo cod_setor
    public ArrayList<Solicitacao> getHistoricoSolicitacoes(int id) {
        ArrayList<Solicitacao> solicitacoes = new ArrayList<>();

        try (Connection conn = new ConectDB_postgres().getConexao()) {

            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(
                    "SELECT sol.id_solicitacao, sol.status_solicitacao, " +
                            "s.nome_setor, u.id_usuario, u.nome_usuario " +
                            "FROM solicitacao sol, setor s, usuario u " +
                            "WHERE u.cod_setor =  s.cod_setor AND sol.usuario_responsavel = u.id_usuario " +
                            "AND sol.status_solicitacao != 'pendente'AND s.cod_setor = ?;");
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
                solic.setStatusSolicitacao(rs.getString("status_solicitacao"));

                solicitacoes.add(solic);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return solicitacoes;
    }

    //busca todas as solicitações que foram feitas no sistema com status de pendente
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

    //busca detalhes da solicitação pelo id dela.
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



    public void solicitarMaterial(Map<Integer, Integer> materiais, int idUsuarioOrigem) {
        try (Connection conn = new ConectDB_postgres().getConexao()) {
            conn.setAutoCommit(false);

            String sql = "INSERT INTO solicitacao(status_solicitacao, observacao, usuario_responsavel) VALUES ('pendente', 'sem observações', ?)";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, idUsuarioOrigem);
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int idSol = generatedKeys.getInt(1);

                    materiais.forEach((idMaterial, quantidade) -> {
                        String sql1 = "INSERT INTO material_solicitacao(id_solicitacao, id_material, quantidade) VALUES (?, ?, ?)";
                        try {
                            PreparedStatement stmt1 = conn.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
                            stmt1.setInt(1, idSol);
                            stmt1.setInt(2, idMaterial);
                            stmt1.setInt(3, quantidade);

                            stmt1.executeUpdate();
                        } catch (SQLException e) {
                            e.printStackTrace();
                            return;
                        }
                    });
                    conn.commit();
                } else {
                    throw new SQLException("erro");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
