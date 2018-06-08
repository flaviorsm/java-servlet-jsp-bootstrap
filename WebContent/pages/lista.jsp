<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
    <%@include file="../templates/menu.jsp"%><hr />
    <div id="main" class="container-fluid">
        <h3 class="page-header">Lista de contatos</h3>
        <div id="list" class="row">
            <div class="table-responsive col-md-12">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nome</th>
                            <th>E-mail</th>
                            <th>Telefone</th>
                            <th class="actions">Ações</th>
                         </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${contatos}" var="contato">
                            <tr>
                                <td><c:out value="${ contato.getId() }" /></td>
                                <td><c:out value="${ contato.getNome() }" /></td>
                                <td><c:out value="${ contato.getEmail() }" /></td>
                                <td><c:out value="${ contato.getTelefone() }" /></td>
                                <td class="actions">
                                	<c:if test="${fn:length(contatos) gt 1}">
	                                    <a class="btn btn-danger btn-xs" 
	                                    	href="../AgendaController?cmd=delete&Id=<c:out value="${contato.getId()}"/>">
	                                    		Delete
	                                    </a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                 </table>
            </div>
        </div> <!-- /#list --> 
    </div>
    <script src="../js/jquery.min.js"></script>
</body>
</html>