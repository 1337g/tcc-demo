//package com.yonyou.cloud.demo.tcc.points.entity;
//
//import java.io.Serializable;
//import java.util.Date;
//import javax.persistence.*;
//
//@Table(name = "tm_user")
//public class TmUser implements Serializable {
//	
//	private static final long serialVersionUID = 6879674411259297611L;
//
//	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String name;
//
//    private String password;
//
//    @Column(name = "create_date")
//    private Date createDate;
//
//    @Column(name = "update_date")
//    private Date updateDate;
//
//    /**
//     * @return id
//     */
//    public Long getId() {
//        return id;
//    }
//
//    /**
//     * @param id
//     */
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    /**
//     * @return name
//     */
//    public String getName() {
//        return name;
//    }
//
//    /**
//     * @param name
//     */
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    /**
//     * @return password
//     */
//    public String getPassword() {
//        return password;
//    }
//
//    /**
//     * @param password
//     */
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    /**
//     * @return create_date
//     */
//    public Date getCreateDate() {
//        return createDate;
//    }
//
//    /**
//     * @param createDate
//     */
//    public void setCreateDate(Date createDate) {
//        this.createDate = createDate;
//    }
//
//    /**
//     * @return update_date
//     */
//    public Date getUpdateDate() {
//        return updateDate;
//    }
//
//    /**
//     * @param updateDate
//     */
//    public void setUpdateDate(Date updateDate) {
//        this.updateDate = updateDate;
//    }
//}