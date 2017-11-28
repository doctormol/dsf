package com.github.skjolber.dsf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * Utility for mapping codes via name.
 * 
 */

public class ISO3166ListMappingTool {

	public static final void main(String[] args) throws IOException {
		Map<String, String> iso3166 = readISO3166();
		Map<String, String> dsf = readDSF(iso3166);

		Map<String, String> dsfToIISO3166 = new HashMap<>();

		for (Entry<String, String> entry : dsf.entrySet()) {
			String standardCode = iso3166.get(entry.getKey());
			String dsfCode = entry.getValue();
			
			dsfToIISO3166.put(dsfCode, standardCode);
		}
		
		File output = new File("./src/main/resources/dsf/iso3166.mapping.txt");
		FileOutputStream fout = new FileOutputStream(output);
		try {
			OutputStreamWriter writer = new OutputStreamWriter(fout, StandardCharsets.UTF_8);
			
			List<String> codes = new ArrayList<>();
			
			for (Entry<String, String> entry : dsfToIISO3166.entrySet()) {
				codes.add(entry.getValue() + " " + entry.getKey());
			}
			
			Collections.sort(codes);
			
			for(String code : codes) {
				writer.write(code);
				writer.write("\n");
			}
			
			writer.close();
		} finally {
			fout.close();
		}
	}

	private static Map<String, String> readDSF(Map<String, String> iso3166) throws IOException {
		Map<String, String> map = new HashMap<>(); // name, code
		
		// NAVN KODE
		BufferedReader reader = new BufferedReader(new InputStreamReader(ISO3166ListMappingTool.class.getResourceAsStream("/folkeregisteret_countries.txt"), StandardCharsets.UTF_8));
		
		String line;
		while((line = reader.readLine()) != null) {
			if(line.trim().isEmpty() || line.startsWith("#")) {
				continue;
			}
		
			int index = line.lastIndexOf(' ');
			
			String code = line.substring(index).trim();
			String name = line.substring(0, index).trim();

			if(!iso3166.containsKey(name.toLowerCase())) {
				throw new RuntimeException("No match for " + line);
			}
			map.put(name.toLowerCase(), code);

		}
		return map;
	}

	private static Map<String, String> readISO3166() throws IOException {
		Map<String, String> map = new HashMap<>(); // name, code
		
		// KODE NAVN
		BufferedReader reader = new BufferedReader(new InputStreamReader(ISO3166ListMappingTool.class.getResourceAsStream("/iso3166-nb.txt"), StandardCharsets.ISO_8859_1));
		
		String line;
		while((line = reader.readLine()) != null) {
			if(line.trim().isEmpty() || line.startsWith("#")) {
				continue;
			}
		
			int index = line.indexOf('\t');
			map.put(line.substring(index).trim().toLowerCase(), line.substring(0, index));
		}
		return map;
	}
}
