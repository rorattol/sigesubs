<%--
  Created by IntelliJ IDEA.
  User: Lucas
  Date: 18/10/2019
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Gerenciador UBS | Usuario</title>
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
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">

    <style>
        .example-modal .modal {
            position: relative;
            top: auto;
            bottom: auto;
            right: auto;
            left: auto;
            display: block;
            z-index: 1;
        }

        .example-modal .modal {
            background: transparent !important;
        }
    </style>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <header class="main-header">
        <!-- Logo -->
        <a href="../../index.jsp" class="logo">
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
                Gerenciar Usuários
            </h1>
            <ol class="breadcrumb">
                <li><a href="menuAdmin"><i class="fa fa-dashboard"></i> Home</a></li>
                <li class="active">Gerenciar Usuários</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">

                    <c:if test="${not empty sucesso}">
                        <div class="alert alert-success alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            <h4><i class="icon fa fa-check"></i> Sucesso</h4>
                                ${sucesso}
                        </div>
                    </c:if>
                    <c:if test="${not empty erro}">
                        <div class="alert alert-danger alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            <h4><i class="icon fa fa-ban"></i> Erro</h4>
                                ${erro}
                        </div>
                    </c:if>

                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">Usuários</h3>
                            <!-- tools box -->
                            <div class="pull-right box-tools">
                                <a href="adicionarUsuario" type="button" class="btn btn-success btn-flat">Adicionar Usuários</a>
                            </div>
                            <!-- /. tools -->
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
                            <jsp:useBean id="usuarioDAO" class="br.ufsm.dao.UsuarioDAO"></jsp:useBean>
                            <table id="example2" class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>Nome</th>
                                    <th>Unidade Responsável</th>
                                    <th>Email</th>
                                    <th style="width: 100px">Nível de Acesso</th>
                                    <th style="width: 75px">Status</th>
                                    <th style="width: 65px">Ação</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="usuario" items="${usuarioDAO.usuarios}">
                                    <tr>
                                        <td>${usuario.nomeUsuario}</td>
                                        <td>${usuario.setor.nomeSetor}</td>
                                        <td>${usuario.loginUsuario}</td>
                                        <td>${usuario.tipoUsuario.nomeTipoUsuario}</td>
                                        <td>
                                            <c:if test = "${usuario.situacao == false}">
                                                Inativo
                                            </c:if>
                                            <c:if test = "${usuario.situacao == true}">
                                                Ativo
                                            </c:if>
                                        </td>
                                        <td>
                                            <div class="btn-group">
                                                <a href="updateUser?id=${usuario.idUsuario}" type="button" class="btn btn-default">
                                                    <span class="glyphicon glyphicon-edit"></span>
                                                </a>
                                                <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#modal-default" data-whatever="${usuario.idUsuario}">
                                                    <span class="glyphicon glyphicon-trash"></span>
                                                </button>
                                            </div>
                                        </td>
                                    </tr>

                                </c:forEach>
                                </tbody>
                                <tfoot>
                                <tr>
                                    <th>Nome</th>
                                    <th>Unidade Responsável</th>
                                    <th>Email</th>
                                    <th>Nível de Acesso</th>
                                    <th>Status</th>
                                    <th>Ações</th>
                                </tr>
                                </tfoot>
                            </table>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->

                    <div class="modal fade" id="modal-default">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title">Deletar Usuário</h4>
                                </div>
                                <form method="post" action="deleteUsuario">
                                    <div class="modal-body">
                                        <input type="hidden" name="idUsuario">
                                        <p>Você deseja deletar este usuário?</p>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Fechar</button>
                                        <button type="submit" class="btn btn-danger">Excluir</button>
                                    </div>
                                </form>
                            </div>
                            <!-- /.modal-content -->
                        </div>
                        <!-- /.modal-dialog -->
                    </div>
                    <!-- /.modal -->

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
<script type="text/javascript">
    $('#modal-default').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget)
        var recipient = button.data('whatever')
        var modal = $(this)
        modal.find('.modal-body input').val(recipient)
    })
</script>
</body>
</html>
