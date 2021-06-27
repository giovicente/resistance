# Star Wars Resistence Social Network

## Sobre o sistema
Este sistema tem como objetivo criar uma rede para facilitar o compartilhamento de
recursos entre os rebeldes, a fim de apoiar na luta contra o Império.
  
O sistema também oferece um mecanismo de reportes de traição. 

## Sobre os testes
Por decisão de projeto / timming, inicialmente optei por incluir uma collection do Postman
para relização dos testes de integração, em detrimento de fazer as (importantíssimas) classes de teste unitário.
___

## Documentação dos endpoints

### POST /itens
Cadastra os itens em um repositório, que será usado para calcular as pontuações dos
rebeldes de acordo com o valor de cada item.

*Rota Request (considerando a subida da aplicação na porta 8080)*: localhost:8080/itens

**Exemplo de Request Body**
```json
{
    "nome": "ARMA",
    "pontos": 4
}
```
**Response 201 - item cadastrado com sucesso, dados do cadastro retornados**
```json
{
    "id": 1,
    "nome": "ARMA",
    "pontos": 4
}
```
--- 
### POST /rebeldes
Efetua o cadastro de um rebelde à rede.

*Rota Request (considerando a subida da aplicação na porta 8080)*: localhost:8080/rebeldes 

**Exemplo de Request Body**
```json
{
  "nome": "Jyn Erso",
  "genero": "FEMININO",
  "localizacao": {
    "latitude": 42.443087,
    "longitude": 76.488707,
    "nome_base_galaxia": "Jedha"   
  },
  "inventario": [
    {
      "nome_item": "AGUA",
      "quantidade": 2
    },
    {
      "nome_item": "COMIDA",
      "quantidade": 1
    }   
  ]
}
```
**Response 201 - rebelde cadastrado com sucesso, dados do cadastro retornados**
```json
{
    "id": 1,
    "nome": "Jyn Erso",
    "genero": "FEMININO",
    "localizacao": {
        "id": 1,
        "latitude": 42.443087,
        "longitude": 76.488707,
        "id_rebelde": 1,
        "nome_base_galaxia": "Jedha"
    },
    "inventario": [
        {
            "id": 1,
            "quantidade": 2,
            "id_item": 3,
            "id_rebelde": 1,
            "nome_item": "AGUA"
        },
        {
            "id": 2,
            "quantidade": 1,
            "id_item": 4,
            "id_rebelde": 1,
            "nome_item": "COMIDA"
        }
    ],
    "traidor": false,
    "quantidade_denuncias_traicao": 0,
    "pontuacao_total": 5
}
```

### GET /rebeldes
Rota simples criada para listar todos os rebeldes e prover uma consulta rápida de sua pontuação
e também validar se o rebelde é de fato um aliado ou um traidor. 

*Rota Request (considerando a subida da aplicação na porta 8080)*: localhost:8080/rebeldes

**Response 200 - lista com a relação de rebeldes**
```json
[
    {
        "id": 1,
        "nome": "Jyn Erso",
        "genero": "FEMININO",
        "traidor": false,
        "quantidade_denuncias_traicao": 0,
        "pontuacao_total": 5
    },
    {
        "id": 2,
        "nome": "Han Solo",
        "genero": "MASCULINO",
        "traidor": false,
        "quantidade_denuncias_traicao": 0,
        "pontuacao_total": 8
    }
]
```

---
### PUT /localizacoes/{id}
Atualiza a localização de um rebelde de acordo com o seu identificador. Na rota abaixo,
estamos atualizando a localização de id = 1 e do rebelde cujo id é igual a 1.

*Rota Request (considerando a subida da aplicação na porta 8080)*: localhost:8080/localizacoes/1

**Exemplo de Request Body**
```json
{
    "id_rebelde": 1,
    "latitude": 40.366633,
    "longitude": 74.640832,
    "nome_base_galaxia": "Dagobah"   
}
```
**Response 200 (OK) - localização alterada com sucesso**
```json
{
    "id": 1,
    "latitude": 40.366633,
    "longitude": 74.640832,
    "id_rebelde": 1,
    "nome_base_galaxia": "Dagobah"
}
```
**Response 404 - id da localização não encontrado na base**

Na mensagem de retorno, o path contém o id que foi informado na entrada.
```json
{
    "timestamp": "2021-06-27T13:15:41.670+00:00",
    "status": 404,
    "error": "Not Found",
    "path": "/localizacoes/1"
}
```
---
### PUT /rebeldes/denunciar/{id}
Denuncia um rebelde por traição. O retorno são os dados do rebelde, já considerando a quantidade
de denúncias atualizada. Após 3 denúncias, o campo que indica se o rebelde é traidor ou não passa a 
ser definido como verdadeiro.

*Rota Request (considerando a subida da aplicação na porta 8080)*: localhost:8080/rebeldes/denunciar/1

**Response 200 (OK) - Denúncia realizada com sucesso**
```json
{
    "id": 1,
    "nome": "Jyn Erso",
    "genero": "FEMININO",
    "traidor": false,
    "quantidade_denuncias_traicao": 1,
    "pontuacao_total": 5
}
```
**Response 200 (OK) - Terceira denúncia recebida com sucesso e marcação de traidor definida como verdadeira**
```json
{
    "id": 1,
    "nome": "Jyn Erso",
    "genero": "FEMININO",
    "traidor": true,
    "quantidade_denuncias_traicao": 3,
    "pontuacao_total": 5
}
```
**Response 404 - id do rebelde não encontrado na base**

Na mensagem de retorno, o path contém o id que foi informado na entrada.
```json
{
    "timestamp": "2021-06-27T13:15:41.670+00:00",
    "status": 404,
    "error": "Not Found",
    "path": "/rebeldes/reportar/1"
}

```
---
### GET relatorios/traidores
Retorna o percentual de traidores obtidos na base.

*Rota Request (considerando a subida da aplicação na porta 8080)*: localhost:8080/relatorios/traidores

**Response 200 (OK) - Relatório retornado com sucesso**
```json
{
    "percentual_traidores": 25.0
}
```

### GET relatorios/rebeldes
Retorna o percentual de rebeldes obtidos na base.

*Rota Request (considerando a subida da aplicação na porta 8080)*: localhost:8080/relatorios/rebeldes

**Response 200 (OK) - Relatório retornado com sucesso**
```json
{
    "percentual_rebeldes": 75.0
}
```

### GET relatorios/pontos_perdidos
Retorna a quantidade de pontos perdidos devido à rebeldes traidores.

*Rota Request (considerando a subida da aplicação na porta 8080)*: localhost:8080/relatorios/pontos_perdidos

**Response 200 (OK) - Relatório retornado com sucesso**
```json
{
    "pontos_perdidos_traicao": 3
}
```

### GET relatorios/media_itens
Retorna a média de itens por rebelde.

*Rota Request (considerando a subida da aplicação na porta 8080)*: localhost:8080/relatorios/media_itens

**Response 200 (OK) - Relatório retornado com sucesso**
```json
{
    "media_armas": 0.0,
    "media_municao": 1.0,
    "media_agua": 0.5,
    "media_comida": 0.5
}
```