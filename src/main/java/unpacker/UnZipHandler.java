package unpacker;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class UnZipHandler {
    private final String ZIP_ARCHIVE;
    private static final int BUFFER_SIZE = 128 * 1024;

    public UnZipHandler() {
        this.ZIP_ARCHIVE = "C:/Users/Админ/Documents/GitHub/ZipArchiveHandler" +
                "/src/main/resources/zip_archive.zip";
    }

    public UnZipHandler(String ZIP_ARCHIVE) {
        this.ZIP_ARCHIVE = ZIP_ARCHIVE;
    }

    public byte[] zipToBytes() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(bos);
        byte[] contents;

        File pdf = new File(ZIP_ARCHIVE);

        FileInputStream fis = new FileInputStream(pdf);

        ZipEntry zipEntry = new ZipEntry(ZIP_ARCHIVE.substring(ZIP_ARCHIVE.lastIndexOf(File.separator)+1));
        zos.putNextEntry(zipEntry);

        byte[] buffer = new byte[1024];

        int len;

        while ((len = fis.read(buffer)) > 0) {
            zos.write(buffer,0,len);
        }

        fis.close();
        zos.closeEntry();
        zos.flush();

        contents = bos.toByteArray();

        zos.close();
        bos.close();
        return contents;
    }

    public void unZip() {
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
