package com.rawmakyamu.util.mapping;
// Generated Jan 28, 2016 9:45:39 AM by Hibernate Tools 3.6.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Task generated by hbm2java
 */
@Entity
@Table(name = "TASK"
)
public class Task implements java.io.Serializable {

    private String taskcode;
    private Status status;
    private String description;
    private Byte sortkey;
    private String lastupdateduser;
    private Date lastupdatedtime;
    private Date createtime;
    private Set<Pagetask> pagetasks = new HashSet<Pagetask>(0);
    private Set<Loginhistory> loginhistory = new HashSet<Loginhistory>(0);

    public Task() {
    }

    public Task(String taskcode) {
        this.taskcode = taskcode;
    }

    public Task(String lastupdateduser, Date lastupdatedtime, Date createtime, String taskcode, Status status, String description, Byte sortkey, Set<Pagetask> pagetasks) {

        this.taskcode = taskcode;
        this.status = status;
        this.description = description;
        this.sortkey = sortkey;
        this.pagetasks = pagetasks;
        this.lastupdateduser = lastupdateduser;
        this.lastupdatedtime = lastupdatedtime;
        this.createtime = createtime;
    }

    @Id

    @Column(name = "TASKCODE", unique = true, nullable = false, length = 20)
    public String getTaskcode() {
        return this.taskcode;
    }

    public void setTaskcode(String taskcode) {
        this.taskcode = taskcode;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STATUS")
    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Column(name = "DESCRIPTION", length = 64)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "SORTKEY", precision = 2, unique = true, scale = 0)

    public Byte getSortkey() {
        return this.sortkey;
    }

    public void setSortkey(Byte sortkey) {
        this.sortkey = sortkey;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "task")
    public Set<Pagetask> getPagetasks() {
        return this.pagetasks;
    }

    public void setPagetasks(Set<Pagetask> pagetasks) {
        this.pagetasks = pagetasks;
    }

    @Column(name = "LASTUPDATEDUSER", length = 64)
    public String getLastupdateduser() {
        return lastupdateduser;
    }

    public void setLastupdateduser(String lastupdateduser) {
        this.lastupdateduser = lastupdateduser;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LASTUPDATEDTIME", length = 23)
    public Date getLastupdatedtime() {
        return lastupdatedtime;
    }

    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATEDTIME", length = 23)
    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "task")
    public Set<Loginhistory> getLoginhistory() {
        return loginhistory;
    }

    public void setLoginhistory(Set<Loginhistory> loginhistory) {
        this.loginhistory = loginhistory;
    }

}
