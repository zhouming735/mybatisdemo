package com.lomen.mybatisdemo.user.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * t_user
 *
 *
 * @mbggenerated 2018-02-08
 */
public class UserEntity implements Serializable {
    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 最后访问时间
     */
    private Date lastVisit;

    /**
     * 最后访问时间
     */
    private String lastIp;

    /**
     * t_user
     */
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     * @return user_id 用户ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 用户ID
     * @param userId 用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 用户名
     * @return user_name 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 用户名
     * @param userName 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 密码
     * @return password 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 最后访问时间
     * @return last_visit 最后访问时间
     */
    public Date getLastVisit() {
        return lastVisit;
    }

    /**
     * 最后访问时间
     * @param lastVisit 最后访问时间
     */
    public void setLastVisit(Date lastVisit) {
        this.lastVisit = lastVisit;
    }

    /**
     * 最后访问时间
     * @return last_ip 最后访问时间
     */
    public String getLastIp() {
        return lastIp;
    }

    /**
     * 最后访问时间
     * @param lastIp 最后访问时间
     */
    public void setLastIp(String lastIp) {
        this.lastIp = lastIp == null ? null : lastIp.trim();
    }
}