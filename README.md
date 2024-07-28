# Projeto API Controle de Gastos

```mermaid
classDiagram
  class User {
    +int id
    +string name
    +string email
    +string password
    +BigDecimal balance
  }

  class Transaction {
    +int id
    +string description
    +BigDecimal amount
    +Date date
    +string category
    +int userId
  }

  class Expense {
    +string type
  }

  class Income {
    +string source
  }

  User "1" -- "0..*" Transaction : has
  Transaction <|-- Expense
  Transaction <|-- Income

```
