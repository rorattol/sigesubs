package br.ufsm.model;

import java.util.Date;

//CREATE TABLE movimentacao(
//        id_movimentacao int not null,
//        data_mov date,
//        id_solicitacao int,
//        almox_destino int,
//        primary key (id_movimentacao),
//        foreign key (id_solicitacao) references solicitacao(id_solicitacao),
//        foreign key (id_material) references material(id_material),
//        foreign key (almox_destino) references setor(cod_setor)
//        );
public class Movimentacao {

    private int idMovimentacao;
    private Date dataMov;
    private Solicitacao solicitacao;
    private Setor almoxDestino;

    public Movimentacao(int idMovimentacao, Date dataMov, Solicitacao solicitacao, Setor almoxDestino) {
        this.idMovimentacao = idMovimentacao;
        this.dataMov = dataMov;
        this.solicitacao = solicitacao;
        this.almoxDestino = almoxDestino;
    }

    public int getIdMovimentacao() {
        return idMovimentacao;
    }

    public void setIdMovimentacao(int idMovimentacao) {
        this.idMovimentacao = idMovimentacao;
    }

    public Date getDataMov() {
        return dataMov;
    }

    public void setDataMov(Date dataMov) {
        this.dataMov = dataMov;
    }

    public Solicitacao getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(Solicitacao solicitacao) {
        this.solicitacao = solicitacao;
    }

    public Setor getAlmoxDestino() {
        return almoxDestino;
    }

    public void setAlmoxDestino(Setor almoxDestino) {
        this.almoxDestino = almoxDestino;
    }
}
