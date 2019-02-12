package com.makun.service.fastdfs.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.cleverframe.fastdfs.client.DefaultStorageClient;
import org.cleverframe.fastdfs.client.DefaultTrackerClient;
import org.cleverframe.fastdfs.model.StorageNode;
import org.cleverframe.fastdfs.model.StorePath;
import org.cleverframe.fastdfs.protocol.storage.callback.DownloadByteArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.makun.dao.fastdfs.DocumentDao;
import com.makun.entity.fastdfs.Document;
import com.makun.service.fastdfs.DocumentService;
import com.makun.utils.SubstringUtil;

/**
 * @说明：文件服务实现层
 * @author makun
 */
@Service
public class DocumentServiceImpl extends ServiceImpl<DocumentDao, Document> implements DocumentService {

    @Autowired
    private DefaultTrackerClient fastDfsTrackerClient;

    @Autowired
    private DefaultStorageClient fastDfsStorageClient;

    /**
     * 上传文件
     */
    @Override
    public StorePath uploadFile(String groupName, MultipartFile file) throws IOException {
        StorageNode storageNode = fastDfsTrackerClient.getStorageNode(groupName);
        if (null == storageNode) {
            throw new IOException("FastDFS存储节点 " + groupName + " 异常," + "无法获取到当前节点");
        }
        try {
            return fastDfsStorageClient.uploadFile(storageNode.getGroupName(), file.getInputStream(), file.getSize(),
                    SubstringUtil.getFilePostfix(file.getOriginalFilename()));
        } finally {
            file.getInputStream().close();
        }
    }

    /**
     * 下载文件
     */
    @Override
    public InputStream downloadFile(String groupName, String filePath) {
        DownloadByteArray downloadByteArray = new DownloadByteArray();
        byte[] fileByteArray = fastDfsStorageClient.downloadFile(groupName, filePath, downloadByteArray);
        return new ByteArrayInputStream(fileByteArray);
    }

    /**
     * 删除文件
     */
    @Override
    public boolean deleteFile(String groupName, String filePath) {
        return fastDfsStorageClient.deleteFile(groupName, filePath);
    }

}
