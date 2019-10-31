package br.ufsm.dao;

import br.ufsm.model.Material;

import java.sql.*;
import java.util.ArrayList;

public class MaterialDAO {
    String sql = "";
    PreparedStatement pre;
    ResultSet rs;
    boolean retorno = false;

    public boolean create(Material material) {
        try (Connection conn = new ConectDB_postgres().getConexao()) {

            sql = "INSERT INTO material(nome_material, unidade_medida, id_categoria) " +
                    "VALUES (?, ?, ?);";
            pre = conn.prepareStatement(sql);
            pre.setString(1, material.getNomeMaterial());
            pre.setString(2, material.getUnidadeMedida());
            pre.setInt(3, material.getCategoria());
            pre.execute();

            retorno = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return retorno;
    }

    public Material read(int id) {
        try (Connection conn = new ConectDB_postgres().getConexao()) {

            sql = "SELECT * FROM material WHERE id_material = ?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            rs = pre.executeQuery();
            while (rs.next()) {
                Material m = new Material();
                m.setIdMaterial(rs.getInt("nome_material"));
                m.setNomeMaterial(rs.getString("unidade_medida"));
                m.setCategoria(rs.getInt("id_categoria"));
                return m;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean update (Material material) {
        try (Connection conn = new ConectDB_postgres().getConexao()) {

            sql = "UPDATE material SET nome_material = ?, unidade_medida = ?, id_categoria = ? WHERE id_material = ?;";
            pre = conn.prepareStatement(sql);
            pre.setString(1, material.getNomeMaterial());
            pre.setString(2, material.getUnidadeMedida());
            pre.setInt(3, material.getCategoria());
            pre.setInt(4, material.getIdMaterial());
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
            sql = "DELETE FROM material WHERE id_material = ?";
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

    public ArrayList<Material> getMateriais() {
        ArrayList<Material> materiais = new ArrayList<>();

        try (Connection conn = new ConectDB_postgres().getConexao()) {

            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM material");

            while (rs.next()) {

                Material mat = new Material();
                mat.setIdMaterial(rs.getInt("id_material"));
                mat.setNomeMaterial(rs.getString("nome_material"));
                mat.setUnidadeMedida(rs.getString("unidade_medida"));
                mat.setCategoria(rs.getInt("id_categoria"));
                materiais.add(mat);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return materiais;
    }
}
