package br.ufsm.model;

import java.util.Date;

//CREATE TABLE solicitacao(
//        id_solicitacao int not null,
//        data_solicitacao date,
//        status_solicitacao varchar (25),
//        observacao text,
//        usuario_responsavel int,
//        setor_solicitante int,
//        primary key (id_solicitacao),
//        foreign key (usuario_responsavel) references usuario(id_usuario),
//        foreign key (setor_solicitante) references setor(cod_setor)
//        );
public class Solicitacao {

    private int idSolcitacao;
    private Date dataSolicitacao;
    private String statusSolicitacao;
    private String observacao;
    private Usuario usuarioSolicitante;
    private Setor setorSolicitante;

    public Solicitacao(int idSolcitacao, Date dataSolicitacao, String statusSolicitacao, String observacao, Usuario usuarioSolicitante, Setor setorSolicitante) {
        this.idSolcitacao = idSolcitacao;
        this.dataSolicitacao = dataSolicitacao;
        this.statusSolicitacao = statusSolicitacao;
        this.observacao = observacao;
        this.usuarioSolicitante = usuarioSolicitante;
        this.setorSolicitante = setorSolicitante;
    }

    public int getIdSolcitacao() {
        return idSolcitacao;
    }

    public void setIdSolcitacao(int idSolcitacao) {
        this.idSolcitacao = idSolcitacao;
    }

    public Date getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(Date dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public String getStatusSolicitacao() {
        return statusSolicitacao;
    }

    public void setStatusSolicitacao(String statusSolicitacao) {
        this.statusSolicitacao = statusSolicitacao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Usuario getUsuarioSolicitante() {
        return usuarioSolicitante;
    }

    public void setUsuarioSolicitante(Usuario usuarioSolicitante) {
        this.usuarioSolicitante = usuarioSolicitante;
    }

    public Setor getSetorSolicitante() {
        return setorSolicitante;
    }

    public void setSetorSolicitante(Setor setorSolicitante) {
        this.setorSolicitante = setorSolicitante;
    }
}
