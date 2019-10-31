package br.ufsm.model;

//CREATE TABLE categoria(
//        id_categoria int not null,
//        nome varchar (40),
//        area varchar(40),
//        primary key (id_categoria)
//        );
public class Categoria {

    private int idCategoria;
    private String nomeCat;
    private String areaCat;

    public Categoria(int idCategoria, String nomeCat, String areaCat) {
        this.idCategoria = idCategoria;
        this.nomeCat = nomeCat;
        this.areaCat = areaCat;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNomeCat() {
        return nomeCat;
    }

    public void setNomeCat(String nomeCat) {
        this.nomeCat = nomeCat;
    }

    public String getAreaCat() {
        return areaCat;
    }

    public void setAreaCat(String areaCat) {
        this.areaCat = areaCat;
    }
}
