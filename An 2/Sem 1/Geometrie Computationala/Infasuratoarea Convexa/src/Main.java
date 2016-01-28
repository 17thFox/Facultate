import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.management.loading.MLet;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class Main extends JFrame {

	private JTextArea puncteTextArea;
	private JPanel contentPane;
	private JLabel scaleLabel;
	private JLabel translateXLabel;
	private JLabel translateYLabel;
	private PointsPanel punctePanel;
	private float scale;
	private float translateX;
	private float translateY;
	private final int pointSize = 20; //pixeli
	private ArrayList<MyPoint> listaPuncte = new ArrayList<>();
	private ArrayList<MyPoint> listaPuncteSolutie = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		scale = 1;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 0, 974, 100);
		contentPane.add(scrollPane);

		puncteTextArea = new JTextArea();
		scrollPane.setViewportView(puncteTextArea);

		final JButton button = new JButton("Draw");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				listaPuncteSolutie.clear();
				getPuncteFromText(puncteTextArea.getText());

				if (listaPuncte.size() >= 3) {
					swapCelMaiDeJosPunct();
					sorteazaPuncte();
					gasestePunctele();

					punctePanel.repaint();
				}
			}
		});
		button.setBounds(12, 112, 117, 25);
		contentPane.add(button);

		JButton button_1 = new JButton("Scale +");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				scale += 10.1f;
				scaleLabel.setText("Scale: " + scale);
				
				punctePanel.repaint();
			}
		});
		button_1.setBounds(141, 112, 117, 25);
		contentPane.add(button_1);

		JButton button_2 = new JButton("Scale -");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				scale -= 10.1f;
				if (scale <= 0) {
					scale = 0.1f;
				}
				scaleLabel.setText("Scale: " + scale);
				
				punctePanel.repaint();
			}
		});
		button_2.setBounds(270, 112, 117, 25);
		contentPane.add(button_2);

		JButton button_3 = new JButton("<-");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				translateX -= (0.1f * scale);
				translateXLabel.setText("Translate X: " + translateX);
				
				punctePanel.repaint();
			}
		});
		button_3.setBounds(399, 112, 117, 25);
		contentPane.add(button_3);

		JButton button_4 = new JButton("->");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				translateX += (0.1f * scale);
				translateXLabel.setText("Translate X: " + translateX);
				
				punctePanel.repaint();
			}
		});
		button_4.setBounds(528, 112, 117, 25);
		contentPane.add(button_4);

		JButton button_5 = new JButton("Translate Sus");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				translateY += (0.1f * scale);
				translateYLabel.setText("Translate Y: " + translateY);
				
				punctePanel.repaint();
			}
		});
		button_5.setBounds(657, 112, 137, 25);
		contentPane.add(button_5);

		JButton button_6 = new JButton("Translate Jos");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				translateY -= (0.1f * scale);
				translateYLabel.setText("Translate Y: " + translateY);
				
				punctePanel.repaint();
			}
		});
		button_6.setBounds(806, 112, 137, 25);
		contentPane.add(button_6);

		scaleLabel = new JLabel("Scale: 1");
		scaleLabel.setBounds(12, 150, 200, 15);
		contentPane.add(scaleLabel);

		translateXLabel = new JLabel("Translate X: 0");
		translateXLabel.setBounds(12, 165, 200, 15);
		contentPane.add(translateXLabel);

		translateYLabel = new JLabel("Translate Y: 0");
		translateYLabel.setBounds(12, 180, 200, 15);
		contentPane.add(translateYLabel);

		punctePanel = new PointsPanel();
		punctePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		punctePanel.setBounds(12, 200, 974, 450);
		contentPane.add(punctePanel);
	}

	private void getPuncteFromText(String text) {
		listaPuncte.clear();

		String[] pointsString = text.split("\n");
		for (int i = 0; i < pointsString.length; i++) {
			try {
				String[] coordinatesString = pointsString[i].split(" ");
	
				float x = Float.parseFloat(coordinatesString[0]);
				float y = Float.parseFloat(coordinatesString[1]);
	
				System.out.println(x + " " + y);
	
				listaPuncte.add(new MyPoint(x, y));
			}
			catch(Exception exception){
				
			}
		}
	}

	private void sorteazaPuncte() {
		
		final MyPoint celMaiDeJos = listaPuncte.get(0);
		listaPuncte.remove(0);
		
		Collections.sort(listaPuncte, new Comparator<MyPoint>() {

			/**
			 * return -1 : A -> B
			 * return  1 : B -> A
			 * return  0 : A -> B
			 */
			@Override
			public int compare(MyPoint A, MyPoint B) {

				if (getPolarAngle(A, celMaiDeJos) < getPolarAngle(B, celMaiDeJos)) {
					return -1;
				} else if (getPolarAngle(A, celMaiDeJos) > getPolarAngle(B, celMaiDeJos)) {
					return 1;
				}
				return 0;
			}
		});
		listaPuncte.add(0, celMaiDeJos);
		
	}

	float getPolarAngle(MyPoint point, MyPoint source) {
		//Math.atan2 <=> tan^(-1)
		return (float) (Math.atan2(point.y - source.y, point.x - source.x));
	}

	/**
	 * 
	 * @param A
	 * @param B
	 * @param C
	 * @return > 0 => C e la stanga AB < 0 => C e la dreapta AB ==0 => A, B, C -
	 *         coliniare
	 */
	float direction(MyPoint A, MyPoint B, MyPoint C) {
		return (B.x - A.x) * (C.y - A.y) - (B.y - A.y) * (C.x - A.x);
	}

	/**
	 * 
	 * @param points
	 *            lista de puncte (sus sau jos)
	 * @param upOrDown
	 *            -1 pentru lista de jos, 1 pentru lista de sus
	 */

	private void gasestePunctele() {
		
		int nrPuncte = listaPuncte.size();
		// adauga ultimul punct pe prima pozitie (el va face parte din
		// infasuratoare)
		listaPuncte.add(0, listaPuncte.get(nrPuncte - 1));

//		System.out.println("sorted:");
//		for(int i = 0; i < listaPuncte.size(); i++)
//		{
//			System.out.println(i + " " + listaPuncte.get(i).x + " " + listaPuncte.get(i).y);
//		}
		
		// Numarul de pucte din solutie
		int nrPuncteSol = 1;
		for (int i = 2; i <= nrPuncte; i++) {
			// Cat timp punctul este coliniar sau in dreapta liniei determinate de ultimele doua pcte din solutie
			while (directie(listaPuncte.get(nrPuncteSol - 1), listaPuncte.get(nrPuncteSol), listaPuncte.get(i)) <= 0) {
				//Ultimul pct din solutie nu e bun, asa ca il elimin
				if (nrPuncteSol > 1) {
					nrPuncteSol -= 1;
				}
				// All points are collinear
				else if (i == nrPuncte) {
					break;
				} else {
					i += 1;
				}
			}

			nrPuncteSol += 1;
			swapPoints(nrPuncteSol, i);
		}
		
		for(int i = 0; i < nrPuncteSol; i++)
		{
			listaPuncteSolutie.add(listaPuncte.get(i));
			System.out.println("solutie, punctul #" + (i+1) + ": " + listaPuncte.get(i).x + " " + listaPuncte.get(i).y);
		}
	}

	/**
	 * Interschimba punctul de pe pozitia 0 cu punctul cel mai de jos (sau
	 * cel mai din stanga in caz de egalitate)
	 */
	private void swapCelMaiDeJosPunct() {
		MyPoint celMaiDeJos = listaPuncte.get(0);
		int indexCelMaiDeJos = 0;
		for (int i = 1; i < listaPuncte.size(); i++) {

			if (listaPuncte.get(i).y < celMaiDeJos.y) {
				celMaiDeJos = listaPuncte.get(i);
				indexCelMaiDeJos = i;
			} else if (listaPuncte.get(i).y == celMaiDeJos.y) {

				if (listaPuncte.get(i).x < celMaiDeJos.x) {
					celMaiDeJos = listaPuncte.get(i);
					indexCelMaiDeJos = i;
				}
			}
		}

		swapPoints(0, indexCelMaiDeJos);
	}

	private void swapPoints(int i, int j) {
		MyPoint aux = listaPuncte.get(i);
		listaPuncte.set(i, listaPuncte.get(j));
		listaPuncte.set(j, aux);
	}

	/**
	 *
	 * | 1 	1  1  | 
	 * | Ax Bx Cx |
	 * | Ay By Cy | 
	 * Det = Bx * Cy + Ax * By + Ay * Cx - Bx * Ay - By * Cx - Cy * Ax
	 *
	 * @param A
	 * @param B
	 * @param C
	 * @return 
	 * ==0 => cele 3 puncte sunt coliniare, 
	 * > 0 => C este pe partea stanga a lui AB, 
	 * < 0 => C este pe partea dreapta a lui AB
	 */
	int directie(MyPoint A, MyPoint B, MyPoint C) {
		return (int) (B.x * C.y + A.x * B.y + A.y * C.x - B.x * A.y - B.y * C.x - C.y * A.x);
	}

	class PointsPanel extends JPanel {

		@Override
		public void paintComponent(Graphics graphics) {
			super.paintComponent(graphics);

			System.out.println("in paint: " + listaPuncteSolutie.size());

			if (listaPuncte.size() >= 3) {
				drawListOfPoints(graphics, Color.BLACK, listaPuncte);
				drawListOfPoints(graphics, Color.RED, listaPuncteSolutie);

				for (int i = 0; i < listaPuncteSolutie.size(); i++) {
					MyPoint A = listaPuncteSolutie.get(i);
					MyPoint B = listaPuncteSolutie.get((i + 1) % listaPuncteSolutie.size());

					drawLine(graphics, Color.BLACK, A, B);
				}
			}
		}

		private void drawListOfPoints(Graphics graphics, Color color, ArrayList<MyPoint> points) {

			graphics.setColor(color);
			
			for (int i = 0; i < points.size(); i++) {
				
				graphics.fillOval(
						(int) (points.get(i).x * scale + translateX) - pointSize / 2, 
						punctePanel.getHeight() - (int) (points.get(i).y * scale + translateY) - pointSize / 2, 
						pointSize,
						pointSize
					);
			}
		}

		private void drawLine(Graphics graphics, Color color, MyPoint A, MyPoint B) {
			graphics.setColor(color);
			
			graphics.drawLine(
					(int) (A.x * scale + translateX), 
					punctePanel.getHeight() - (int) (A.y * scale + translateY), 
					(int) (B.x * scale + translateX), 
					punctePanel.getHeight() - (int) (B.y * scale + translateY)
				);
		}
		
	}
	
}

class MyPoint {
	float x;
	float y;

	public MyPoint(float x, float y) {
		this.x = x;
		this.y = y;
	}
}
