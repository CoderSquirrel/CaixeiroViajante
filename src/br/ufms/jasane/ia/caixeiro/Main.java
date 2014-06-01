package br.ufms.jasane.ia.caixeiro;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Main {

	static final int MAX = 30;
	static int dist[][] = { { 0, 325, 420, 230, 243, 189 },
			{ 325, 0, 335, 270, 285, 474 }, { 420, 335, 0, 588, 563, 609 },
			{ 230, 270, 588, 0, 373, 413 }, { 243, 285, 563, 373, 0, 213 },
			{ 189, 474, 609, 413, 213, 0 }, };
	static int sort[] = { 0, 0, 0, 0, 0, 0 };
	static Random aleatorio = new Random();
	static int[] menores = { Integer.MAX_VALUE, Integer.MAX_VALUE,
			Integer.MAX_VALUE };
	static int vs[][] = new int[MAX][6];
	static int trajeto[][] = new int[MAX][2];

	public void selecao() {

	}

	public static int[] cruzamento(int[] v1, int[] v2) {
		int resultado[] = new int[5];
		int divisao = 0;
		do {
			divisao = aleatorio.nextInt(5);
		} while (divisao == 0);
		printVetorLinha(v1);
		printVetorLinha(v2);
		print(divisao + "\n");

		for (int i = 0; i < divisao; i++) {
			resultado[i] = v1[i];
		}
		for (int i = v2.length-1; i >= divisao; i--) {
			resultado[i] = v2[i];
		}
		
		printVetorLinha(resultado);
		for (int i = 0, a = 0; i < resultado.length; i++) {
			for (int j = 0; j < resultado.length; j++) {
				if (!(i == j)) {
					sort[resultado[i]] = 1;
					if (resultado[i] == resultado[j]) {
						a = new Random().nextInt(100) % 2;
						switch (a) {
						case 0:
							resultado[i] = 0;
							sort[resultado[i]] = 0;
							break;
						case 1:
							resultado[j] = 0;
							sort[resultado[j]] = 0;
							break;
						}
					}
					
				}
			}
		}
		for (int j = 0; j < resultado.length; j++) {

			int a = new Random().nextInt(100) % 2;
			if (resultado[j] == 0) {
				switch (a) {
				case 1:
					for (int i = 1; i < sort.length; i++) {
						if (sort[i] == 0) {
							resultado[j] = i;
							sort[i] = 1;
							break;
						}
					}
					break;

				case 0:
					for (int i = sort.length-1; i >= 1; i--) {
						if (sort[i] == 0) {
							resultado[j] = i;
							sort[i] = 1;
							break;

						}
					}
					break;
				}
			}
		}
		
		printVetorLinha(sort);
		printVetorLinha(resultado);
		print("\n");
		Arrays.fill(sort, 0);
		return resultado;
	}

	public static int[] mutacao(int[] v) {
		int nMut, nMut2;
		do {
			nMut = aleatorio.nextInt(5);
			nMut2 = aleatorio.nextInt(5);
		} while (nMut == nMut2);

		int aux = v[nMut];
		v[nMut] = v[nMut2];
		v[nMut2] = aux;

		return v;
	}

	/**
	 * Faz a verificação se a cidade escolida pelo aleatorio ja esta no trajeto
	 * ou nao depois adiciona ela ao trajeto
	 * 
	 * @return um novo trajeto
	 */
	public static int[] criar() {

		int[] nPop = new int[5];
		for (int i = 0; i < 5; i++) {
			int num = aleatorio.nextInt(6);
			do {
				num = aleatorio.nextInt(6);

			} while (sort[num] == 1 || num == 0);

			nPop[i] = num;
			sort[num] = 1;
		}

		Arrays.fill(sort, 0);
		return nPop;
	}

	public static int calcularDistancia(int[] pop) {
		int calc = dist[0][pop[0]];
		for (int i = 1; i < pop.length; i++) {
			calc = calc + dist[pop[i - 1]][pop[i]];
		}
		calc = calc + dist[0][pop[pop.length - 1]];
		return calc;
	}

	public static void main(String[] args) {
		for (int i = 0; i < MAX; i++) {
			vs[i] = criar();
			trajeto[i][0] = i;
			trajeto[i][1] = calcularDistancia(vs[i]);
			// print(i+" ");
			// printVetor(vs[i]);
			// print(trajeto[i][1] + "\n");
		}
		System.out.print("\n");
		int[][] k = sort(trajeto);
		// for (int i = 0; i < 3; i++) {
		// print(k[i][0] + " " + k[i][1] + "\n");
		// }

		// mutaçãoo
		// int i = aleatorio.nextInt(MAX);
		// printVetor(vs[i]);
		// vs[i] = mutacao(vs[i]);
		// print("\n");
		// printVetor(vs[i]);
		//
		// cruzamento
		for (int i = 0; i < 40; i++) {
			int v1 = aleatorio.nextInt(MAX), v2 = aleatorio.nextInt(MAX);
			int[] r = cruzamento(vs[v1], vs[v2]);
		}
	}

	public static int[][] sort(int m[][]) {
		int[][] tres = new int[3][2];
		int[][] aux = new int[1][2];
		for (int j = 0; j < m.length; j++) {
			for (int i = 0; i < m.length; i++) {
				if (!(i == m.length - 1)) {
					if (m[i][1] > m[i + 1][1]) {
						aux[0] = m[i];
						m[i] = m[i + 1];
						m[i + 1] = aux[0];
					}
				}
			}
		}

		tres[0] = m[0];
		tres[1] = m[1];
		tres[2] = m[2];

		return tres;
	}

	public static void print(String a) {
		System.out.print(a);
	}

	public static void printVetor(int[] v) {
		for (int i = 0; i < v.length; i++)
			System.out.print(v[i] + " ");

	}

	public static void printVetorLinha(int[] v) {
		for (int i = 0; i < v.length; i++)
			System.out.print(v[i] + " ");

		System.out.print("\n");
	}

}
