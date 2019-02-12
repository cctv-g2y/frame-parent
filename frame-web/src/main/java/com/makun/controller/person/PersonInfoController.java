package com.makun.controller.person;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cleverframe.fastdfs.model.StorePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.makun.config.base.UserUtils;
import com.makun.entity.fastdfs.Document;
import com.makun.entity.person.PersonInfo;
import com.makun.service.fastdfs.DocumentService;
import com.makun.service.person.PersonInfoService;
import com.makun.utils.DateUtils;
import com.makun.utils.R;
import com.makun.utils.SubstringUtil;
import com.makun.utils.FinalData;

import net.coobird.thumbnailator.Thumbnails;

/**
 * @说明：个人资料控制层
 * @author makun
 */
@RestController
@RequestMapping("/person")
public class PersonInfoController {

    @Autowired
    private PersonInfoService personInfoService;

    @Autowired
    private DocumentService documentService;

    /**
     * 个人设置主页跳转
     */
    @RequestMapping("/page")
    public ModelAndView personinfo() {
        ModelAndView view = new ModelAndView("modules/person/personInfo");
        PersonInfo personInfo = personInfoService.getPersonInfo(UserUtils.getUserId());
        if (null == personInfo) {
            personInfo = new PersonInfo();
            personInfo.setPersonNum(DateUtils.nowString());
        }
        view.addObject("personInfo", personInfo);
        return view;
    }

    /**
     * 个人资料保存
     */
    @RequestMapping("/save")
    public R save(PersonInfo personInfo) {
        personInfo.setPersonId(UserUtils.getUserId());
        if (personInfoService.savePersonInfo(personInfo)) {
            return R.ok("保存成功");
        }
        return R.error("保存失败");
    }

    /**
     * 个人资料修改
     */
    @RequestMapping("/update")
    public R update(PersonInfo personInfo) {
        if (personInfoService.updatePersonInfo(personInfo)) {
            UserUtils.getUser().setUserPhoto(personInfo.getPersonHeader());
            return R.ok("修改成功");
        }
        return R.error("修改失败");
    }

    /**
     * 上传个人头像
     */
    @RequestMapping(value = "/uploadphoto", method = RequestMethod.POST, produces = "application/json;charset=utf8")
    public R uploadPhoto(@RequestParam("file") MultipartFile file) {
        String groupName = FinalData.First;
        String noUploadSpace = FinalData.noUploadSpace;
        if (!file.isEmpty()) {
            StorePath storePath = null;
            try {
                storePath = documentService.uploadFile(groupName, file);
            } catch (IOException i) {
                return R.error("上传个人头像异常！");
            } catch (Exception e) {
                return R.error("服务器连接异常！");
            }
            Document document = new Document();
            document.setCreateTime(new Date());
            document.setCreateId(UserUtils.getUserId());
            document.setName(file.getOriginalFilename());
            document.setFilePath(storePath.getPath());
            document.setFileSize((int) file.getSize());
            document.setFileType(SubstringUtil.getFilePostfix(file.getOriginalFilename()));
            document.setGroupName(groupName);
            document.setType(noUploadSpace);
            documentService.insert(document);
            return R.ok("上传成功").put("doc", document);
        } else {
            return R.error("未找到照片，请重试");
        }
    }

    /**
     * 加载个人头像
     */
    @RequestMapping("/loadphoto")
    public void loadphoto(String path, HttpServletRequest req, HttpServletResponse response) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = documentService.downloadFile(FinalData.First, path);
        } catch (Exception e) {
            // 如果文件服务器报错，就使用默认的图片。
            File cfgFile = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "static/img/deftximg.jpg");
            inputStream = new FileInputStream(cfgFile);
        } finally {
            OutputStream outputStream = response.getOutputStream();
            Thumbnails.of(inputStream).size(68, 68).toOutputStream(outputStream);
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        }
    }

}
