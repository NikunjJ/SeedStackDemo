Project: OrderProcessing

1. REST Endpoints 

--Get all orders
http://localhost:8080/order/all

--Get all order with version
http://localhost:8080/order/O001/1

2.  Java classes:

Rest Controller 	: OrderResource 
Dto / Representation: OrderDto
Assembler Dto > Entity / Entity > Dto: OrderAssembler
Repository : OrderRepository
Sample Data Generator: OrderDataGenerator

domain.model.order > Entity related classes
domain.policy : DiscountPolicy rules applied based on Item Amount 
domain.service.order > OrderServiceImpl (logic impl)

3. Refer OrderResponse.json for output of Get all orders url.

4. Order Create URL and Json

127.0.0.1:8080/order/create

{
    "orderId": {
        "orderId": "O100"
    },
    "orderVersion": 1,
    "createdDateTime": "08-Apr-2021",
    "orderStatus": "CONFIRMED",
    "items": [
        {
            "orderId": "O100",
            "itemId": "I1",
            "itemName": "TV",
            "grossAmount": 10000.0,
            "taxAmount": 2800.0,
            "manufactureDate": "08-Apr-2021",
            "expireDate": "08-Apr-2021",
            "quantity": 2
        }
    ]
}