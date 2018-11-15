import unpacker.UnZipHandler;

public class Main {

    private static final String ZIP_ARCHIVE =
            "C:/Users/Админ/Documents/GitHub/ZipArchiveHandler" +
                    "/src/main/resources/zip_archive.zip";

    public static void main(String[] args) {
        new UnZipHandler(ZIP_ARCHIVE).unZip();
    }
}
