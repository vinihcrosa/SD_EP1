# EP1 de sistemas distribuidos

## Compilação

Para compilar o programa é necessário estar na pasta src, então execute o
comando:

```shell
javac **/*.java
```

## Execução do servidor de nomes

Para executar o servidor de nomes do Java, ainda na pasta src rode o comando:

```shell
java EP.server.CentralNameServer
```

### Adicionar PartRepositorys no servidor de nomes

Para adicoinar mais um PartRepository ao servidor primeiro precisa se certificar
de que a JVM que sera executada tenha acesso ao servidor de nomes, seja local
ou pela rede.

Tendo esse requisito cumprido, na mesma pasta src basta rodar o comando:

```shell
java EP.server.ServerRebind <repositoryName> <serverAddress> <serverPort>
```

Onde os parametros significam:

- repositoryName: Nome do PartRepository no servdor de nomes
- serverAddress: Endereço do servidor de nomes
- serverPort: Porta do servidor de nomes.

Exemplo de execuçãoÇ

```shell
java EP.server.ServerRebind repository_2 localhost 1091
```
