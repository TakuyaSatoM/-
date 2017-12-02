import java.util.ArrayList;
import java.util.List;

public class JavaChannel {

	/**
	 * Javaについての質問ができるちゃんねる
	 * 
	 * @param numberOfParticipant
	 *            ちゃんねるの参加者数
	 */
	public static void main(String[] args) {

		// 質問者を省いた人数分回答者を作成(5人の場合)
		int numberOfParticipant = 5;
		List<Answerer> answerList = new ArrayList(numberOfParticipant - 1);
		answerList.forEach(answer -> new Thread(answer::waitTillAnswer).start());

		// 質問者の作成
		Questioner questioner = new Questioner("質問内容");
		new Thread(questioner::run);
	}

	interface Participant extends Runnable {
		/**
		 * 質問をする
		 */
		public void ask();

		/**
		 * 回答する
		 * 
		 * @return 答え
		 */
		public void answer();
	}

	class Answerer implements Participant {
		String knowledge;

		public Answerer() {
			this.knowledge = "答え";
		}

		@Override
		public void run() {
			answer();
		}

		/**
		 * 質問が来るまで待機する。
		 */
		public void waitTillAnswer() {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void ask() {
			// TODO Auto-generated method stub

		}

		@Override
		/**
		 * 回答する
		 * 
		 * @return knowledge 答え
		 */
		public void answer() {
			System.out.println(knowledge);
		}

	}

	static class Questioner implements Participant {
		String question;

		public Questioner(String question) {
			this.question = question;
		}

		@Override
		public void run() {
			ask();
		}

		@Override
		/**
		 * 質問する
		 */
		public void ask() {
			System.out.println(question);
			notifyAll();
		}

		@Override
		public void answer() {
			// TODO Auto-generated method stub
		}

	}
}
