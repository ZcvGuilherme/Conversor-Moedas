# 💱 Conversor de Moedas em Java

Este é um simples conversor de moedas feito em Java, utilizando Maven como gerenciador de dependências. Ele consome dados da [AwesomeAPI](https://docs.awesomeapi.com.br/api-de-moedas) para realizar as conversões em tempo real.

## ✅ Requisitos

- **Java** 21 ou superior  
- **Maven** para gerenciamento do projeto e das dependências

## 📦 Dependências (definidas no `pom.xml`)

```xml
<dependencies>
  <dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.10.1</version>
  </dependency>

  <dependency>
    <groupId>io.github.cdimascio</groupId>
    <artifactId>dotenv-java</artifactId>
    <version>3.0.0</version>
  </dependency>
</dependencies>
```

## 📁 Estrutura do Projeto

```
.
├── .settings/
├── src/
│   └── main/
│       └── java/
│           ├── elements/
│           │   ├── Coin.java
│           │   └── Flag.java
│           ├── network/
│           │   └── APIConnection.java
│           └── principal/
│               ├── Main.java
│               └── Util.java
├── .gitignore
├── pom.xml
└── readme.md
```

## 🌐 API de Moedas

Este projeto consome a [API de Moedas da AwesomeAPI](https://docs.awesomeapi.com.br/api-de-moedas).  
Você precisa obter uma **chave de API gratuita** e adicioná-la em um arquivo `.env` na raiz do projeto com o seguinte conteúdo:

```
API_KEY=sua_chave_aqui
```

## 🔧 Funcionamento

- O `APIConnection` se conecta à API, recebendo no construtor a **moeda base** para conversão.
- Você pode alterar a moeda base a qualquer momento durante a execução.
- O método `connect(Flag coin)` retorna um objeto `Coin` com os dados preenchidos automaticamente usando a biblioteca `Gson`.

## 🧩 Classes Principais

### `Flag.java` (enum)

Define moedas com seus códigos, bandeiras e nomes legíveis:

```java
BRL("BRL", "🇧🇷", "Real Brasileiro"),
USD("USD", "🇺🇸", "Dólar Americano");
```

Essa estrutura torna o código flexível e fácil de estender com novas moedas.

### `Coin.java`

Classe que armazena os dados retornados da API, como valor de compra, venda, variação percentual, etc.

### `APIConnection.java`

Responsável por fazer a requisição à API. Usa `Gson` para transformar o JSON em objetos Java automaticamente.

### `Util.java`

Contém utilitários como:
- Estilização dos prints
- Tratamento de informações
- Conexão entre os elementos (`Coin`, `Flag`, etc.)

### `Main.java`

Executa o **loop principal do programa**, mostra o menu e interage com o usuário.

## 📌 Observações

- Todo o projeto gira em torno do `enum Flag`, o que facilita a manipulação de diferentes moedas.
- O sistema é simples e modular, ideal para ser expandido com novas funcionalidades futuramente.

---

Feito com ☕ por [Guilherme](https://github.com/ZcvGuilherme)
