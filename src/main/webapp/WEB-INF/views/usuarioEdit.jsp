<%--
  Created by IntelliJ IDEA.
  User: Lucas
  Date: 18/10/2019
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>AdminLTE 2 | Editar Usuário</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet" href="../../bower_components/bootstrap/dist/css/bootstrap.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="../../bower_components/font-awesome/css/font-awesome.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="../../bower_components/Ionicons/css/ionicons.css">
    <!-- Select2 -->
    <link rel="stylesheet" href="../../bower_components/select2/dist/css/select2.css">
    <!-- iCheck for checkboxes and radio inputs -->
    <link rel="stylesheet" href="../../plugins/iCheck/all.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="../../dist/css/AdminLTE.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="../../dist/css/skins/_all-skins.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Google Font -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <header class="main-header">
        <!-- Logo -->
        <a href="index.html" class="logo">
            <!-- mini logo for sidebar mini 50x50 pixels -->
            <span class="logo-mini">UBS</span>
            <!-- logo for regular state and mobile devices -->
            <span class="logo-lg">Gerenciador <b>UBS</b></span>
        </a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top">
            <!-- Sidebar toggle button-->
            <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
                <span class="sr-only">Toggle navigation</span>
            </a>

            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <!-- User Account: style can be found in dropdown.less -->
                    <li class="dropdown user user-menu">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <img src="../../dist/img/user2-160x160.png" class="user-image" alt="User Image">
                            <span class="hidden-xs">${logado.nomeUsuario}</span>
                        </a>
                        <ul class="dropdown-menu">
                            <!-- User image -->
                            <li class="user-header">
                                <img src="../../dist/img/user2-160x160.png" class="img-circle" alt="User Image">

                                <p>${logado.nomeUsuario}
                                    <small>Administrador</small>
                                </p>
                            </li>

                            <!-- Menu Footer-->
                            <li class="user-footer">
                                <div class="pull-left">
                                    <a href="visualizarPerfil" class="btn btn-default btn-flat">Perfil</a>
                                </div>
                                <div class="pull-right">
                                    <a href="logout" class="btn btn-default btn-flat">Sair</a>
                                </div>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>
    </header>

    <!-- Left side column. contains the logo and sidebar -->
    <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
            <!-- sidebar menu: : style can be found in sidebar.less -->
            <ul class="sidebar-menu" data-widget="tree">
                <li class="header">MENU</li>
                <li>
                    <a href="menuAdmin"><i class="fa fa-fw fa-home"></i> <span>Home</span></a>
                </li>
                <li class="active">
                    <a href="gerenciarUsuario"><i class="fa fa-fw fa-users"></i> <span>Gerenciar Usuário</span></a>
                </li>
                <li>
                    <a href="gerenciarMaterial"><i class="fa fa-th"></i> <span>Gerenciar Material</span></a>
                </li>
                <li>
                    <a href="transferirMaterial"><i class="fa fa-fw fa-exchange"></i> <span>Transferir Materiais</span></a>
                </li>
                <li>
                    <a href="gerenciarSolicitacao"><i class="fa fa-th"></i> <span>Gerenciar Solicitações</span></a>
                </li>
                <li>
                    <a href="historicoAdmin"><i class="fa fa-fw fa-history"></i> <span>Histórico de Solicitações</span></a>
                </li>
                <%--<li>
                    <a href="estoqueAlmox"><i class="fa fa-th"></i> <span>Adicionar Materiais ao Almox</span></a>
                </li>--%>
            </ul>
        </section>
        <!-- /.sidebar -->
    </aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>Gerenciar Usuário</h1>
            <ol class="breadcrumb">
                <li><a href="menuAdmin"><i class="fa fa-dashboard"></i> Home</a></li>
                <li><a href="gerenciarUsuario">Gerenciar Usuários</a></li>
                <li class="active">Editar Usuário</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-3"></div>
                <!-- left column -->
                <div class="col-md-6">
                    <!-- general form elements -->
                    <div class="box box-primary">
                        <div class="box-header with-border">
                            <h3 class="box-title">Editar Usuário</h3>
                        </div>
                        <!-- /.box-header -->
                        <!-- form start -->
                        <jsp:useBean id="setorDAO" class="br.ufsm.dao.SetorDAO"></jsp:useBean>
                        <form role="form" action="updateUsuario" method="post">
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="nameInput">Nome do Usuário</label>
                                    <input type="hidden" name="idUsuario" value="${usuario.idUsuario}">
                                    <input type="text" class="form-control" id="nameInput" name="nomeUsuario" value="${usuario.nomeUsuario}" placeholder="Nome do Usuário">
                                </div>
                                <div class="form-group">
                                    <label for="inputEmail">Login</label>
                                    <input type="email" class="form-control" id="inputEmail" value="${usuario.loginUsuario}" name="loginUsuario" placeholder="Login">
                                </div>
                                <div class="form-group">
                                    <label>Unidade a ser atribuído</label>
                                    <select class="form-control select2" name="idSetor" style="width: 100%;" required>
                                        <c:forEach var="unidade" items="${unidades}">
                                            <option value="${unidade.idSetor}"
                                                    <c:if test="${usuario.setor.idSetor == unidade.idSetor}">selected="selected" </c:if>
                                            >${unidade.nomeSetor}</option>
                                        </c:forEach>
                                    </select>
                                </div>
