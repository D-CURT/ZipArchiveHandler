package handlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnZipHandler {
    List fileList;
    private static final String INPUT_ZIP_FILE = "c:\\Users\\Алексей\\Documents\\GitHub\\ZipArchiveHandler\\src\\main\\resources\\zip_archive.zip";
    private static final String OUTPUT_FOLDER = "c:\\Users\\Алексей\\Documents\\GitHub\\ZipArchiveHandler\\src\\main\\resources\\UNZIPPED_file";

    public static void main( String[] args ) {
        UnZipHandler unZip = new UnZipHandler();
        unZip.unZipIt(INPUT_ZIP_FILE,OUTPUT_FOLDER);
    }
    /**
     * Unzip it
     * @param zipFile input zip file
     */
    public void unZipIt(String zipFile, String outputFolder){
        byte[] buffer = new byte[128 * 1024];
        try{
            //create output directory is not exists
            File folder = new File(OUTPUT_FOLDER);
            if(!folder.exists()){
                folder.mkdir();
            }
            //get the zip file content
            ZipInputStream zis =
                    new ZipInputStream(new FileInputStream(zipFile));
            //get the zipped file list entry
            ZipEntry ze = zis.getNextEntry();
            while(ze!=null){
                String fileName = ze.getName();
                File newFile = new File(outputFolder + File.separator + fileName);
                System.out.println("file unzip : "+ newFile.getAbsoluteFile());
                //create all non exists folders
                //else you will hit FileNotFoundException for compressed folder
                new File(newFile.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                ze = zis.getNextEntry();
            }
            zis.closeEntry();
            zis.close();
            System.out.println("Done");
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
