<%--
  Created by IntelliJ IDEA.
  User: Lucas
  Date: 31/10/2019
  Time: 13:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Gerenciador UBS | Dar Baixa Materiais</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="bower_components/font-awesome/css/font-awesome.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="bower_components/Ionicons/css/ionicons.css">
    <!-- DataTables -->
    <link rel="stylesheet" href="bower_components/datatables.net-bs/css/dataTables.bootstrap.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="dist/css/AdminLTE.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="dist/css/skins/_all-skins.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Google Font -->
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <header class="main-header">
        <!-- Logo -->
        <a href="#" class="logo">
            <!-- mini logo for sidebar mini 50x50 pixels -->
            <span class="logo-mini">UBS</span>
            <!-- logo for regular state and mobile devices -->
            <span class="logo-lg">Gerenciador <b>UBS</b></span>
        </a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top">
            <!-- Sidebar toggle button-->
            <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
                <span class="sr-only">Menu</span>
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
                                    <small>Usuario UBS</small>
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
                    <a href="menuUsuario?id=${logado.setor.idSetor}"><i class="fa fa-th"></i> <span>Home</span></a>
                </li>
                <li class="active">
                    <a href="#"><i class="fa fa-th"></i> <span>Dar Baixa no Estoque</span></a>
                </li>
                <li>
                    <a href="fazerSolicitacao?id=${logado.setor.idSetor}"><i class="fa fa-th"></i> <span>Solicitar Materiais</span></a>
                </li>
                <li>
                    <a href="historicoUsuario?id=${logado.setor.idSetor}"><i class="fa fa-th"></i> <span>Histórico de Solicitação</span></a>
                </li>
            </ul>
        </section>
        <!-- /.sidebar -->
    </aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Dar Baixa Materiais
            </h1>
            <ol class="breadcrumb">
                <li><a href="menuUsuario?id=${logado.setor.idSetor}"><i class="fa fa-dashboard"></i> Home</a></li>
                <li class="active">Dar Baixa em Materiais</li>

            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <form class="box" method="post" action="materiaisUsados">
                        <div class="box">
                            <div class="box-header">
                                <h3 class="box-title">Estoque Atual - Unidade ${logado.setor.nomeSetor}</h3>
                            </div>
                            <!-- /.box-header -->
                            <div class="box-body">

                                <input type="hidden" name="idSetor" value="${logado.setor.idSetor}" id="idSetor">
                                <table id="example2" class="table table-bordered table-hover">
                                    <thead>
                                    <tr>
                                        <th>Nome do Material</th>
                                        <th>Unidade de Medida</th>
                                        <th style="width: 65px">Estoque Atual</th>
                                        <th style="width: 85px">Quantidade usada</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="estoque" items="${materiais}">
                                        <tr>
                                            <td>${estoque.material.nomeMaterial}</td>
                                            <input name="idMaterial" value="${estoque.material.idMaterial}" type="hidden">
                                            <td>${estoque.material.unidadeMedida}</td>
                                            <td>
                                                <input type="text" class="form-control" value="${estoque.qtdEstoque}" disabled>
                                            </td>
                                            <td>
                                                <input type="number" name="quantidade" class="form-control input-number quantity-field" value="0" min="0" max="${estoque.qtdEstoque}">
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                    <tfoot>
                                    <tr>
                                        <th>Nome do Material</th>
                                        <th>Unidade de Medida</th>
                                        <th>Estoque Atual</th>
                                        <th>Quantidade usada</th>
                                    </tr>
                                    </tfoot>

                                </table>
                                <div class="box-footer">
                                    <button type="submit" class="btn btn-success pull-right">Dar Baixa</button>
                                    <a href="menuUsuario?id=${logado.setor.idSetor}" type="button" class="btn btn-default pull-right" style="margin-right: 5px;">Cancelar</a>

                                </div>
                            </div>
                            <!-- /.box-body -->
                        </div>
                        <!-- /.box -->
                    </form>
                </div>
                <!-- /.content-wrapper -->

            </div>
        </section>

    </div>
    <!-- ./wrapper -->
    <footer class="main-footer">
        <div class="pull-right hidden-xs">
            <b>Versão</b> 1.0.1
        </div>
        Sistema de Gerenciamento de Estoque para Unidades Básicas de Saúde.
    </footer>

</div>
<!-- jQuery 3 -->
<script src="bower_components/jquery/dist/jquery.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="bower_components/bootstrap/dist/js/bootstrap.js"></script>
<!-- SlimScroll -->
<script src="bower_components/jquery-slimscroll/jquery.slimscroll.js"></script>
<!-- FastClick -->
<script src="bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="dist/js/adminlte.js"></script>
<!-- page script -->
</body>
</html>
