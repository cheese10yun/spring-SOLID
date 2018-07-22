# SOLID

해당 프로젝트는 객체지향 SOLID 적용 전, 적용 후 예제 코드를 통해서 

해당 프로젝


해당 프로젝트는 Spring 예제로 SOLID 원칙을 간단하게 소개 하려고합니다. 

# 목차
<!-- TOC -->

- [SOLID](#solid)
- [목차](#%EB%AA%A9%EC%B0%A8)
- [요구사항](#%EC%9A%94%EA%B5%AC%EC%82%AC%ED%95%AD)
- [DIP](#dip)
    - [적용전](#%EC%A0%81%EC%9A%A9%EC%A0%84)
    - [적용후](#%EC%A0%81%EC%9A%A9%ED%9B%84)
- [OCP](#ocp)
    - [적용전](#%EC%A0%81%EC%9A%A9%EC%A0%84)
    - [적용후](#%EC%A0%81%EC%9A%A9%ED%9B%84)

<!-- /TOC -->


# 요구사항

* 카드 결제 결제 시스템을 만들어야 한다.
* 현재 지원하는 카드는 신한 카드 하나 뿐이다.
* 카드는 지속적으로 추가될 예정이다.


# DIP
DIP 설명 블라블라...

상위 수준 정책은 하위 수준 세부 정보에 의존해서는 안됩니다.

## 적용전
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

<p align="center">
    <img src="https://i.imgur.com/z2nxBc6.png">
</p>

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


# OCP
OCP 설명 블라블라...

 CheckOut 모듈 수정 없이, PaymentMethod를 확장 할 수 있다.

## 적용전
<p align="center">
    <img src="https://i.imgur.com/a21midG.png">
</p>

**PaymentController 수정 없이 CardPayment를 확잘 할 수 있어야합니다.**

```java
class PaymentController {
    private final ShinhanCardPaymentService shinhanCardPaymentService;
    private final WooriCardPaymentService wooriCardPaymentService;

    @RequestMapping(value = "/ocp/anti/payment", method = RequestMethod.POST)
    public void pay(@RequestBody CardPaymentDto.PaymentRequest req){
        if(req.getType() == CardType.SHINHAN){
            shinhanCardPaymentService.pay(req);
        }else if(req.getType() == CardType.WOORI){
            wooriCardPaymentService.pay(req);
        }
        // 그 해당 카드 이외의 타입이 들어오면 예외처리는 어떻게??...
    }
}
```
이제 우리 카드 결제가 추가되었다고 가정하겠습니다. 그렇다면 PaymentController에서는 기존 신한 카드결제, 새롭게 추가된 우리 카드 결제 두 개의 카드 결제를 담당하게 됩니다. 그렇다면 위처럼 if 이 추가되고 해당 카드에 알맞은 처리를 진행하게 됩니다. 지금까지는 카드가 2개 뿐이지만 지속해서 추가될 예정입니다. 그때마다 if 문을 추가하고 그뿐만이 아닙니다. 추가될 카드의 결제를 담당하는 XXXPaymentService 클래스들이 지속해서 의존성 이루어집니다.

그 결과 PaymentController는 컨트롤러 계층임에도 너무 많은 책임을 갖게 되며, **확장에 어렵고, 변경에 취약한  구조가 됩니다.**


## 적용후

<p align="center">
    <img src="https://i.imgur.com/TdGYl8n.png">
</p>

```java
public class PaymentController {
    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    public void pay(@RequestBody CardPaymentDto.PaymentRequest req) {
        final CardPaymentService cardPaymentService = cardPaymentFactory.getType(req.getType());
        cardPaymentService.pay(req);
    }
}

public class ShinhanCardPaymentService implements CardPaymentService {
    @Override
    public void pay(CardPaymentDto.PaymentRequest req) {
        shinhanCardApi.pay(req);
    }
}

public class WooriCardPaymentService implements CardPaymentService {    
    @Override
    public void pay(CardPaymentDto.PaymentRequest req) {
        wooriCardApi.pay(req);
    }
}
```
DIP에서 사용했던 방법과 같습니다. 의존관계를 인터페이스를 통해서 역전시키는 것입니다. **새로운 카드가 추가된다고 하더라도 PaymentController 수정 없이 CardPayment를 확장하고 있습니다.** 이처럼 확장에는 열려 있고 변경에 는 닫혀있는 구조를 가질 수 있습니다. 물론 cardPaymentFactory 클래스의 의존성 주입 코드가 한번은 추가됩니다.

이처럼 인터페이스를 두는 것만 해도 클래스 간의 강한 결합 관계를 느슨한 관계로 만들 수 있습니다. 그렇다면 모든 클래스의 결합 관계를 인터페이스를 두고 느슨한 관계로 유지해야 하는 걸까요?

**제 개인적인 생각은 아니라고 생각합니다.** 모든 변경사항을 예상하는 것은 불가능하며 그것을 하는 것 또한 지나치게 비효율적이라고 생각합니다. 위처럼 명확한 요구사항인 지속해서 카드 결제가 추가되어야 한다. 라는 명확한 변경이 예상되는 시점, 경험을 통해서 예측할 수 있는 시점에 적용하는 것이 바람직하다고 생각합니다.

**그렇다면 중요한 것은 캡슐화, 객체의 올바른 책임, 역할을 부여하여 예측 변경 시점에 OCP를 쉽게 적용할 수 있도록 하는 것이 중요하다고 생각합니다.**