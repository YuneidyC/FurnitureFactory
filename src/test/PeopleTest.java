package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import factory.Factory;

public class PeopleTest {
	private Factory factory = null;

	@Before
	public void tearUp() {
		factory = new Factory();
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
		assertEquals(1, factory.getSalesmans().size());
	}

	@Test
	public void addSalesmanWithEmptyString() {
		factory.sc = new Scanner(new ByteArrayInputStream("".getBytes()));
		factory.addSalesman();
		assertEquals(0, factory.getSalesmans().size());
	}

	@Test
	public void addSalesmanWithEmptyDNI() {
		factory.sc = new Scanner(new ByteArrayInputStream("Lola\n".getBytes()));
		factory.addSalesman();
		assertEquals(0, factory.getSalesmans().size());
	}

	@Test
	public void addSalesmanWithEmptyName() {
		factory.sc = new Scanner(new ByteArrayInputStream("\n876497C".getBytes()));
		factory.addSalesman();
		assertEquals(0, factory.getSalesmans().size());
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

}
