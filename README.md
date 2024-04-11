카페 키오스크 구현 및 테스트

//TODO : readme 작성  
1. 버전  
2. 구현 일자  


# 📌 기능별 요구사항 

### ver1

- 주문 목록에 음료 추가/삭제 기능
- 주문 목록 전체 지우기
- 주문 목록 총 금액 계산하기
- 주문 생성하기

### ver2

- 한 종류의 음료 여러 잔을 한 번에 담는 기능

### ver3

- 가게 운영 시간(10:00~22:00) 외에는 주문을 생성할 수 없다.


---

# 🔍 테스트 개념 & 참고사항

## Test-Case 세분화

✅ 해피 케이스  
✅ 예외 케이스  

-> 경계값 테스트{범위(이상,이하,초과,미만),구간,날짜 등}

## 테스트하기 어려운 영역을 구분하고 분리하기

- ver3에 대한 예외 : 메서드 내에서 현재 시간 기준으로 정의한다면, 테스트 케이스를 구성하기 어렵다.
  - 해당 문제점은 메서드 내에서 현재 시간을 기점으로 테스트를 구성했기 때문에, 전체가 테스트 불가해지는 상황이 발생한다.

- 따라서 매개변수에서 시간을 받도록 구성한다면, 메서드 자체의 테스트 케이스를 구현할 수 있다.
  - **테스트 하기 어려운 영역을 구분하고, 분리한다. (파라미터로 받는 것으로 해결)**
  - 해당 이유가 가능한 이유는 우리는 테스트 하고자 하는 영역이 LocalDateNow가 아닌, 운영시간과 관련된 테스트이기 떄문이다.

### 테스트하기 어려운 영역은 어떤 것일까?

> - **관측할 때마다 다른 값에 의존하는 코드**
>   - 현재 날짜/시간 , 랜덤 값 , 전역 변수/함수 , 사용자 입력 등
> - **외부 세계에 영향을 주는 코드**
>   - 표준 출력, 메시지 발송, 데이터베이스에 기록하기 등


## TDD - Test Driven Development
> Red - Green - refactoring 사이클
> 1. Test코드를 작성하고 빨간불을 확인한다.
> 2. 정말 "빠르게" 구현부를 작성 후 초록불을 확인한다.
> 3. 초록불을 유지시킨 채, 리팩토링을 해나간다.

### Why TDD?

**장점**
> - 테스트 자체의 누락 가능성 배제
> - 특정(해피 케이스) 테스트 케이스만 검증할 가능성 배제
> - 잘못된 구현을 다소 늦게 발견할 가능성 배제
> - 구현에 대한 빠른 피드백을 받을 수 있음
> - 과감한 리팩토링 가능

## BDD - Behavior Driven Development
> - Given : 시나리오 진행에 필요한 모둔 준비 과정(객체,값,상태 등)
> - When : 시나리오 행동 진행
> - Then : 시나리오 진행에 대한 결과 명시,검증