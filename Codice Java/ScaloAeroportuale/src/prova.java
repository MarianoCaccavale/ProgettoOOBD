
public class prova {

	public static String treconsonanti(String s) {
		
		String stringa = new String();
		stringa = s;
		String result = new String();
		int i = 0;
		
		for (char c : stringa.toCharArray()){
			
			if(c != 'a' && c != 'e' && c != 'o' && c != 'u' && c != 'i') {
				
				result = result + c;
				i++;
				
			}else if  (i >= 3) {
				
				break;
				
			}
			
		}
		
		return result;
		
		
		
	}
	
	public static void main(String[] args) {
		
		
		String s = new String();
		String trelettere = new String();
		
		s = "perugia";
		trelettere = treconsonanti(s);

		
		System.out.println(trelettere);
	}

}
