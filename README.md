# Projeto forum 
Tecnologias: java, spring rest, jwt, spring security, spring data jpa, mysql

## EndPoints

### LISTAR TODOS OS TÓPICOS
#### EndPoint PÚBLICO
#### Method GET
/topicos

### LISTA COM PAGINAÇÃO E ORDENAÇÃO
#### page -> número de página (parametro opcional)
#### size -> total de itens para recuperar (parametro opcional)
#### sort -> ordena de acordo o com o atributo informado (parametro opcional)
#### Method GET
/topicos?page=0&size=10&sort=id,asc

### DETALHAR TÓPICO
#### EndPoint PÚBLICO
#### {id} -> Representa um id de um tópico
/topicos/{id}

### CADASTRAR
#### EndPoint PRIVADO - PRECISA SE AUTENTICAR
#### Method POST
/topicos

##### objeto que deve ser enviado 
##### objeto json
{
    "titulo": "valor",
    "mensagem": "valor",
    "nomeCurso": "o valor deve ser somente cursos cadastrados"
}

### ATUALIZAR
#### EndPoint PRIVADO - PRECISA SE AUTENTICAR
#### Method PUT
#### {id} -> Representa um id de um tópico
/topicos/{id}

##### objeto que deve ser enviado
##### objeto json
{
    "titulo": "valor",
    "mensagem": "valor"
}

### DELETAR
#### EndPoint PRIVADO - PRECISA SE AUTENTICAR
#### Method DELETE
#### {id} -> Representa um id de um tópico
/topicos/{id}


### AUTENTICAR
#### autenticação via JWT - É retornado um objeto com o token e o tipo de autenticação
#### EndPoint PÚBLICO 
#### Method POST
/auth

##### objeto que deve ser enviado 
##### objeto json
{
    "email": "aluno@email.com",
    "senha": "123456"
}



