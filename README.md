# 🎮 TypingPractice-Sonagi  
### _소나기처럼 떨어지는 단어들을 잡는 Java 타자 연습 게임_

<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"/>
  <img src="https://img.shields.io/badge/Swing-GUI-blue?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/Typing%20Game-Project-green?style=for-the-badge"/>
</p>

---

## 🌧️ 프로젝트 소개

**TypingPractice-Sonagi**는 하늘에서 *소나기처럼 떨어지는 단어들을 입력해 제거하는*  
**Java Swing 기반 타자 연습 게임**입니다.

⏱️ **플레이 시간 측정**,  
🎯 **명중률 계산**,  
💥 **난이도 선택**,  
📉 **목숨 시스템**  
등의 기능이 있으며, 게임 결과에서 학번·이름·플레이 시간 등을 확인할 수 있습니다.

단어는 `word.txt`에서 불러오며, 매 게임마다 **랜덤으로 셔플**됩니다.

---

## ✨ 주요 기능

### 🕹️ 1. 단어 떨어짐(소나기 연출)
- 단어가 위에서 아래로 떨어지는 애니메이션  
- 정확히 입력하면 단어 제거  
- 놓치면 목숨(life) 감소  

### ⚙️ 2. 난이도 선택
| 난이도 | 설명 | 속도(ms) |
|--------|------|-----------|
| **Low (하)** | 가장 느린 속도 | 1700ms |
| **Mid (중)** | 중간 속도 | 1200ms |
| **High (상)** | 가장 빠름 | 700ms |

### 🧮 3. 결과 화면 제공
- 🔹 총 플레이 시간  
- 🔹 명중률  
- 🔹 이름  
- 🔹 학번  

### 📄 4. 단어 파일(word.txt) 기반
- 단어 리스트를 파일에서 로드  
- 게임 시작 시 랜덤 셔플  

---

## 🗂️ 프로젝트 구조

TypingPractice-Sonagi/
├─ src/
│ └─ taja/
│ ├─ Gui.java
│ ├─ Main.java
│ ├─ Rain.java
│ ├─ Word.java
│ ├─ WordData.java
│ └─ totalPlayTime.java
├─ img/
│ ├─ background.jpg
│ ├─ background2.jpg
│ ├─ background3.jpg
│ ├─ button.jpg
│ ├─ buttonclick.jpg
│ ├─ life3.png
│ ├─ low.png
│ ├─ lowclick.jpg
│ ├─ mid.png
│ ├─ midclick.jpg
│ ├─ high.png
│ └─ highclick.jpg
├─ word.txt
├─ README.md
└─ ...

---

## 🚀 실행 방법

### 1️⃣ JDK 설치
- **JDK 8 이상** 필요  
- OpenJDK / Adoptium / Oracle JDK 모두 가능

### 2️⃣ 프로젝트 다운로드
```bash
git clone https://github.com/eun903/TypingPractice-Sonagi.git

3️⃣ IDE에서 실행

Eclipse 또는 IntelliJ에서
→ Main.java 실행

4️⃣ 터미널에서 실행
cd TypingPractice-Sonagi
javac -encoding UTF-8 -d bin src/taja/*.java
java -cp bin taja.Main


📌 주요 클래스 설명
▶️ Gui.java

전체 GUI 구성

난이도 버튼, 시작/종료 버튼 기능

게임 화면 렌더링

입력 이벤트 처리

▶️ Rain.java

단어 떨어짐을 담당하는 Thread

단어 움직임

생명 감소 및 Game Over 처리

▶️ WordData.java

word.txt 파일 읽기

단어 리스트 생성

랜덤 셔플

▶️ totalPlayTime.java

게임 시간 측정 스레드

종료 시 누적 시간 출력

▶️ Word.java

단어 오브젝트

단어 문자열 및 위치 정보 관리

▶️ Main.java

프로그램 실행 시작점

GUI 프레임 표시

