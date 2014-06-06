package br.ufms.jasane.ia.caixeiro.oo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Main {
	/**
	 * funcao fitness ou custo quando o valor minino não mudar a cada tantas
	 * gerações
	 * 
	 * @param args
	 */
	static int geracao = 1;
	static int TAXA_MUTACAO = 4;
	static int TAXA_CRUZAMENTO = 3;
	static int TAXA_SELECAO = 5;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		ArrayList<Trajeto> populacao = new ArrayList<Trajeto>();
		Random aleatorio = new Random();

		// Inicio
		for (int i = 0; i < 10; i++) {
			populacao.add(Caixeiro.criar());
		}
		// Ordena a lista de trajetos do menor valor total até o maior, logo o
		// primeiro da lista sera o com menor fitness
		Collections.sort(populacao);

		// fitness
		Trajeto menor = populacao.get(0);
		int menorAnterior = menor.getDistancia(), menorAtual = 0;

		imprimirPopulacao(populacao);
		ArrayList<Trajeto> popAux = new ArrayList<Trajeto>();
		int menorDurante = 0;
		while (menorDurante < 5) {

			// [Selecao] Selecionar dois cromossomos de acordo com seu fitness
			// if (aleatorio.nextInt(10) < TAXA_SELECAO) {
			// Trajeto novo = new Trajeto(Caixeiro.selecaoRoleta(populacao)
			// .getVetor());
			// popAux.add(novo);
			// }
			System.out.println("\t\t\tHouve seleção. Taxa = "
					+ TAXA_SELECAO + "%");
			int taxa = aleatorio.nextInt(TAXA_SELECAO);
			for (int i = 0; i < taxa; i++) {
				Trajeto novo = new Trajeto(Caixeiro.selecaoRoleta(populacao)
						.getVetor());
				popAux.add(novo);
			}

			// [Crossover] Cruzar os cromossomos de acordo com a probabilidade
			// de crossover.
			if (aleatorio.nextInt(10) < TAXA_CRUZAMENTO) {
				System.out.println("\t\t\tHouve Cruzamento. Taxa = "
						+ TAXA_CRUZAMENTO + "%");

				Trajeto filho = Caixeiro.cruzamento(
						populacao.get(aleatorio.nextInt(populacao.size())),
						populacao.get(aleatorio.nextInt(populacao.size())));
				popAux.add(filho);
			}
			// [Mutação] Aplicar a probabilidade de mutação para cada
			// posição no cromossomo.
			if (aleatorio.nextInt(10) < TAXA_MUTACAO) {
				System.out.println("\t\t\tHouve Mutação. Taxa = "
						+ TAXA_MUTACAO + "%");
				int posMut = aleatorio.nextInt(populacao.size());
				populacao.set(posMut, Caixeiro.mutacao(populacao.get(posMut)));
			}
			if (popAux.size() != 0) {
				populacao = popAux;
				Collections.sort(populacao);
				Trajeto menorGeracao = populacao.get(0);
				menorAtual = menorGeracao.getDistancia();
				if (menorAtual == menorAnterior) {
					menorDurante++;
				} else if (menorAtual < menorAnterior) {
					menorDurante = 0;
					menorAnterior = menorAtual;
				} else if (menorAtual > menorAnterior) {
					menorDurante = 0;
					menorAnterior = menorAtual;
				}
			} else {
				System.out.println("Na geração " + geracao
						+ " a população poderia ser extinta");
			}
//			System.out.println("População após Selecao/Mutacao/Cruamento");
//			imprimirPopulacao(populacao);

			geracao++;
			imprimirPopulacao(populacao);
			// cria nova populacao
			// for (int i = 0; i < ; i++) {
			// populacao.add(Caixeiro.criar());
			// }
		}

		System.out.println("Demoraram " + geracao
				+ " geração para a população chegar ao ponto ótimo");
		// }

	}

	public static void printVetor(int[] v) {
		for (int i = 0; i < v.length; i++)
			System.out.print(v[i] + " ");

	}

	public static void imprimirPopulacao(ArrayList<Trajeto> lista) {
		System.out.print(geracao + "ª geração.\n\t\t");
		for (Trajeto t : lista) {
			// Trajeto t = lista.get(0);
			printVetor(t.getVetor());
			System.out.print(t.getDistancia() + "\n\t\t");
		}
		System.out.print("\n");
	}

}
