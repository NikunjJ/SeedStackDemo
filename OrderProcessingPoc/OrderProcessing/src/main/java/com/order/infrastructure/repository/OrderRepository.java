package com.order.infrastructure.repository;

import org.seedstack.jpa.BaseJpaRepository;

import com.order.domain.model.order.Order;
import com.order.domain.model.order.OrderId;

public class OrderRepository extends BaseJpaRepository<Order, OrderId> implements IOrderRepository{

}
