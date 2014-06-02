package Paquet;

import Enum.Type;

public class Carte {

	private int type_int;
	private int valeur;

	public Carte(int valeur, int type) {
		this.type_int = type;
		this.valeur = valeur;

	}

	public Type getType() {
		switch (type_int) {
		case 0:
			return Type.TREFLE;
		case 1:
			return Type.CARREAU;
		case 2:
			return Type.COEUR;
		case 3:
			return Type.PIQUE;

		}
		return Type.RIEN;

	}

	public int getValeur() {
		return valeur;
	}

	@Override
	public String toString() {
		return "[Carte : " + valeur + " Type: " + getType().toString() + "] ";
	}

}
