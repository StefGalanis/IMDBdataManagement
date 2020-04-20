import java.io.IOException;

public class Evaluator {

	public Evaluator() {
		
	}

	public static void main(String[] args) throws IOException {
		long startTime = System.nanoTime();
		Question11 question11 = new Question11();
		Question12 question12 = new Question12();
		Question13 question13 = new Question13();
		Question21 question21 = new Question21();
		Question22 question22 = new Question22();
		long stopTime = System.nanoTime();
		System.out.println("total executing time is:" + "\t" + (stopTime - startTime));
	}
}
