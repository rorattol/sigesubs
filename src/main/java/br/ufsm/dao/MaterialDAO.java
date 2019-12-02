package br.ufsm.dao;

import br.ufsm.model.Material;
import br.ufsm.model.Solicitacao;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import org.apache.jasper.util.FastRemovalDequeue;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

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
                m.setIdMaterial(rs.getInt("id_material"));
                m.setNomeMaterial(rs.getString("nome_material"));
                m.setUnidadeMedida(rs.getString("unidade_medida"));
                m.setCategoria(rs.getInt("id_categoria"));
                return m;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean update(Material material) {
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

    public void movimentar(Map<Integer, Integer> materiais, int idSetorDestino) {

        try (Connection conn = new ConectDB_postgres().getConexao()) {
            conn.setAutoCommit(false);

            String sql = "INSERT INTO movimentacao(id_solicitacao, almox_destino) VALUES (null, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            stmt.setInt(1, idSolicitacao);
            stmt.setInt(1, idSetorDestino);
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int idMov = generatedKeys.getInt(1);

                    materiais.forEach((idMaterial, quantidade) -> {
                        String sql1 = "INSERT INTO material_movimentacao (id_material, quantidade, id_movimentacao) VALUES (?, ?, ?)";
                        try {
                            PreparedStatement stmt1 = conn.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
                            stmt1.setInt(1, idMaterial);
                            stmt1.setInt(2, quantidade);
                            stmt1.setInt(3, idMov);

                            stmt1.executeUpdate();
                        } catch (SQLException e) {
                            e.printStackTrace();
                            return;
                        }
                    });
                    conn.commit();
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void movimentarSolicitacao(Map<Integer, Integer> materiais, int idSetorDestino, int idSolicitacao) {

        try (Connection conn = new ConectDB_postgres().getConexao()) {
            conn.setAutoCommit(false);

            String sql = "INSERT INTO movimentacao(id_solicitacao, almox_destino) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, idSolicitacao);
            stmt.setInt(2, idSetorDestino);
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int idMov = generatedKeys.getInt(1);

                    materiais.forEach((idMaterial, quantidade) -> {
                        String sql1 = "INSERT INTO material_movimentacao (id_material, quantidade, id_movimentacao) VALUES (?, ?, ?)";
                        try {
                            PreparedStatement stmt1 = conn.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
                            stmt1.setInt(1, idMaterial);
                            stmt1.setInt(2, quantidade);
                            stmt1.setInt(3, idMov);

                            stmt1.execute();
                        } catch (SQLException e) {
                            e.printStackTrace();
                            return;
                        }
                    });
                    conn.commit();
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void transferir(Map<Integer, Integer> materiais, int idSetorDestino) {
        try (Connection conn = new ConectDB_postgres().getConexao()) {
            conn.setAutoCommit(false);

            String sql = "INSERT INTO movimentacao(id_solicitacao, almox_destino) VALUES (null, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idSetorDestino);
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {

                if (true) {


//                    DO $$ BEGIN
                    materiais.forEach((idMaterial, quantidade) -> {
//                        upsert_setor_material(cod_unidade int, id_mat int, qtd int)
                        //String sql1 = "DO $$ BEGIN PERFORM upsert_setor_material(?, ?, ?); END $$";
                        String sql1 = "SELECT upsert_setor_material(?, ?, ?);";
                        try {
                            CallableStatement stmt1 = conn.prepareCall(sql1);
                            stmt1.setInt(1, idSetorDestino);
                            stmt1.setInt(2, idMaterial);
                            stmt1.setInt(3, quantidade);

                            stmt1.execute();
                        } catch (SQLException e) {
                            e.printStackTrace();
                            return;
                        }
                    });
//
                    conn.commit();
                } else {
                    throw new SQLException("Erro");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void darBaixa(Map<Integer, Integer> materiais, int idSetor) {
        try (Connection conn = new ConectDB_postgres().getConexao()) {
            conn.setAutoCommit(false);
            if (true) {
                materiais.forEach((idMaterial, quantidade) -> {
//                        upsert_setor_material(cod_unidade int, id_mat int, qtd int)
//                        String sql1 = "DO $$ BEGIN PERFORM upsert_setor_material(?, ?, ?); END $$";
                    String sql1 = "SELECT upsert_setor_material(?, ?, ?);";
                    try {
                        CallableStatement stmt1 = conn.prepareCall(sql1);
                        stmt1.setInt(1, idSetor);
                        stmt1.setInt(2, idMaterial);
                        stmt1.setInt(3, quantidade);

                        stmt1.execute();
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return;
                    }
                });
                conn.commit();
            } else {
                throw new SQLException("Erro.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public boolean transferirSolicitacao(int idSolicitacao, int idSetorDestino) {
        ArrayList<Material> materiais = new ArrayList<>();

        try (Connection conn = new ConectDB_postgres().getConexao()) {
            conn.setAutoCommit(false);

            sql = "SELECT m.id_material, ms.quantidade " +
                    "FROM material_solicitacao ms, material m " +
                    "WHERE ms.id_material = m.id_material " +
                    "AND id_solicitacao = ?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, idSolicitacao);
            rs = pre.executeQuery();

            Solicitacao sol = new Solicitacao();
            while (rs.next()) {
                Material mat = new Material();
                mat.setIdMaterial(rs.getInt("id_material"));
                mat.setQuantidade(rs.getInt("quantidade"));
                sol.adicionarMaterial(mat);
                materiais.add(mat);
            }

            String sql = "INSERT INTO movimentacao(id_solicitacao, almox_destino) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, idSolicitacao);
            stmt.setInt(2, idSetorDestino);
            stmt.executeUpdate();
            ResultSet rs2 = stmt.getGeneratedKeys();
            int idGenerator = 0;
            if (rs2.next()) {
                idGenerator = rs2.getInt(1);
                for (Material m : materiais) {

                    sql = "INSERT INTO material_movimentacao (id_material, quantidade, id_movimentacao) VALUES (?, ?, ?);";
                    try {
                        PreparedStatement stmt1 = conn.prepareStatement(sql);
                        stmt1.setInt(1, m.getIdMaterial());
                        stmt1.setInt(2, m.getQuantidade());
                        //noinspection JpaQueryApiInspection
                        stmt1.setInt(3, idGenerator);
                        stmt1.execute();

                        String sql1 = "insert into setor_material (cod_setor, id_material, quantidade) VALUES  (?,?,?) " +
                                "ON CONFLICT (cod_setor, id_material) do update set quantidade =  setor_material.quantidade + ?;";

                        CallableStatement stmt2 = conn.prepareCall(sql1);
                        stmt2.setInt(1, idSetorDestino);
                        stmt2.setInt(2, m.getIdMaterial());
                        stmt2.setInt(3, m.getQuantidade());
                        stmt2.setInt(4, m.getQuantidade());

                        stmt2.execute();
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return false;
                    }
                }
            }

            conn.commit();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return retorno;
    }


    public void transferirSolicitacao(Map<Integer, Integer> materiais, int idSetorDestino, int idSolicitacao) {
        try (Connection conn = new ConectDB_postgres().getConexao()) {
            conn.setAutoCommit(false);
            if (true) {
                materiais.forEach((idMaterial, quantidade) -> {
//                        upsert_setor_material(cod_unidade int, id_mat int, qtd int)
//                        String sql1 = "DO $$ BEGIN PERFORM upsert_setor_material(?, ?, ?); END $$";
                    String sql1 = "DO $$ BEGIN PERFORM  upsert_setor_material(?, ?, ?); END $$";
                    try {
                        CallableStatement stmt1 = conn.prepareCall(sql1);
                        stmt1.setInt(1, idSetorDestino);
                        stmt1.setInt(2, idMaterial);
                        stmt1.setInt(3, quantidade);

                        stmt1.execute();
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return;
                    }
                });
                conn.commit();
            } else {
                throw new SQLException("Erro.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
//
//    CREATE OR REPLACE FUNCTION upsert_setor_material(cod_unidade int, id_mat int, qtd int) RETURNS VOID AS $$
//    DECLARE
//
//            BEGIN
//    UPDATE setor_material SET quantidade = quantidade+(qtd) where id_material = id_mat AND cod_setor = cod_unidade;
//    IF NOT FOUND THEN
//    INSERT INTO setor_material (id_material, quantidade, cod_setor) VALUES (id_mat, qtd, cod_unidade);
//    END IF;
//    END;
//    $$ LANGUAGE plpgsql;

}
