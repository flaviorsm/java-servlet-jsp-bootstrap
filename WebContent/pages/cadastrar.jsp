<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Cadastrar</title>	
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/style.css" rel="stylesheet">    
</head>
<body> 
    <%@include file="../templates/menu.jsp"%>
    <hr />
    <div id="main" class="container-fluid">
        <h3 class="page-header">Cadastro de contatos</h3>
        <form action="../AgendaController?cmd=cadastrar" method="post">
            <div class="row">
                <div class="form-group col-md-4">
                    <label for="nome">Nome:</label>
                    <input type="text" class="form-control" id="nome" name="nome" required="required">
                </div>
                 <div class="form-group col-md-4">
                    <label for="email">E-mail:</label>
                    <input type="email" class="form-control" id="email" name="email" required="required">
                 </div>
                 <div class="form-group col-md-4">
                    <label for="tel">Telefone:</label> 
                    <input type="text" class="form-control" id="telefone" name="tel" data-mask="(00) 0000-0000" data-mask-selectonfocus="true" required/>
                </div>
            </div>
            <hr />
            <div id="actions" class="row">
            <div class="col-md-12">
                <button type="submit" class="btn btn-primary">Salvar</button>
                <a href="./agenda.jsp" class="btn btn-default">Cancelar</a>
            </div>
            </div>			  	
        </form>
    </div>	 	
    <script type="text/javascript" src="http://code.jquery.com/jquery-3.0.0.min.js"></script>
  	<script type="text/javascript" src="http://code.jquery.com/qunit/qunit-1.11.0.js"></script>
  	<script type="text/javascript" src="../js/sinon-1.10.3.js"></script>
  	<script type="text/javascript" src="../js/sinon-qunit-1.0.0.js"></script>
  	<script type="text/javascript" src="../js/jquery.mask.js"></script>
  	
    <script src="../js/bootstrap.min.js"></script>    
    
</body>
</html>