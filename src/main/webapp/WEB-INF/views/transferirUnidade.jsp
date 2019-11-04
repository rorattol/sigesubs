<%--
  Created by IntelliJ IDEA.
  User: Lucas
  Date: 29/10/2019
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Gerenciador UBS | Transferir Materiais</title>
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
        <a href="index2.html" class="logo">
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
<%--                            <img src="../../dist/img/user2-160x160.jpg" class="user-image" alt="User Image">--%>
                            <span class="hidden-xs">${logado.nomeUsuario}</span>
                        </a>
                        <ul class="dropdown-menu">
                            <!-- User image -->
                            <li class="user-header">
<%--                                <img src="../../dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">--%>

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
                    <a href="menuAdmin"><i class="fa fa-th"></i> <span>Home</span></a>
                </li>
                <li>
                    <a href="gerenciarUsuario"><i class="fa fa-th"></i> <span>Gerenciar Usuário</span></a>
                </li>
                <li>
                    <a href="gerenciarMaterial"><i class="fa fa-th"></i> <span>Gerenciar Material</span></a>
                </li>
                <li>
                    <a href="transferirMaterial"><i class="fa fa-th"></i> <span>Transferir Materiais</span></a>
                </li>
                <li>
                    <a href="gerenciarSolicitacao"><i class="fa fa-th"></i> <span>Gerenciar Solicitações</span></a>
                </li>
                <li>
                    <a href="historicoAdmin"><i class="fa fa-th"></i> <span>Histórico de Solicitações</span></a>
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
            <h1>
                Transferir Materiais
            </h1>
            <ol class="breadcrumb">
                <li><a href="menuAdmin"><i class="fa fa-dashboard"></i> Home</a></li>
                <li class="active">Transferir Materiais</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">Materiais</h3>
                        </div>
                        <form action="#" method="post">
                        <!-- /.box-header -->
                        <div class="box-body">

                            <table id="example2" class="table table-bordered table-hover">

                                <thead>
                                <tr>
                                    <th>Nome Material</th>
                                    <th>Unidade de Medida</th>
                                    <th style="width: 65px">Estoque Atual</th>
                                    <th style="width: 190px">Ação</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>Trident</td>
                                    <td>Win 95+</td>
                                    <td>
                                        <input type="text" class="form-control" value="17" disabled>
                                    </td>
                                    <td>
                                        <div class="input-group">
												  <span class="input-group-btn">
													  <button type="button" class="btn btn-default btn-number button-minus" data-field="quantity">
														  <span class="glyphicon glyphicon-minus"></span>
													  </button>
												  </span>
                                            <input type="number" name="quantity" class="form-control input-number quantity-field" value="1" min="1" max="10" step="1">
                                            <span class="input-group-btn">
													  <button type="button" class="btn btn-default btn-number button-plus" data-field="quantity">
														  <span class="glyphicon glyphicon-plus"></span>
													  </button>
												  </span>
                                        </div>

                                        <!--
                                                    <div class="input-group">
													  <input type="button" value="-" class="button-minus" data-field="quantity">
													  <input type="number" step="1" max="" value="1" name="quantity" class="quantity-field">
													  <input type="button" value="+" class="button-plus" data-field="quantity">
													</div>-->
                                        <!--		https://bootsnipp.com/snippets/dGWP
                                                    http://jsfiddle.net/polaszk/1oyfxoor/ -->

                                    </td>
                                </tr>
                                <tr>
                                    <td>aaaaa</td>
                                    <td>teste</td>
                                    <td><input type="text" class="form-control" value="7" disabled></td>
                                    <td>
                                        <div class="input-group">
												  <span class="input-group-btn">
													  <button type="button" class="btn btn-default btn-number button-minus" data-field="quantity">
														  <span class="glyphicon glyphicon-minus"></span>
													  </button>
												  </span>
                                            <input type="number" name="quantity" class="form-control input-number quantity-field" value="1" min="1" max="10" step="1">
                                            <span class="input-group-btn">
													  <button type="button" class="btn btn-default btn-number button-plus" data-field="quantity">
														  <span class="glyphicon glyphicon-plus"></span>
													  </button>
												  </span>
                                        </div>
                                    </td>
                                </tr>
                                </tbody>
                                <tfoot>
                                <tr>
                                    <th>Nome Material</th>
                                    <th>Unidade de Medida</th>
                                    <th>Estoque Atual</th>
                                    <th>Ações</th>
                                </tr>
                                </tfoot>


                            </table>
                            <button type="submit" class="btn btn-success pull-right">Transferir Materiais</button>
                            <a href="transferirMaterial" type="button" class="btn btn-danger pull-right" style="margin-right: 5px;">Cancelar</a>
                        </div>
                        </form>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.content-wrapper -->
            </div>
        </section>
    </div>
    <!-- ./wrapper -->

    <footer class="main-footer">
        <div class="pull-right hidden-xs">
            <b>Version</b> 2.4.18
        </div>
        <strong>Copyright &copy; 2014-2019 <a href="https://adminlte.io">AdminLTE</a>.</strong> All rights
        reserved.
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

<!-- page script -->
<script>

    function incrementValue(e) {
        e.preventDefault();
        var fieldName = $(e.target).data('field');
        var parent = $(e.target).closest('div');
        var currentVal = parseInt(parent.find('input[name=' + fieldName + ']').val(), 10);

        if (!isNaN(currentVal)) {
            parent.find('input[name=' + fieldName + ']').val(currentVal + 1);
        } else {
            parent.find('input[name=' + fieldName + ']').val(0);
        }
    }

    function decrementValue(e) {
        e.preventDefault();
        var fieldName = $(e.target).data('field');
        var parent = $(e.target).closest('div');
        var currentVal = parseInt(parent.find('input[name=' + fieldName + ']').val(), 10);

        if (!isNaN(currentVal) && currentVal > 0) {
            parent.find('input[name=' + fieldName + ']').val(currentVal - 1);
        } else {
            parent.find('input[name=' + fieldName + ']').val(0);
        }
    }

    $('.input-group').on('click', '.button-plus', function(e) {
        incrementValue(e);
    });

    $('.input-group').on('click', '.button-minus', function(e) {
        decrementValue(e);
    });
</script>
</body>
</html>

