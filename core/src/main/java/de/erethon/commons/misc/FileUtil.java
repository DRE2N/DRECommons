/*
 * Written from 2015-2018 by Daniel Saukel
 *
 * To the extent possible under law, the author(s) have dedicated all
 * copyright and related and neighboring rights to this software
 * to the public domain worldwide.
 *
 * This software is distributed without any warranty.
 *
 * You should have received a copy of the CC0 Public Domain Dedication
 * along with this software. If not, see <http://creativecommons.org/publicdomain/zero/1.0/>.
 */
package de.erethon.commons.misc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author Daniel Saukel
 */
public class FileUtil {

    /**
     * @param folder
     * the folder to check
     * @return
     * a List of Files inside the folder and its subfolders
     */
    public static List<File> getFilesForFolder(File folder) {
        List<File> files = new ArrayList<>();
        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                List<File> childFolderFiles;
                childFolderFiles = getFilesForFolder(file);
                files.addAll(childFolderFiles);
                
            } else {
                files.add(file);
            }
        }
        
        return files;
    }

    /**
     * Copies a file or directory
     *
     * @param inFile
     * the File to copy
     * @param outFile
     * the target path
     * @return
     * if the file has been copied correctly
     */
    public static void copy(File inFile, File outFile) {
        if (inFile.isDirectory()) {
            copyDir(inFile, outFile);
        } else {
            copyFile(inFile, outFile);
        }
    }

    /**
     * @param inFile
     * the File to copy
     * @param outFile
     * the target path
     * @return
     * if the file has been copied correctly
     */
    public static boolean copyFile(File inFile, File outFile) {
        return org.bukkit.util.FileUtil.copy(inFile, outFile);
    }

    /**
     * @param inDir
     * the directory to copy
     * @param outFile
     * the target path
     * @return
     * if the directory has been copied correctly
     */
    public static void copyDir(File inDir, File outDir, String... exclude) {
        Collection<String> e = Arrays.asList(exclude);
        if (!outDir.exists()) {
            outDir.mkdir();
        }
        for (String p : inDir.list()) {
            if (!e.contains(p)) {
                copy(new File(inDir, p), new File(outDir, p));
            }
        }
    }

    /**
     * @param dir
     * the directory to remove
     */
    public static boolean removeDir(File dir) {
        if (dir.isDirectory()) {
            for (File f : dir.listFiles()) {
                if (!removeDir(f)) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    /**
     * @param path
     * a file path
     * @return
     * the File
     */
    public static File createIfNotExisting(String path) {
        return createIfNotExisting(new File(path));
    }

    /**
     * @param file
     * a file
     * @return
     * the File
     */
    public static File createIfNotExisting(File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return file;
    }
    
}
