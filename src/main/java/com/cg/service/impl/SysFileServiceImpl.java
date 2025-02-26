package com.cg.service.impl;

import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cg.entity.SysFile;
import com.cg.mapper.SysFileMapper;
import com.cg.service.SysFileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * <p>
 * 文件表 服务实现类
 * </p>
 *
 * @author 海カ布
 * @since 2024-10-22
 */
@Service
public class SysFileServiceImpl extends ServiceImpl<SysFileMapper, SysFile> implements SysFileService {
    @Value("${upload.path}")
    private String uploadPath;

    // 文件预览的 URL 前缀，通常从配置文件中读取
    @Value("${preview.url}")
    private String previewUrl;

    /**
     * 文件上传方法
     *
     * @param file 要上传的文件
     * @return 上传结果封装对象 SaResult，包含成功上传的文件信息或错误信息
     */
    public SaResult upload(MultipartFile file) {
        try {
            // 获取上传文件的原始文件名
            String fileName = file.getOriginalFilename();
            // 创建查询包装器，用于查询数据库中是否存在同名文件
            LambdaQueryWrapper<SysFile> lqw = new LambdaQueryWrapper<>();
            lqw.eq(SysFile::getFileName, fileName);
            // 从数据库中查询同名文件
            SysFile dbFile = getOne(lqw);
            if (!Objects.isNull(dbFile)) {
                // 如果数据库中已存在同名文件，返回错误信息
                return SaResult.error(String.format("文件[%s]已存在", fileName));
            }

            // 创建新的文件实体对象
            SysFile sysFile = new SysFile();
            // 设置文件名
            sysFile.setFileName(fileName);
            // 设置文件在服务器上的存储路径
            sysFile.setRealPath(uploadPath + fileName);
            // 设置文件的预览 URL
            sysFile.setFileUrl(previewUrl + fileName);
            // 创建文件对象，代表服务器上存储文件的位置
            File diskFile = new File(sysFile.getRealPath());
            // 将上传的文件保存到服务器指定位置
            file.transferTo(diskFile);
            // 将文件信息保存到数据库
            this.save(sysFile);
            // 返回成功上传的文件信息
            return SaResult.data(sysFile);
        } catch (IOException e) {
            // 如果在文件保存过程中出现异常，抛出运行时异常并记录错误日志
            log.error("文件上传失败", e);
            throw new RuntimeException(e);
        }
    }


}
