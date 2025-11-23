package taja;

import javax.swing.JLabel;

public class totalPlayTime extends Thread { // 게임시간을 측정
	public int gamePlayTime;
	public JLabel playTime = new JLabel("0"); // 초기값 0

	public void run() {
		for (gamePlayTime = 0;; gamePlayTime++) {
			try {
				Thread.sleep(1000); // 1초에 한번씩 gamePlayTime 변수 증가
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			playTime.setText(gamePlayTime + ""); // playTime레이블을 gamePlayTime변수로 설정
		}
	}
}