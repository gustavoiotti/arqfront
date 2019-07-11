<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Gerencia Curso</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>

    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>

<body>
    <div class="container">
        <div class="jumbotron">
            <h1>Gerenciamento de Curso</h1>
            <p> :) </p>
        </div>
        <div class="row">
            <div class="col">
                <form action="/curso/alterar" method="post">
                    <div class="form-group">
                        <label for="nome">Nome:</label>
                        <input value="${(cursoAtual.nome)!}" name="nome" type="text" class="form-control" id="nome">
                    </div>
                    <div class="form-group">
                         <label for="departamento">Departamento:</label>
                         <input value="${(cursoAtual.departamento)!}"  name="departamento" type="text" class="form-control" id="departamento">
                    </div>

                    <input type="hidden" name="id" value="${(cursoAtual.id)!}">

                    <button type="submit" class="btn btn-warning">Alterar</button>
                </form>

            </div>
        </div>
        <div class="row">
            <div class="col">
                <table class="table table-striped table-hover">
                    <thead class="thead-dark">
                        <tr>
                            <th>Nome</th>
                            <th>Departamento</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <#list professores as curso>
                            <tr>
                                <td>${curso.nome}</td>
                                <td>${curso.departamento}</td>
                                <td>
                                    <a href="/curso/prepara-alterar?id=${curso.id}">Alterar</a>
                                    <a href="/curso/excluir?id=${curso.id}">Excluir</a>
                                </td>
                            </tr>        
                        </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>

</html>