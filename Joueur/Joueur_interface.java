package Joueur;
import java.util.ArrayList;

import Enum.Statut;
import Paquet.Carte;

public abstract class Joueur_interface {

	protected int score;
	protected ArrayList<Carte> main;

	protected Statut status;

	public ArrayList<Carte> getMain() {
		return main;
	}

	public Joueur_interface() {
		score = 0;
		status = Statut.MISE;
		main = new ArrayList<>();
	}

	public int getNombreCarte() {
		return main.size();
	}

	public void supprimerMain() {
		main.removeAll(main);
		score = 0;
	}

	public void afficheCartes() {
		System.out.println("");
		for (int i = 0; i < main.size(); i++) {
			System.out.print(main.get(i) + " ");
		}
	}

	public void ajout(Carte c) {
		if (c.getValeur() > 10) {
			score += 10;
		} else
			score += c.getValeur();
		if ((c.getValeur() == 1) && (score + 10 <= 21)) {
			score += 10;
		}

		main.add(new Carte(c.getValeur(), c.getType().ordinal()));
	}

	public void demanderCarte() {
		status = Statut.CARTE;
	}

	public Statut getStatus() {
		return status;
	}

}
