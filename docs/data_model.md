# Modelo de Dados - Le Cheff
## Cheff
| Campo | Tipo   | Observação             |
|-------|--------|------------------------|
| email | String | obrigatório, único, PK | 
| senha | String | obrigatório            |

## Cliente
| Campo    | Tipo    | Observação         |
|----------|---------|--------------------|
| id       | inteiro | gerado pelo banco  | 
| nome     | String  | obrigatório        |
| idade    | inteiro | opcional           | 
| email    | String  | obrigatório, único |
| senha    | String  | obrigatório        | 
| telegone | String  | obrigatório        |

## Endereco
| Campo       | Tipo    | Observação            |
|-------------|---------|-----------------------|
| id          | inteiro | gerado pelo banco, PK |
| cliente_id  | inteiro | obrigatório, FK       |
| cidade      | String  | obrigatório           |
| cep         | String  | obrigatório           |
| rua         | String  | obrigatório           |
| numero      | String  | obrigatório           |
| bairro      | String  | obrigatório           |
| estado      | String  | obrigatório           |
| complemento | String  | opcional              |

## Prato
| Campo        | Tipo    | Observação            |
|--------------|---------|-----------------------|
| id           | inteiro | gerado pelo banco, PK |
| nome         | String  | obrigatório           |
| descricao    | String  | obrigatório           |
| categoria    | String  | obrigatório           |

## Ingrediente
| Campo   | Tipo    | Observação            |
|---------|---------|-----------------------|
| id      | inteiro | gerado pelo banco, PK |
| nome    | String  | obrigatório, único    |
| unidade | String  | obrigatório           |

## PratoIngrediente - junção
| Campo          | Tipo    | Observação      |
|----------------|---------|-----------------|
| prato_id       | inteiro | obrigatório, FK |
| ingrediente_id | inteiro | obrigatório, FK |
| quantidade     | Double  | obrigatório     |

## Pedido
| Campo       | Tipo          | Observação            |
|-------------|---------------|-----------------------|
| id          | inteiro       | gerado pelo banco, PK |
| cliente_id  | inteiro       | obrigatório, FK       |
| endereco_id | inteiro       | obrigatório, FK       |
| qnt_pessoas | inteiro       | obrigatório           |
| observacoes | String        | opcional              |
| data_hora   | LocalDateTime | obrigatório           |
| status      | String        | obrigatório, FK       |

### Status do Pedido - Pendente, Confirmado, Realizado
| Campo  | Tipo    | Observação            |
|--------|---------|-----------------------|
| id     | inteiro | gerado pelo banco, PK | 
| status | String  | obrigatório           | 


## PedidoPrato - quais pratos o cliente escolheu
| Campo     | Tipo    | Observação      |
|-----------|---------|-----------------|
| pedido_id | inteiro | obrigatório, FK |
| prato_id  | inteiro | obrigatório, FK |