package cs454.search_engine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Map;

public class Crawler {
	public void walk(String path) {
		File root = new File(path);
		File[] list = root.listFiles();
		if (list == null)
			return;
		for (File f : list) {
			Path fullpath = Paths.get(f.getAbsolutePath());
			if (f.isDirectory()) {
				walk(f.getAbsolutePath());
			} 
			else {
				
				System.out.println("File:" + f.getAbsoluteFile());
				BasicFileAttributes basicAttributes;
				try {
					basicAttributes = Files.readAttributes(fullpath, BasicFileAttributes.class);
					System.out.println("Creation Time: "+basicAttributes.creationTime());
					System.out.println("Last accessed on: "+basicAttributes.lastAccessTime());
					System.out.println("Last modified on: "+basicAttributes.lastModifiedTime());
					System.out.println("File size: "+basicAttributes.size()+"bytes");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Map<String, String> metadata = Extraction.extract(f);
				
				Storage.save(f, metadata);
			}
		}
	}

	
}
