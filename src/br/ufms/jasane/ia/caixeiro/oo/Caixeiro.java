package br.ufms.jasane.ia.caixeiro.oo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

	public static Trajeto selecao(ArrayList<Trajeto> tjs) {
		double roleta = aleatorio.nextDouble();
		ArrayList<Trajeto> t2 = tjs;
		ArrayList<Double> probSelecao = new ArrayList<Double>();
		Collections.sort(t2);

		int total = 0;
		for (Trajeto t : t2) {
			total = total + t.getDistancia();
		}
		double cem = 0.0;
		for (Trajeto t : t2) {
			double dv = (double) t.getDistancia() / total;
			cem = cem + dv;
			probSelecao.add(dv);
			// System.out.println("\t\t\t" + t.getDistancia() + "\t" + dv);
		}
		// System.out.println(cem);
		Trajeto retorno = tjs.get(tjs.size() - 1);
		// System.out.println("Roleta " + roleta);
		for (int i = 0; i < probSelecao.size(); i++) {
			if (probSelecao.get(i) < roleta) {
				retorno = tjs.get(i);
				// System.out.println("\t\t\t" + retorno.getDistancia()
				// + " foi selecionado com " + probSelecao.get(i));
				break;
			}
		}

		return retorno;
	}

	public static Trajeto selecaoRoleta(ArrayList<Trajeto> tjs) {
		// float[] posicoes = new float[tjs.size()];
		float roleta = aleatorio.nextFloat();
		int amostra[] = new int[tjs.size()];
		float[] probs = new float[tjs.size()];

		ArrayList<Trajeto> t2 = tjs;
		Collections.sort(t2);

		int total = 0;
		int ultimo = tjs.get(tjs.size() - 1).getDistancia();

		// Espaco amostra imaginaria
		for (int p = 0; p < tjs.size(); p++) {
			amostra[p] = ultimo - tjs.get(p).getDistancia();
			total = total + amostra[p];
		}

		// probabiliade
		for (int p = 0; p < tjs.size(); p++) {
			probs[p] = (float) amostra[p] / total;
			// System.out.println(probs[p]);
		}

		// escolher
		float somaFinal = 0;
		// System.out.println("Roleta " + roleta);
		for (int p = 0; p < tjs.size(); p++) {
			int prox = p > 1 ? p - 1 : p;
			if (roleta > somaFinal) {
				if (roleta < (somaFinal + probs[prox])) {
//					System.out.println("Escoliho " + tjs.get(p).getDistancia()
//							+ " Porcentagem " + probs[p] + " Roleta " + roleta
//							+ " Numero atual " + somaFinal + " Proximo "
//							+ (somaFinal + probs[prox]));
					return tjs.get(p);
				} else if (p == 0) {
//					System.out.println("Escoliho" + tjs.get(p).getDistancia()
//							+ " Porcentagem " + probs[p] + " Roleta " + roleta
//							+ " Numero atual " + somaFinal);

					return tjs.get(p);
				}
			}

			somaFinal += probs[p];
		}
		// System.out.println(somaFinal);
		return null;

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
