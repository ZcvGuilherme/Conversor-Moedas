package principal;

import java.util.ArrayList;
import java.util.Scanner;

import elements.Coin;
import elements.Flag;
import network.APIConnection;

public class Util {
	public static final String RESET = "\u001B[0m";
	public static final String RED = "\u001B[31m";
	public static final String GREEN = "\u001B[32m";
	public static final String YELLOW = "\u001B[33m";
	public static final String BLUE = "\u001B[34m";
	public static final String CYAN = "\u001B[36m";
	
	public static void clear_screen() {
		for (int i = 0; i < 50; i++) {
			System.out.println();
		}
	}
	
	private static void printCurrency(Coin coin, Scanner scanner) {
	    // Conversões e formatações
	    double bid = Double.parseDouble(coin.getBid());
	    double ask = Double.parseDouble(coin.getAsk());
	    double high = Double.parseDouble(coin.getHigh());
	    double low = Double.parseDouble(coin.getLow());
	    double pct = Double.parseDouble(coin.getPctChange());

	    String bidFormatado = String.format("%.4f", bid);
	    String askFormatado = String.format("%.4f", ask);
	    String highFormatado = String.format("%.4f", high);
	    String lowFormatado = String.format("%.4f", low);
	    String pctFormatado = String.format("%+.4f%%", pct);

	    String pctColor = pct >= 0 ? GREEN : RED;
	    
	    System.out.printf(CYAN+"╔════════════╦══════════════════════════════╗\n");
	    System.out.printf(CYAN+"║"+YELLOW+"   Bandeira "+CYAN+"║ %-28s   ║\n", Flag.getFlagByCode(coin.getCode()));
	    System.out.printf(CYAN+"╠════════════╬══════════════════════════════╣\n");
	    System.out.printf(CYAN+"║"+RESET+" Código ISO "+CYAN+"║"+RESET+" %-28s "+CYAN+"║\n", coin.getCode());
	    System.out.printf(CYAN+"║ "+RESET+"Nome       "+CYAN+"║"+RESET+" %-28s "+CYAN+"║\n", Flag.getNameByCode(coin.getCode()));
	    System.out.printf(CYAN+"║"+RESET+" Base       "+CYAN+"║"+RESET+" %-28s "+CYAN+"║\n", coin.getCodein());
	    System.out.printf(CYAN+"║"+RESET+" Compra     "+CYAN+"║"+RESET+" %-28s "+CYAN+"║\n", bidFormatado);
	    System.out.printf(CYAN+"║"+RESET+" Venda      "+CYAN+"║"+RESET+" %-28s "+CYAN+"║\n", askFormatado);
	    System.out.printf(CYAN+"║"+RESET+" Alta       "+CYAN+"║"+RESET+" %-28s "+CYAN+"║\n", highFormatado);
	    System.out.printf(CYAN+"║"+RESET+" Baixa      "+CYAN+"║"+RESET+" %-28s "+CYAN+"║\n", lowFormatado);
	    System.out.printf(CYAN+"║"+RESET+" Variação   "+CYAN+"║"+RESET+" %-28s "+CYAN+"║\n", pctFormatado);
	    System.out.printf(CYAN+"╚════════════╩══════════════════════════════╝"+RESET);
	  
	    System.out.println("\nAperte Enter para voltar:");
	    scanner.nextLine();
	}


