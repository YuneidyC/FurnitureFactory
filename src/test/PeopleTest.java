package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import factory.Factory;
import factory.Order;
import people.Boss;
import people.Craftsman;

public class PeopleTest {
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
	public void addBossWithString() {
		factory.sc = new Scanner(new ByteArrayInputStream("Julian\n457813".getBytes()));
		boolean bossAdd = factory.addBoss();
		assertTrue(bossAdd);
		assertNotNull(factory.boss);
	}

	@Test
	public void addBossWithEmptyString() {
		factory.sc = new Scanner(new ByteArrayInputStream("".getBytes()));
		boolean bossAdd = factory.addBoss();
		assertFalse(bossAdd);
	}

	@Test
	public void addBossWithEmptyDNI() {
		factory.sc = new Scanner(new ByteArrayInputStream("Julian\n".getBytes()));
		boolean bossAdd = factory.addBoss();
		assertFalse(bossAdd);
	}

	@Test
	public void addBossWithEmptyName() {
		factory.sc = new Scanner(new ByteArrayInputStream("\n457813N".getBytes()));
		boolean bossAdd = factory.addBoss();
		assertFalse(bossAdd);
	}

	@Test
	public void addSalesmanWithString() {
		factory.sc = new Scanner(new ByteArrayInputStream("Lola\n457885M".getBytes()));
		factory.addSalesman();
		assertEquals(1, factory.getPeople().size());
	}

	@Test
	public void addSalesmanWithEmptyString() {
		factory.sc = new Scanner(new ByteArrayInputStream("".getBytes()));
		factory.addSalesman();
		assertEquals(0, factory.getPeople().size());
	}

	@Test
	public void addSalesmanWithEmptyDNI() {
		factory.sc = new Scanner(new ByteArrayInputStream("Lola\n".getBytes()));
		factory.addSalesman();
		assertEquals(0, factory.getPeople().size());
	}

	@Test
	public void addSalesmanWithEmptyName() {
		factory.sc = new Scanner(new ByteArrayInputStream("\n876497C".getBytes()));
		factory.addSalesman();
		assertEquals(0, factory.getPeople().size());
	}

	@Test
	public void addSalesmanWithNameNumber() {
		factory.sc = new Scanner(new ByteArrayInputStream("Mano1o\n487669C".getBytes()));
		factory.addSalesman();
		assertEquals(0, factory.getPeople().size());
	}

	@Test
	public void addCraftsmanWithString() {
		String input = "Pepe\n48734X\n1\n";
		input += "Pepe\n48734H\n2";
		factory.sc = new Scanner(new ByteArrayInputStream(input.getBytes()));
		assertEquals(0, factory.getCraftsmans().size());
		factory.addCraftman();
		factory.addCraftman();
		assertEquals(2, factory.getCraftsmans().size());
	}

	@Test
	public void addCraftsmanWithSameDNI() {
		String input = "Pepe\n48734X\n1\n";
		input += "Pepe\n48734X\n2";
		factory.sc = new Scanner(new ByteArrayInputStream(input.getBytes()));
		assertEquals(0, factory.getCraftsmans().size());
		factory.addCraftman();
		factory.addCraftman();
		assertEquals(1, factory.getCraftsmans().size());
	}

	@Test
	public void addCraftsmanWithEmptyName() {
		factory.sc = new Scanner(new ByteArrayInputStream("\n876497C".getBytes()));
		factory.addCraftman();
		assertEquals(0, factory.getCraftsmans().size());
	}

	@Test
	public void addCraftsmanWithEmptyDNI() {
		factory.sc = new Scanner(new ByteArrayInputStream("Lola\n".getBytes()));
		factory.addCraftman();
		assertEquals(0, factory.getCraftsmans().size());
	}

	@Test
	public void addCraftsmanWithEmptyString() {
		factory.sc = new Scanner(new ByteArrayInputStream("".getBytes()));
		factory.addCraftman();
		assertEquals(0, factory.getCraftsmans().size());
	}

	@Test
	public void addCraftsmanWithNameNumber() {
		factory.sc = new Scanner(new ByteArrayInputStream("Mano1o\n487669C".getBytes()));
		factory.addCraftman();
		assertEquals(0, factory.getCraftsmans().size());
	}

