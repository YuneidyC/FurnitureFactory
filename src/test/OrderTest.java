package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import factory.Factory;
import factory.Order;
import furniture.Furniture;
import people.Boss;
import people.Client;
import people.Craftsman;

public class OrderTest {
	private Factory factory = null;

	@Before
	public void tearUp() {
		factory = new Factory();
	}

	@After
	public void tearDown() {
		factory = null;
	}

	@Test
	public void createAnOrder() {
		String input = "789435M\nJulian\n6895731\n1";
		input += "\n1\n1\nSG7985\n500\n2\n3\n3\n2";
		factory.sc = new Scanner(new ByteArrayInputStream(input.getBytes()));
		factory.createAnOrder();
		assertEquals(1, factory.getOrderList().size());
	}

	/**
	 * static causes an interference problem between the tests, so since we know
	 * that we only have one ID, we take it.
	 */

	@Test
	public void createAnOrderWithMultipleWithAndWithoutFeaturesFurniture() {
		String input = "569873C\nRubby\n6598317\n1";
		input += "\n1\n1\nSG78943\n500\n1\nBlack\n3\n1";
		input += "\n2\n2\n1\nCC78964\n2000\n2\n2\n2";
		factory.sc = new Scanner(new ByteArrayInputStream(input.getBytes()));
		factory.createAnOrder();
		assertEquals(1, factory.getOrderList().size());
		Order order = factory.getOrderList().get(0);
		assertNotNull(order);
		assertEquals(2, factory.getFurnitures().size());
		assertEquals(5500, factory.getOrder(order.getId()).getTotalPrice());
	}

	@Test
	public void tryCreateOrderWitoutPrice() {
		String input = "569873C\nRubby\n6598317\n1";
		input += "\n1\n1\nSG78943\n0\n1\nBlack\n3\n1";
		input += "\n2\n2\n1\nCC78964\n0\n2\n2\2";
		factory.sc = new Scanner(new ByteArrayInputStream(input.getBytes()));
		factory.createAnOrder();
		assertEquals(0, factory.getOrderList().size());
	}

	@Test
	public void modifyFurnitureDataAndOrderTotalPrice() {
		String input = "789435M\nJulian\n6895731\n1";
		input += "\n1\n1\nSG7985\n500\n2\n3\n3\n2";
		factory.sc = new Scanner(new ByteArrayInputStream(input.getBytes()));
		factory.createAnOrder();
		assertEquals(1, factory.getOrderList().size());
		Order order = factory.getOrderList().get(0);
		assertNotNull(order);
		int ID = getIDFurniture(order.getIdsAndItemsFurniture());
		input = ID + "\n\n800\n2";
		factory.sc = new Scanner(new ByteArrayInputStream(input.getBytes()));
		assertEquals(1, factory.getFurnitures().size());
		factory.modifyFurnitureData();
		assertEquals(2400, factory.getOrder(order.getId()).getTotalPrice());
	}

	@Test
	public void assignOrderToCraftsman() {
		String input = "Julian\n457813";
		input += "\nPepe\n48734X\n1\nLola\n365987M\n2";
		input += "\n789435M\nJose\n6895731\n1";
		input += "\n1\n1\nSG7985\n500\n2\n3\n3\n2";
		factory.sc = new Scanner(new ByteArrayInputStream(input.getBytes()));
		factory.addBoss();
		factory.addCraftman();
		factory.addCraftman();
		factory.createAnOrder();
		assertEquals(4, factory.getPeople().size());
		assertEquals(1, factory.getOrderList().size());
		Boss boss = factory.getBoss();
		assertNotNull(boss);
		Order order = factory.getOrderList().get(0);
		assertNotNull(order);
		input = "1\n48734X\n" + Integer.toString(order.getId());
		factory.sc = new Scanner(new ByteArrayInputStream(input.getBytes()));
		boss.assignCraftsman();
		assertEquals("48734X", factory.getOrder(order.getId()).getEmployeeAssigned());
		Craftsman craftsman = factory.getCraftsman(order.getEmployeeAssigned());
		assertNotNull(craftsman);
		assertTrue(craftsman.getAssignedOrders().contains(order.getId()));
	}

