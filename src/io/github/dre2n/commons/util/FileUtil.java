package io.github.dre2n.commons.util;

import io.github.dre2n.commons.util.messageutil.MessageUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
	
	public static List<File> getFilesForFolder(File folder) {
		List<File> files = new ArrayList<File>();
		for (File file : folder.listFiles()) {
			if (file.isDirectory()) {
				List<File> childFolderFiles = new ArrayList<File>();
				childFolderFiles = getFilesForFolder(file);
				files.addAll(childFolderFiles);
				
			} else {
				files.add(file);
			}
		}
		
		return files;
	}
	
	public static void copyDirectory(File sourceLocation, File targetLocation, String[] excludedFiles) {
		if (sourceLocation.isDirectory()) {
			if ( !targetLocation.exists()) {
				targetLocation.mkdir();
			}
			
			String[] children = sourceLocation.list();
			for (String element : children) {
				boolean isOk = true;
				
				for (String excluded : excludedFiles) {
					if (element.contains(excluded)) {
						isOk = false;
						break;
					}
				}
				
				if (isOk) {
					copyDirectory(new File(sourceLocation, element), new File(targetLocation, element), excludedFiles);
				}
			}
			
		} else {
			try {
				if ( !targetLocation.getParentFile().exists()) {
					
					new File(targetLocation.getParentFile().getAbsolutePath()).mkdirs();
					targetLocation.createNewFile();
					
				} else if ( !targetLocation.exists()) {
					
					targetLocation.createNewFile();
				}
				
				InputStream in = new FileInputStream(sourceLocation);
				OutputStream out = new FileOutputStream(targetLocation);
				
				byte[] buf = new byte[1024];
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				
				in.close();
				out.close();
				
			} catch (Exception e) {
				if (e.getMessage().contains("Zugriff") || e.getMessage().contains("Access")) {
					MessageUtil.log("Error: " + e.getMessage() + " // Access denied");
				} else {
					MessageUtil.log("Error: " + e.getMessage());
				}
			}
		}
	}
	
	public static void deleteUnusedFiles(File directory) {
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.getName().equalsIgnoreCase("uid.dat") || file.getName().contains(".id_")) {
				file.delete();
			}
		}
	}
	
	public static boolean removeDirectory(File directory) {
		if (directory.isDirectory()) {
			for (File f : directory.listFiles()) {
				if ( !removeDirectory(f)) {
					return false;
				}
			}
		}
		return directory.delete();
	}
	
	public static void copyFile(File in, File out) throws IOException {
		FileChannel inChannel = null;
		FileChannel outChannel = null;
		try {
			inChannel = new FileInputStream(in).getChannel();
			outChannel = new FileOutputStream(out).getChannel();
			inChannel.transferTo(0, inChannel.size(), outChannel);
			
		} catch (IOException e) {
			throw e;
			
		} finally {
			if (inChannel != null) {
				inChannel.close();
			}
			if (outChannel != null) {
				outChannel.close();
			}
		}
	}
	
}
