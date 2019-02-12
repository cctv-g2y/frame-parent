package com.makun.service.fastdfs;

import java.io.IOException;
import java.io.InputStream;

import org.cleverframe.fastdfs.model.StorePath;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.service.IService;
import com.makun.entity.fastdfs.Document;

/**
 * @说明：文件服务层
 * @author makun
 */
public interface DocumentService extends IService<Document> {

    /**
     * @see 上传文件
     */
    public StorePath uploadFile(String groupName, MultipartFile file) throws IOException;

    /**
     * @see 下载文件
     */
    public InputStream downloadFile(String groupName, String filePath);

    /**
     * @see 删除文件
     */
    public boolean deleteFile(String groupName, String filePath);

}
