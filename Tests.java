package com.snavarro.ejercicio3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.snavarro.ejercicio1.DNI;

class Tests {

	@Test
	void testDNIsDuplicados() {
		DNI[] dnis = new DNI[2];
		dnis[0] = new DNI(0);
		dnis[1] = new DNI(0);
		
		assertFalse(DNI.sonDistintos(dnis));
	}
	
	@Test
	void testDNIsDistintos() {
		DNI[] dnis = new DNI[2];
		dnis[0] = new DNI(0);
		dnis[1] = new DNI(2);
			
		assertTrue(DNI.sonDistintos(dnis));
	}
	
	@Test
	void testLetrasConocidas() {
		DNI[] dnis = new DNI[3];
		
		for(int i = 0; i < 3; i++) {
			dnis[i] = new DNI(i);
			assertEquals((char)(65 + i), dnis[i].getLetra());
		}
		
		
	}

}
