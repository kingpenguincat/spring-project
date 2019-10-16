//package com.longmaominsu.project.common.persistence;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.longmaominsu.project.common.utils.IdWorker;
//import com.longmaominsu.project.modules.sys.entity.SysUser;
//import com.longmaominsu.project.modules.sys.utils.UserUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.hibernate.validator.constraints.Length;
//
//import java.util.Date;
//
///**
// * Created by liupeng on 2019/9/19 8:28 PM.
// */
//
//public abstract class DataEntity<T> extends BaseEntity<T> {
//    private static final long serialVersionUID = 1L;
//
//    protected String remarks;	// 备注
//    protected SysUser createBy;	// 创建者
//    protected Date createDate;	// 创建日期
//    protected SysUser updateBy;	// 更新者
//    protected Date updateDate;	// 更新日期
//
//
//    public DataEntity() {
//        super();
//
//    }
//
//    public DataEntity(Long id) {
//        super(id);
//    }
//
//    /**
//     * 插入之前执行方法，需要手动调用
//     */
//    @Override
//    public void preInsert(){
//        // 不限制ID为UUID，调用setIsNewRecord()使用自定义ID
////        if (!this.isNewRecord){
////            //setId(IdGen.uuid());
////            IdWorker idWorker = new IdWorker(1,1,1);
////
////        }
//        SysUser user = UserUtils.getUser();
//        if (user.getId()!=null){
//            this.updateBy = user;
//            this.createBy = user;
//        }else{
//            this.createBy = user;
//            this.updateBy = user;
//
//        }
//        this.updateDate = new Date();
//        this.createDate = this.updateDate;
//    }
//
//    /**
//     * 更新之前执行方法，需要手动调用
//     */
//    @Override
//    public void preUpdate(){
//        SysUser user = UserUtils.getUser();
//        if (user.getId()!=null){
//            this.updateBy = user;
//        }
//        this.updateDate = new Date();
//    }
//
//    @Length(min=0, max=255)
//    public String getRemarks() {
//        return remarks;
//    }
//
//    public void setRemarks(String remarks) {
//        this.remarks = remarks;
//    }
//
//    @JsonIgnore
//    public SysUser getCreateBy() {
//        return createBy;
//    }
//
//    public void setCreateBy(SysUser createBy) {
//        this.createBy = createBy;
//    }
//
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    public Date getCreateDate() {
//        return createDate;
//    }
//
//    public void setCreateDate(Date createDate) {
//        this.createDate = createDate;
//    }
//
//    @JsonIgnore
//    public SysUser getUpdateBy() {
//        return updateBy;
//    }
//
//    public void setUpdateBy(SysUser updateBy) {
//        this.updateBy = updateBy;
//    }
//
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    public Date getUpdateDate() {
//        return updateDate;
//    }
//
//    public void setUpdateDate(Date updateDate) {
//        this.updateDate = updateDate;
//    }
//
//
//}
