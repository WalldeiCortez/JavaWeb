<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"/>
<jsp:include page="menu.jsp"/>

<div class="row">
    <div class="col-12">
        <div class="card shadow-sm">
            <div class="card-header d-flex justify-content-between align-items-center">
                <h4 class="mb-0">📋 Livros Cadastrados</h4>
                <a href="${pageContext.request.contextPath}/LivroCadastrar"
                   class="btn btn-success btn-sm">
                    ➕ Novo Livro
                </a>
            </div>
            <div class="card-body">

                
                <c:if test="${not empty mensagem}">
                    <div class="alert alert-danger">${mensagem}</div>
                </c:if>

                
                <table id="tabelaLivros" class="table table-bordered table-striped table-hover">
                    <thead class="thead-dark">
                        <tr>
                            <th>#</th>
                            <th>Nome do Livro</th>
                            <th>ISBN</th>
                            <th>Autor</th>
                            <th>Data Publicação</th>
                            <th>Valor (R$)</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="livro" items="${listaLivros}">
                            <tr>
                                <td>${livro.id}</td>
                                <td>${livro.nomeLivro}</td>
                                <td>${livro.isbn}</td>
                                <td>${livro.autor}</td>
                                <td>
                                    <fmt:parseDate value="${livro.dataPublicacao}"
                                                   pattern="yyyy-MM-dd" var="dataParsed"/>
                                    <fmt:formatDate value="${dataParsed}" pattern="dd/MM/yyyy"/>
                                </td>
                                <td>
                                    <fmt:formatNumber value="${livro.valorLivro}"
                                                      type="currency" currencySymbol="R$ "/>
                                </td>
                                <td class="table-actions">
                                    <a href="${pageContext.request.contextPath}/LivroAlterar?id=${livro.id}"
                                       class="btn btn-warning btn-sm" title="Editar">
                                        ✏️ Editar
                                    </a>
                                    <a href="#"
                                       onclick="confirmarExclusao(${livro.id}, '${livro.nomeLivro}')"
                                       class="btn btn-danger btn-sm" title="Excluir">
                                        🗑️ Excluir
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>

                        <c:if test="${empty listaLivros}">
                            <tr>
                                <td colspan="7" class="text-center text-muted">
                                    Nenhum livro cadastrado.
                                    <a href="${pageContext.request.contextPath}/LivroCadastrar">
                                        Cadastre o primeiro!
                                    </a>
                                </td>
                            </tr>
                        </c:if>
                    </tbody>
                </table>

            </div>
        </div>
    </div>
</div>

<script>
    // Inicializa o DataTables
    $(document).ready(function () {
        $('#tabelaLivros').DataTable({
            language: {
                url: '//cdn.datatables.net/plug-ins/1.10.22/i18n/Portuguese-Brasil.json'
            }
        });
    });

    // Confirmação de exclusão com SweetAlert2
    function confirmarExclusao(id, nome) {
        Swal.fire({
            title: 'Confirmar Exclusão',
            text: 'Deseja realmente excluir o livro "' + nome + '"?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#d33',
            cancelButtonColor: '#6c757d',
            confirmButtonText: 'Sim, excluir!',
            cancelButtonText: 'Cancelar'
        }).then((result) => {
            if (result.isConfirmed) {
                window.location.href = '${pageContext.request.contextPath}/LivroExcluir?id=' + id;
            }
        });
    }
</script>

<jsp:include page="footer.jsp"/>
