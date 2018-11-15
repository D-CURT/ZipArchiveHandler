package unpacker;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnZipHandler {
    private final String ZIP_ARCHIVE1;
    private static final int BUFFER_SIZE = 128 * 1024;

    public UnZipHandler() {
        this.ZIP_ARCHIVE1 = "C:/Users/Админ/Documents/GitHub/ZipArchiveHandler" +
                "/src/main/resources/zip_archive.zip";
    }

    public UnZipHandler(String ZIP_ARCHIVE) {
        this.ZIP_ARCHIVE1 = ZIP_ARCHIVE;
    }

    public byte[] zipToBytes(){
        File file = new File(ZIP_ARCHIVE1);

        byte[] bFile = new byte[(int) file.length()];

        try (FileInputStream fileInputStream = new FileInputStream(ZIP_ARCHIVE1)) {
            //convert file into array of bytes
            fileInputStream.read(bFile);

        }catch(Exception e){
            e.printStackTrace();
        }
        return bFile;




    }

    public void unZip() {
        final String ZIP_ARCHIVE = "C:/Users/Админ/Documents/GitHub/ZipArchiveHandler"
                + "/src/main/resources/products.zip";
        byte[] buffer = new byte[BUFFER_SIZE];

        final String dstDirectory = destinationDirectory(ZIP_ARCHIVE);
        final File dstDir = new File(dstDirectory);
        if (!dstDir.exists()) {
            dstDir.mkdir();
        }

        try (ZipInputStream zis = new ZipInputStream(
                new FileInputStream(ZIP_ARCHIVE))) {

            ZipEntry ze = zis.getNextEntry();
            String nextFileName;
            while (ze != null) {
                nextFileName = ze.getName();
                File nextFile = new File(dstDirectory + File.separator
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
        } catch (IOException ex) {
            System.out.println("wtf");
        }
    }


    private String destinationDirectory(final String srcZip) {
        return srcZip.substring(0, srcZip.lastIndexOf("/"));
    }

    public void writeToFile(byte[] data) {
        try {

            FileOutputStream fop;
            File file;

            file = new File("C:/Users/Админ/Documents/GitHub/ZipArchiveHandler"
                    + "/src/main/resources/products.zip");
            fop = new FileOutputStream(file);
            if (!file.exists()) {
                file.createNewFile();
            }
            fop.write(data);
        } catch (Exception E) {

        }
    }
}
