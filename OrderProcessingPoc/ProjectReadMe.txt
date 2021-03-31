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
