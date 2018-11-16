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
        ZipHandler.bytesToZip(ZipHandler.getBytesFromRequest(req));
        ZipHandler.unZip(path);
    }
}
