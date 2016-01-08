import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import sun.launcher.resources.launcher;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println('a' + 0);
		Huffman obj = new Huffman();
		obj.solve("aabaccdddd");
	}

}

class Huffman {
	int frecv[] = new int[256];
	ArrayList<Integer> sumaNodurilor = new ArrayList<>();
	ArrayList<Nod> nodurile = new ArrayList<>();

	public void solve(String text) {

		for (int i = 0; i < text.length(); i++) {
			if (frecv[text.charAt(i)] == 0) {

				Nod n = new Nod();
				n.caracter = text.charAt(i);

				nodurile.add(n);
//				nodurile.add(new Nod());
			}
			frecv[text.charAt(i)]++;
		}

		for (int i = 0; i < nodurile.size(); i++) {
			nodurile.get(i).frecventa = frecv[nodurile.get(i).caracter];
			// frecventaCaracterelor[i] - vector
			// frecventaCaracterelor.get(i) - arrayList
		}

		for(int i = 0; i < nodurile.size() - 1; i += 2) {
			Nod nod1 = celMaiMicNod();
			Nod nod2 = celMaiMicNod();

			
			Nod newNod = new Nod();
			newNod.frecventa = nod1.frecventa + nod2.frecventa;
			if (nod1.frecventa < nod2.frecventa) {
				newNod.stanga = nod1;
				newNod.dreapta = nod2;
			} else {
				newNod.stanga = nod2;
				newNod.dreapta = nod1;
			}
			nodurile.add(newNod);
		}
		afisare(nodurile.get(nodurile.size() - 1), "");
	}

	private void afisare(Nod node, String code) {
		if (node != null) {
			if (node.caracter != null) {
				System.out.println(node.caracter + ": " + code);
			} else {
				afisare(node.stanga, code + "0");
				afisare(node.dreapta, code + "1");
			}
		}
	}

	private Nod celMaiMicNod() {
		int pos = 0;
		int min = 100000;
		for (int i = 0; i < nodurile.size(); i++) {
			if (nodurile.get(i).getFolosire() == false) {
				if (nodurile.get(i).frecventa < min) {
					min = nodurile.get(i).frecventa;
					pos = i;
				}
			}
		}
		nodurile.get(pos).foloseste();
		return nodurile.get(pos);
	}
}

class Nod {
	int frecventa;
	Character caracter;
	Nod stanga;
	Nod dreapta;
	boolean folosit;

	public Nod() {
		caracter = null;
	}

	public void foloseste() {
		folosit = true;
	}

	public boolean getFolosire() {
		return folosit;
	}

}
