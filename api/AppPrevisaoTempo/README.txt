Para realizar o deploy da aplicação no WildFly 9:
> mvn clean compile package -P development wildfly:deploy-only

Atualizar o webservice:
wsimport -keep -p com.net.webservicex http://www.webservicex.net/globalweather.asmx?WSDL

Para acesso ao banco de dados do postgresql é necessário possuir o usuário postgres e senha root
