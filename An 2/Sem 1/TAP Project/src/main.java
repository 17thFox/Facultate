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
import javax.swing.SwingUtilities;

import java.awt.Color;

public class main extends JFrame {

	private JPanel contentPane;
	private static JPanel panel;
	private ThreadQuicksort threadQuicksort;
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
							6,25,26,2,5,4,1,3
					};
//					quicksort2(0, 7);
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
	private JButton btnNewButton_2;
	private JTextField textField_1;
	
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
	 

	/**
	 * Create the frame.
	 */
	public main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1280, 700);
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
		
		JButton btnNewButton = new JButton("Init");
		int position = 200;
		btnNewButton.setBounds(580 - position, 79, 117, 25);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated lmethod stub
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
//						createNumbers();
//						clearTurnuri();
//						creareTurnuri();
//						clearTurnuri();
//						creareTurnuri();
						if (threadQuicksort == null){
						}
						else {
							threadQuicksort.stopAll();
							threadQuicksort.clearTurnuri();
						}
						threadQuicksort = new ThreadQuicksort(textField.getText().toString(), panel, true, textField_1);					
						threadQuicksort.start();
					}
				});
			}
		});

		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Automat");
		btnNewButton_1.setBounds(580 + position, 79, 117, 25);
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated lmethod stub
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
//						createNumbers();
//						clearTurnuri();
//						creareTurnuri();
//						clearTurnuri();
//						creareTurnuri();
						if (threadQuicksort == null){
						}
						else {
							threadQuicksort.stopAll();
							threadQuicksort.clearTurnuri();
						}
						threadQuicksort = new ThreadQuicksort(textField.getText().toString(), panel, false, textField_1);					
						threadQuicksort.start();
					}
				});
			}
		});
		contentPane.add(btnNewButton_1);
		
		panel = new JPanel();
		panel.setBounds(12, 127, 1254, 421);
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnNewButton_2 = new JButton("Pas cu pas");
		btnNewButton_2.setBounds(380, 104, 117, 25);
		btnNewButton_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated lmethod stub
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
//						createNumbers();
//						clearTurnuri();
//						creareTurnuri();
//						clearTurnuri();
//						creareTurnuri();
						if (threadQuicksort != null){
							threadQuicksort.nextPas();

							System.out.println("click");
						}
					}
				});
			}
		});

		contentPane.add(btnNewButton_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(12, 593, 1254, 25);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
//		textField_1.setEnabled(false);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.BLACK);
		panel_1.setForeground(Color.BLACK);
		panel_1.setBounds(372, 289, 251, 120);
//		panel.add(panel_1);
//		creareTurnuri();
	}
	
	
//	private static JPanel[] turnuri;
//	
//	private static void creareTurnuri(){
//		turnuri = new JPanel[v.length];
//		for (int i = 0; i < v.length; i++){
//			int spacing = 15;
//			int width = 25;
//			int height = 15 * v[i];
//			int pozitie = (width + spacing) * i;
//			
//			turnuri[i] = new JPanel();
//			turnuri[i].setBackground(Color.BLACK);
//			turnuri[i].setForeground(Color.BLACK);
//			turnuri[i].setBounds(pozitie, 289+120 - height, width, height);
//			turnuri[i].setVisible(true);
//			System.out.println(i + " " + spacing + " " + width + " " + height + " " + pozitie);
//			panel.add(turnuri[i]);
//		}
//		panel.revalidate();
//	}
}

class ThreadQuicksort extends Thread{
	private String numereMele;
	private JPanel panel;
	private boolean stop;
	private boolean next;
	private boolean isPascuPas;
	private JTextField textField_1;

	
	public ThreadQuicksort(String numereMele, JPanel panel, boolean isPascuPas, JTextField textField_1) {
			this.numereMele = numereMele;
			this.panel = panel;
			this.isPascuPas = isPascuPas;
			this.textField_1 = textField_1;
	}
	
