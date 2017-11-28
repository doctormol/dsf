package com.github.skjolber.dsf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Country code mapper for Folkeregisteret.<br/><br/>
 * 
 * Thread safe.
 *
 */

public class FolkeregisterCountryMapper {
	
	private static final String RESOURCE = "/dsf/iso3166.mapping.txt";
	private static final int COUNTRIES = 250;

	// opting to using two maps instead of introducing guava Bimap dependency in project
	private Map<String, String> dsfToIso3166;
	private Map<String, String> iso3166ToDsf;

	public FolkeregisterCountryMapper() {
		
		Map<String, String> dsfToIso3166 = new HashMap<>(COUNTRIES * 2);
		Map<String, String> iso3166ToDsf = new HashMap<>(COUNTRIES * 2);

		// NAVN KODE
		BufferedReader reader = new BufferedReader(new InputStreamReader(ISO3166ListMappingTool.class.getResourceAsStream(RESOURCE), StandardCharsets.UTF_8));
		
		try {
			String line;
			while((line = reader.readLine()) != null) {
				if(line.trim().isEmpty() || line.startsWith("#")) {
					continue;
				}
			
				int index = line.lastIndexOf(' ');
				if(index == -1) {
					throw new RuntimeException("Expected space in " + line);
				}
				String f = line.substring(index + 1);
				String iso = line.substring(0, index);
				
				dsfToIso3166.put(f, iso);
				iso3166ToDsf.put(iso, f);
			}
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
			
		this.dsfToIso3166 = dsfToIso3166;
		this.iso3166ToDsf = iso3166ToDsf;
		
	}
	
	/**
	 * Map from Folkeregister country code (3 digits) to ISO3166 country code (two uppercase letters).
	 * 
	 * @param dsfCountryCode
	 * @return ISO3166 country code, null if unknown.
	 */
	
	public String toISO3166(String dsfCountryCode) {
		return dsfToIso3166.get(dsfCountryCode);
	}

	/**
	 * Map from ISO3166 country code (two uppercase letters) to Folkeregister country code (3 digits).
	 * 
	 * @param dsfCountryCode
	 * @return Folkeregister country code, null if unknown.
	 */

	public String fromISO3166(String countryCode) {
		return iso3166ToDsf.get(countryCode);
	}

}
