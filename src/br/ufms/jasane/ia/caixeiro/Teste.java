package br.ufms.jasane.ia.caixeiro;

import java.util.Random;

public class Teste {
	public static void main(String[] args) {
		int[] v1 = { 4, 2, 3, 5, 1 };
		int[] v2 = { 5, 1, 2, 4, 3 };
		int sort[] = { 0, 0, 0, 0, 0, 0 };
		int[] r = new int[5];
		for (int i = 0, k = v1.length - 1; i <= 2; i++, k--) {
			r[i] = v1[i];
			r[k] = v2[k];
		}

		for (int i = 0; i < r.length; i++) {
			System.out.print(r[i] + " ");
		}
		System.out.println("");
		for (int i = 0, a = 0; i < r.length; i++) {
			for (int j = 0; j < r.length; j++) {
				if (!(i == j)) {
					if (r[i] == r[j]) {
						a = new Random().nextInt(100) % 2;
						switch (a) {
						case 0:
							r[i] = 0;
							sort[r[i]] = 0;
							break;
						case 1:
							r[j] = 0;
							sort[r[j]] = 0;
							break;
						}

					}
					if (r[i] != 0) {
						sort[r[i]] = 1;
					}
				}
			}
		}
		for (int i = 0; i < r.length; i++) {
			System.out.print(r[i] + " ");
		}
//		System.out.println("");
//		for (int i = 0; i < sort.length; i++) {
//			System.out.print(sort[i] + " ");
//		}
		System.out.println("");
		for (int j = 0; j < r.length; j++) {

			int a = new Random().nextInt(100) % 2;
			if (r[j] == 0) {
				switch (a) {
				case 1:
					for (int i = 1; i < sort.length; i++) {
						if (sort[i] == 0) {
							r[j] = i;
							sort[i] = 1;
							break;
						}
					}
					break;

				case 0:
					for (int i = sort.length-1; i >= 1; i--) {
						if (sort[i] == 0) {
							r[j] = i;
							sort[i] = 1;
							break;

						}
					}
					break;
				}
			}
		}
		for (int i = 0; i < r.length; i++) {
			System.out.print(r[i] + " ");
		}
	}
}
