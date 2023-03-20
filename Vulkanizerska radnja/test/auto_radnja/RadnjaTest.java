package auto_radnja;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import auto_radnja.gume.AutoGuma;

public abstract class RadnjaTest {

	protected Radnja r;
	
	@Test
	void testDodajGumuNull() {
		assertThrows(NullPointerException.class, () -> r.dodajGumu(null));
	}
	
	@Test
	void testDodajGumuDuplikat() {
		AutoGuma g=new AutoGuma();
		g.setMarkaModel("Renault Clio");
		g.setPrecnik(20);
		g.setSirina(200);
		g.setVisina(50);
		
		r.dodajGumu(g);
		
		assertThrows(RuntimeException.class, () -> r.dodajGumu(g));
	}
	
	@Test
	void testDodajGumuDuplikat2() {
		AutoGuma g=new AutoGuma();
		
		r.dodajGumu(g);
		
		assertThrows(RuntimeException.class, () -> r.dodajGumu(g));
	}
	
	@Test
	void testDodajGumuSveOk() {
		AutoGuma g=new AutoGuma();
		g.setMarkaModel("Renault Clio");
		g.setPrecnik(20);
		g.setSirina(200);
		g.setVisina(50);
		
		r.dodajGumu(g);
		
		List<AutoGuma>gume=r.vratiSveGume();
		
		assertEquals(1, gume.size());
		assertTrue(gume.contains(g));
	}
	
	@Test
	void testDodajGumuSveOk2() {
		AutoGuma g=new AutoGuma();
		g.setMarkaModel("Renault Clio");
		g.setPrecnik(20);
		g.setSirina(200);
		g.setVisina(50);
		
		r.dodajGumu(g);
		
		AutoGuma g2=new AutoGuma();
		g2.setMarkaModel("Renault Clio");
		g2.setPrecnik(20);
		g2.setSirina(300);
		g2.setVisina(50);
		
		r.dodajGumu(g2);
		
		List<AutoGuma>gume=r.vratiSveGume();
		
		assertEquals(2, gume.size());
		assertTrue(gume.contains(g));
		assertTrue(gume.contains(g2));
	}

	@Test
	void testPronadjiGumuNull() {
		assertEquals(null, r.pronadjiGumu(null));
	}
	
	@Test
	void testPronadjiGumuNePostoji() {
		List<AutoGuma>gume=r.pronadjiGumu("Renault Clio");
		
		assertTrue(gume.isEmpty());
	}
	
	@Test
	void testPronadjiGumu() {
		AutoGuma g=new AutoGuma();
		g.setMarkaModel("Renault Clio");
		g.setPrecnik(20);
		g.setSirina(200);
		g.setVisina(50);
		
		r.dodajGumu(g);
		
		List<AutoGuma>gume=r.pronadjiGumu("Renault Clio");
		
		assertEquals(1, gume.size());
		assertTrue(gume.contains(g));
	}
	
	@Test
	void testPronadjiGumeSaIstomMarkomIModelom() {
		AutoGuma g=new AutoGuma();
		g.setMarkaModel("Renault Clio");
		g.setPrecnik(20);
		g.setSirina(200);
		g.setVisina(50);
		
		r.dodajGumu(g);
		
		AutoGuma g2=new AutoGuma();
		g2.setMarkaModel("Renault Clio");
		g2.setPrecnik(21);
		g2.setSirina(300);
		g2.setVisina(60);
		
		r.dodajGumu(g2);

		AutoGuma g3=new AutoGuma();
		g3.setMarkaModel("Renault Clio");
		g3.setPrecnik(17);
		g3.setSirina(180);
		g3.setVisina(40);
		
		r.dodajGumu(g3);
		
		List<AutoGuma>gume=r.pronadjiGumu("Renault Clio");
		
		assertEquals(3, gume.size());
		assertTrue(gume.contains(g));
		assertTrue(gume.contains(g2));
		assertTrue(gume.contains(g3));
	}
	
	@Test
	void testVratiSveGumePraznaLista() {
		assertEquals(0, r.vratiSveGume().size());
	}

}
