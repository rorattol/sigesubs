package br.ufsm.dao;

import br.ufsm.model.Setor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SetorDAO {
    String sql = "";
    PreparedStatement pre;
    ResultSet rs;
    boolean retorno = false;

    public boolean create(Setor setor) {
        try (Connection conn = new ConectDB_postgres().getConexao()) {

            sql = "INSERT INTO setor (nome_setor, atencao_saude) VALUES (?, ?);";
            pre =  conn.prepareStatement(sql);
            pre.setString(1, setor.getNomeSetor());
            pre.setString(2, setor.getAtencaoSaude());
            pre.executeUpdate();

            retorno = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return retorno;
    }

    public Setor read(int id) {
        try (Connection conn = new ConectDB_postgres().getConexao()) {

            sql = "SELECT * FROM setor WHERE cod_setor = ?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            rs = pre.executeQuery();
            while (rs.next()) {
                Setor setor = new Setor();
                setor.setIdSetor(rs.getInt("cod_setor"));
                setor.setNomeSetor(rs.getString("nome_setor"));
                setor.setAtencaoSaude(rs.getString("atencao_saude"));
                return setor;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean update(Setor setor) {
        try (Connection conn = new ConectDB_postgres().getConexao()) {

            sql = "UPDATE setor SET nome_setor = ?, atencao_saude = ? WHERE cod_setor = ?;";

            pre = conn.prepareStatement(sql);
            pre.setString(1, setor.getNomeSetor());
            pre.setString(2, setor.getAtencaoSaude());
            pre.setInt(3, setor.getIdSetor());
            pre.executeUpdate();
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
            sql = "DELETE FROM setor WHERE cod_setor = ?";
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

    public ArrayList<Setor> getSetor() {

        ArrayList<Setor> unidades = new ArrayList<Setor>();

        try (Connection conn = new ConectDB_postgres().getConexao()) {
            sql = "select * from setor;";    //adicionar condição para não incluir a secretaria de saude
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                Setor s = new Setor();

                s.setNomeSetor(rs.getString("nome_setor"));
                s.setAtencaoSaude(rs.getString("atencao_saude"));
                s.setIdSetor(rs.getInt("cod_setor"));

                unidades.add(s);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return unidades;
    }
}
