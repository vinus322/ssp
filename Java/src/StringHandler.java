
/*
 * @author KANG LEE
 */
public class StringHandler {
	
	/*
	 * @return Alphabet filtered String
	 */
	public String filterAlphabet(String s) {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<s.length(); i++) {
			if(s.charAt(i)>='A' && s.charAt(i)<='z') {
				sb.append(s.charAt(i));
			}
		}
		return sb.toString();
	}
	
	/*
	 * @return reversed String
	 */
	public String reverse(String s) {
		StringBuilder sb = new StringBuilder(s);
		sb.reverse();
		return sb.toString();
	}
	
	/*
	 * @param Line split String array (capital alphabet only)
	 * @return Line compressed String
	 * @ex 
	 * 		ABDSFE			ABDSFE
	 * 		DKEJIW   -> 	2#DKEJIW
	 * 		DKEJIW
	 */
	public String lineCompress(String[] lineArr) {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<lineArr.length; i++) {
			int duplicatedLines = 1;
			int lastInx = i;
			for(int j=i+1; j<lineArr.length; j++) {
				if(lineArr[i].equals(lineArr[j])) {
					duplicatedLines++;
				} else {
					lastInx = j-1;
					break;
				}
				lastInx = j;
			}
			if(duplicatedLines > 1) {
				sb.append(duplicatedLines);
				sb.append("#");
				sb.append(lineArr[i]);
				sb.append("\n");
			} else {
				sb.append(lineArr[i]);
				sb.append("\n");
			}
			i = lastInx;
		}
		
		return sb.toString().trim();
	}
	
	/*
	 * @param line String (capital alphabet only)
	 * @return Char compressed String
	 */
	public String charCompress(String s) {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<s.length(); i++) {
			int duplicatedChar = 1;
			int lastInx = i;
			char c = s.charAt(i);
			for(int j=i+1; j<s.length(); j++) {
				if(c == s.charAt(j)) {
					duplicatedChar++;
				} else {
					lastInx = j-1;
					break;
				}
				lastInx = j;
			}
			if(duplicatedChar > 2) {
				sb.append(duplicatedChar);
				sb.append(c);
			} else {
				for(int j=0; j<duplicatedChar; j++) {
					sb.append(c);
				}
			}
			i = lastInx;
		}
		
		return sb.toString();
	}
	
	/*
	 * @param Capital letter String, shifting number
	 * @return Shifted String 
	 */
	public String shiftAlphabet(String s, int n) {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<s.length(); i++) {
			char c = (char) (s.charAt(i) + n);
			if(c > 'Z') {
				c = (char) (c - 26);
			}
			sb.append(c);
		}
		return sb.toString();
	}
}
