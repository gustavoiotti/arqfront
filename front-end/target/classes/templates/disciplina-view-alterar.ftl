<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Gerencia Disciplina</title>
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
            <h1>Gerenciamento de Disciplina</h1>
            <p>Essa página é responsável por fazer o geranciamento de disciplinas. </p>
        </div>
        <div class="row">
            <div class="col">
                <form action="/disciplina/alterar" method="post">
                    <div class="form-group">
                        <label for="nome">Nome:</label>
                        <input value="${(disciplinaAtual.nome)!}" name="nome" type="text" class="form-control" id="nome">
                    </div>
                    <div class="form-group">
                        <label for="professor">Professor:</label>
                        <input value="${(disciplinaAtual.professor)!}"  name="professor" type="text" class="form-control" id="professor">
                    </div>
                    <div class="form-group">
                         <label for="curso">Curso:</label>
                         <input value="${(disciplinaAtual.curso)!}"  name="curso" type="text" class="form-control" id="curso">
                    </div>
                    <div class="form-group">
                        <label for="campus">Campus:</label>
                        <input value="${(disciplinaAtual.campus)!}"  name="campus" type="text" class="form-control" id="campus">
                    </div>

                    <input type="hidden" name="id" value="${(disciplinaAtual.id)!}">

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
                            <th>Professor</th>
                            <th>Curso</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <#list disciplinas as disciplina>
                            <tr>
                                <td>${disciplina.nome}</td>
                                <td>${disciplina.professor}</td>
                                <td>${disciplina.curso}</td>
                                <td>
                                    <a href="/disciplina/prepara-alterar?id=${disciplina.id}">Alterar</a>
                                    <a href="/disciplina/excluir?id=${disciplina.id}">Excluir</a>
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