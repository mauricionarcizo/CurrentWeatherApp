Para realizar o deploy da aplicação no WildFly 9:
> mvn clean compile package -P development wildfly:deploy-only

Atualizar o webservice:
wsimport -keep -p com.net.webservicex http://www.webservicex.net/globalweather.asmx?WSDL
