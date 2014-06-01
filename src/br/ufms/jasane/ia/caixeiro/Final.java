package br.ufms.jasane.ia.caixeiro;

import java.util.Arrays;
import java.util.Random;

public class Final {
	/**
	 * Tamanho maximo
	 */
	static final int MAX = 30;
	/**
	 * Tabela de distancias
	 */
	static int dist[][] = { { 0, 325, 420, 230, 243, 189 },
			{ 325, 0, 335, 270, 285, 474 }, { 420, 335, 0, 588, 563, 609 },
			{ 230, 270, 588, 0, 373, 413 }, { 243, 285, 563, 373, 0, 213 },
			{ 189, 474, 609, 413, 213, 0 }, };
	/**
	 * Variavel aleatoria
	 */
	static Random aleatorio = new Random();
	/**
	 * Vetor onde são armazenados os individuos criados
	 */
	static int vs[][] = new int[MAX][6];
	/**
	 * Matriz onde são salvos a posição do trajeto na matriz vs e sua soma
	 */
	static int trajeto[][] = new int[MAX][2];
	/**
	 * @see {@link criar()}
	 */
	static int sort[] = { 0, 0, 0, 0, 0, 0 };

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * <b>nPop</b> nova população sendo crada. <br />
	 * É gerado um numero aleatorio, esse numero nao pode ser 0 e nem já ter
	 * sido usado, as posições já usadas são marcadas na variavel {@link sort}.
	 * Após todos os numeros serem sorteados e colocados em suas posições é
	 * finalizado a criação do vetor.
	 * 
	 * @return nPop
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

	/**
	 * <b>nMut e nMut2</b> as poscições no vetor que serão trocadas para ocorrer
	 * a mutação. <br />
	 * 
	 * @param v
	 *            o trajeto a ser mutado
	 * @return um trajeto monificado
	 */
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
	 * <b>calc</b> variavel onde é armazenado a soma das distancias
	 * 
	 * @param pop
	 *            um trajeto
	 * @return calc
	 */
	public static int calcularDistancia(int[] pop) {
		int calc = dist[0][pop[0]];
		for (int i = 1; i < pop.length; i++) {
			calc = calc + dist[pop[i - 1]][pop[i]];
		}
		calc = calc + dist[0][pop[pop.length - 1]];
		return calc;
	}

	/**
	 * Usando o algotimo de ordenação bubbleSort ordena a matriz de acordo com a
	 * distancia do trajeto (<b>m[][1]</b>)
	 * 
	 * @param m
	 *            matriz população
	 * @return tres um vetor com os 3 trajetos mais curtos
	 */
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

	public static int[] cruzamento(int[] v1, int[] v2) {
		int resultado[] = new int[5];
		int divisao = aleatorio.nextInt(5);

		for (int i = 0, k = v1.length - 1; i <= divisao; i++, k--) {
			resultado[i] = v1[i];
			resultado[k] = v2[k];
		}

		return resultado;
	}
}
