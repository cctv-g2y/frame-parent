package com.makun.config.base;

import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.makun.entity.fastdfs.Document;

/**
 * @说明：文件工具类
 * @author: makun
 */
public class FileUtil extends FileUtils {

    public static final String PDF = "pdf";
    public static final String CSV = "csv";
    public static final String DOC = "doc";
    public static final String DOCX = "docx";
    public static final String XLS = "xls";
    public static final String XLSX = "xlsx";

    public static void downloadFile(HttpServletRequest request, HttpServletResponse response, Document doc,
            InputStream inputStream) throws Exception {
        // 设置文件类型(这样设置就不止是下Excel文件了，一举多得)
        if (PDF.equals(doc.getFileType())) {
            response.setContentType("application/pdf;charset=GBK");
        } else if (CSV.equals(doc.getFileType())) {
            response.setContentType("application/msexcel;charset=GBK");
        } else if (DOC.equals(doc.getFileType())) {
            response.setContentType("application/msword;charset=GBK");
        } else if (DOCX.equals(doc.getFileType())) {
            response.setContentType("application/msword;charset=GBK");
        } else if (XLS.equals(doc.getFileType())) {
            response.setContentType("application/msexcel;charset=GBK");
        } else if (XLSX.equals(doc.getFileType())) {
            response.setContentType("application/msexcel;charset=GBK");
        }
        response.setContentLength(doc.getFileSize());
        try {
            // 文件名
            response.setContentType("text/html;charset=UTF-8");
            String header = request.getHeader("User-Agent").toUpperCase();
            String fileName = "";
            // IE下中文名称乱码问题。
            if (header.contains("MSIE") || header.contains("TRIDENT") || header.contains("EDGE")) {
                fileName = URLEncoder.encode(doc.getName(), "utf-8");
                fileName = fileName.replace("+", "%20"); // IE下载文件名空格变+号问题
            } else {
                fileName = new String(doc.getName().getBytes(), "ISO8859-1");
            }
            response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
            IOUtils.copy(inputStream, response.getOutputStream());
            /**
             * 上传 客户端发送文件到服务的 --- 服务器接受inputStream 我将 inputStream 复制到服务器某个 (outputStream) 上传
             * 下载 服务器发送文件到客户端 --- 客户端接收inputStream 我将 inputStream 复制到客户端某个 (outputStream) 上传
             */
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("文件下载失败，请稍后重试");
        } finally {
            inputStream.close();
        }
    }

}