	public static void searchCoin(APIConnection conn, Scanner scanner) {
		clear_screen();
	    ArrayList<String> codeList = new ArrayList<>();
	    ArrayList<String> flagList = new ArrayList<>();

	    for (Flag m : Flag.values()) {
	    	if (m != conn.getBase()) {
	    		codeList.add(m.getCode());
		        flagList.add(m.getFlag());
	    	}
	    }

	    System.out.println("\n"+ CYAN +"╔══════════════════════════════════════════════════╗"+ RESET);
	    System.out.println(CYAN +"║           💱 Moedas disponíveis para busca       ║");
	    System.out.println(CYAN +"╚══════════════════════════════════════════════════╝"+ RESET);

	    for (int i = 0; i < codeList.size(); i++) {
	        System.out.printf(" %-3s %-4s", flagList.get(i), codeList.get(i));
	        
	        // Quebra de linha a cada 5 moedas para melhor visualização
	        if ((i + 1) % 5 == 0 || i == codeList.size() - 1) {
	            System.out.println();
	        } else {
	            System.out.print("   ");
	        }
	    }

	    System.out.println("\nDigite o código da moeda que deseja ver:");
	    
		Flag flag = Flag.searchByCode(scanner.nextLine().toUpperCase());
		
		if (flag != null) {
			clear_screen();
			Coin coin = conn.connect(flag);
			if (coin != null) {
				printCurrency(coin, scanner);
			} else {
				System.out.println(RED + "Ocorreu um erro ao converter a moeda, tente outra combinação."+ RESET);
				try { Thread.sleep(1500); } catch (InterruptedException ignored) {}
			}
			
		} else {
			clear_screen();
			searchCoin(conn, scanner);
		}
		
		clear_screen();
	}
	
	
	public static void changeBaseCurrency(APIConnection conn, Scanner scanner) {
		clear_screen();
		ArrayList<String> codeList = new ArrayList<>();
	    ArrayList<String> flagList = new ArrayList<>();

	    for (Flag m : Flag.values()) {
	        codeList.add(m.getCode());
	        flagList.add(m.getFlag());
	    }
	    
	    System.out.println("\n"+CYAN+"╔══════════════════════════════════════════════════╗"+ RESET);
	    System.out.println(CYAN +"║           💱 Moedas disponíveis para base        ║");
	    System.out.println(CYAN +"╚══════════════════════════════════════════════════╝\n"+ RESET);

	    for (int i = 0; i < codeList.size(); i++) {
	        System.out.printf(" %-3s %-4s", flagList.get(i), codeList.get(i));
	        
	        // Quebra de linha a cada 5 moedas para melhor visualização
	        if ((i + 1) % 5 == 0 || i == codeList.size() - 1) {
	            System.out.println();
	        } else {
	            System.out.print("   ");
	        }
	    }
	    System.out.println("Digite o código da moeda que deseja usar para conversão: ");
	    
	    Flag flag = Flag.searchByCode(scanner.nextLine().toUpperCase());
	
		if (flag != null) {
			conn.setBase(flag);
			clear_screen();
			System.out.println("\nBase modificada com sucesso!!\n");
		} else {
			clear_screen();
			changeBaseCurrency(conn, scanner);
		}
		
		
	}
	
	public static void getAllCurrencies(APIConnection conn, Scanner scanner) {
	    clear_screen();
	    System.out.println("\n" + CYAN + "╔════════════════════════════════════════════════════════════════════════════════════════╗" + RESET);
	    System.out.println(CYAN + "║                      💱 Cotações em relação à moeda base: " + GREEN + conn.getBase().getCode() +" "+ conn.getBase().getFlag()+ CYAN + "                       ║" + RESET);
	    System.out.println(CYAN + "╚════════════════════════════════════════════════════════════════════════════════════════╝\n" + RESET);

	    System.out.printf(YELLOW + " %-3s | %-7s | %-22s | %10s | %10s | %10s | %10s | %10s%n" + RESET,
	            "🌐", "Código", "Nome Oficial", "Compra", "Venda", "Alta", "Baixa", "Variação");
	    System.out.println(YELLOW + "-----|---------|------------------------|------------|------------|------------|------------|------------" + RESET);

	    for (Flag m : Flag.values()) {
	        if (m != conn.getBase()) {
	            Coin coin = conn.connect(m);
	            if (coin != null) {
	                printCurrencyRowColored(coin);
	            }
	        }
	    }

	    System.out.println("\nAperte Enter para voltar:");
	    scanner.nextLine();
	    clear_screen();
	}
	
	private static void printCurrencyRowColored(Coin coin) {
	    String bandeira = Flag.getFlagByCode(coin.getCode());
	    String nome = Flag.getNameByCode(coin.getCode());

	    double bid = Double.parseDouble(coin.getBid());
	    double ask = Double.parseDouble(coin.getAsk());
	    double high = Double.parseDouble(coin.getHigh());
	    double low = Double.parseDouble(coin.getLow());
	    double pct = Double.parseDouble(coin.getPctChange());

	    String bidFormatado = String.format("%.2f", bid);
	    String askFormatado = String.format("%.2f", ask);
	    String highFormatado = String.format("%.2f", high);
	    String lowFormatado = String.format("%.2f", low);
	    String pctFormatado = String.format("%+.2f%%", pct);

	    // Se variação positiva, verde; negativa, vermelho
	    String pctColor = pct >= 0 ? GREEN : RED;

	    System.out.printf(" %s  | %s%-7s%s | %-22s | %10s | %10s | %10s | %10s | %s%10s%s%n",
	            bandeira,
	            CYAN, coin.getCode(), RESET,
	            nome,
	            bidFormatado,
	            askFormatado,
	            highFormatado,
	            lowFormatado,
	            pctColor, pctFormatado, RESET);
	}

}
