package br.csi.model;

//CREATE TABLE usuario(
//        id_usuario int not null,
//        login varchar(50),
//        senha varchar,
//        situacao boolean,
//        primary key (id_usuario),
//        id_tipousuario int,
//        cod_setor int,
//        foreign key (cod_setor) references setor (cod_setor),
//        foreign key (id_tipousuario) references tipo_usuario (id_tipousuario)
//        );
public class Usuario {

    private int idUsuario;
    private String nomeUsuario;
    private String loginUsuario;
    private String senhaUsuario;
    private boolean situacao;  //true=ativo false=inativo
    private String tipoUsuario;
    private Setor setor;

    public Usuario(int idUsuario, String nomeUsuario, String loginUsuario, String senhaUsuario, boolean situacao, String tipoUsuario, Setor setor) {
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
        this.loginUsuario = loginUsuario;
        this.senhaUsuario = senhaUsuario;
        this.situacao = situacao;
        this.tipoUsuario = tipoUsuario;
        this.setor = setor;
    }

    public Usuario() {
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    public boolean isSituacao() {
        return situacao;
    }

    public void setSituacao(boolean situacao) {
        this.situacao = situacao;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }
}
