package auto_radnja.gume;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class AutoGumaTest {
	
	AutoGuma a;
	@BeforeEach
	void setUp() throws Exception {
		a=new AutoGuma();
	}

	@AfterEach
	void tearDown() throws Exception {
		a=null;
	}

	@Test
	void testAutoGumaKonstruktorBezParametara() {
		assertEquals(null, a.getMarkaModel());
		assertEquals(-1, a.getPrecnik());
		assertEquals(-1, a.getSirina());
		assertEquals(-1, a.getVisina());
	}

	@Test
	void testAutoGumaKonstruktorSaParametrima() {
		AutoGuma a1=new AutoGuma("Renault Clio",15,200,50);
		
		assertEquals("Renault Clio", a1.getMarkaModel());
		assertEquals(15, a1.getPrecnik());
		assertEquals(200, a1.getSirina());
		assertEquals(50, a1.getVisina());
	}

	@Test
	void testSetMarkaModelNull() {
		assertThrows(NullPointerException.class, () -> a.setMarkaModel(null));
	}
	
	@Test
	void testSetMarkaModelDuzinaManjaOd3() {
		assertThrows(IllegalArgumentException.class, () -> a.setMarkaModel("ab"));
	}
	
	@Test
	void testSetMarkaModelSveOk() {
		a.setMarkaModel("abc");
		
		assertEquals("abc", a.getMarkaModel());
	}
	
	@ParameterizedTest
	@CsvSource({
		"-3",
		"0",
		"12",
		"23"
	})
	void testSetPrecnikNedozvoljeno(int precnik) {
		assertThrows(IllegalArgumentException.class, () -> a.setPrecnik(precnik));
	}
	
	@ParameterizedTest
	@CsvSource({
		"13",
		"15",
		"20",
		"22"
	})
	void testSetPrecnikSveOk(int precnik) {
		a.setPrecnik(precnik);
		
		assertEquals(precnik, a.getPrecnik());
	}

	@ParameterizedTest
	@CsvSource({
		"-3",
		"0",
		"134",
		"356"
	})
	void testSetSirinaNedozvoljeno(int sirina) {
		assertThrows(IllegalArgumentException.class, () -> a.setSirina(sirina));
	}
	
	@ParameterizedTest
	@CsvSource({
		"135",
		"200",
		"300",
		"355"
	})
	void testSetSirinaSveOk(int sirina) {
		a.setSirina(sirina);
		
		assertEquals(sirina, a.getSirina());
	}
	
	@ParameterizedTest
	@CsvSource({
		"-3",
		"0",
		"24",
		"96"
	})
	void testSetVisinaNedozvoljeno(int visina) {
		assertThrows(IllegalArgumentException.class, () -> a.setVisina(visina));
	}
	
	@ParameterizedTest
	@CsvSource({
		"25",
		"50",
		"70",
		"95"
	})
	void testSetVisinaSveOk(int visina) {
		a.setVisina(visina);
		
		assertEquals(visina, a.getVisina());
	}
	
	@Test
	void testToString() {
		a.setMarkaModel("Renault Clio");
		a.setPrecnik(15);
		a.setSirina(200);
		a.setVisina(30);
		
		String s=a.toString();
		
		assertTrue(s.contains("Renault Clio"));
		assertTrue(s.contains("15"));
		assertTrue(s.contains("200"));
		assertTrue(s.contains("30"));
	}

	@ParameterizedTest
	@CsvSource({
		"Renault Clio,15,200,30,Renault Clio,15,200,30,true",
		"Renault Clio,15,200,30,BMW X6,15,200,30,false",
		"null,15,200,30,BMW X6,15,200,30,false",
		"null,15,200,30,null,15,200,30,true",
		"Renault Clio,15,200,30,Renault Clio,20,200,30,false",
		"Renault Clio,15,200,30,Renault Clio,15,300,30,false",
		"Renault Clio,15,200,30,Renault Clio,15,200,50,false",
		"Renault Clio,15,200,30,BMW X6,20,300,50,false"
	})
	void testEqualsObject(String markaModel1, int precnik1, int sirina1, int visina1, 
					String markaModel2, int precnik2, int sirina2, int visina2, boolean isti) {
		
		a.setMarkaModel(markaModel1);
		a.setPrecnik(precnik1);
		a.setSirina(sirina1);
		a.setVisina(visina1);
		
		AutoGuma a2=new AutoGuma();

		a2.setMarkaModel(markaModel2);
		a2.setPrecnik(precnik2);
		a2.setSirina(sirina2);
		a2.setVisina(visina2);
		
		assertEquals(isti, a.equals(a2));
	}
	
	@Test
	void testEqualsObjectNull() {
		assertFalse(a.equals(null));
	}
	
	@Test
	void testEqualsObjectIsti() {
		assertTrue(a.equals(a));
	}
	
	@Test
	void testEqualsObjectDrugaKlasa() {
		assertFalse(a.equals(new Exception()));
	}

}
