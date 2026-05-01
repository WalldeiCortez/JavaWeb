<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"/>
<jsp:include page="menu.jsp"/>

<div class="row justify-content-center">
    <div class="col-md-7">
        <div class="card shadow-sm">
            <div class="card-header">
                <h4 class="mb-0">➕ Cadastrar Novo Livro</h4>
            </div>
            <div class="card-body">

                
                <c:if test="${not empty mensagem}">
                    <div class="alert alert-danger">${mensagem}</div>
                </c:if>

                <form action="${pageContext.request.contextPath}/LivroCadastrar"
                      method="POST">

                    <div class="form-group">
                        <label for="nomeLivro">Nome do Livro <span class="text-danger">*</span></label>
                        <input type="text" class="form-control" id="nomeLivro"
                               name="nomeLivro" maxlength="200"
                               placeholder="Ex: Clean Code" required>
                    </div>

                    <div class="form-group">
                        <label for="isbn">ISBN <span class="text-danger">*</span></label>
                        <input type="text" class="form-control" id="isbn"
                               name="isbn" maxlength="20"
                               placeholder="Ex: 9780132350884" required>
                    </div>

                    <div class="form-group">
                        <label for="autor">Autor <span class="text-danger">*</span></label>
                        <input type="text" class="form-control" id="autor"
                               name="autor" maxlength="150"
                               placeholder="Ex: Robert C. Martin" required>
                    </div>

                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="dataPublicacao">Data de Publicação <span class="text-danger">*</span></label>
                            <input type="date" class="form-control" id="dataPublicacao"
                                   name="dataPublicacao" required>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="valorLivro">Valor (R$) <span class="text-danger">*</span></label>
                            <input type="number" class="form-control" id="valorLivro"
                                   name="valorLivro" min="0" step="0.01"
                                   placeholder="Ex: 89.90" required>
                        </div>
                    </div>

                    <hr>
                    <div class="d-flex justify-content-between">
                        <a href="${pageContext.request.contextPath}/LivroListar"
                           class="btn btn-secondary">
                            ← Voltar
                        </a>
                        <button type="submit" class="btn btn-success">
                            💾 Salvar Livro
                        </button>
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>
