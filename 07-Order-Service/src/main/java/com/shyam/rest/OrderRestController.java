package com.shyam.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationPid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shyam.constants.AppConstants;
import com.shyam.dto.ProductOrderDto;
import com.shyam.props.AppProperties;
import com.shyam.response.ApiResponse;
import com.shyam.service.OrderService;

import jakarta.ws.rs.Path;

@RestController
public class OrderRestController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private AppProperties props;
	
	@PostMapping("/addOrders")
	public ResponseEntity<ApiResponse<List<ProductOrderDto>>> addOrders(@RequestBody List<ProductOrderDto> orderDto){
		ApiResponse<List<ProductOrderDto>> response=new ApiResponse<>();
		Map<String,String> message = props.getMessage();
		List<ProductOrderDto> ordersAdded = orderService.addOrder(orderDto);
	if(ordersAdded!=null) {
		response.setStatus(200);
		response.setMessage(message.get(AppConstants.ADD_ORDERS));
		response.setData(ordersAdded);
	}else {
		response.setStatus(500);
		response.setMessage(message.get(AppConstants.ADD_ORDERS_ERR));
	}
	return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	@PutMapping("/updateOrder")
	public ResponseEntity<ApiResponse<ProductOrderDto>> updateOrder(@RequestBody ProductOrderDto productOrderDto){
		ApiResponse<ProductOrderDto> response=new ApiResponse<>();
		Map<String,String> message = props.getMessage();
		ProductOrderDto updateOrder = orderService.updateOrder(productOrderDto);
		if(updateOrder!=null) {
			response.setStatus(200);
			response.setMessage(message.get(AppConstants.UPDATE_ORDER));
			response.setData(updateOrder);
		}else {
			response.setStatus(500);
			response.setMessage(message.get(AppConstants.UPDATE_ORDER_ERR));
		}
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/orders/{userId}")
	public ResponseEntity<ApiResponse<List<ProductOrderDto>>> getOrderByUserId(@PathVariable("userId") Integer userId){
		ApiResponse<List<ProductOrderDto>> response=new ApiResponse<>();
		Map<String,String> message = props.getMessage();
		List<ProductOrderDto> ordersByUserId = orderService.getOrdersByUserId(userId);
		
		if(ordersByUserId!=null) {
			response.setStatus(200);
			response.setMessage(message.get(AppConstants.SELECT_ORDERS_USERID));
			response.setData(ordersByUserId);
		}else {
			response.setStatus(500);
			response.setMessage(message.get(AppConstants.SELECT_ORDERS_USERID_ERR));
		}
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/orders/{orderDate}/{orderStatus}")
	public ResponseEntity<ApiResponse<List<ProductOrderDto>>> getOrderByDateAndStatus(@PathVariable("orderDate") String orderDate, @PathVariable("orderSatus") String orderStatus){
		ApiResponse<List<ProductOrderDto>> response=new ApiResponse<>();
		Map<String,String> message = props.getMessage();
		List<ProductOrderDto> orderByDateAndStatus = orderService.getOrderByDateAndStatus(orderDate, orderStatus);
		
		if(orderByDateAndStatus!=null) {
			response.setStatus(200);
			response.setMessage(message.get(AppConstants.SELECT_ORDERS_DATE_AND_STATUS));
			response.setData(orderByDateAndStatus);
		}else {
			response.setStatus(500);
			response.setMessage(message.get(AppConstants.SELECT_ORDERS_DATE_AND_STATUS_ERR));
		}
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/orders")
	public ResponseEntity<ApiResponse<List<ProductOrderDto>>> getAllOrders(){
		ApiResponse<List<ProductOrderDto>> response=new ApiResponse<>();
		Map<String,String> message = props.getMessage();
		List<ProductOrderDto> allOrders = orderService.getAllOrders();
		if(allOrders!=null) {
			response.setStatus(200);
			response.setMessage(message.get(AppConstants.FETCH_ORDERS));
			response.setData(allOrders);
		}else {
			response.setStatus(500);
			response.setMessage(message.get(AppConstants.FETCH_ORDERS_ERR));
		}
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
}
