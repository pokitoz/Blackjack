import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import java.awt.Image;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Joueur.Croupier;
import Joueur.Joueur;
import Paquet.Carte;

public class Jeu extends JPanel implements MouseListener {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private int PIXELY_CARTE = 0;

	private Image carte_img[] = null;
	private Image tapis = null;
	private Image tapis_normal = null;

	private Image revers = null;
	private Croupier croupier = null;

	public static int PIXELX_TAPIS = 0;
	public static int PIXELY_TAPIS = 91;

	private Font font = null;
	private boolean commencer;

	public Jeu(int nombreJoueur) throws IOException {

		croupier = new Croupier();
		commencer = false;

		String input_nom = "";
		for (int i = 0; i < nombreJoueur; i++) {
			input_nom = JOptionPane.showInputDialog(null, "Nom du joueur");
			if (input_nom.equals("")) {
				input_nom = "Inconnu";
			}
			if (input_nom.length() > 10) {
				input_nom = input_nom.substring(0, 10);
			}
			croupier.ajoutJoueur(new Joueur(input_nom, Croupier.ARGENT_DEPART));
		}

		load_images();
		buildPanel();

	}

	private void buildPanel() {

		addMouseListener(this);
		font = new Font("Courier", Font.BOLD, 14);
	}

	private void load_images() throws IOException {
		carte_img = new Image[52];
		// tapis_normal = ImageIO.read(new File("img/cartes/table.png"));
		tapis_normal = ImageIO.read(getClass().getResource(
				"img/cartes/table.png"));
		tapis = tapis_normal;
		PIXELX_TAPIS = tapis.getWidth(null);
		PIXELY_TAPIS = tapis.getHeight(null);
		// tapis = ImageIO.read(new File("img/tapis.jpg"));
		// revers = ImageIO.read(new File("img/revers.jpg"));

		// revers = ImageIO.read(new File("img/cartes/revers.gif"));
		revers = ImageIO.read(getClass().getResource("img/cartes/revers.gif"));
		for (int j = 0; j < carte_img.length; j++) {
			// carte_img[j] = ImageIO.read(new File("img/" + (j + 1) + ".png"));
			// carte_img[j] = ImageIO.read(new File("img/cartes/" + (j + 1)+
			// ".gif"));

			carte_img[j] = ImageIO.read(getClass().getResource(
					"img/cartes/" + (j + 1) + ".gif"));
		}

		PIXELY_CARTE = carte_img[0].getHeight(null);
	}

	public void paintComponent(Graphics g) {
		/*
		 * if (commencer) { b1.setVisible(true); b2.setVisible(true);
		 * b4.setVisible(false); }
		 */
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawImage(tapis, 0, 0, this);

		int x = PIXELX_TAPIS / 2 - 50;
		int index = 0;
		int y = 120;

		g.drawString("Croupier ", x + 150, y);
		g.drawString("Score  : " + croupier.getScore(), x + 150, y + 20);

		if (croupier.getCache() && commencer) {
			g.drawImage(revers, x, y, this);
			index = 1;
			x += 20;
		}

		ArrayList<Carte> cartes_croupier = croupier.getMain();
		for (; index < cartes_croupier.size(); index++) {
			g.drawImage(carte_img[cartes_croupier.get(index).getType()
					.ordinal()
					* 13 + cartes_croupier.get(index).getValeur() - 1], x, y,
					this);

			x += 20;

		}

		// x = 420 / croupier.getJoueurs().size();
		x = 25;
		x = 450 / croupier.getJoueurs().size();
		y = 350;

		ArrayList<Joueur> joueurs = croupier.getJoueurs();

		for (int i = 0; i < joueurs.size(); i++) {
			ArrayList<Carte> cartes_joueur = joueurs.get(i).getMain();
			g.drawString("Joueur: " + joueurs.get(i).getNom(), x, y
					+ PIXELY_CARTE + 20);
			g.drawString("Score: " + joueurs.get(i).getScore(), x, y
					+ PIXELY_CARTE + 40);
			g.drawString("Argent: " + joueurs.get(i).getArgent() + "(+"
					+ joueurs.get(i).getMise() + ")", x, y + PIXELY_CARTE + 60);

			int n_x = x;
			for (int j = 0; j < cartes_joueur.size(); j++) {
				g.drawImage(carte_img[cartes_joueur.get(j).getType().ordinal()
						* 13 + cartes_joueur.get(j).getValeur() - 1], n_x, y,
						this);
				n_x += 20;
			}

			x += 200;

		}

	}

	private void showMessage(String titre, String message) {
		JOptionPane.showMessageDialog(null, titre, message,
				JOptionPane.INFORMATION_MESSAGE);
	}

	private void cacherBoutons() {
		commencer = false;
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		System.out.println(e.getX() + " " + e.getY());
		int x = e.getX();
		int y = e.getY();
		// Bet
		if ((x <= 705 && x >= 670 && y <= 600 && y >= 570)) {
			if (!commencer) {
				int montant = 0;
				String input = "";

				while (input.equals("") || input.length() > 10
						|| input.matches("[^0123456789]+[0123456789]*")
						|| input.matches("[0123456789]*[^0123456789]+")
						|| input.startsWith("-")) {
					
					input = JOptionPane.showInputDialog("Mise");
					if (input == null) {
						return;
					}
				}

				if (input != null) {

					commencer = true;
					montant = Integer.valueOf(input);
					croupier.getJoueurs().get(0).miser(montant);
					croupier.rejouer();
				}
				repaint();
			}

		}

		else if (x <= 985 && x >= 940 && y <= 600 && y >= 560) {
			showMessage("Aurevoir", "A bientot");
			System.exit(0);
		}

		if (commencer) {
			// Carte

			if (x <= 765 && x >= 725 && y <= 575 && y >= 555) {
				croupier.getJoueurs().get(0).demanderCarte();
				croupier.donneCarte();

				if (croupier.getJoueurs().get(0).getScore() > 21) {
					croupier.setCache(false);
					repaint();

					showMessage("Perdu", "Vous avez un score trop haut.");
					croupier.checkJoueurs(croupier.getJoueurs().get(0));
					croupier.supprimerMains();
					croupier.getJoueurs().get(0).miser(0);
					cacherBoutons();
				}

			} else if ((x <= 825 && x >= 790 && y <= 595 && y >= 550)) {

				croupier.croupierJoue();
				croupier.setCache(false);
				repaint();

				if (croupier.checkJoueurs(croupier.getJoueurs().get(0))) {
					showMessage("Gagné !", "Vous avez gagné contre la banque !");
					croupier.supprimerMains();
					croupier.getJoueurs().get(0).miser(0);

				} else {
					showMessage("Perdu", "La banque a un meilleur score!");
					croupier.supprimerMains();
					croupier.getJoueurs().get(0).miser(0);

				}

				cacherBoutons();

			}

			repaint();
			return;
		}

	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {

	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
	}
}
