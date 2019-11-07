package br.ufsm.model;

public class EstoqueSetor {

    private Material material;
    private int qtdEstoque;
    private Setor setor;

    public EstoqueSetor(){

    }

    public EstoqueSetor(Material material, int qtdEstoque, Setor setor) {
        this.material = material;
        this.qtdEstoque = qtdEstoque;
        this.setor = setor;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }
}
