import handlers.ZipHandler;

import java.io.IOException;

public class Main {

    private static final String ZIP_ARCHIVE =
            "c:\\Users\\Алексей\\Documents\\GitHub\\ZipArchiveHandler\\src\\main\\resources\\zip_archive.zip";

    public static void main(String[] args) throws IOException {
        //ZipHandler.bytesToZip(ZipHandler.zipToBytes(ZIP_ARCHIVE));
        ZipHandler.unZip(ZIP_ARCHIVE);
    }
}
