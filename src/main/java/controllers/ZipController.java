package controllers;

import handlers.ZipHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class ZipController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("in zip_controller");
        String path = req.getParameter("path");
        copyInputStreamToFile(req.getInputStream());
        //ZipHandler.bytesToZip(ZipHandler.getBytesFromRequest(req));
        ZipHandler.unZip("c:\\Users\\Алексей\\Documents\\GitHub\\ZipArchiveHandler\\src\\main\\resources\\products.zip");
    }

    private void copyInputStreamToFile(InputStream in) {
        try (OutputStream out = new FileOutputStream(new File("c:\\Users\\Алексей\\Documents\\GitHub\\ZipArchiveHandler\\src\\main\\resources\\products.zip"))) {
            byte[] buf = new byte[128 * 1024];
            int len;
            while((len=in.read(buf))>0){
                out.write(buf,0,len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