	@Test
	public void assignOrderToRandomCraftsman() {
		String input = "Julian\n457813";
		input += "\nPepe\n48734X\n1\nLola\n365987M\n2";
		input += "\n789435M\nJose\n6895731\n1";
		input += "\n1\n1\nSG7985\n500\n2\n3\n3\n2";
		factory.sc = new Scanner(new ByteArrayInputStream(input.getBytes()));
		factory.addBoss();
		factory.addCraftman();
		factory.addCraftman();
		factory.createAnOrder();
		assertEquals(4, factory.getPeople().size());
		assertEquals(1, factory.getOrderList().size());
		Boss boss = factory.getBoss();
		assertNotNull(boss);
		Order order = factory.getOrderList().get(0);
		assertNotNull(order);
		// We can't hardcode the order ID because of the static's interference between
		// tests.
		input = "2\n" + order.getId();
		factory.sc = new Scanner(new ByteArrayInputStream(input.getBytes()));
		boss.assignCraftsman();
		String IDCraftsmanAssigned = order.getEmployeeAssigned();
		Craftsman craftsman = factory.getCraftsman(IDCraftsmanAssigned);
		assertNotNull(craftsman);
		assertTrue(craftsman.getAssignedOrders().contains(order.getId()));
	}

	@Test
	public void changeStatusFurniture() {
		String input = "Julian\n457813";
		input += "\nPepe\n48734X\n1";
		input += "\n789435M\nJose\n6895731\n1";
		input += "\n1\n1\nSG7985\n500\n2\n3\n3\n2";
		factory.sc = new Scanner(new ByteArrayInputStream(input.getBytes()));
		factory.addBoss();
		factory.addCraftman();
		factory.createAnOrder();
		assertEquals(3, factory.getPeople().size());
		assertEquals(1, factory.getOrderList().size());
		Boss boss = factory.getBoss();
		assertNotNull(boss);
		Order order = factory.getOrderList().get(0);
		assertNotNull(order);
		input = "1\n48734X\n" + order.getId();
		factory.sc = new Scanner(new ByteArrayInputStream(input.getBytes()));
		boss.assignCraftsman();
		assertEquals("48734X", factory.getOrder(order.getId()).getEmployeeAssigned());
		Craftsman craftsman = factory.getCraftsman(order.getEmployeeAssigned());
		assertNotNull(craftsman);
		assertTrue(craftsman.getAssignedOrders().contains(order.getId()));
		int IDsFurniture = factory.getIDsFurnitureOrder(order);
		assertNotEquals(-1, IDsFurniture);
		input = order.getId() + "\n" + Integer.toString(IDsFurniture) + "\n1";
		factory.sc = new Scanner(new ByteArrayInputStream(input.getBytes()));
		craftsman.modifyFurnitureStatus();
		Furniture furniture = factory.getFurniture(IDsFurniture);
		assertNotNull(furniture);
		List<String> status = furniture.getStatusHistory();
		assertEquals("Pending", status.get(status.size() - 1));
	}

	@Test
	public void confirmOrderByCustomer() {
		String input = "789435M\nJulian\n6895731\n1";
		input += "\n1\n1\nSG7985\n500\n2\n3\n3\n2";
		factory.sc = new Scanner(new ByteArrayInputStream(input.getBytes()));
		factory.createAnOrder();
		assertEquals(1, factory.getOrderList().size());
		Client client = factory.getClient("789435M");
		input = "0\n1";
		factory.sc = new Scanner(new ByteArrayInputStream(input.getBytes()));
		client.confirmOrder();
	}

	@Test
	public void changeStatusFurnitureMissingPart() {
		String input = "Julian\n457813";
		input += "\nPepe\n48734X\n1";
		input += "\n789435M\nJose\n6895731\n1";
		input += "\n1\n1\nSG7985\n500\n2\n3\n3\n2";
		factory.sc = new Scanner(new ByteArrayInputStream(input.getBytes()));
		factory.addBoss();
		factory.addCraftman();
		factory.createAnOrder();
		assertEquals(3, factory.getPeople().size());
		assertEquals(1, factory.getOrderList().size());
		Boss boss = factory.getBoss();
		assertNotNull(boss);
		Order order = factory.getOrderList().get(0);
		assertNotNull(order);
		input = "1\n48734X\n" + order.getId();
		factory.sc = new Scanner(new ByteArrayInputStream(input.getBytes()));
		boss.assignCraftsman();
		assertEquals("48734X", factory.getOrder(order.getId()).getEmployeeAssigned());
		Craftsman craftsman = factory.getCraftsman(order.getEmployeeAssigned());
		assertNotNull(craftsman);
		assertTrue(craftsman.getAssignedOrders().contains(order.getId()));
		int IDsFurniture = factory.getIDsFurnitureOrder(order);
		assertNotEquals(-1, IDsFurniture);
		input = order.getId() + "\n" + Integer.toString(IDsFurniture) + "\n3\n1\nTornillo479";
		factory.sc = new Scanner(new ByteArrayInputStream(input.getBytes()));
		craftsman.modifyFurnitureStatus();
		Furniture furniture = factory.getFurniture(IDsFurniture);
		assertNotNull(furniture);
		List<String> status = furniture.getStatusHistory();
		assertEquals("Stopped due to missing part", status.get(status.size() - 1));
	}

