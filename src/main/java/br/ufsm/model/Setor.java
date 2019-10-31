package br.ufsm.model;

//CREATE TABLE setor(
//        cod_setor int not null,
//        nom_setor varchar (70),
//        atencao_saude varchar (60),
//        primary key (cod_setor)
//        );
public class Setor {

    private int idSetor;
    private String nomeSetor;
    private String atencaoSaude;

    public Setor(int idSetor, String nomeSetor, String atencaoSaude) {
        this.idSetor = idSetor;
        this.nomeSetor = nomeSetor;
        this.atencaoSaude = atencaoSaude;
    }

    public Setor(){

    }

    public int getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(int idSetor) {
        this.idSetor = idSetor;
    }

    public String getNomeSetor() {
        return nomeSetor;
    }

    public void setNomeSetor(String nomeSetor) {
        this.nomeSetor = nomeSetor;
    }

    public String getAtencaoSaude() {
        return atencaoSaude;
    }

    public void setAtencaoSaude(String atencaoSaude) {
        this.atencaoSaude = atencaoSaude;
    }
}
