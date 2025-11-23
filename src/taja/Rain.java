package taja;

import javax.swing.JOptionPane;

public class Rain extends Thread {

   public int life = 3; // 생명 3

   public void run() {
      for (int i = 0; i < 100; i++) {
         try {
            for (int j = 0; j < Gui.arrJlabel.length; j++) {
               int x = Gui.arrJlabel[j].getX(); // Gui의 arrJLabel의 x좌표값을 x변수에 저장
               int y = Gui.arrJlabel[j].getY();// Gui의 arrJLabel의 y좌표값을 y변수에 저장
               y += 10; // 상중하 버튼 안눌렀을때 단어가 떨어지는 속도
               Gui.arrJlabel[j].setLocation(x, y); // arrJLabel의 위치 다시 지정
               if (Gui.arrJlabel[j].isVisible() && Gui.arrJlabel[j].getY() > 460) {
                  Gui.arrJlabel[j].setVisible(false); // 만약 arrJlabe의 Y좌표가 460이상이 되면
                  life -= 1; // 생명 1감소
                  switch (life) { 
                  case 2: // 생명이 2일 때
                     Gui.lifeMark[2].setVisible(false); // 생명감소
                     break;
                  case 1:
                     Gui.lifeMark[1].setVisible(false);
                     break;
                  case 0:
                     Gui.lifeMark[0].setVisible(false);
                     JOptionPane.showMessageDialog(Gui.lifeMark[0], "Game Over", "Game Over",
                           JOptionPane.INFORMATION_MESSAGE); // 생명이 0이되면 game over 출력
                     System.exit(0); // 프로그램 종료
                     break;

                  }
               }

            }
            Thread.sleep(Gui.speed); // Gui의 speed변수만큼 떨어지는 속도 지정
         } catch (InterruptedException e) {
            e.printStackTrace();
         }

      }
   }

}