	@Test
	public void changeStatusFurnitureWithoutMissingPart() {
		String input = "Julian\n457813";
		input += "\nPepe\n48734X\n1";
		input += "\n789435M\nJose\n6895731\n1";
		input += "\n1\n1\nSG7985\n500\n2\n3\n3\n2";
		factory.sc = new Scanner(new ByteArrayInputStream(input.getBytes()));
		factory.addBoss();
		factory.addCraftman();
		factory.createAnOrder();
		assertEquals(3, factory.getPeople().size());
		assertEquals(1, factory.getOrderList().size());
		Boss boss = factory.getBoss();
		assertNotNull(boss);
		Order order = factory.getOrderList().get(0);
		assertNotNull(order);
		input = "1\n48734X\n" + order.getId();
		factory.sc = new Scanner(new ByteArrayInputStream(input.getBytes()));
		boss.assignCraftsman();
		assertEquals("48734X", factory.getOrder(order.getId()).getEmployeeAssigned());
		Craftsman craftsman = factory.getCraftsman(order.getEmployeeAssigned());
		assertNotNull(craftsman);
		assertTrue(craftsman.getAssignedOrders().contains(order.getId()));
		int IDsFurniture = factory.getIDsFurnitureOrder(order);
		assertNotEquals(-1, IDsFurniture);
		input = order.getId() + "\n" + Integer.toString(IDsFurniture) + "\n3\n1\n";
		factory.sc = new Scanner(new ByteArrayInputStream(input.getBytes()));
		craftsman.modifyFurnitureStatus();
		Furniture furniture = factory.getFurniture(IDsFurniture);
		assertNotNull(furniture);
		List<String> status = furniture.getStatusHistory();
		assertEquals("On hold", status.get(status.size() - 1));
	}

	@Test
	public void tryToChangeStatusWhenFurnitureIsFinished() {
		String input = "Julian\n457813";
		input += "\nPepe\n48734X\n1";
		input += "\n789435M\nJose\n6895731\n1";
		input += "\n1\n1\nSG7985\n500\n2\n3\n3\n2";
		factory.sc = new Scanner(new ByteArrayInputStream(input.getBytes()));
		factory.addBoss();
		factory.addCraftman();
		factory.createAnOrder();
		assertEquals(3, factory.getPeople().size());
		assertEquals(1, factory.getOrderList().size());
		Boss boss = factory.getBoss();
		assertNotNull(boss);
		Order order = factory.getOrderList().get(0);
		assertNotNull(order);
		input = "1\n48734X\n" + order.getId();
		factory.sc = new Scanner(new ByteArrayInputStream(input.getBytes()));
		boss.assignCraftsman();
		assertEquals("48734X", factory.getOrder(order.getId()).getEmployeeAssigned());
		Craftsman craftsman = factory.getCraftsman(order.getEmployeeAssigned());
		assertNotNull(craftsman);
		assertTrue(craftsman.getAssignedOrders().contains(order.getId()));
		int IDsFurniture = factory.getIDsFurnitureOrder(order);
		assertNotEquals(-1, IDsFurniture);
		input = order.getId() + "\n" + Integer.toString(IDsFurniture) + "\n6";
		factory.sc = new Scanner(new ByteArrayInputStream(input.getBytes()));
		craftsman.modifyFurnitureStatus();
		Furniture furniture = factory.getFurniture(IDsFurniture);
		assertNotNull(furniture);
		List<String> status = furniture.getStatusHistory();
		assertEquals("Finished", status.get(status.size() - 1));
		input = "5";
		factory.sc = new Scanner(new ByteArrayInputStream(input.getBytes()));
		craftsman.modifyFurnitureStatus();
		assertEquals("Finished", status.get(status.size() - 1));
	}

	public int getIDFurniture(HashMap<Integer, Integer> IDList) {
		for (Integer ID : IDList.keySet()) {
			if (ID == null) {
				continue;
			}
			return ID;
		}
		return -1;
	}
}
