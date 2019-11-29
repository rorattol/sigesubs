package br.ufsm.dao;

import br.ufsm.model.EstoqueSetor;
import br.ufsm.model.Material;
import br.ufsm.model.Setor;

import java.sql.*;
import java.util.ArrayList;

public class EstoqueSetorDAO {

    String sql = "";
    PreparedStatement pre;
    ResultSet rs;
    boolean retorno = false;

    //cria o estoque de uma unidade
    public boolean create(EstoqueSetor estoque) {



        return retorno;
    }

    //atualiza o estoque de uma unidade com a quantidade de materiais
    public boolean update (EstoqueSetor material) {

        return retorno;
    }


    //busca dados do estoque de um determinado setor com estoque zerado
    public ArrayList<EstoqueSetor> getEstoqueSetor(int id) {
        ArrayList<EstoqueSetor> almox = new ArrayList<>();

        try (Connection conn = new ConectDB_postgres().getConexao()) {

            sql = "SELECT mat.id_material, mat.*, null as quantidade, null as cod_setor FROM material mat " +
                    "    where not exists (select * from setor_material se " +
                    "           where se.id_material = mat.id_material " +
                    "                     and se.cod_setor = ?) union" +
                    "                 SELECT  mat.id_material, mat.*, se.quantidade, se.cod_setor" +
                    "                    FROM setor_material se inner JOIN material mat" +
                    "                             ON mat.id_material = se.id_material" +
                    "                           WHERE se.cod_setor = ?;";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            pre.setInt(2, id);
            rs = pre.executeQuery();

            while (rs.next()) {
                EstoqueSetor estoque = new EstoqueSetor();

                Setor setor = new Setor();
                setor.setIdSetor(rs.getInt("cod_setor"));
                estoque.setSetor(setor);
                estoque.setQtdEstoque(rs.getInt("quantidade"));
                Material material = new Material();
                material.setIdMaterial(rs.getInt("id_material"));
                material.setNomeMaterial(rs.getString("nome_material"));
                material.setUnidadeMedida(rs.getString("unidade_medida"));
                estoque.setMaterial(material);

                almox.add(estoque);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return almox;
    }

    //pegar o estoque atual por um determinado id_solicitacao
    public ArrayList<EstoqueSetor> getEstoqueAtual(int id) {
        ArrayList<EstoqueSetor> almox = new ArrayList<>();

        try (Connection conn = new ConectDB_postgres().getConexao()) {

            sql = "select se.* from setor_material se, material m, material_solicitacao s" +
                    "  where se.id_material = m.id_material and m.id_material = s.id_material " +
                    "and s.id_solicitacao = ?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            rs = pre.executeQuery();

            while (rs.next()) {
                EstoqueSetor estoque = new EstoqueSetor();

                estoque.setQtdEstoque(rs.getInt("quantidade"));
                almox.add(estoque);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return almox;

    }


    public ArrayList<EstoqueSetor> getEstoqueSetorAtual(int id) {
        ArrayList<EstoqueSetor> almox = new ArrayList<>();

        try (Connection conn = new ConectDB_postgres().getConexao()) {

            sql = "SELECT se.quantidade, mat.*, s.nome_setor, s.cod_setor\n" +
                    "FROM setor_material se, material mat, setor s\n" +
                    "WHERE mat.id_material = se.id_material\n" +
                    "  AND s.cod_setor = se.cod_setor AND se.cod_setor = ?;";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            rs = pre.executeQuery();

            while (rs.next()) {
                EstoqueSetor estoque = new EstoqueSetor();

                Setor setor = new Setor();
                setor.setIdSetor(rs.getInt("cod_setor"));
                setor.setNomeSetor(rs.getString("nome_setor"));
                estoque.setSetor(setor);
                estoque.setQtdEstoque(rs.getInt("quantidade"));
                Material material = new Material();
                material.setIdMaterial(rs.getInt("id_material"));
                material.setNomeMaterial(rs.getString("nome_material"));
                material.setUnidadeMedida(rs.getString("unidade_medida"));
                estoque.setMaterial(material);

                almox.add(estoque);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return almox;
    }
}


