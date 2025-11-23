package taja;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Gui extends JPanel implements ActionListener, KeyListener {
   public static JLabel[] arrJlabel = new JLabel[20]; // 문제수 20
   public static JLabel[] lifeMark = new JLabel[3]; // 목숨 3
   public static int speed = 1000;
   private JLabel numLabel, nameLabel, resultName, resultNumber, resultAc, resultTime;
   private JButton startButton, lowButton, highButton, midButton, quitButton;
   
   private JTextField inputText, inputName, inputNum;
   private Random myRandom = new Random(); // 랜덤함수
   totalPlayTime total_play_time = new totalPlayTime();
   Rain data_rain = new Rain(); // 글자가 떨어지게하는 클래스
   WordData word_create = new WordData(); // 문제를 생성하는 클래스
   private float tryCount = 0; // 시도 횟수
   private float correctCount = 0; // 맞은 횟수
   private int correctPercent; // 명중률
   public static ImageIcon icon, buttonIcon, buttonOnclick, lifeIcon, lowIcon, lowIconClick, midIcon, midIconClick, highIcon, highIconClick;
   private String studentName, studentNumber;

   public Gui() {
      setSize(800, 600);
      setLayout(null);

      icon = new ImageIcon("img/background.jpg");
      buttonIcon = new ImageIcon("img/button.jpg");
      buttonOnclick = new ImageIcon("img/buttonclick.jpg");
      lifeIcon = new ImageIcon("img/life3.png");
      lowIcon = new ImageIcon("img/low.png");
      lowIconClick = new ImageIcon("img/lowclick.jpg");
      midIcon = new ImageIcon("img/mid.png");
      midIconClick = new ImageIcon("img/midclick.jpg");
      highIcon = new ImageIcon("img/high.png");
      highIconClick = new ImageIcon("img/highclick.jpg");

      startButton = new JButton(buttonIcon);
      startButton.setRolloverIcon(buttonOnclick);
      startButton.setOpaque(false); // startButton을 보이게
      startButton.setBounds(650, 215, 250, 250); // strartButton의 좌표와 범위
      add(startButton); // Gui에 startButton 추가
      startButton.addActionListener(this);
      startButton.setBorder(javax.swing.BorderFactory.createEmptyBorder());
      startButton.setBorderPainted(false);
      startButton.setFocusPainted(false);
      startButton.setContentAreaFilled(false); // startButton 투명으로

      lowButton = new JButton(lowIcon);
      lowButton.setOpaque(false);
      lowButton.setBounds(820, 480, 50, 50);
      add(lowButton);
      lowButton.addActionListener(this);
      lowButton.setBorder(javax.swing.BorderFactory.createEmptyBorder());
      lowButton.setBorderPainted(false);
      lowButton.setFocusPainted(false);
      lowButton.setContentAreaFilled(false); // 난이도 하 버튼 추가

      midButton = new JButton(midIcon);
      midButton.setOpaque(false);
      midButton.setBounds(750, 480, 50, 50);
      add(midButton);
      midButton.addActionListener(this);
      midButton.setBorder(javax.swing.BorderFactory.createEmptyBorder());
      midButton.setBorderPainted(false);
      midButton.setFocusPainted(false);
      midButton.setContentAreaFilled(false); // 난이도 중 버튼 추가

      highButton = new JButton(highIcon);
      highButton.setOpaque(false);
      highButton.setBounds(680, 480, 50, 50);
      add(highButton);
      highButton.addActionListener(this);
      highButton.setBorder(javax.swing.BorderFactory.createEmptyBorder());
      highButton.setBorderPainted(false);
      highButton.setFocusPainted(false);
      highButton.setContentAreaFilled(false); // 난이도 상 버튼 추가

      inputNum = new JTextField(10);
      inputNum.setOpaque(false);
      inputNum.setBounds(210, 250, 180, 35); // 배경1의 텍스트필드
      add(inputNum);
      inputNum.setBorder(javax.swing.BorderFactory
            .createEmptyBorder());// 학번 입력 텍스트필드 추가하고 투명으로

      inputName = new JTextField(10);
      inputName.setOpaque(false);
      inputName.setBounds(210, 375, 180, 35); // 배경1의 텍스트필드의 위치
      add(inputName);
      inputName.setBorder(javax.swing.BorderFactory
            .createEmptyBorder()); // 이름 입력 텍스트필드 추가하고 투명으로
      total_play_time.playTime.setBounds(390, 0, 200, 50);
      total_play_time.playTime.setFont(new Font("Dialog", Font.BOLD, 30));
      add(total_play_time.playTime);
      total_play_time.playTime.setVisible(false); // 타이머추가하고, 안보이게
      word_create.create();

   }

   @Override
   public void actionPerformed(ActionEvent e) { // 버튼이벤트
      if (e.getSource() == startButton) { // startButton 클릭시
         studentName = inputName.getText(); // studentName은 inputName텍스트필드에 입력한값
         studentNumber = inputNum.getText(); // studentNumber은 inputNum텍스트필드에 입력한 값
         nameLabel = new JLabel(studentName);
         numLabel = new JLabel(studentNumber);
         firstStart();

      }

      else if (e.getSource() == lowButton) { // lowButton 버튼 클릭시
         lowButton.setIcon(lowIconClick);
         midButton.setIcon(midIcon);
         highButton.setIcon(highIcon);
         speed = 1700; // speed를 1200으로 지정
      }

      else if (e.getSource() == midButton) {// midButton 버튼 클릭시
         midButton.setIcon(midIconClick);
         lowButton.setIcon(lowIcon);
         highButton.setIcon(highIcon);
         speed = 1200; // speed를 1200으로 지정

      }

      else if (e.getSource() == highButton) {// highButton 버튼 클릭시
         highButton.setIcon(highIconClick);
         lowButton.setIcon(lowIcon);
         midButton.setIcon(midIcon);
         speed = 700;// speed를 700으로 지정

      } else if (e.getSource() == quitButton) {
         System.exit(0);
      }
   }

   @Override
   public void keyPressed(KeyEvent e) {
      // TODO Auto-generated method stub
      if (e.getKeyCode() == 10) { // 엔터가 눌리면
         tryCount++; // 시도 회수 1 증가
         removeAnswer(); // 정답처리 메소드 실행
         endAnswer(); // 모두 정답시 종료 메소드 실행
      }
   }

   @Override
   public void keyReleased(KeyEvent e) {
   }

   @Override
   public void keyTyped(KeyEvent e) {
   }

   private void removeAnswer() { // 정답처리 
      for (int i = 0; i < 30; i++) {
         if (inputText.getText().equals(
               word_create.arr.get(i))) { // 입력된 값이 일치하면
            arrJlabel[i].setVisible(false);
            correctCount++;
         } // JLabel을 안보이게 하고, 맞은 수 1 증가
      }
      inputText.setText("");

   }

   private void endAnswer() { // 정답을 모두 맞췄을 때
      if (arrJlabel[0].isVisible() == false && arrJlabel[1].isVisible() == false && arrJlabel[2].isVisible() == false
            && arrJlabel[3].isVisible() == false && arrJlabel[4].isVisible() == false
            && arrJlabel[5].isVisible() == false && arrJlabel[6].isVisible() == false
            && arrJlabel[7].isVisible() == false && arrJlabel[8].isVisible() == false
            && arrJlabel[8].isVisible() == false && arrJlabel[9].isVisible() == false
            && arrJlabel[10].isVisible() == false && arrJlabel[11].isVisible() == false
            && arrJlabel[12].isVisible() == false && arrJlabel[13].isVisible() == false
            && arrJlabel[14].isVisible() == false && arrJlabel[15].isVisible() == false
            && arrJlabel[16].isVisible() == false && arrJlabel[17].isVisible() == false
            && arrJlabel[18].isVisible() == false && arrJlabel[19].isVisible() == false) { 
    
   

         for (int i = 0; i < arrJlabel.length; i++) {
            arrJlabel[i].setVisible(true);
            arrJlabel[i].setFont(new Font("굴림", Font.BOLD, 1));
            arrJlabel[i].setForeground(Color.white);
            ;
            arrJlabel[i].setLocation(i * 90, 0);
         } // 문제로 나왔던 단어들을 게임상단에 표시

         data_rain.stop(); // 산성비 쓰레드 멈춤
         total_play_time.stop(); // 시간 타이머 쓰레드 멈춤.
         correctPercent = Math.round((correctCount / tryCount) * 100);
         icon = new ImageIcon("img/background3.jpg"); // 배경을 background3으로

         resultNumber = new JLabel(studentNumber);
         resultNumber.setBounds(335, 210, 200, 100);
         resultNumber.setFont(new Font("굴림", Font.BOLD, 22));
         resultNumber.setForeground(Color.WHITE);
         add(resultNumber); // 결과창에 학번추가

         resultName = new JLabel(studentName);
         resultName.setBounds(335, 265, 200, 100);
         resultName.setFont(new Font("굴림", Font.BOLD, 22));
         resultName.setForeground(Color.WHITE);
         add(resultName);// 결과창에 이름추가

         resultTime = new JLabel((Integer.toString(total_play_time.gamePlayTime) + "초"));
         resultTime.setBounds(335, 327, 200, 100);
         resultTime.setFont(new Font("굴림", Font.BOLD, 22));
         resultTime.setForeground(Color.WHITE);
         add(resultTime);

         resultAc = new JLabel(Integer.toString(correctPercent) + "%");

         resultAc.setBounds(365, 380, 200, 100);
         resultAc.setFont(new Font("굴림", Font.BOLD, 22));
         resultAc.setForeground(Color.WHITE);
         add(resultAc); // 명중률

         quitButton = new JButton();
         quitButton.setOpaque(false);
         quitButton.setBounds(470, 485, 80, 30);
         add(quitButton);
         quitButton.addActionListener(this);
         quitButton.setBorder(javax.swing.BorderFactory.createEmptyBorder());
         quitButton.setBorderPainted(false);
         quitButton.setFocusPainted(false);
         quitButton.setContentAreaFilled(false); // 종료하기 버튼을 만들어서 투명으로 설정(이미지의 확인버튼으로 표시)
         }
      }
   

   private void firstStart() { // 게임시작
      inputText = new JTextField(2);
      inputText.addKeyListener(this);
      inputText.setBounds(315, 577, 150, 58); // 게임 시작 후 텍스트
      inputText.setOpaque(false);
      inputText.setBorder(javax.swing.BorderFactory.createEmptyBorder());
      add(inputText); // 답을 입력하는 텍스트필드를 투명  

      for (int i = 0; i < lifeMark.length; i++) {
         lifeMark[i] = new JLabel(lifeIcon);
         lifeMark[i].setOpaque(false);
         lifeMark[i].setBounds(15 + (i * 80), 560, 80, 80);
         add(lifeMark[i]);
         lifeMark[i].setBorder(javax.swing.BorderFactory.createEmptyBorder());
         lifeMark[i].setVisible(true); // 생명 보여줌
      }

      numLabel.setBounds(105, 4, 100, 60);
      nameLabel.setBounds(110, 34, 100, 60);

      startButton.setVisible(false); // 시작 버튼 안보이게
      inputName.setVisible(false);
      inputNum.setVisible(false);
      inputText.setVisible(true); // 답 입력창 보이게
      lowButton.setVisible(false);
      midButton.setVisible(false);
      highButton.setVisible(false);
      icon = new ImageIcon("img/background2.jpg"); // 배경 이미지를 두번째 이미지로
      
      word_create.shuffle();// word_create클래스에서 단어순서를 섞음
      for (int i = 0; i < arrJlabel.length; i++) {
         arrJlabel[i] = new JLabel(
               word_create.arr.get(i)); 

         arrJlabel[i].setBounds(0, 0, 150, 20); // arrJLabel 범위
         arrJlabel[i].setFont(new Font("굴림", Font.BOLD, 15)); // arrJLabel 폰트
         arrJlabel[i].setLocation(20 + i * 45, myRandom.nextInt(250) + 10); // arrJLabel 위치
         add(arrJlabel[i]); // arrJLabel을 패널에 추가

      }

      for (int j = 0; j < lifeMark.length; j++) {
         lifeMark[j].setVisible(true); // 생명현황
      }
      total_play_time.playTime.setVisible(false); // 시간타이머를 안보이게
      total_play_time.start();
      data_rain.start();

   }

   public void paintComponent(Graphics g) {
      g.drawImage(icon.getImage(), 0, 0, null); // 0,0좌표부터 이미지를 뿌림
      setOpaque(false); // 그림 표시, 투명하게
      super.paintComponent(g);
   }

}