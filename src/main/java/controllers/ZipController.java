package controllers;

import com.sun.deploy.association.utility.AppConstants;
import handlers.ByteUnZipHandler;
import sun.rmi.runtime.Log;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.zip.ZipInputStream;

public class ZipController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("in zip_controller");
        String path = req.getParameter("path");
        ByteUnZipHandler.writeToFile(getBytes(req));
        ByteUnZipHandler.unZip(path);
    }

    private byte[] getBytes(HttpServletRequest request) {
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
    }
}
