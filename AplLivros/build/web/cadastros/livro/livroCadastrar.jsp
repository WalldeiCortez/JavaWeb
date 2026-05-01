<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>

<div class="container-fluid">
    <h1 class="h3 mb-2 text-gray-800">Livros</h1>
    <p class="mb-4">Manutenção de Livros</p>

    <div class="card shadow mb-4">
        <div class="card-body">

            
            <input type="hidden" id="id" value="${livro.id}">

            <div class="form-group">
                <label>Nome do Livro</label>
                <input type="text" class="form-control" id="nomeLivro"
                       maxlength="200" value="${livro.nomeLivro}"
                       placeholder="Nome do livro">
            </div>

            <div class="form-group">
                <label>ISBN</label>
                <input type="text" class="form-control" id="isbn"
                       maxlength="20" value="${livro.isbn}"
                       placeholder="Ex: 9780132350884">
            </div>

            <div class="form-group">
                <label>Autor</label>
                <input type="text" class="form-control" id="autor"
                       maxlength="150" value="${livro.autor}"
                       placeholder="Nome do autor">
            </div>

            <div class="form-group">
                <label>Data de Publicação</label>
                <input type="date" class="form-control" id="dataPublicacao"
                       value="<fmt:formatDate pattern='yyyy-MM-dd' value='${livro.dataPublicacao}'/>">
            </div>

            <div class="form-group">
                <label>Valor (R$)</label>
                <input type="text" class="form-control money" id="valorLivro"
                       value="${livro.valorLivro}"
                       placeholder="0,00">
            </div>

            <hr>
            <button class="btn btn-success" onclick="salvar()">Salvar</button>
            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/LivroListar">Cancelar</a>

        </div>
    </div>
</div>

<script>
    // Aplica máscara de dinheiro ao carregar
    $(document).ready(function () {
        $('.money').maskMoney({prefix: 'R$ ', allowNegative: false, thousands: '.', decimal: ','});
    });

    function salvar() {
        var id            = $('#id').val();
        var nomeLivro     = $('#nomeLivro').val();
        var isbn          = $('#isbn').val();
        var autor         = $('#autor').val();
        var dataPublicacao = $('#dataPublicacao').val();
        var valorLivro    = $('#valorLivro').val();

        $.ajax({
            type:     'POST',
            url:      '${pageContext.request.contextPath}/LivroCadastrar',
            data:     {
                id:             id,
                nomeLivro:      nomeLivro,
                isbn:           isbn,
                autor:          autor,
                dataPublicacao: dataPublicacao,
                valorLivro:     valorLivro
            },
            success: function (retorno) {
                if (retorno == '1') {
                    Swal.fire({
                        title: 'Sucesso!',
                        text:  'Livro salvo com sucesso.',
                        icon:  'success'
                    }).then(function () {
                        window.location.href = '${pageContext.request.contextPath}/LivroListar';
                    });
                } else if (retorno == '5') {
                    Swal.fire('Atenção', 'Preencha todos os campos obrigatórios.', 'warning');
                } else {
                    Swal.fire('Erro', 'Não foi possível salvar o livro.', 'error');
                }
            },
            error: function () {
                Swal.fire('Erro', 'Falha na comunicação com o servidor.', 'error');
            }
        });
    }
</script>

<%@include file="/footer.jsp"%>
