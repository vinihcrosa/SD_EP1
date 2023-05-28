package EP.client;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

import EP.IPartRepository;
import EP.server.Part;
import EP.server.PartData;
import EP.server.PartInventory;

public class Client {
    private Registry registry;
    private IPartRepository partRepository;
    private Scanner scanner;
    private RegistryMessages registryMessages;
    private Part currentPart;
    private PartInventory subParts;

    public static void main(String[] args) throws RemoteException{
        Client client = new Client();
        client.setup(args);
        client.runCLI(args);
    }

    public void runCLI(String[] args) {
        if(args.length != 2) {
            System.out.println("Usage: java Client <host> <port>");
            return;
        }
        try {
            while (true) {
                System.out.println("Enter command: ");
                String command = scanner.nextLine();

                /*
                 * Comandos a serem implementados:
                 * showP - mostra uma peca do repositorio atual
                 * clear - limpa a lista de subpecas corrente
                 * addSubPart - adiciona uma subpeca a lista de subpecas corrente
                 * addP - adiciona uma peca ao repositorio atual
                 */

                switch (command) {
                    case "bind":
                        this.setRegistry();
                        break;
                    case "listParts":
                        this.listParts();
                        break;
                    case "addSubPart":
                        this.addSubPart();
                        break;
                    case "listSubParts":
                        this.listSubParts();
                        break;
                    case "help":
                        this.help();
                        break;
                    case "exit":
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Comando invalido");
                        break;
                }

                this.endCommand();
            }
        } catch (Exception e) {
            System.out.println("Client failed to start!");
        }
    }

    private void endCommand() {
        int quantidade = 10; // Número de caracteres "-" a serem impressos

        for (int i = 0; i < quantidade; i++) {
            System.out.print("-");
        }

        System.out.println(); // Pular linha após a impressão dos caracteres "-"
    }

    private void help() {
        System.out.println();
        System.out.println("Comandos disponiveis:");
        System.out.println("bind - conecta ao repositorio");
        System.out.println("listParts - lista as pecas do repositorio");
        System.out.println("addSubPart - adiciona uma subpeca a lista de subpecas corrente");
        System.out.println("listSubParts - lista as subpecas da peca corrente");
        System.out.println("help - mostra os comandos disponiveis");
        System.out.println("exit - sai do programa");
    }

    private void listSubParts() {
        System.out.println("Subparts:");
        if(this.subParts.getSubPartsCount() == 0) {
            System.out.println("Nenhuma subpart adicionada ainda!");
        }
        for(PartData subpart : this.subParts.getSubParts()) {
            System.out.println(subpart.toString());
        }
    }

    protected void setup(String[] args) throws NumberFormatException, RemoteException {
        this.registry = LocateRegistry.getRegistry(args[0], Integer.parseInt(args[1]));
        this.scanner = new Scanner(System.in);
        this.registryMessages = new RegistryMessages(this.registry, this.scanner);
        this.setRegistry();
        this.subParts = new PartInventory();
        this.currentPart = null;

    }

    protected void setRegistry() {
        try {
            String serverName = this.registryMessages.getServerName();
            this.partRepository = (IPartRepository) this.registry.lookup(serverName);
        } catch (Exception e) {
            System.out.println("Failed to get registry!");
            throw new RuntimeException("Failed to get registry!");
        }
    }

    protected void listParts() throws RemoteException {
        System.out.println("Parts:");

        List<Part> parts = this.partRepository.getParts();
        if (parts.size() == 0) {
            System.out.println("No parts found!");
        }
        for (Part part : parts) {
            System.out.println(part.toString());
        }
    }

    protected void addSubPart() {
        try {
            System.out.println("Qual o id da peça? ");
            Integer subPartId = Integer.parseInt(scanner.nextLine());

            System.out.println("Quantas peças deseja adicionar? ");
            Integer subPartQuantity = Integer.parseInt(scanner.nextLine());

            System.out.println("Em qual repositorio a peça se encontra? ");
            String subPartRepositoryName = scanner.nextLine();

            this.subParts.addSubPart(subPartId, subPartQuantity, subPartRepositoryName);
        } catch (Exception e) {
            System.out.println("Failed to add subpart!");
        }
    }
}
