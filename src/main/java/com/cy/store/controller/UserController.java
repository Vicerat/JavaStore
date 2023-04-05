package com.cy.store.controller;

import com.cy.store.controller.ex.FileEmptyException;
import com.cy.store.controller.ex.FileStateException;
import com.cy.store.controller.ex.FileTypeException;
import com.cy.store.controller.ex.FileUploadException;
import com.cy.store.entity.User;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.UsernameDuplicatedException;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@RestController//@controller+@ResponseBody
@RequestMapping("users")
public class UserController extends BaseController{
    @Autowired
    private IUserService userService;

    //@ResponseBody//表示此方法响应结果以json格式进行数据的响应给前端
    @RequestMapping("reg")
    public JsonResult<Void> reg(User user) {
        userService.reg(user);
        //出错会自动到BaseController中处理异常，如无异常则返回Ok,只有O大写
        return  new JsonResult<>(Ok);
    }

    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session) {
        User data = userService.login(username, password);

        session.setAttribute("uid",data.getUid());
        session.setAttribute("username",data.getUsername());
        System.out.println(getuidFromSession(session));
        System.out.println(getUsernameFromSession(session));
        return new JsonResult<User>(Ok,data);
    }

    @RequestMapping("change_password")
    public JsonResult<Void> changPassword(String oldPassword,String newPassword,HttpSession session) {
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changePassword(oldPassword,uid,username,newPassword);
        return new JsonResult<Void>(Ok);
    }

    @RequestMapping("get_by_uid")
    public JsonResult<User> getByUid(HttpSession session) {
        User data = userService.getByUid(getuidFromSession(session));
        return new JsonResult<>(Ok,data);
    }

    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(User user, HttpSession session) {
        Integer uid = getuidFromSession(session);
        String usernamem = getUsernameFromSession(session);
        userService.changeInfo(uid,usernamem,user);
        return new JsonResult<>(Ok);
    }

    public static final int AVATAR_MAX_SIZE = 10 * 1024 *1024;

    public static final List<String> AVATAR_TYPE = new ArrayList<>();
    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");
    }

    @RequestMapping("change_avatar")
    public JsonResult<String> changeAvatar(HttpSession session, MultipartFile file) {
         if (file.isEmpty()) {
             throw new FileEmptyException("文件为空");
         }
         if (!AVATAR_TYPE.contains(file.getContentType())) {
             throw new FileTypeException("文件类型不支持");
         }

         String parent = session.getServletContext().getRealPath("upload");
         File dir = new File(parent);
         if (!dir.exists()) {
             dir.mkdirs();
         }

         String orignalFilename = file.getOriginalFilename();
        System.out.println("OriginalFilename=" + orignalFilename);
        int dotIndex = orignalFilename.lastIndexOf(".");
        String suffix = orignalFilename.substring(dotIndex);//文件后缀
        String filename = UUID.randomUUID().toString().toUpperCase() + suffix;
        File dest = new File(dir, filename);

        try {
            file.transferTo(dest);
        } catch (FileStateException e) {
            throw new FileStateException("文件状态异常");
        } catch (IOException e) {
            throw new FileUploadException("文件读写异常");
        }

        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        String avatar = "/upload/" + filename;
        userService.changeAvatar(uid,avatar,username);
        //
        return new JsonResult<>(Ok,avatar);

    }
}