<%--                                <div class="form-group">--%>
<%--                                    <label for="inputSenha">Informe a nova senha</label>--%>
<%--                                    <input type="password" class="form-control" id="inputSenha" name="senhaUsuario" placeholder="Senha" value="null">--%>
<%--                                </div>--%>
<%--                                <div class="form-group">--%>
<%--                                    <label for="inputSenha2">Digite a nova senha novamente</label>--%>
<%--                                    <input type="password" class="form-control" id="inputSenha2" placeholder="Repetir Senha">--%>
<%--                                </div>--%>

                                <div class="form-group">
                                    <label>Função</label>
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <div class="radio">
                                                <label>
                                                    <input type="radio" name="tipoU" id="optionsRadios1" value="2"
                                                           <c:if test="${usuario.tipoUsuario.idTipoUsuario == 2}">checked </c:if>>
                                                    Administrador Geral
                                                </label>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="radio">
                                                <label>
                                                    <input type="radio" name="tipoU" id="optionsRadios2" value="1"
                                                           <c:if test="${usuario.tipoUsuario.idTipoUsuario == 1}">checked </c:if>>
                                                    Administrador UBS
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button type="submit" class="btn btn-success pull-right">Salvar</button>
                                <a href="gerenciarUsuario" type="button" class="btn btn-danger pull-right" style="margin-right: 5px;">Cancelar</a>

                            </div>
                        </form>
                    </div>
                    <!-- /.box -->
                </div>
            </div>
            <!-- /.row -->
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
    <footer class="main-footer">
        <div class="pull-right hidden-xs">
            <b>Versão</b> 1.0.1
        </div>
        Sistema de Gerenciamento de Estoque para Unidades Básicas de Saúde.
    </footer>
</div>
<!-- jQuery 3 -->
<script src="../../bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="../../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script src="../../bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="../../dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="../../dist/js/demo.js"></script>
<!-- iCheck 1.0.1 -->
<script src="../../plugins/iCheck/icheck.js"></script>
<!-- Select2 -->
<script src="../../bower_components/select2/dist/js/select2.full.js"></script>
<script>
    $(function () {
        //Initialize Select2 Elements
        $('.select2').select2()
    })

    //iCheck for checkbox and radio inputs
    $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
        checkboxClass: 'icheckbox_minimal-blue',
        radioClass   : 'iradio_minimal-blue'
    })
    //Flat red color scheme for iCheck
    //    $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
    //      checkboxClass: 'icheckbox_flat-blue',
    //      radioClass   : 'iradio_flat-blue'
    //    })
</script>
</body>
</html>

