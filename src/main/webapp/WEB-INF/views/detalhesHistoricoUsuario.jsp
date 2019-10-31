<%--
  Created by IntelliJ IDEA.
  User: Lucas
  Date: 23/10/2019
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Gerenciador UBS | Detalhes Historico</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet" href="../../bower_components/bootstrap/dist/css/bootstrap.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="../../bower_components/font-awesome/css/font-awesome.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="../../bower_components/Ionicons/css/ionicons.css">
    <!-- DataTables -->
    <link rel="stylesheet" href="../../bower_components/datatables.net-bs/css/dataTables.bootstrap.css">
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
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <header class="main-header">
        <!-- Logo -->
        <a href="menuAdmin" class="logo">
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
                            <img src="../../dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
                            <span class="hidden-xs">${logado.nomeUsuario}</span>
                        </a>
                        <ul class="dropdown-menu">
                            <!-- User image -->
                            <li class="user-header">
                                <img src="../../dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">

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
                    <a href="menuUsuario"><i class="fa fa-th"></i> <span>Home</span></a>
                </li>
                <li>
                    <a href="darBaixa"><i class="fa fa-th"></i> <span>Dar Baixa no Estoque</span></a>
                </li>
                <li>
                    <a href="fazerSolicitacao"><i class="fa fa-th"></i> <span>Solicitar Materiais</span></a>
                </li>
                <li>
                    <a href="historicoUsuario"><i class="fa fa-th"></i> <span>Histórico de Solicitação</span></a>
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
                Histórico de Solicitações
            </h1>
            <ol class="breadcrumb">
                <li><a href="menuUsuario"><i class="fa fa-dashboard"></i> Home</a></li>
                <li><a href="historicoUsuario">Historico de Solicitações</a></li>
                <li class="active">Detalhes Solicitacão</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <div class="box">
                        <div class="box-header">
                            <div class="col-md-12">
                                <h3 class="box-title">Unidade Dom Antonio Reis</h3>
                            </div>
                            <div class="col-md-6">
                                <div class="box-body">
                                    <dl>
                                        <dt>Nome Solicitante</dt>
                                        <dd>Luiz Pedro</dd>
                                        <br/>
                                        <dt>Data da Solicitação</dt>
                                        <dd>12/12/2012</dd>
                                        <br/>
                                        <dt>Status</dt>
                                        <dd>Negado</dd>

                                    </dl>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="box-body">
                                    <dl>
                                        <dt>Nome do Avaliador</dt>
                                        <dd>Maria Tereza</dd>
                                        <br/>
                                        <dt>Data da Avaliação</dt>
                                        <dd>21/12/2012</dd>
                                    </dl>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="box-body">
                                    <dl>
                                        <dt>Observações</dt>
                                        <dd>Muitos itens solicitados. Grande parte deeles desnecessários</dd>
                                    </dl>
                                </div>
                            </div>
                            <!-- /.box-header -->
                            <div class="box-body">
                                <table id="example2" class="table table-bordered table-hover">
                                    <thead>
                                    <tr>
                                        <th>Nome Material</th>
                                        <th>Unidade de Medida</th>
                                        <th style="width: 70px">Quantidade Pedida</th>
                                        <th style="width: 70px">Quantidade Recebida</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td>Trident</td>
                                        <td>Win 95+</td>
                                        <td>4</td>
                                        <td>0</td>
                                    </tr>
                                    </tbody>
                                    <tfoot>
                                    <tr>
                                        <th>Nome Material</th>
                                        <th>Unidade de Medida</th>
                                        <th>Quantidade Pedida</th>
                                        <th>Quantidade Recebida</th>
                                    </tr>
                                    </tfoot>
                                </table>
                            </div>
                            <!-- /.box-body -->
                        </div>
                        <!-- /.box -->
                    </div>
                    <!-- /.content-wrapper -->
                </div>
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
<script src="../../bower_components/jquery/dist/jquery.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="../../bower_components/bootstrap/dist/js/bootstrap.js"></script>
<!-- DataTables -->
<script src="../../bower_components/datatables.net/js/jquery.dataTables.js"></script>
<script src="../../bower_components/datatables.net-bs/js/dataTables.bootstrap.js"></script>
<!-- SlimScroll -->
<script src="../../bower_components/jquery-slimscroll/jquery.slimscroll.js"></script>
<!-- FastClick -->
<script src="../../bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="../../dist/js/adminlte.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="../../dist/js/demo.js"></script>
<!-- page script -->
<script>
    $(function () {
        $('#example1').DataTable()
        $('#example2').DataTable({
            'paging': true,
            'lengthChange': false,
            'searching': false,
            'ordering': true,
            'info': true,
            'autoWidth': false
        })
    })
</script>
</body>
</html>