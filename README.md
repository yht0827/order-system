# order-System

## 프로젝트 구조 설명

- OrderApp : 주문 프로그램 실행하는 애플리케이션
- WebServer : http 통신을 위한 웹서버 설정 및 API Mapping 관련 클래스
- OrderHandler: 주문 프로그램 Json -> String, String -> Json 변환 처리 및 API 컨트롤러 역할 클래스
- Order : 주문 저장 형태 엔티티
- OrderStatus: 주문 상태 속성관련 Enum (처리 중, 배송 중, 완료)
- OrderRequestDto: 주문 요청 DTO
- OrderResponseDto: 주문 조회 DTO
- OrderService: 주문 데이터 처리 관련한 서비스 인터페이스
- OrderServiceImpl: 주문 데이터 처리 관련한 서비스 구현체
- OrderRepository : 주문 데이터 저장소


## API

- GET http://localhost:8080/api/orders/{id} : 주문 데이터 조회
- GET http://localhost:8080/api/orders/list : 주문 데이터 전체 리스트 조회
- POST http://localhost:8080/api/orders : 주문 데이터 저장
> {
    "customerName" : "홍길동", 
    "orderTime" :  "2024-11-04T06:34:20",
    "orderStatus" : "processing"
> }
