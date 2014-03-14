#include "iostream"
using namespace std;

int main() {
	int nrNoduri, nrMuchii;
	
	cout << "Numar de Noduri: ";
	cin >> nrNoduri;
	
	int matrice[nrNoduri + 1][nrNoduri + 1], gradInterior[nrNoduri + 1], gradExterior[nrNoduri + 1];
	
	for (int index_linii = 1; index_linii <= nrNoduri; index_linii++)
		for (int index_coloane = 1; index_coloane <= nrNoduri; index_coloane++)
			matrice[index_linii][index_coloane] = 0;
	
	for (int index = 1; index <= nrNoduri; index++) {
		gradInterior[index] = 0;
		gradExterior[index] = 0;
	}
	
	for (int index = 1; index <= nrNoduri; index++) {
		cout << "Nodul " << index << " cate muchii are? - ";
		cin >> nrMuchii;
		
		while (nrMuchii != 0) {
			int nodAdiacent, tipGrad;
			
			cout << "Nodul Adiacent? - ";
			cin >> nodAdiacent;
			
			cout << "Gradul este interior sau exterior?- ";
			cin >> tipGrad;
			
			if (tipGrad == 1) {
				matrice[index][nodAdiacent] = 1;
				matrice[nodAdiacent][index] = -1;
				gradInterior[nodAdiacent]++;
			}
			else {
				matrice[index][nodAdiacent] = -1;
				matrice[nodAdiacent][index] = 1;
				gradExterior[nodAdiacent]++;
			}
			
			nrMuchii--;
		}
	}
	cout << endl << "Matricea de adiacenta: " << endl;
	for (int index_linii = 1; index_linii <= nrNoduri; index_linii++) {
		for (int index_coloane = 1; index_coloane <= nrNoduri; index_coloane++) {
			if (matrice[index_linii][index_coloane] >= 0) 
				cout << matrice[index_linii][index_coloane] << "   ";
			else 
				cout << matrice[index_linii][index_coloane] << "  ";
		}
		cout << endl;
	}
	
	//gradele unui nod
	for (int index = 1; index <= nrNoduri; index++) {
		
		cout << "\nNodul " << index << "\ngradul interior: " << gradInterior[index] << "\ngradul exterior: " << gradExterior[index] << "." << endl;
	}
}