package br.ufsm.model;

import java.util.Date;

public class Solicitacao {

    private int idSolicitacao;
    private Date dataSolicitacao;
    private String statusSolicitacao;
    private String observacao;
    private Usuario usuarioSolicitante;
    private Setor setorSolicitante;

    public Solicitacao(){

    }

    public Solicitacao(int idSolicitacao, Date dataSolicitacao, String statusSolicitacao, String observacao, Usuario usuarioSolicitante, Setor setorSolicitante) {
        this.idSolicitacao = idSolicitacao;
        this.dataSolicitacao = dataSolicitacao;
        this.statusSolicitacao = statusSolicitacao;
        this.observacao = observacao;
        this.usuarioSolicitante = usuarioSolicitante;
        this.setorSolicitante = setorSolicitante;
    }

    public int getIdSolcitacao() {
        return idSolicitacao;
    }

    public void setIdSolcitacao(int idSolcitacao) {
        this.idSolicitacao = idSolcitacao;
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
