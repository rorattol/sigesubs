package br.ufsm.model;

//CREATE TABLE material(
//        id_material int not null,
//        nome_material varchar (50),
//        unidade_medida varchar (50),
//        id_categoria int,
//        primary key (id_material),
//        foreign key (id_categoria) references categoria(id_categoria)
//        );
public class Material {

    private int idMaterial;
    private String nomeMaterial;
    private String unidadeMedida;
    private int categoria;

    public Material(int idMaterial, String nomeMaterial, String unidadeMedida, int categoria) {
        this.idMaterial = idMaterial;
        this.nomeMaterial = nomeMaterial;
        this.unidadeMedida = unidadeMedida;
        this.categoria = categoria;
    }

    public Material(){

    }

    public int getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
    }

    public String getNomeMaterial() {
        return nomeMaterial;
    }

    public void setNomeMaterial(String nomeMaterial) {
        this.nomeMaterial = nomeMaterial;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }
}
