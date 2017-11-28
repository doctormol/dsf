package com.github.skjolber.dsf;

import org.junit.Assert;
import org.junit.Test;

public class FolkeregisterCountryMapperTest {

	private FolkeregisterCountryMapper mapper = new FolkeregisterCountryMapper();
	
	@Test
	public void testCodes() {
		Assert.assertEquals("000", mapper.fromISO3166("NO"));
		Assert.assertEquals("NO", mapper.toISO3166("000"));
	}
}
