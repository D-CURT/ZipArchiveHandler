import unpacker.UnZipHandler;

import java.io.IOException;

public class Main {

    private static final String ZIP_ARCHIVE =
            "C:/Users/Админ/Documents/GitHub/ZipArchiveHandler" +
                    "/src/main/resources/zip_archive.zip";

    public static void main(String[] args) throws IOException {
        UnZipHandler handler = new UnZipHandler(ZIP_ARCHIVE);
        //handler.unZip();
        handler.writeToFile(handler.zipToBytes());
    }
}
