package principal;

import java.util.Scanner;

import elements.Flag;
import network.APIConnection;

public class Main {
    public static final String RESET = "\u001B[0m";
    public static final String CYAN = "\u001B[36m";
    public static final String YELLOW = "\u001B[33m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";

    public static void main(String[] args) {
        APIConnection conn = new APIConnection(Flag.USD);
        Scanner scanner = new Scanner(System.in);
        int value;
        boolean cond = true;

        while (cond) {

            System.out.println(CYAN + "╔════════════════════════════════════════╗" + RESET);
            System.out.println(CYAN + "║          " + YELLOW + "💱 MENU DE OPÇÕES" + CYAN + "             ║" + RESET);
            System.out.println(CYAN + "╠════════════════════════════════════════╣" + RESET);
            System.out.println(CYAN + "║ " + GREEN + "1" + RESET + " - Ver todas as moedas                "+CYAN+"║" + RESET);
            System.out.println(CYAN + "║ " + GREEN + "2" + RESET + " - Buscar moeda                       "+CYAN+"║" + RESET);
            System.out.println(CYAN + "║ " + GREEN + "3" + RESET + " - Mudar moeda base                   "+CYAN+"║" + RESET);
            System.out.println(CYAN + "║ " + GREEN + "0" + RESET + " - Sair                               "+CYAN+"║" + RESET);
            System.out.println(CYAN + "╚════════════════════════════════════════╝" + RESET);
            System.out.print("\nDigite um valor: ");

            try {
                value = scanner.nextInt();
                scanner.nextLine();

                switch (value) {
                    case 0 -> cond = false;
                    case 1 -> Util.getAllCurrencies(conn, scanner);
                    case 2 -> Util.searchCoin(conn, scanner);
                    case 3 -> Util.changeBaseCurrency(conn, scanner);
                    default -> {
                        Util.clear_screen();
                        System.out.println(RED + "\nValor inválido, tente novamente...\n" + RESET);
                        Thread.sleep(1500);
                    }
                }
            } catch (Exception e) {
                scanner.nextLine(); // Limpa entrada inválida
                Util.clear_screen();
                System.out.println(RED + "\nEntrada inválida! Digite um número.\n" + RESET);
                try { Thread.sleep(1500); } catch (InterruptedException ignored) {}
            }
        }
        scanner.close();
    }
}

