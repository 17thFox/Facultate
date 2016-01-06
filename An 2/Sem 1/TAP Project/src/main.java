import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import java.awt.Color;

public class main extends JFrame {

	private JPanel contentPane;
	private static JPanel panel;
//	JLabel lblBunaTeRog = new JLabel("<html>Buna! Te rog sa adaugi un sir de numere in casuta de mai jos.<br> Acesta va fi sortat dupa metoda Quicksort!</html>");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					v = new int[8];
					v[0] = 0;
					v[1] = 7;
					v = new int[]{
							0,25,26,2,5,4,1,3
					};
					quicksort2(0, 7);
					for(int i =0; i<8; i++){
//						System.out.println(v[i]);
					}

					main frame = new main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private static int v[];// = new int[100];
	private static JTextField textField;
	
//	private static void quicksort(int start, int finish){
//		if (start == finish || start>finish){
//			return;
//		}
//		int i = start;
//		int j = finish;
//		int ii = 1;
//		int jj = 0;
//		int k = seteazaPozitie(start, finish);
//		while (i != k && j!= k)
//		{
////			System.out.println(i+ " " + j + " " + k);
//			if (v[i] < v[j]){
//				
//			}
//			else {
//				interschimba(i,j);
//				if (ii == 1){
//					jj = -1;
//					ii = 0;
//				}
//				else {
//					jj = 0;
//					ii = 1;
//				}
//			}
//			i+=ii;
//			j+=jj;
//		}
//	quicksort(start, k-1);
//	quicksort(k+1, finish); // CTRL + Space pentru autocomplete
//		
//	}
//	/**
//	 * Pune ultimul element (pivot) pe pozitia care trebuie.
//	 * @param start
//	 * @param finish
//	 * @return Returneaza pozitia pe care se afla pivotul.
//	 */
//	private static int seteazaPozitie(int start, int finish){
//		int h = 0;
//		for (int i = start; i < finish; i++ ){
//			if (v[finish] > v[i])
//			{
//				h++;
//			}
//		}
//		interschimba(h + start,finish);
//		return h+start;
//	}
	/**
	 * Functia interschimba elementul de pe pozitia i cu elementul de pe pozitia j. (vector) 
	 * @param i pozitia i
	 * @param j pozitia j
	 */
	 
	private static void interschimba(int i, int j){
		int y = v[i];
		v[i] = v[j];
		v[j] = y;
	}
	
	private static void quicksort2(int start, int finish){
		if (start < 0 || start > finish)
		{
			return;
		}
		int i = start;
		for(int j = start; j < finish; j++){
			if(v[j] < v[finish]){
				interschimba(i, j);
				i++;
			}
		}
		interschimba(finish, i);
		quicksort2(start, i - 1);
		quicksort2(i + 1, finish);
	}

	/**
	 * Create the frame.
	 */
	public main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1280, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<html>Buna! Te rog sa adaugi un sir de numere in casuta de mai jos. Acesta va fi sortat dupa metoda Quicksort!</html>");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(40, 0, 1198, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(12, 26, 1254, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Pas cu pas");
		int position = 200;
		btnNewButton.setBounds(580 - position, 79, 117, 25);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated lmethod stub
				createNumbers();
				System.out.println(v.length);
				System.out.println(turnuri.length);
				clearTurnuri();
				creareTurnuri();
				System.out.println(turnuri.length);
			}
		});
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Automat");
		btnNewButton_1.setBounds(580 + position, 79, 117, 25);
		contentPane.add(btnNewButton_1);
		
		panel = new JPanel();
		panel.setBounds(12, 127, 1254, 421);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.BLACK);
		panel_1.setForeground(Color.BLACK);
		panel_1.setBounds(372, 289, 251, 120);
//		panel.add(panel_1);
		creareTurnuri();
	}
	private static void createNumbers(){
		String numereText = textField.getText().toString();
		String[] numere = numereText.split(" ");
		v = new int [numere.length];
		for(int i = 0; i < numere.length; i++)
		{
			v[i] = Integer.parseInt(numere[i]);
//			System.out.println(v[i]);
		}
	}
	private static void clearTurnuri(){
		if (turnuri == null || turnuri.length == 0){
			return;
		}
		for (int i = 0; i < turnuri.length; i++)
		{
			turnuri[i].setVisible(false);
		}
	}
	private static JPanel[] turnuri;
	private static void creareTurnuri(){
		turnuri = new JPanel[v.length];
		for (int i = 0; i < v.length; i++){
			int spacing = 15;
			int width = 25;
			int height = 15 * v[i];
			int pozitie = (width + spacing) * i;
			
			turnuri[i] = new JPanel();
			turnuri[i].setBackground(Color.BLACK);
			turnuri[i].setForeground(Color.BLACK);
			turnuri[i].setBounds(pozitie, 289+120 - height, width, height);
			turnuri[i].setVisible(true);
			panel.add(turnuri[i]);
		}
	}
}


class ceva {
	private int a;
	public int b;
	public static int c;
	public final int d = 3;
	public static final int e = 4;
//	public static final int e; - gresit, e-ul trebuie initializat 
	public static final String coloanaBazaDeDate = "Coloana Prenume";
}
class altaClasa {
	public void f(){
		ceva obj = new ceva();
		ceva obj2 = new ceva();
		obj.c = 1;
		obj2.c = 2;
		ceva.c = 3;
		obj.b = 1;
		obj2.b = 2;
//		ceva.b = 3; - gresit, b nu este static (ctrl + /  -  comenteaza si decomenteaza)
		System.out.println(obj.c); // afiseaza 3
//		obj.d = 4;  - gresit, d este final, adica constant
	}
}