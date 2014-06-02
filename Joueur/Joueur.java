package Joueur;

import Enum.Statut;

public class Joueur extends Joueur_interface {

	protected int argent;
	private String nom;

	private int mise;

	public Joueur(String nom, int argent) {
		super();
		this.nom = nom;
		this.argent = argent;
		mise = 0;

	}

	public void miser(int mise) {
		this.mise = mise;
		argent -= mise;
		status = Statut.RIEN;
	}

	public int getMise() {
		return mise;
	}

	public void resetMise() {
		mise = 0;
	}

	public String getNom() {
		return nom;
	}

	public int getArgent() {
		return argent;
	}

	public int getScore() {
		return score;
	}

	public void ajouterGain(int i) {
		argent += i;
	}

	@Override
	public String toString() {
		return "Joueur " + nom + " Argent : " + argent + " score: " + score
				+ " Nombre carte " + getNombreCarte();
	}
}
