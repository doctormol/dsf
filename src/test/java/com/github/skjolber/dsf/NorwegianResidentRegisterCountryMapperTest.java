package com.github.skjolber.dsf;

import org.junit.Assert;
import org.junit.Test;

public class NorwegianResidentRegisterCountryMapperTest {

	private NorwegianResidentRegisterCountryMapper mapper = new NorwegianResidentRegisterCountryMapper();
	
	@Test
	public void testCodes() {
		Assert.assertEquals("000", mapper.fromISO3166("NO"));
		Assert.assertEquals("NO", mapper.toISO3166("000"));
	}
}