	@Override
	public void run(){ // ce scriu in run o sa se faca pe alt thread
		createNumbers();
		
		creareTurnuri();
		quicksort2(0, v.length - 1);
		afiseaza();
//		clearTurnuri();
//		creareTurnuri();
	}
	private void afiseaza(){
//		adaug toate elementele vectorului in string, dupa care afisez stringul in textfield_1 prin settext
		String elemente = "";
		for (int i = 0; i < v.length; i++){
			elemente += String.valueOf(v[i]) + " ";
		}
	textField_1.setText(elemente);	
		
	}
	
	int[] v;
	
	private void createNumbers(){
		String numereText = numereMele;
		String[] numere = numereText.split(" ");
		v = new int [numere.length];
		for(int i = 0; i < numere.length; i++)
		{
			v[i] = Integer.parseInt(numere[i]);
//			System.out.println(v[i]);
		}
	}
	
	public void clearTurnuri(){
		if (turnuri == null || turnuri.length == 0){
			return;
		}
		System.out.println("clear size " + turnuri.length);
		for (int i = 0; i < turnuri.length; i++)
		{
			turnuri[i].setVisible(false);
		}
		panel.removeAll();
		panel.revalidate();
		panel.repaint();
	}
	private JPanel[] turnuri;
	
	private void creareTurnuri(){
		turnuri = new JPanel[v.length];
		for (int i = 0; i < v.length; i++){
//			if(stop)
//			{
//				return;
//			}
			int spacing = 15;
			int width = 25;
			int height = 15 * v[i];
			int pozitie = (width + spacing) * i;
			
			turnuri[i] = new JPanel();
			turnuri[i].setBackground(Color.BLACK);
			turnuri[i].setForeground(Color.BLACK);
			turnuri[i].setBounds(pozitie, 289+120 - height, width, height);
			turnuri[i].setVisible(true);
//			System.out.println(i + " " + spacing + " " + width + " " + height + " " + pozitie);
			panel.add(turnuri[i]);
			panel.revalidate();
			panel.repaint();
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
	}
	
	public void stopAll()
	{
		stop = true;
	}
	
	private void interschimba(int i, int j){
		int y = v[i];
		v[i] = v[j];
		v[j] = y;
	}
	
	private void quicksort2(int start, int finish){
		if (start < 0 || start > finish || stop == true)
		{
			return;
		}
		int i = start;
		for(int j = start; j < finish; j++){
			updateColors(finish, i, j);
			pas();
			if (stop == true){
				return;
			}
			updateColors2(finish, i, j);
			if(v[j] < v[finish]){
				interschimba(i, j);
				resize(i);
				resize(j);
				i++;
			}
		}
		interschimba(finish, i);
		pas();
		if (stop == true){
			return;
		}
		resize(i);
		resize(finish);
		pas();
		if (stop == true){
			return;
		}
		updateColors2(finish, i, finish);
		quicksort2(start, i - 1);
		quicksort2(i + 1, finish);
	}
	
	private void updateColors(int pivotPosition,int i, int j){
		turnuri[pivotPosition].setBackground(Color.RED);
		
		if ( i == j){
			turnuri[i].setBackground(Color.BLUE);
		}
		else{
			turnuri[i].setBackground(Color.YELLOW);
			turnuri[j].setBackground(Color.GREEN);	
		}
		panel.revalidate();
		panel.repaint();
	}
	private void updateColors2(int pivotPosition,int i, int j){
		turnuri[pivotPosition].setBackground(Color.RED);
		if (pivotPosition == j){
			turnuri[pivotPosition].setBackground(Color.BLACK);
		}
			turnuri[i].setBackground(Color.BLACK);
			turnuri[j].setBackground(Color.BLACK);	
		
		panel.revalidate();
		panel.repaint();
	}
	private void resize(int i)
	{

		int spacing = 15;
		int width = 25;
		int height = 15 * v[i];
		int pozitie = (width + spacing) * i;

		turnuri[i].setBounds(pozitie, 289+120 - height, width, height);
		panel.revalidate();
		panel.repaint();
	}
	private void pas(){
		if(isPascuPas == false ){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		else {
			while (next == false && stop == false){

				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			next = false;
//			System.out.println("pas");
		}
	}
	public void nextPas(){
		next = true;
	}
}
