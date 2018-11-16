package handlers;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ByteUnZipHandler {
    private static final int BUFFER_SIZE = 128 * 1024;

    public ByteUnZipHandler() {
    }

    public static byte[] zipToBytes(final String ZIP_ARCHIVE){
        File file = new File(ZIP_ARCHIVE);

        byte[] bFile = new byte[(int) file.length()];

        try (FileInputStream fileInputStream = new FileInputStream(ZIP_ARCHIVE)) {
            fileInputStream.read(bFile);

        }catch(Exception e){
            e.printStackTrace();
        }
        return bFile;
    }

    public static void unZip(String zipFilePath) {
        System.out.println("in unzip");
        System.out.println(zipFilePath);
        final String ZIP_ARCHIVE = "c:\\Users\\Алексей\\Documents\\GitHub\\ZipArchiveHandler\\src\\main\\resources\\products.zip";
        byte[] buffer = new byte[BUFFER_SIZE];

        final String dstDirectory = destinationDirectory(zipFilePath);
        final File dstDir = new File(dstDirectory);
        System.out.println(dstDir);
        if (!dstDir.exists()) {
            dstDir.mkdir();
        }

        try (ZipInputStream zis = new ZipInputStream(
                new FileInputStream(ZIP_ARCHIVE))) {

            ZipEntry ze = zis.getNextEntry();
            System.out.println(ze);
            String nextFileName;
            while (ze != null) {
                nextFileName = ze.getName();
                File nextFile = new File(dstDirectory + "\\"
                        + nextFileName);
                System.out.println("Распаковываем: "
                        + nextFile.getAbsolutePath());
                if (ze.isDirectory()) {
                    nextFile.mkdir();
                } else {
                    new File(nextFile.getParent()).mkdirs();
                    try (FileOutputStream fos
                                 = new FileOutputStream(nextFile)) {
                        int length;
                        while((length = zis.read(buffer)) > 0) {
                            fos.write(buffer, 0, length);
                        }
                    }
                }
                ze = zis.getNextEntry();
            }
            zis.closeEntry();
            System.out.println("end unzip");
        } catch (IOException ex) {
            System.out.println("wtf");
        }
    }


    private static String destinationDirectory(final String srcZip) {
        return srcZip.substring(0, srcZip.lastIndexOf("\\"));
    }

    public static void writeToFile(byte[] data) {
        try {

            FileOutputStream fop;
            File file;

            file = new File("c:\\Users\\Алексей\\Documents\\GitHub\\ZipArchiveHandler\\src\\main\\resources\\products.zip");
            fop = new FileOutputStream(file);
            if (!file.exists()) {
                file.createNewFile();
            }
            fop.write(data);
        } catch (Exception E) {

        }
    }
}
