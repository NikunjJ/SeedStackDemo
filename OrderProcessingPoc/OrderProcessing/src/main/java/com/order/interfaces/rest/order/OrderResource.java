package com.order.interfaces.rest.order;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.seedstack.business.domain.AggregateNotFoundException;
import org.seedstack.jpa.JpaUnit;
import org.seedstack.seed.Bind;
import org.seedstack.seed.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.order.domain.model.order.Order;
import com.order.domain.services.order.OrderService;

import javassist.NotFoundException;

@Path("order")
@Bind
public class OrderResource {

	@Inject
	private OrderService orderService;

	// this will help to assemble multiple aggregator into Dtos
	@Inject
	private OrderAssembler orderAssembler;

	private Logger logger = LoggerFactory.getLogger(OrderResource.class);

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	@JpaUnit("myUnit")
	@Transactional
	public List<OrderDto> getAllOrders() {

		List<Order> allOrders = this.orderService.getAllOrders();

		logger.info("--------All Orders:-------");
		allOrders.stream().forEach(System.out::println);

		List<OrderDto> allOrdersTO = new ArrayList<>();

		allOrders.stream().forEach(order -> {

			OrderDto orderDto = new OrderDto();

			orderAssembler.mergeAggregateIntoDto(order, orderDto);

			allOrdersTO.add(orderDto);
		});

		return allOrdersTO;
	}

	@GET
	@Path("/{orderId}/{version}")
	@Produces(MediaType.APPLICATION_JSON)
	@JpaUnit("myUnit")
	@Transactional
	public OrderDto getOrder(@PathParam("orderId")String orderId, @PathParam("version")int orderVersion) throws NotFoundException
	{
		OrderDto orderDto = new OrderDto();

		Order order = this.orderService.getOrder(orderId, orderVersion);
		
		orderAssembler.mergeAggregateIntoDto(order, orderDto);
		
		return orderDto;
	}
	
	@DELETE
    @Path("/{orderId}")
	@JpaUnit("myUnit")
	@Transactional
    public void deleteOrder(@PathParam("orderId") String orderId) throws NotFoundException {
        
		try 
        {
            this.orderService.deleteOrder(orderId);
            
        } 
		catch (AggregateNotFoundException e) 
		{
            throw new NotFoundException("Order " + orderId + " not found", e);
        }
    }

	
	@POST
    @Path("/create")
	@JpaUnit("myUnit")
	@Transactional
	@Consumes(MediaType.APPLICATION_JSON)
	public String createOrder(OrderDto orderDto)
	{
		Order order = new Order();
		this.orderAssembler.mergeDtoIntoAggregate(orderDto, order);
		
		//save order
		this.orderService.createOrder(order);
		
		return "Order created:"+order.getOrderId().getOrderId() + " Date:"+ order.getCreatedDateTime();
	}
}
