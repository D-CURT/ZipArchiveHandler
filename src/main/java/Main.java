import handlers.ByteUnZipHandler;

import java.io.IOException;

public class Main {

    private static final String ZIP_ARCHIVE =
            "c:\\Users\\Алексей\\Documents\\GitHub\\ZipArchiveHandler\\src\\main\\resources\\zip_archive.zip";

    public static void main(String[] args) throws IOException {
        ByteUnZipHandler.writeToFile(ByteUnZipHandler.zipToBytes(ZIP_ARCHIVE));
        ByteUnZipHandler.unZip(ZIP_ARCHIVE);
    }
}
