# Prova de Seleção da ASAP LOG






<!-- ABOUT THE PROJECT -->
## Sobre a prova

Desafio de desenvolvimento Java<br/>
Crie uma API para uma seguradora de veículos.<br/>
Tecnologias que devem ser usadas<br/>
* Java 8
* Spring Boot ou Grails (utilizando Gradle)
* MongoDB 3.4 ou superior

Cadastro de clientes (CRUD completo)
- Deve conter:
* Nome
* CPF
* Cidade
* UF
Todos os dados são obrigatórios
CPF deve ser válido e único

Cadastro de apólices (CRUD completo)
- Deve conter
* Número
* Vigência (início e fim)
* Placa do veículo
* Valor
- Todos os dados são obrigatórios
- Toda apólice deve pertencer a um cliente
- O número da apólice deve ser gerado aleatoriamente e único

Consultar apólice por número (endpoint separado)
* Mostrar todos os dados da apólice
* Informar em campo se a apólice venceu ou não
* Informar em campo quantos dias para vencer, ou há quantos dias venceu

Finalização
* Publicar arquivo JAR para rodar o projeto no GitHub
* Documentar endpoints com ferramentas como Postman, Swagger,Insomnia, etc.
* Publicar no GitHub


<!-- GETTING STARTED -->
## Começando

* Faça checkout deste projeto
* Abra em uma IDE de sua preferência
* Execute o maven para baixar as depedências.

### Pre-requisitos

MongoBD 4.4 ou superior<br/>
Java 8 ou Superior


## Como Usar

* Compile o projeto
* Vá a ate a pasta target do projeto
* Execute o comando:
```JS
  java -jar asap-0.0.1-SNAPSHOT.jar;
   ```
* Abra o navegador e acesse:
```JS
  http://localhost:8080/swagger-ui.html
   ```





<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/othneildrew/Best-README-Template.svg?style=for-the-badge
[contributors-url]: https://github.com/othneildrew/Best-README-Template/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/othneildrew/Best-README-Template.svg?style=for-the-badge
[forks-url]: https://github.com/othneildrew/Best-README-Template/network/members
[stars-shield]: https://img.shields.io/github/stars/othneildrew/Best-README-Template.svg?style=for-the-badge
[stars-url]: https://github.com/othneildrew/Best-README-Template/stargazers
[issues-shield]: https://img.shields.io/github/issues/othneildrew/Best-README-Template.svg?style=for-the-badge
[issues-url]: https://github.com/othneildrew/Best-README-Template/issues
[license-shield]: https://img.shields.io/github/license/othneildrew/Best-README-Template.svg?style=for-the-badge
[license-url]: https://github.com/othneildrew/Best-README-Template/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/othneildrew
[product-screenshot]: images/screenshot.png
