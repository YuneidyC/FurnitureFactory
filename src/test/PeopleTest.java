package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import org.junit.Test;

import factory.Factory;

public class PeopleTest {

	@Test
	public void addBossWithString() {
		Factory.sc = new Scanner(new ByteArrayInputStream("Julian\n457813".getBytes()));
		boolean bossAdd = Factory.addBoss();
		assertTrue(bossAdd);
		assertNotNull(Factory.boss);
	}

	@Test
	public void addBossWithEmptyString() {
		Factory.sc = new Scanner(new ByteArrayInputStream("".getBytes()));
		boolean bossAdd = Factory.addBoss();
		assertFalse(bossAdd);
	}

	@Test
	public void addBossWithEmptyDNI() {
		Factory.sc = new Scanner(new ByteArrayInputStream("Julian\n".getBytes()));
		boolean bossAdd = Factory.addBoss();
		assertFalse(bossAdd);
	}

	@Test
	public void addBossWithEmptyName() {
		Factory.sc = new Scanner(new ByteArrayInputStream("\n457813N".getBytes()));
		boolean bossAdd = Factory.addBoss();
		assertFalse(bossAdd);
	}

	@Test
	public void addSalesmanWithString() {
		Factory.sc = new Scanner(new ByteArrayInputStream("Lola\n457885M".getBytes()));
		Factory.addSalesman();
		assertEquals(1, Factory.getSalesmans().size());
	}

	@Test
	public void addSalesmanWithEmptyString() {
		Factory.sc = new Scanner(new ByteArrayInputStream("".getBytes()));
		Factory.addSalesman();
		assertEquals(0, Factory.getSalesmans().size());
	}

	@Test
	public void addSalesmanWithEmptyDNI() {
		Factory.sc = new Scanner(new ByteArrayInputStream("Lola\n".getBytes()));
		Factory.addSalesman();
		assertEquals(0, Factory.getSalesmans().size());
	}

	@Test
	public void addSalesmanWithEmptyName() {
		Factory.sc = new Scanner(new ByteArrayInputStream("\n876497C".getBytes()));
		Factory.addSalesman();
		assertEquals(0, Factory.getSalesmans().size());
	}

	@Test
	public void addCraftsmanWithString() {
		String input = "Pepe\n48734X\n1\n";
		input += "Pepe\n48734H\n2";
		Factory.sc = new Scanner(new ByteArrayInputStream(input.getBytes()));
		assertEquals(0, Factory.getCraftsmans().size());
		Factory.addCraftman();
		Factory.addCraftman();
		assertEquals(2, Factory.getCraftsmans().size());
	}

	@Test
	public void addCraftsmanWithSameDNI() {
		String input = "Pepe\n48734X\n1\n";
		input += "Pepe\n48734X\n2";
		Factory.sc = new Scanner(new ByteArrayInputStream(input.getBytes()));
		assertEquals(0, Factory.getCraftsmans().size());
		Factory.addCraftman();
		Factory.addCraftman();
		assertEquals(1, Factory.getCraftsmans().size());
	}

	@Test
	public void addCraftsmanWithEmptyName() {
		Factory.sc = new Scanner(new ByteArrayInputStream("\n876497C".getBytes()));
		Factory.addCraftman();
		assertEquals(0, Factory.getCraftsmans().size());
	}

	@Test
	public void addCraftsmanWithEmptyDNI() {
		Factory.sc = new Scanner(new ByteArrayInputStream("Lola\n".getBytes()));
		Factory.addCraftman();
		assertEquals(0, Factory.getCraftsmans().size());
	}

	@Test
	public void addCraftsmanWithEmptyString() {
		Factory.sc = new Scanner(new ByteArrayInputStream("".getBytes()));
		Factory.addCraftman();
		assertEquals(0, Factory.getCraftsmans().size());
	}

}
