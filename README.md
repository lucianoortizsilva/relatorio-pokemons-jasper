### Tech Stack
- Java 17
- Jasper Reports 6.20
- Spring Boot 3
- Thymeleaf
- Docker
- MySQL 8

### O que é ?
Um exemplo de aplicação reponsável por gerar arquivos PDF, utilizando a lib `jasperreports`.

Para isso criei 2 projetos em Java: 
- relatorio-pokemon-jaspersoft: Um projeto utilizado para criar o modelo do PDF, via Jasper Soft Studio.
- relatorio-pokemon-web: Uma aplicação WEB, responsável por disponibilizar uma simples página em HTML, para realizar pesquisas e fazer o download do dado desejado. 

### Como rodar ?
> 1º Inicializar banco de dados, via docker-compose:
- Na raiz do repositório, execute **`docker-compose up`**

<br/>

> 2º Build e Deploy `relatorio-pokemon-web`:
- No diretório relatorio-pokemon-web: **`mvn clean package`**
- No diretório relatorio-pokemon-web: **`mvn spring-boot:run`**

<br/>

### Como testar ?

> Acessar via browser:  `http://localhost:8080`