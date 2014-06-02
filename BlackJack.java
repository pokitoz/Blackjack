import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
//import javax.swing.JOptionPane;

public class BlackJack {

//	private static ImageIcon background = new ImageIcon("img/back.jpg");
	public static void main(String[] args) {

		//ImageIcon background = new ImageIcon("img/back.jpg");
//		JFrame f = new JFrame();
//		JLabel back = new JLabel(background);
//		f.setSize(642, 310);
//		f.setLocation(500, 0);
//		f.setVisible(true);
//		f.add(back);

		try {
			JFrame frame = new JFrame("BlackJack");
			int nombreJoueur;
//			int nombreJoueur = -1;
//			String input = "";
//			while (nombreJoueur <= 0 || nombreJoueur > 5 || input.matches("[^0123456789]+")) {
//				input = JOptionPane.showInputDialog(null,
//						"Nombre de joueur(s) [min 1, max 5]");
//				if (input == null) {
//					System.exit(0);
//				}	
//				if (input.matches("[0123456789]+")) {
//					nombreJoueur = Integer.valueOf(input);
//				}
//
//			}
			nombreJoueur = 1;
			
			//Si le fichier n'existe pas, on le créé.
			File f = new File("Scores.txt");
			
			Jeu jeu = new Jeu(nombreJoueur);

			frame.setSize(Jeu.PIXELX_TAPIS, Jeu.PIXELY_TAPIS + 50);
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setContentPane(jeu);

			frame.setVisible(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
