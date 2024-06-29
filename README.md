# Pizzaria API

Este é um projeto de exemplo de uma API para gerenciar uma pizzaria, construída com Spring Boot. A API permite a manipulação de informações sobre pizzas e pedidos.

## Diagrama de Classes

classDiagram

    class Pizza {
    Long id
    String name
    String description
    Double price
    }

    class Order {
        Long id
        List~Pizza~ pizzas
        Double totalPrice
    }

    class PizzaDTO {
        String name
        String description
        Double price
    }

    class OrderDTO {
        List~Long~ pizzaIds
    }

    class PizzaController {
        -PizzaService pizzaService
        +ResponseEntity~List~ getAllPizzas()
        +ResponseEntity~Pizza~ getPizzaById(Long id)
        +ResponseEntity~Void~ addPizza(PizzaDTO pizzaDTO)
    }

    class OrderController {
        -OrderService orderService
        -PizzaService pizzaService
        +ResponseEntity~List~ getAllOrders()
        +ResponseEntity~Order~ getOrderById(Long id)
        +ResponseEntity~Void~ placeOrder(OrderDTO orderDTO)
    }

    Pizza --> PizzaDTO
    Order --> OrderDTO
    PizzaController --> Pizza
    OrderController --> Order
    OrderController --> Pizza


## Funcionalidades da API
### Pizzas
- Obter todas as pizzas: GET /api/pizzas
- Obter pizza por ID: GET /api/pizzas/{id}
- Adicionar nova pizza: POST /api/pizzas

### Pedidos
- Obter todos os pedidos: GET /api/orders
- Obter pedido por ID: GET /api/orders/{id}
- Fazer um novo pedido: POST /api/orders

## Endpoints

### Obter todas as pizzas: GET /api/pizzas

Retorna uma lista de todas as pizzas disponíveis.

Exemplo de Resposta:

```json
[
{
"id": 1,
"name": "Margherita",
"description": "Tomato, mozzarella, and basil",
"price": 12.99
},
{
"id": 2,
"name": "Pepperoni",
"description": "Pepperoni, tomato sauce, and mozzarella",
"price": 14.99
}
]
```

### Obter pizza por ID: GET /api/pizzas/{id}

Retorna uma pizza específica pelo seu ID.

Exemplo de Resposta:

```json 
{
"id": 1,
"name": "Margherita",
"description": "Tomato, mozzarella, and basil",
"price": 12.99
}
```
### Adicionar nova pizza: POST /api/pizzas

Adiciona uma nova pizza ao sistema.

Exemplo de Requisição:

```json
{
"name": "Quattro Formaggi",
"description": "Mozzarella, gorgonzola, parmesan, and fontina",
"price": 15.99
}
```

### Obter todos os pedidos: GET /api/orders

Retorna uma lista de todos os pedidos realizados.

Exemplo de Resposta:

```json
[
{
"id": 1,
"pizzas": [
{
"id": 1,
"name": "Margherita",
"description": "Tomato, mozzarella, and basil",
"price": 12.99
}
],
"totalPrice": 12.99
}
]
```

### Obter pedido por ID: GET /api/orders/{id}

Retorna um pedido específico pelo seu ID.

Exemplo de Resposta:

```json
{
"id": 1,
"pizzas": [
  {
    "id": 1,
    "name": "Margherita",
    "description": "Tomato, mozzarella, and basil",
    "price": 12.99
  }
],
"totalPrice": 12.99
}
```
### Fazer um novo pedido: POST /api/orders

Faz um novo pedido com uma lista de IDs de pizzas.

Exemplo de Requisição:

```json
{
"pizzaIds": [1, 2]
}
```

### Configuração do Swagger
A API está configurada para usar Swagger UI para documentação. Para acessar a documentação da API, abra o navegador e vá para:

```bash
http://localhost:8080/documentation.html
```

### Dependências
- spring-boot-starter-data-jpa
- spring-boot-starter-web
- springdoc-openapi-starter-webmvc-ui:2.5.0
- flyway-core
- flyway-database-postgresql
- spring-boot-devtools
- postgresql
- spring-boot-starter-test
- junit-platform-launcher

## Contribuindo
Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou pull requests.

## Licença
Este projeto está licenciado sob a licença MIT.