package EP.client;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
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
                System.out.println("Insira um comando ou digite help para ver a lista de comandos disponíveis: ");
                String command = scanner.nextLine();

                switch (command) {
                    case "bind":
                        this.setRegistry();
                        break;
                    case "listp":
                        this.listParts();
                        break;
                    case "getp":
                        this.getPart(); // implementar
                        break;
                    case "showp":
                        this.showP();
                        break;
                    case "clearlist":
                        this.clear();
                        break;
                    case "addsubpart":
                        this.addSubPart();
                        break;
                    case "addp":
                        this.addP();
                        break;
                    case "listsubparts":
                        this.listSubParts();
                        break;
                    case "help":
                        this.help();
                        break;
                    case "quit":
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Comando invalido");
                        break;
                }

                this.endCommand();
            }
        } catch (Exception e) {
            System.out.println("Erro no client! Por favor rode novamente");
            System.out.println(e);
        }
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

    protected void getPart() {
        System.out.println("Qual o id da peça? ");
        Integer partId = Integer.parseInt(scanner.nextLine());
        try {
            this.currentPart = this.partRepository.getPartById(partId);
            System.out.println("A peca foi setada como peca corrente");
        } catch (Exception e) {
            System.out.println("Failed to get part!");
        }
    }

    protected void showP() {
        System.out.println("Qual o id da peça? ");
        Integer partId = Integer.parseInt(scanner.nextLine());
        try {
            this.currentPart = this.partRepository.getPartById(partId);
        } catch (Exception e) {
            System.out.println("Failed to get part!");
        }

        if (this.currentPart == null) {
            System.out.println("Part not found!");
        } else {
            this.subParts = this.currentPart.getSubParts();
            System.out.println(this.currentPart.toString());
        }
    }

    protected void clear() {
        if(this.currentPart == null) {
            System.out.println("Voce nao setou uma part corrente! Use o comando getp para isso");
            return;
        }

        try{
            this.subParts = new PartInventory();
            System.out.println("A list de subparts da peca " + this.currentPart.getId() + " foi esvaziada com sucesso!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    protected void addSubPart() {
        if (this.subParts == null) {
            this.subParts = new PartInventory();
        }
        try {
            System.out.println("Qual o id da subpeça? ");
            Integer subPartId = Integer.parseInt(scanner.nextLine());

            System.out.println("Quantas peças deseja adicionar? ");
            Integer subPartQuantity = Integer.parseInt(scanner.nextLine());

            System.out.println("Em qual repositorio a peça se encontra? ");
            String subPartRepositoryName = scanner.nextLine();

            this.subParts.addSubPart(subPartId, subPartQuantity, subPartRepositoryName);
        } catch (Exception e) {
            System.out.println("Failed to add subpart!");
            System.out.println(e);
        }
    }

    protected void addP() {
        System.out.println("Qual o id da peça? ");
        Integer partId = Integer.parseInt(scanner.nextLine());

        System.out.println("Qual o nome da peça? ");
        String partName = scanner.nextLine();

        System.out.println("Qual a descrição da peça? ");
        String partDescription = scanner.nextLine();

        System.out.println("Deseja utilizar o repositório de subParts corrente? S / N");
        String useSubParts = scanner.nextLine();

        PartInventory subPartsNew = null;

        if (useSubParts.equals("S") || useSubParts.equals("s")) {
            subPartsNew = this.subParts;
        } else {
            this.subParts = new PartInventory();
            System.out.println("Quantas subpeças deseja adicionar? ");
            Integer subPartsCount = Integer.parseInt(scanner.nextLine());

            for (int i = 0; i < subPartsCount; i++) {
                System.out.println("Adicionando subpeça " + (i + 1) + " de " + subPartsCount + ":\n");
                this.addSubPart();
            }
            subPartsNew = this.subParts;
        }

        
        try {
            Part tempPart = new Part(partId, partName, partDescription, subPartsNew);
            Part existingPart = this.partRepository.getPartById(partId);

            if (existingPart != null) {
                System.out.println("Part already exists!");
                this.currentPart = existingPart;
                this.showP();
                return;
            }

            this.partRepository.addPart(tempPart);
            System.out.println(tempPart.toString() + " added!");
            System.out.println("Subparts:");
            if (tempPart.getSubParts() != null) {
                ArrayList<PartData> subParts = tempPart.getSubParts().getSubParts();
                for (PartData subpart : subParts) {
                    System.out.println(subpart.toString() + " added!");
                }
            }
            this.currentPart = tempPart;
            return;
        } catch (Exception e) {
            System.out.println("Failed to add part!");
            System.out.println(e);
        }
    }

    protected void listSubParts() {
        try {
            System.out.println("Subparts:");
            if (currentPart.getSubParts() == null) {
                System.out.println("Nenhuma subpart adicionada!");
                return;
            }
            System.out.println(currentPart.getSubParts().getSubPartsCount());
            if(this.subParts.getSubPartsCount() == 0) {
                System.out.println("Nenhuma subpart adicionada ainda!");
                return;
            }
            System.out.println(this.subParts.toString());
            for(PartData subpart : this.subParts.getSubParts()) {
                System.out.println(subpart.toString());
            }
        } catch (Exception e) {
            System.out.println("Failed to list subparts!");
            System.out.println(e.getStackTrace());
        }
    }

    private void help() {
        System.out.println();
        System.out.println("Comandos disponiveis:");
        System.out.println("bind - conecta ao repositorio");
        System.out.println("listp - lista as pecas do repositorio");
        System.out.println("getp - seta a peca corrente");
        System.out.println("showp - seta a peca corrente e mostra seus dados");
        System.out.println("clearlist - limpa a lista de subpecas corrente");
        System.out.println("addsubpart - adiciona uma subpeca a lista de subpecas corrente");
        System.out.println("addp - adiciona uma peca ao repositorio atual");
        System.out.println("listsubparts - lista as subpecas da peca corrente");
        System.out.println("help - mostra os comandos disponiveis");
        System.out.println("quit - sai do programa");
    }

    private void endCommand() {
        int quantidade = 10; // Número de caracteres "-" a serem impressos

        for (int i = 0; i < quantidade; i++) {
            System.out.print("-");
        }

        System.out.println(); // Pular linha após a impressão dos caracteres "-"
    }

    protected void setup(String[] args) throws NumberFormatException, RemoteException {
        this.registry = LocateRegistry.getRegistry(args[0], Integer.parseInt(args[1]));
        this.scanner = new Scanner(System.in);
        this.registryMessages = new RegistryMessages(this.registry, this.scanner);
        this.setRegistry();
        this.subParts = new PartInventory();
        this.currentPart = null;
    }
}
