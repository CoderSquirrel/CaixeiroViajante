package br.ufms.jasane.ia.caixeiro.oo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Caixeiro {
	static Random aleatorio = new Random();

	static int sort[] = { 0, 0, 0, 0, 0, 0 };

	/**
	 * <b>nMut e nMut2</b> as poscições no vetor que serão trocadas para ocorrer
	 * a mutação. <br />
	 * 
	 * @param v
	 *            o trajeto a ser mutado
	 * @return um trajeto monificado
	 */
	public static Trajeto mutacao(Trajeto tj) {
		int nMut, nMut2;
		do {
			nMut = aleatorio.nextInt(5);
			nMut2 = aleatorio.nextInt(5);
		} while (nMut == nMut2);

		int aux = tj.getVetorAt(nMut);
		tj.setVetorAt(nMut, tj.getVetorAt(nMut2));
		tj.setVetorAt(nMut2, aux);

		return tj;
	}

	/**
	 * <b>nPop</b> nova população sendo crada. <br />
	 * É gerado um numero aleatorio, esse numero nao pode ser 0 e nem já ter
	 * sido usado, as posições já usadas são marcadas na variavel {@link sort}.
	 * Após todos os numeros serem sorteados e colocados em suas posições é
	 * finalizado a criação do vetor.
	 * 
	 * @return um noto objeto Trajeto
	 */
	public static Trajeto criar() {
		int[] nPop = new int[5];
		for (int i = 0; i < 5; i++) {
			int num = aleatorio.nextInt(6);
			do {
				num = aleatorio.nextInt(6);

			} while (sort[num] == 1 || num == 0);

			nPop[i] = num;
			sort[num] = 1;
		}
		Trajeto tj = new Trajeto(nPop);
		Arrays.fill(sort, 0);
		return tj;
	}

	public static ArrayList<Trajeto> selecao(ArrayList<Trajeto> tjs) {
		ArrayList<Trajeto> t2 = new ArrayList<Trajeto>();
		t2 = BubbleSortByDist(tjs);

		for (int i = 0; tjs.size() > 5; i++) {
			tjs.remove(i);
		}

		return t2;
	}

	public static ArrayList<Trajeto> BubbleSortByDist(ArrayList<Trajeto> tjs) {

		for (int i = 0; i < tjs.size(); i++) {
			for (int j = 0; j < tjs.size(); j++) {

				if (!(i == tjs.size() - 1)) {

					if (tjs.get(i).getDistancia() > tjs.get(i + 1)
							.getDistancia()) {
						Trajeto auxT = new Trajeto(tjs.get(i).getVetor());
						tjs.set(i, tjs.get(i + 1));
						tjs.set(i + 1, auxT);

					}

				}
			}
		}

		return tjs;
	}
	
	public static Trajeto cruzamento(Trajeto v1, Trajeto v2) {
		int resultado[] = new int[5];
		int divisao = 0;
		do {
			divisao = aleatorio.nextInt(5);
		} while (divisao == 0);
		// Passo 1
		for (int i = 0; i < divisao; i++) {
			resultado[i] = v1.getVetorAt(i);
		}
		for (int i = v2.getVetor().length - 1; i >= divisao; i--) {
			resultado[i] = v2.getVetorAt(i);
		}

		// Passo 2
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

		// Passo 3
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
					for (int i = sort.length - 1; i >= 1; i--) {
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

		Arrays.fill(sort, 0);
		return new Trajeto(resultado);
	}
	
	
}
