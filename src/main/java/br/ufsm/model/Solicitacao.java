package br.ufsm.model;

import java.util.Date;

public class Solicitacao {

    private int id;
    private Date dataSolicitacao;
    private String statusSolicitacao;
    private String observacao;
    private Usuario usuarioSolicitante;
    private Setor setorSolicitante;

    public Solicitacao(){

    }

    public Solicitacao(int id, Date dataSolicitacao, String statusSolicitacao, String observacao, Usuario usuarioSolicitante, Setor setorSolicitante) {
        this.id = id;
        this.dataSolicitacao = dataSolicitacao;
        this.statusSolicitacao = statusSolicitacao;
        this.observacao = observacao;
        this.usuarioSolicitante = usuarioSolicitante;
        this.setorSolicitante = setorSolicitante;
    }

    public int getId() {
        return id;
    }

    public void setId(int idSolcitacao) {
        this.id = idSolcitacao;
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
