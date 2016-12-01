package persistence.util;

public class PrintAtDepth {
	
	public static void print(int depth,String text){
		for(int i = 0; i<depth;i++){
			System.out.print("    ");
		}
		System.out.println(text);
	}
	
	public static void err(int depth,String text){
		for(int i = 0; i<depth;i++){
			System.out.print("    ");
		}
		System.err.println(text);
	}
}
