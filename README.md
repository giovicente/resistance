# Star Wars Resistence Social Network

## Sobre o sistema
Este sistema tem como objetivo criar uma rede para facilitar o compartilhamento de
recursos entre os rebeldes, a fim de apoiar na luta contra o Império.
  
O sistema também oferece um mecanismo de reportes de traição. 
___

## Documentação dos endpoints
 
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
            "id_item": 1,
            "id_rebelde": 2,
            "nome_item": "ARMA"
        }
    ],
    "traidor": false,
    "quantidade_denuncias_traicao": 0,
    "pontuacao_total": 8
}
```
**Response 400 - falha no cadastro: obrigatório informar o nome, gênero e localização**
```json
{
    "timestamp": "2021-06-26T14:38:16.369+0000",
    "status": 400,
    "error": "Bad Request",
    "message": "O preenchimento do nome é obrigatório",
    "path": "/"
}
```
```json
{
    "timestamp": "2021-06-26T14:38:16.369+0000",
    "status": 400,
    "error": "Bad Request",
    "message": "O preenchimento do gênero é obrigatório",
    "path": "/"
}
```
```json
{
    "timestamp": "2021-06-26T14:38:16.369+0000",
    "status": 400,
    "error": "Bad Request",
    "message": "O preenchimento da localização é obrigatório",
    "path": "/"
}
```
---
### PUT /rebeldes/{id_rebelde}
Atualiza a localização de um rebelde de acordo com o seu identificador. Na rota abaixo,
estamos atualizando a localização do rebelde cujo id é igual a 1.

*Rota Request (considerando a subida da aplicação na porta 8080)*: localhost:8080/rebeldes/1

**Exemplo de Request Body**
```json
{
  "localizacao": {
    "latitude": 40.366633,
    "longitude": 74.640832,
    "nome_base_galaxia": "Dagobah"   
  }
}
```
**Response 204 (No Content) - localização alterada com sucesso**
