# 엄랭 to Lua Transcompiler
© 2022 Dark Tornado, All rights reserved.

* 엄랭으로 작성된 소스 코드를 [루아](https://github.com/lua/lua) 소스로 바꿔줍니다.
* 루아로 바꾼 소스를 실행하는 것과 `swing`으로 대충 만든 UI도 포함되어 있으니, 알아서 원하는 부분만 뽑아먹으세요?<br><br>
* [엄랭 Github](https://github.com/rycont/umjunsik-lang)
* 일단 [여기서](https://youtu.be/G0psQ54f5zE) 설명한 것들만 구현중

## Core
* `com.darktornado.umm2lua` 패키지 안에 있는 것들
```java
import com.darktornado.umm2lua.Umm2Lua;
.
.
.
String result = Umm2Lua.compile("엄랭소스");
```
같은거 하면, 변수 `result`에 루아로 바뀐 소스 저장됩니다.

## PC에서 실행
* 대충 이 레포 전체

## 모바일에서 실행
* 안드로이드는 [여기](https://github.com/DarkTornado/Umm2Lua-Android) 참고
* 아이폰은 만들 줄 모름
