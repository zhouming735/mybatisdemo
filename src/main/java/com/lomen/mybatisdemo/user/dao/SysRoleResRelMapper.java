package com.lomen.mybatisdemo.user.dao;

import com.lomen.mybatisdemo.user.model.SysRoleResRel;

public interface SysRoleResRelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_role_res_rel
     *
     * @mbggenerated Thu Feb 08 15:38:13 CST 2018
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_role_res_rel
     *
     * @mbggenerated Thu Feb 08 15:38:13 CST 2018
     */
    int insert(SysRoleResRel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_role_res_rel
     *
     * @mbggenerated Thu Feb 08 15:38:13 CST 2018
     */
    int insertSelective(SysRoleResRel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_role_res_rel
     *
     * @mbggenerated Thu Feb 08 15:38:13 CST 2018
     */
    SysRoleResRel selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_role_res_rel
     *
     * @mbggenerated Thu Feb 08 15:38:13 CST 2018
     */
    int updateByPrimaryKeySelective(SysRoleResRel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_role_res_rel
     *
     * @mbggenerated Thu Feb 08 15:38:13 CST 2018
     */
    int updateByPrimaryKey(SysRoleResRel record);
}