package peter_workshop1;


import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model_class.OrderLine;
import model_class.Product;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utility.DatabaseConnection;
import dao.OrderLineDao;
import dao.OrderLineDaoImpl;

public class OrderLineDaoImplJunit {
private static final Logger LOG = LoggerFactory.getLogger(OrderLineDaoImplJunit.class);

OrderLineDao orderLineDaoImpl = new OrderLineDaoImpl();

	@Before
	public void Preparation() {
			DatabaseInit.DeleteRows();
			DatabaseInit.ResetAutoIncement();
			DatabaseInit.InsertTestCustomer();
			DatabaseInit.InsertTestOrder();
			DatabaseInit.InsertTestProduct();
			DatabaseInit.InsertTestOrderLine();
			}
	
	@Test
	public void testCreateOrderLine() {	
		LOG.info("entering testCreateOrderLine()...");
		OrderLine orderline = new OrderLine();		
		orderline.setAmount(3);
		orderline.setOrderId(1);
		Product product = new Product (1,"TestProductName", new BigDecimal("5.10"),15);
		orderline.setProduct (product);

        assertNotNull("Amount isn't null", orderline.getAmount());
        assertNotNull("Product isn't null",orderline.getProduct());
        assertNotNull("OrderId isn;t null", orderline.getOrderId());

		int affectedRows = orderLineDaoImpl.createOrderLine(orderline);
		assertEquals("Equals?: ",1, affectedRows);
	}

	@Test
	public void testUpdateOrderLine() {
		LOG.info ("entering testUpdateOrderLine...");
		OrderLine orderLine = orderLineDaoImpl.readOrderLineById(1);
		LOG.info(orderLine.toString());
		orderLine.setAmount(999);
		orderLineDaoImpl.updateOrderLine(orderLine);
		try (Connection connection = DatabaseConnection.INSTANCE.getConnection(); Statement statement = connection.createStatement()) {
		String query = "SELECT * FROM order_line WHERE amount = 999";
		ResultSet rs = statement.executeQuery(query);
		rs.next();
		assertEquals(rs.getInt("amount"),999);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		LOG.info ("exiting testUpdateOrderLine");
	}

	@Test
	public void testReadOrderLineById(){
		LOG.info ("entering testReadOrderLineById...");
		OrderLine readOrderLine = orderLineDaoImpl.readOrderLineById(1);
		Product product = new Product (1, "TestProductName", new BigDecimal("4.50"), 10);
		OrderLine insertedOrderLine = new OrderLine (product,10,1);
		assertThat (readOrderLine, instanceOf(OrderLine.class));
		assertEquals (insertedOrderLine, readOrderLine);
		LOG.info ("exiting ReadOrderLineById");
	}

	@Test
	public void testDeleteOrderLine(){
			LOG.info("entering testDeleteOrderLine()...");
			assertEquals(orderLineDaoImpl.deleteOrderLine(1), 1);
			try (Connection connection = DatabaseConnection.INSTANCE.getConnection(); Statement statement = connection.createStatement()) {
			String query = "SELECT * FROM order_line WHERE id = 1";
			ResultSet rs = statement.executeQuery(query);
			assertFalse(rs.next());
			LOG.info("OrderLine id=1 not found in database after deletion");
			} catch (SQLException e) {
				e.printStackTrace();
			} 
			LOG.info("exiting testDeleteOrderLine()...");
	}
}
	
	/*
	public void testReadOrderLinesOfOrderId(){
	}
	
	public void testReadAllOrderLines () {
	}
*/