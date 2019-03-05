Algumas informações sobre os micro-serviços:

Gerais:

1 - Foi utilizado o suite spring para praticamente todas as necessidades dos micro serviços, uma vez que é simples, facil, legivel, performatico e é uma tecnologia de ponta atualmente e muito promissora.
2 - A mensageria utilizada foi o ActiveMQ da Apache, nenhuma configuração inicial foi alterada (porta, etc) para facilitar os testes do avaliador. Ele pode ser instalado facilmente baixando diretamente do link http://activemq.apache.org/download.html, descompactando o zip e executando o ${ActiveMQ_Home}\bin\win64\activemq.bat ou simplismente subindo a imagem em docker do mesmo, disponivel em https://hub.docker.com/r/webcenter/activemq/.
3 - Todas as configurações das filas, do mq, de acesso ao banco, configurações de email e SMS estão nos arquivos application.properties dos projetos.
4 - Os scripts de criação da tabela fato e de um facilitador de inserção de registros estão no arquivo disponivel em caseTecnicoItau\scripts\SCRIPTS.sql.
5 - Utilizei o lombok (https://projectlombok.org/setup/eclipse e https://projectlombok.org/features/all) para fins de legibilidade de código.


distributor-notify:

1 - Para fins de facilitar os testes, eu mantive o metodo como get e o serviço aberto. Em um cenário corporativo, esse webservice seria acessivel apenas via post e o spring security seria implementado para garantir a segurança e a exclusividade do acesso pelo serviço do cluster.
2 - O serviço roda em threads para que o consumo e a distribuição das notificações nas filas aconteça o mais rapido possivel. Quanto mais recurso mais rapido esse processo acontece (ele verificar o total de threads disponivel e gerencia o uso das que estão livres para não estourar os recursos).
3 - No meu teste local (4 threads disponivel na minha maquina, compartilhando com SQLServer e alguns outros recursos), testei esse serviço com 1.000.000 de registros e o tempo total ficou em pouco menos de 12 minutos, em um ambiente produtivo, o esperado é que rode em 50% ou menos.
4 - Pelo fato de conforme é executado ja alimentar as filas de consumo das notificações, ou seja, assim que começa a rodar os outros micros-serviços ja começam a consumir as filas, a performance ao meu ver está OK, mesmo considerando que essa tabela provavelmente terá alguns milhões de registros.


email-sender:

1 - O Foi criado um email no gmail exclusivamente para esse case técnico, então, para fins de testes, não há necessidade de alterar esses parametros.
2 - Pelo fato do item 1, é possivel logar diretamente pelo browser no gmail e verificar o envio (e falha de recebimento dos destinários caso não existam os e-mails) dos e-mails.
3 - Conforme explicado na apresentação.ppt, esse serviço tem seu tempo variavel de acordo com a latencia de acesso ao servidor de email e deve ser executado em mais de uma instancia para garantir a fluidez da fila. Usando o gmail, que é um serviço lento pra conexão e envio, 1 unica instancia do micro-serviço estava enviando 2 e-mails por segundo em média. Em um host de e-mail interno, esse numero de envios de e-mail será muito maior. Seria necessário entender o ambiente no qual as instancias desse serviço rodariam para avaliar se a performance está dentro ou aquém do esperado.


sms-sender:

1 - Micro-serviço feito porém não é possivel ser testado, uma vez que seria necessário um serviço de envio de sms gratuito disponivel.
2 - Utilizei a twilio (api paga) como api de envio por sua simplicidade e eficiência (bem avaliada pela comunidade), devido a isso, esse é o micro-serviço mais simples de entender.
3 - Mesmo não sendo possivel o teste de envio e recebimento de SMS, a ideia do micro-serviço pode ser avaliada, em um cenário corporativo, seria alterado apenas o twilio pelo serviço da corporação.



Para executar os serviços:

1 - Garantir que a base, a tabela e os registros foram criados corretamente.
2 - Garantir que todas as configurações nos application.properties estão corretas (Se atentar ao diretório de logs, D://logs, caso a partição não exista colocar um diretório existente).
3 - Iniciar o ActiveMQ conforme explicado anteriormente.
4 - Executar os comandos na raiz do projeto email-sender: 
	- mvn clean install
	- java -jar target\email-sender.jar
5 - Verificar no ActiveMQ que a fila itau.emailsender.queue foi criada e esta diponivel (http://localhost:8161/admin/queues.jsp admim/admin menu Queues).
6 - Executar os comandos na raiz do projeto sms-sender: 
	- mvn clean install
	- java -jar target\sms-sender.jar
7 - Verificar no ActiveMQ que a fila itau.smssender.queue foi criada e esta diponivel (http://localhost:8161/admin/queues.jsp admim/admin menu Queues).
8 - Executar os comandos na raiz do projeto distributor-notify: 
	- mvn clean install
	- java -jar target\distributor-notify.jar
9 - Via browser acessar: http://localhost:8083/distributor/start
10 - Validar que as filas no MQ estão recebendo e sendo consumida.
11 - Para o serviço de e-mails, se não forem alteradas as configurações do gmail, é preciso garantir que a rede não bloqueie o acesso ao gmail.
12 - O Serviço de sms irá apresentar erros de log referentes ao twilio apenas.