package Joueur;

import java.util.ArrayList;

import Enum.Statut;
import Joueur.Joueur;
import Joueur.Joueur_interface;
import Paquet.Paquet;
import Paquet.Carte;

public class Croupier extends Joueur_interface {

	private ArrayList<Joueur> joueurs;
	private Paquet cartes;
	private boolean cache;
	public final static int ARGENT_DEPART = 150;

	public ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}

	public Croupier() {
		super();
		cache = true;
		joueurs = new ArrayList<>();
		cartes = new Paquet();

		main = new ArrayList<Carte>();
		cartes.buildPacket();
		cartes.melanger_remise_top();
	}

	public boolean getCache() {
		return cache;
	}

	public void ajoutJoueur(Joueur j) {
		joueurs.add(j); // Copie profonde =`=???
	}

	public void croupierJoue() {
		while (score <= 16) { // if

			this.ajout(cartes.getCarte());

		}
	}
	
	

	public void distribuerCarte() {

		this.ajout(cartes.getCarte());
		this.ajout(cartes.getCarte());

		for (Joueur joueur : joueurs) {
			joueur.ajout(cartes.getCarte());
			joueur.ajout(cartes.getCarte());
		}

	}

	public boolean checkJoueurs(Joueur joueur) {

		if (joueur.getScore() > score && joueur.getScore() <= 21 && score <= 21) {
			joueur.ajouterGain(joueur.getMise() * 2);
			return true;
		} else if (joueur.getScore() <= 31 && score > 21) {
			joueur.ajouterGain(joueur.getMise() * 2);
			return true;
		}

		return false;

	}

	public String getScore() {
		if (cache) {
			return "";
		}
		return score + "";
	}

	public void regardejoueur() {
		try {
			for (int i = 0; i < joueurs.size(); i++) {

				if (joueurs.get(i).status.ordinal() == Statut.CARTE.ordinal()) {
					joueurs.get(i).ajout(cartes.getCarte());
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public void setCache(boolean b) {
		cache = b;
	}

	public void supprimerMains() {
		for (Joueur joueur : joueurs) {
//			joueur.miser(0);
			joueur.supprimerMain();
		}

		cache = true;

		this.supprimerMain();

	}

	public void rejouer() {
		supprimerMains();
		cartes.buildPacket();
		cartes.melanger_remise_top();
		distribuerCarte();

	}

	public void donneCarte() {

		for (Joueur joueur : joueurs) {
			if (joueur.getStatus().ordinal() == Statut.CARTE.ordinal()) {

				joueur.ajout(cartes.getCarte());

			}

		}
	}
}