	@Test
	public void addClientsWithString() {
		String input = "487668C\nManolo\n6589741\n1";
		input += "\n487636C\nLaura\n6589742\n1";
		factory.sc = new Scanner(new ByteArrayInputStream(input.getBytes()));
		factory.createOrGetClient();
		factory.createOrGetClient();
		assertEquals(2, factory.getPeople().size());
	}

	@Test
	public void addClientWithSameDNI() {
		factory.sc = new Scanner(new ByteArrayInputStream("487669C\nManolo\n7894563\n1".getBytes()));
		factory.sc = new Scanner(new ByteArrayInputStream("487669C\nManolo\n5987764\n1".getBytes()));
		factory.createOrGetClient();
		factory.createOrGetClient();
		assertEquals(1, factory.getPeople().size());
	}

	@Test
	public void addClientWithSameTelephone() {
		factory.sc = new Scanner(new ByteArrayInputStream("487569C\nManolo\n7894563\n1".getBytes()));
		factory.sc = new Scanner(new ByteArrayInputStream("487669C\nManolo\n7894563\n1".getBytes()));
		factory.createOrGetClient();
		factory.createOrGetClient();
		assertEquals(1, factory.getPeople().size());
	}

	@Test
	public void addClientWithEmptyDNI() {
		factory.sc = new Scanner(new ByteArrayInputStream("\nManolo\n5987764\n1".getBytes()));
		factory.createOrGetClient();
		assertEquals(0, factory.getPeople().size());
	}

	@Test
	public void addClientWithEmptyName() {
		factory.sc = new Scanner(new ByteArrayInputStream("\n797986C\n5987764\n1".getBytes()));
		factory.createOrGetClient();
		assertEquals(0, factory.getPeople().size());
	}

	@Test
	public void addClientWithEmptyTelephone() {
		factory.sc = new Scanner(new ByteArrayInputStream("\n7894658C\nManolo\n1".getBytes()));
		factory.createOrGetClient();
		assertEquals(0, factory.getPeople().size());
	}

	@Test
	public void getClientNameWithDNI() {
		factory.sc = new Scanner(new ByteArrayInputStream("487669C\nManolo\n7894563\n1".getBytes()));
		factory.createOrGetClient();
		assertEquals(1, factory.getPeople().size());
		assertEquals("Manolo", factory.getClient("487669C").getName());
	}

	@Test
	public void getClientWithEmptyDNI() {
		factory.sc = new Scanner(new ByteArrayInputStream("487669C\nManolo\n7894563\n1".getBytes()));
		factory.createOrGetClient();
		assertEquals(1, factory.getPeople().size());
		assertEquals(null, factory.getClient(""));
	}

	@Test
	public void modifyPeopleData() {
		String input = "487669C\nManolo\n7894563\n1";
		input += "\n487669C\n\nJulian\n";
		factory.sc = new Scanner(new ByteArrayInputStream(input.getBytes()));
		factory.createOrGetClient();
		assertEquals(1, factory.getPeople().size());
		factory.modifyPeopleData();
		assertEquals("Julian", factory.getClient("487669C").getName());
	}

	@Test
	public void modifyPeopleDataNoDataInsert() {
		String input = "487669C\nManolo\n7894563\n1";
		input += "\n487669C\n\n\n";
		factory.sc = new Scanner(new ByteArrayInputStream(input.getBytes()));
		factory.createOrGetClient();
		assertEquals(1, factory.getPeople().size());
		factory.modifyPeopleData();
		assertEquals("Manolo", factory.getClient("487669C").getName());
		assertEquals(7894563, factory.getClient("487669C").getTelephone());
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
		input += "\n1\n1\nSG7985\n500\n2\n3\n3\n2\n3\n\n800\n2";
		factory.sc = new Scanner(new ByteArrayInputStream(input.getBytes()));
		factory.createAnOrder();
		assertEquals(1, factory.getOrderList().size());
		Order order = factory.getOrderList().get(0);
		assertNotNull(order);
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
		input += "\n1\n48734X\n2";
		factory.sc = new Scanner(new ByteArrayInputStream(input.getBytes()));
		factory.addBoss();
		factory.addCraftman();
		factory.addCraftman();
		factory.createAnOrder();
		assertEquals(4, factory.getPeople().size());
		assertEquals(1, factory.getOrderList().size());
		Boss boss = factory.getBoss();
		assertNotNull(boss);
		boss.assignCraftsman();
		Order order = factory.getOrderList().get(0);
		assertNotNull(order);
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

}