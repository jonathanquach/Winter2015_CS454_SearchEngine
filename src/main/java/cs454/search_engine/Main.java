package cs454.search_engine;

public class Main {
	public static void main(String[] args) {
		try {
			String startDir = "C:\\Users\\Jonathan\\workspace\\maven\\search_engine-app\\testdirectory";
//			String startDir = args;
			Crawler crawler = new Crawler();
			crawler.walk(startDir);
		} catch (Exception ex) {
			System.out.println("Invalid Directory.");
		}
	}
}
