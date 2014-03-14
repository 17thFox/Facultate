#include "iostream"
using namespace std;

void bubblesort (int secventa[], int dimSecventa) {
	for(int i = 1; i < dimSecventa; i++) { 
		for(int j = 0; j < dimSecventa - 1; j++) { 
			if(secventa[j] > secventa[j+1]) { 
				int temp; 
				temp = secventa[j]; 
				secventa[j] = secventa[j + 1]; 
				secventa[j + 1] = temp; 
			} 
		} 
	} 
}

int main() {
	int dimSecventa;
	
	cout << "Dimensiune Secventa: ";
	cin >> dimSecventa;
	
	int secventa[dimSecventa + 1];
	
	for (int index = 1; index <= dimSecventa; index++) {
		cout << "Termenul " << index << ": ";
		cin >> secventa[index];
	}
	
	bubblesort(secventa, dimSecventa);
	
	int ok = 1, core, ok2 = 1;;
	while (ok == 1 && ok2 == 1) {
		for (int index = 1; index <= dimSecventa; index++) {
		
		
			bubblesort(secventa, dimSecventa);
		
			for (int index = 1; index <= dimSecventa; index++) {
				secventa[index] = secventa[index] - 1;
			}
		
			for (int index = 1; index <= dimSecventa; index++) {
				if (secventa[index] != 0)
					ok2 = 1;
				else 
					ok2 = 0;
			}
		
			if (secventa[index] < 0) {
				ok = 0;
				break;
			} 
		}
	}	
	
	if (ok == 0) 
		cout << "Numerele nu pot forma un graf!\n";
	else 
		cout << "Numerele pot forma un graf!\n";
}