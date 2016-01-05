import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main frame = new main();
					frame.setVisible(true);
					v = new int[8];
					v[0] = 0;
					v[1] = 7;
					v = new int[]{
							0,7,6,2,5,4,1,3
					};
					quicksort(0, 7);
					for(int i =0; i<8; i++){
						System.out.println(v[i]);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private static int v[];// = new int[100];
	
	private static void quicksort(int start, int finish){
		if (start == finish || start>finish){
			return;
		}
		int i = start;
		int j = finish;
		int ii = 1;
		int jj = 0;
		int k = seteazaPozitie(start, finish);
		while (i != k && j!= k)
		{
//			System.out.println(i+ " " + j + " " + k);
			if (v[i] < v[j]){
				
			}
			else {
				interschimba(i,j);
				if (ii == 1){
					jj = -1;
					ii = 0;
				}
				else {
					jj = 0;
					ii = 1;
				}
			}
			i+=ii;
			j+=jj;
		}
	quicksort(start, k-1);
	quicksort(k+1, finish); // CTRL + Space pentru autocomplete
		
	}
	/**
	 * Pune ultimul element (pivot) pe pozitia care trebuie.
	 * @param start
	 * @param finish
	 * @return Returneaza pozitia pe care se afla pivotul.
	 */
	private static int seteazaPozitie(int start, int finish){
		int h = 0;
		for (int i = start; i < finish; i++ ){
			if (v[finish] > v[i])
			{
				h++;
			}
		}
		interschimba(h + start,finish);
		return h+start;
	}
	private static void interschimba(int i, int j){
		int y = v[i];
		v[i] = v[j];
		v[j] = y;
	}

	/**
	 * Create the frame.
	 */
	public main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
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