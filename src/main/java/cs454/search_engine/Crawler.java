package cs454.search_engine;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

import org.apache.commons.io.FilenameUtils;

public class Crawler {
	int dirCount = 0;
	int fileCount = 0;
	
	public void walk(String path) {
		PrintWriter writer;		
		File root = new File(path);
		File[] list = root.listFiles();
		
		if (list == null)
			return;
		for (File f : list) {
			Path fullpath = Paths.get(f.getAbsolutePath());
			if (f.isDirectory()) {
				dirCount++; fileCount++;
				walk(f.getAbsolutePath());
			} 
			else {
				try {
					writer = new PrintWriter("savefile.txt", "UTF-8");
					BasicFileAttributes basicAttributes = Files.readAttributes(fullpath, BasicFileAttributes.class);
					String ext = FilenameUtils.getExtension(f.getAbsoluteFile().toString());
					System.out.println("ext: " + ext);
					System.out.println("File:" + f.getAbsoluteFile());
					System.out.println("File size: "+ basicAttributes.size()+"bytes");
					writer.println(f.getAbsoluteFile());
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

//				Map<String, String> metadata = Extraction.extract(f);
//				Storage.save(f, metadata);
				
			}
			
		}
		
	}

	
}
