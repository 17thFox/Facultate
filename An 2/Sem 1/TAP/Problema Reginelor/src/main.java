
public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProblemaReginelor obj = new ProblemaReginelor();
		obj.solve(4);
	}

}

class ProblemaReginelor {

	int[] regine;

	private boolean isValid(int k) {
		for(int i = 0; i < k; i++) {
			if(regine[i] == regine[k] || regine[i] - i == regine[k] - k || regine[i] + i == regine[k] + k) {
				return false;
			}
		}
		return true;
	}

	private void afisare() {
		for(int i = 0; i < regine.length; i++)
		{
			System.out.print(regine[i] + " ");
		}
		System.out.println();
	}

	private void back(int i) {
		if (i == regine.length) {
			afisare();
		} else {
			for (int k = 0; k < regine.length; k++) {
				regine[i] = k;
				if(isValid(i)){
					back(i+1);
				}
			}
		}
	}

	public void solve(int n) {
		regine = new int[n];
		back(0);
	}
}