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

    public boolean create(EstoqueSetor estoque) {



        return retorno;
    }

    public boolean update (EstoqueSetor material) {

        return retorno;
    }

    public boolean delete(int id) {

        return retorno;
    }

    public ArrayList<EstoqueSetor> getEstoqueSetor(int id) {
        ArrayList<EstoqueSetor> almox = new ArrayList<>();

        try (Connection conn = new ConectDB_postgres().getConexao()) {

            sql = "SELECT se.quantidade, mat.id_material, mat.nome_material, mat.unidade_medida, s.nome_setor, s.cod_setor\n" +
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
                material.setIdMaterial(rs.getInt(1));
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


