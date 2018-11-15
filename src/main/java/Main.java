import unpacker.UnZipHandler;

import java.io.IOException;

public class Main {

    private static final String ZIP_ARCHIVE1 =
            "C:/Users/Админ/Documents/GitHub/ZipArchiveHandler" +
                    "/src/main/resources/zip_archive.zip";

    public static void main(String[] args) throws IOException {
        UnZipHandler handler = new UnZipHandler(ZIP_ARCHIVE1);
        handler.writeToFile(handler.zipToBytes());
        handler.unZip();

    }
}
