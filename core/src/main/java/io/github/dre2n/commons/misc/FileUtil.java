/*
 * Copyright (C) 2012-2017 Frank Baumann
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.dre2n.commons.misc;

import io.github.dre2n.commons.chat.MessageUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Frank Baumann, Daniel Saukel
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
     * @param sourceLocation
     * the source location
     * @param targetLocation
     * the target location
     * @param excludedFiles
     * an Array of the names of files to exclude
     */
    public static void copyDirectory(File sourceLocation, File targetLocation, String[] excludedFiles) {
        if (sourceLocation.isDirectory()) {
            if (!targetLocation.exists()) {
                targetLocation.mkdirs();
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
                if (!targetLocation.getParentFile().exists()) {

                    new File(targetLocation.getParentFile().getAbsolutePath()).mkdirs();
                    targetLocation.createNewFile();

                } else if (!targetLocation.exists()) {

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

            } catch (Exception exception) {
                if (exception.getMessage().contains("Zugriff") || exception.getMessage().contains("Access")) {
                    MessageUtil.log("Error: " + exception.getMessage() + " // Access denied");

                } else {
                    MessageUtil.log("Error: " + exception.getMessage());
                }
            }
        }
    }

    @Deprecated
    public static void deleteUnusedFiles(File directory) {
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.getName().equalsIgnoreCase("uid.dat") || file.getName().contains(".id_")) {
                file.delete();
            }
        }
    }

    /**
     * @param directory
     * the directory to remove
     */
    public static boolean removeDirectory(File directory) {
        if (directory.isDirectory()) {
            for (File f : directory.listFiles()) {
                if (!removeDirectory(f)) {
                    return false;
                }
            }
        }

        return directory.delete();
    }

    /**
     * @param in
     * the File with the input
     * @param out
     * the File with the output
     */
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
