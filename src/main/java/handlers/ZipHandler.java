package handlers;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipHandler {
    private static final int BUFFER_SIZE = 128 * 1024;

    public ZipHandler() {
    }

    /*public void readRequest(HttpServletRequest request, String dstDir) {

    }

    public  byte[] getBytesFromRequest(HttpServletRequest request) {
        int length = request.getContentLength();
        try (InputStream is = new BufferedInputStream(request.getInputStream())) {
            byte[] data = new byte[length];
            int offset = 0;
            while (offset < length) {
                int read = is.read(data, offset, data.length - offset);
                if (read < 0) {
                    break;
                }
                offset += read;
            }
            if (offset < length) {
                throw new IOException(
                        String.format("Read %d bytes; expected %d", offset, length));
            }
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }*/

    public static void bytesToZip(byte[] data) {
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
                new FileInputStream(zipFilePath))) {

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
}
