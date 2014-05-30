package br.ufms.jasane.ia.caixeiro;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Main {
	/**
	 * 0 Campo Grande 1 Bonito 2 Corumba 3 DOurados 4 Camapua 5 Agua Clara
	 */
	static int dist[][] = {
		    { 0, 325, 420, 230, 243, 189 },
			{ 325, 0, 335, 270, 285, 474 },
			{ 420, 335, 0, 588, 563, 609 },
			{ 230, 270, 588, 0, 373, 413 },
			{ 243, 285, 563, 373, 0, 213 },
			{ 189, 474, 609, 413, 213, 0 }, };
	static int sort[] = { 0, 0, 0, 0, 0, 0 };
	static Random aleatorio = new Random();

	public void selecao() {

	}

	public void cruzamento() {

	}

	public void mutacao() {

	}

	public static int[] criar() {

		int[] nPop = new int[5];
		for (int i = 0; i < 5; i++) {
			int num = aleatorio.nextInt(6);
			do {
				num = aleatorio.nextInt(6);

			} while (sort[num] == 1 || num ==0);

			nPop[i] = num;
			sort[num] = 1;
		}

		for (int i = 0; i < 5; i++) {
			System.out.print(nPop[i] + " ");
		}
		Arrays.fill(sort, 0);
		return nPop;
	}

	public static int calcularDistancia(int[] pop) {
		int calc= dist[0][pop[0]];
		for(int i = 1; i< pop.length; i++){
			calc= calc+ dist[pop[i-1]][pop[i]];
		}
		return calc;
	}

	public static void main(String[] args) {
		int test[] = {3, 4, 2, 5, 1 };
		int total = calcularDistancia(criar());
		System.out.println(total);
	}

}
