<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>

<div class="container-fluid">
    
    <h1 class="h3 mb-2 text-gray-800">Livros</h1>
    <p class="mb-4">Cadastro de Livros</p>

    <a class="btn btn-success mb-4" href="${pageContext.request.contextPath}/LivroNovo">
        <strong>Novo</strong>
    </a>

    <div class="card shadow">
        <div class="card-body">
            <table id="datatable" class="display">
                <thead>
                    <tr>
                        <th align="right">Id</th>
                        <th align="left">Nome do Livro</th>
                        <th align="left">ISBN</th>
                        <th align="left">Autor</th>
                        <th align="center">Data Publicação</th>
                        <th align="right">Valor (R$)</th>
                        <th align="center">Excluir</th>
                        <th align="center">Alterar</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="livro" items="${livros}">
                        <tr>
                            <td align="right">${livro.id}</td>
                            <td align="left">${livro.nomeLivro}</td>
                            <td align="left">${livro.isbn}</td>
                            <td align="left">${livro.autor}</td>
                            <td align="center">
                                <fmt:formatDate pattern="dd/MM/yyyy" value="${livro.dataPublicacao}"/>
                            </td>
                            <td align="right">
                                <fmt:formatNumber value="${livro.valorLivro}" type="currency"/>
                            </td>
                            <td align="center">
                                <a href="${pageContext.request.contextPath}/LivroExcluir?id=${livro.id}">
                                    <button>Excluir</button>
                                </a>
                            </td>
                            <td align="center">
                                <a href="${pageContext.request.contextPath}/LivroCarregar?id=${livro.id}">
                                    <button>Alterar</button>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script>
    $(document).ready(function() {
        $('#datatable').DataTable({
            "oLanguage": {
                "sProcessing":   "Processando...",
                "sLengthMenu":   "Mostrar _MENU_ registros",
                "sZeroRecords":  "Nenhum registro encontrado.",
                "sInfo":         "Mostrando de _START_ até _END_ de _TOTAL_ registros",
                "sInfoEmpty":    "Mostrando de 0 até 0 de 0 registros",
                "sInfoFiltered": "",
                "sInfoPostFix":  "",
                "sSearch":       "Buscar:",
                "sUrl":          "",
                "oPaginate": {
                    "sFirst":    "Primeiro",
                    "sPrevious": "Anterior",
                    "sNext":     "Seguinte",
                    "sLast":     "Último"
                }
            }
        });
    });
</script>

<%@include file="/footer.jsp"%>
