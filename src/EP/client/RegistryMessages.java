package EP.client;

import java.rmi.AccessException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class RegistryMessages {

  private Registry registry;
  private Scanner scanner;

  public RegistryMessages(
    Registry registry,
    Scanner scanner
  ){
    this.registry = registry;
    this.scanner = scanner;
  }

  public String getServerName() throws Exception {
    System.out.println("Qual servidor deseja se conectar?");
    try {
      String[] serverNames = this.printServerNames();
      System.out.println("Digite o número do servidor: ");
      int serverNumber = Integer.parseInt(this.scanner.nextLine());
      return serverNames[serverNumber];
    } catch (Exception e) {
      System.out.println("Erro ao listar servidores");
      System.out.println(e.getMessage());
      throw new Exception("Erro ao listar servidores");
    }
  }

  public String[] printServerNames() throws AccessException, RemoteException {
    String[] serverNames = this.registry.list();

    System.out.println("Repositorios disponíveis: ");
    for (int i = 0; i < serverNames.length; i++) {
      System.out.println(i + " - " + serverNames[i]);
    }

    return serverNames;
  }
}
