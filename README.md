# Star Wars Resistence Social Network

## Sobre o sistema
Este sistema tem como objetivo criar uma rede para facilitar o compartilhamento de
recursos entre os rebeldes, a fim de apoiar na luta contra o Império.
  
O sistema também oferece um mecanismo de reportes de traição. 
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
            "id_rebelde": 2,
            "nome_item": "COMIDA"
        }
    ],
    "traidor": false,
    "quantidade_denuncias_traicao": 0,
    "pontuacao_total": 5
}
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
