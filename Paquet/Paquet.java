package Paquet;
import java.util.ArrayList;

public class Paquet {

	private ArrayList<Carte> paquet;
	private final int brassage = 500;

	public Paquet() {
		paquet = new ArrayList<>();
	}

	public void ajout_Carte(int valeur, int type) {
		paquet.add(new Carte(valeur, type));

	}

	public Carte getCarte() {
		return paquet.remove(0);
	}

	public void buildPacket() {

		for (int valeur = 1; valeur < 14; valeur++) {
			for (int type = 0; type < 4; type++) {
				paquet.add(new Carte(valeur, type));
			}

		}

	}

	public void melanger_echange() {
		int carte_1, carte_2;
		int nombre_carte = paquet.size();

		for (int i = 0; i < brassage; i++) {

			carte_1 = (int) (Math.random() * nombre_carte);
			carte_2 = (int) (Math.random() * nombre_carte);
			echange(carte_1, carte_2);
		}

	}

	public void melanger_remise_top() {
		int carte_1;
		int nombre_carte = paquet.size();

		for (int i = 0; i < brassage; i++) {
			carte_1 = (int) (Math.random() * nombre_carte);
			paquet.add(paquet.remove(carte_1));
		}

	}

	private void echange(int carte_index1, int carte_index2) {
		Carte carte_1 = paquet.get(carte_index1);
		Carte carte_2 = paquet.get(carte_index2);

		paquet.add(carte_index1, carte_2);
		paquet.add(carte_index2, carte_1);

	}

	public void affichePacket() {

		for (int i = 0; i < paquet.size(); i++) {
			System.out.println(paquet.get(i).toString());
		}

	}

}
