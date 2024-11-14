# Timezone-test

## 소개
+ JVM, JDBC, MySQL의 타임존에 따라 시간이 어떻게 처리되는지 알기 위한 실습입니다.

<br><br>

## 필드에 대한 정보
+ input은 사용자가 API를 호출할 때, 직접 입력한 값입니다.
+ now는 애플리케이션 서버에서 LocalDateTime.now()를 통해 생성된 값입니다.
+ createdAt과 updatedAt은 하이버네이트를 통해 자동으로 처리되는 값입니다.
+ 실험에서 나머지는 (now, createdAt, updatedAt)를 뜻합니다.

<br><br>

## 실험

<img src="https://github.com/user-attachments/assets/a9298060-e1e1-47a1-bf47-e08019d51744" width="600" height="160">

#### API 리턴 값
+ JVM이 Asia/Seoul이므로 input은 입력 시간 그대로 나머지는 Asia/Seoul로 리턴한다.

#### DB 저장 값
+ JVM이 Asia/Seoul이므로 now, createdAt, updatedAt은 Asia/Seoul으로 생성된다.
+ JDBC이 UTC일 경우, Asia/Seoul -> UTC으로 변해서 input과 나머지가 9시간 줄어든다.
+ JDBC이 Asia/Seoul일 경우, Asia/Seoul가 유지되므로 input은 그대로 나머지는 Asia/Seoul로 저장된다.

<br><br>

<img src="https://github.com/user-attachments/assets/716e6d90-2a33-40ec-b788-8b17515e3035" width="600" height="160">

#### API 리턴 값
+ JVM이 Asia/Seoul이므로 input은 입력 시간 그대로 나머지는 Asia/Seoul로 리턴한다.

#### DB 저장 값
+ JVM이 Asia/Seoul이므로 now, createdAt, updatedAt은 Asia/Seoul으로 생성된다.
+ JDBC이 UTC일 경우, Asia/Seoul -> UTC으로 변해서 input과 나머지가 9시간 줄어든다.
+ JDBC이 Asia/Seoul일 경우, Asia/Seoul가 유지되므로 input은 그대로 나머지는 Asia/Seoul로 저장된다.

<br><br>

<img src="https://github.com/user-attachments/assets/279360ae-a280-4dcd-97a5-4ad3355ec4b1" width="600" height="160">

#### API 리턴 값
+ JVM이 UTC이므로 input은 입력 시간 그대로 나머지는 UTC로 리턴한다.

#### DB 저장 값
+ JVM이 UTC이므로 now, createdAt, updatedAt은 UTC로 생성된다.
+ JDBC이 UTC일 경우, UTC가 유지되므로 input은 그대로 나머지는 UTC로 저장된다.
+ JDBC이 Asia/Seoul일 경우, UTC -> Asia/Seoul으로 변해서 input과 나머지가 9시간 늘어난다.

<br><br>

<img src="https://github.com/user-attachments/assets/8cf53e9e-8c3a-4883-8ced-0afc9a875d4b" width="600" height="160">

#### API 리턴 값
+ JVM이 UTC이므로 input은 입력 시간 그대로 나머지는 UTC로 리턴한다.

#### DB 저장 값
+ JVM이 UTC이므로 now, createdAt, updatedAt은 UTC로 생성된다.
+ JDBC이 UTC일 경우, UTC가 유지되므로 input은 그대로 나머지는 UTC로 저장된다.
+ JDBC이 Asia/Seoul일 경우, UTC -> Asia/Seoul으로 변해서 input과 나머지가 9시간 늘어난다.

<br><br>


## 결론
+ API 리턴 값은 JVM의 타임존에만 영향을 받는다.
+ DB 저장 값은 JVM과 JDBC에 따라 결정된다.
+ MySQL의 타임존은 결과에 영향을 미치지 않는다.
  
<br>

### 참고 사항
+ JDBC의 타임존을 설정하지 않으면 JVM의 타임존을 따라간다.
+ createdAt과 updatedAt을 애플리케이션에서 처리해서 위와 같은 결과가 나왔으나, DB에서 처리하면 DB의 타임존을 따라간다.

