# DIP
DIP 설명 블라블라...

상위 수준 정책은 하위 수준 세부 정보에 의존해서는 안됩니다.

## 적용전

<p align="center">
    <img src="https://i.imgur.com/Zkykv9m.png">
</p>

```java
class PaymentController {
    @RequestMapping(value = "/dip/anti/payment", method = RequestMethod.POST)
    public void pay(@RequestBody ShinhanCardDto.PaymentRequest req){
        shinhanCardPaymentService.pay(req);
    }   
}
class ShinhanCardPaymentService {
    public void pay(ShinhanCardDto.PaymentRequest req) {
        shinhanCardApi.pay(req);
    }   
}
```

**PaymentController의 상위 수준의 정책이 ShinhanCardPaymentService이 하위 수준 세부 정보에 의존 하고 있습니다.**

```json
// RequestBody JSON 포멧
{
  "shinhanCardNumber":"4845-9005-9423-4452",
  "cvc":"233"
}
```

**조금 쉽게 설명해보면 카드 결제(상위 수준 정책)가 신한 카드 결제(하위 수준)에 의존하고 있습니다. 이는 신한 카드결제(하위 수준)의 변경 시 카드 결제(상위 수준 정책)에 영향을 미치게 됩니다.**

코드 관점에서 이야기하면 신한카드 API 변경이 발생하면 ShinhanCardPaymentService 클래스뿐만 아니라 자신을 의존하고 있는 PaymentController 클래스의 변경도 발생할 수 있습니다. 그 외에 클래스에도 자신을 의존하고 있는 클래스에도 그 변경 범위는 커질 수 있습니다.


## 적용후

<p align="center">
    <img src="https://i.imgur.com/mFUqCQw.png">
</p>


```java
class PaymentController {
    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    public void pay(@RequestBody CardPaymentDto.PaymentRequest req) {
        final CardPaymentService cardPaymentService = cardPaymentFactory.getType(req.getType());
        cardPaymentService.pay(req);
    }
}

public interface CardPaymentService {
    void pay(CardPaymentDto.PaymentRequest req);
}

public class ShinhanCardPaymentService implements CardPaymentService {
    @Override
    public void pay(CardPaymentDto.PaymentRequest req) {
        shinhanCardApi.pay(req);
    }
}
```
가장 간단하며 강력한 해결 방법은 의존관계를 인터페이스를 통해서 역전시키는 것입니다. 컴파일 단계에서는 PaymentController는 PaymentService 인터페이스를 바라보지만 런타임에서는 cardPaymentFactory 통해서 ShinhanCardPaymentService를 바라보게 됩니다.

신한카드 API 변경 시에는 ShinhanCardPaymentService만 영향을 받게 되며 자신의 의존하는 클래스들은 인터페이스로 바라보기 의존 관계에 영향을 줄일 수 있습니다.

**즉 신한 카드 결제(하위 수준)의 변경 시 카드 결제(상위 수준 정책)에 영향을 미치지 않습니다.** 물론 인터페이스에 변경이 생기면 그 영향이 크기 때문에 인터페이스를 설계할 때 신중해야 합니다.