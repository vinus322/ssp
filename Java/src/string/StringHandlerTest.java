package string;

public class StringHandlerTest {

	public static void main(String[] args) {
		StringHandler stringHandler = new StringHandler();
		
		// NO.1
		System.out.println("1. String filter alphabet");
		String s1 = "aasd2134teeo214";
//		String s1 = "12432";
		
		System.out.println(stringHandler.filterAlphabet(s1));
		
		// NO.2
		System.out.println("2. String reverse");
		String s2 = "abcdefg";
//		String s2 = "ooqwjkelw";
		
		System.out.println(stringHandler.reverse(s2));
		
		// NO.3
		System.out.println("3. String Line compress");
		String s3 = "ABDSFEEE\nDKABDEJIW\nDKABDEJIW\nABDSFEEE\nABDSFEEE";
		String[] s3Arr = s3.split("\n");
		
		System.out.println(stringHandler.lineCompress(s3Arr));
		
		// NO.4
		System.out.println("4. String Char compress");
		String s4 = "ABDSDDDEWRQQQASSG";
		
		System.out.println(stringHandler.charCompress(s4));
		
		// NO.5
		System.out.println("5. Shifting alphabet");
		String s5 = "EQFPQKLLSNAKX";
		
		System.out.println(stringHandler.shiftAlphabet(s5, 5));
	}
}
