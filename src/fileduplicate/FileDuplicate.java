/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package fileduplicate;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 *
 * @author hp
 */
public class FileDuplicate {
    public static boolean compareFiles(File file1, File file2) throws IOException, NoSuchAlgorithmException {
        byte[] file1Digest = getFileDigest(file1);
        byte[] file2Digest = getFileDigest(file2);

        return Arrays.equals(file1Digest, file2Digest);
    }

    private static byte[] getFileDigest(File file) throws IOException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                md.update(buffer, 0, bytesRead);
            }
        }
        return md.digest();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            File dir = new File("E:\\Coba");
            File[] fileList = dir.listFiles();

            if (fileList != null) {
                for (int x = 0; x < fileList.length; x++) {
                    for (int y = x + 1; y < fileList.length; y++) {
                        if (compareFiles(fileList[x], fileList[y])) {
                            System.out.println("Files " + fileList[x].getName() + " and " + fileList[y].getName() + " are identical.");
                            fileList[x].delete();
                            x++;
                        }
                    }
                }
            }
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    
}
