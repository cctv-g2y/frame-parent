package com.makun.controller.fastdfs;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cleverframe.fastdfs.model.StorePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.makun.config.base.FileUtil;
import com.makun.entity.fastdfs.Document;
import com.makun.service.fastdfs.DocumentService;
import com.makun.utils.R;
import com.makun.utils.SubstringUtil;
import com.makun.utils.FinalData;

/**
 * @说明：文档管理控制层
 * @author: makun
 */
@RestController
@RequestMapping("/document")
public class DocmentController {

    @Autowired
    private DocumentService documentService;

    /** 上传文档 */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST, produces = "application/json;charset=utf8")
    public R uploadFileHandler(@RequestParam("file") MultipartFile file, String categoryId, String categoryName,
            String isCommon, String mark) {
        if (!file.isEmpty()) {
            StorePath storePath = null;
            try {
                storePath = documentService.uploadFile(FinalData.First, file);
            } catch (IOException i) {
                return R.error("文件上传异常");
            } catch (Exception e) {
                return R.error("服务器异常");
            }
            Document document = new Document();
            document.setCreateTime(new Date());
            document.setName(file.getOriginalFilename());
            document.setMark(mark);
            document.setType("0");
            document.setFilePath(storePath.getPath());
            document.setGroupName(categoryName);
            document.setFileSize((int) file.getSize());
            document.setFileType(SubstringUtil.getFilePostfix(file.getOriginalFilename()));
            documentService.insert(document);
            return R.ok("文档上传成功");
        } else {
            return R.error("未找到文档，请重试");
        }
    }

    /** 上传失败回调 */
    @RequestMapping("/fasterror")
    public ModelAndView fasterror() {
        return new ModelAndView("fasterror");
    }

    /** 下载文档 */
    @RequestMapping("/download/{id}")
    public void download(@PathVariable("id") String fileId, HttpServletResponse response, HttpServletRequest request) {
        Document downloadFile = documentService.selectById(fileId);
        try {
            if (downloadFile == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "找不到相关资源");
                return;
            }
            InputStream inputStream = documentService.downloadFile(FinalData.First, downloadFile.getFilePath());
            FileUtil.downloadFile(request, response, downloadFile, inputStream);
        } catch (Exception e) {
            try {
                response.sendRedirect("../../fastdfs_error.html");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    /** 删除文档 */
    @RequestMapping("/delfile")
    public R delfile(String[] ids) {
        for (String id : ids) {
            Document downloadFile = documentService.selectById(id);
            if (downloadFile == null) {
                return R.error();
            }
            boolean isOk = documentService.deleteFile(FinalData.First, downloadFile.getFilePath());
            if (isOk) {
                documentService.deleteById(id);
            } else {
                return R.error();
            }
        }
        return R.ok();
    }

}
