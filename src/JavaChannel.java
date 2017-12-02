import java.util.ArrayList;
import java.util.List;

public class JavaChannel {

	/**
	 * Javaについての質問ができるちゃんねる
	 * 
	 * @param numberOfParticipant
	 *            ちゃんねるの参加者数
	 */
	public static void main(int numberOfParticipant) {

		// 質問者を省いた人数分回答者を作成
		List<Answerer> answerList = new ArrayList(numberOfParticipant - 1);
		answerList.forEach(answer -> new Thread(answer::waitTillAnswer));

		// 質問者の作成
		Questioner questioner = new Questioner();
		new Thread(questioner::run);
	}

	interface Participant extends Runnable {
		/**
		 * 質問をする
		 * 
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
		 * 
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

	class Questioner implements Participant {
		Question question;

		public Questioner() {
			question = new Question("質問内容");
		}

		@Override
		public void run() {
			ask();
		}

		@Override
		/**
		 * 質問する
		 * 
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

	class Question {
		String string;

		public Question(String string) {
			this.string = string;
		}

	}
}
