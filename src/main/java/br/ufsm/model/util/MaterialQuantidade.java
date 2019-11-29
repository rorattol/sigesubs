package br.ufsm.model.util;

public class MaterialQuantidade {

    private Integer id_material;
    private Integer quantidade;

    public MaterialQuantidade(Integer id_material, Integer quantidade) {
        this.id_material = id_material;
        this.quantidade = quantidade;
    }

    public Integer getId_material() {
        return id_material;
    }

    public void setId_material(Integer id_material) {
        this.id_material = id_material;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
