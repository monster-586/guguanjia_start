import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.dfbz.Listener.SysAreaListener;
import com.dfbz.config.SpringMybatis;
import com.dfbz.dao.AreaMapper;
import com.dfbz.dao.ExamineMapper;
import com.dfbz.dao.SysRoleMapper;
import com.dfbz.dao.SysUserMapper;
import com.dfbz.entity.*;
import com.dfbz.service.*;
import com.github.pagehelper.PageInfo;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringMybatis.class)
public class Test {


    @Autowired
    QualificationService qualificationService;
    @Autowired
    AppVersionService appVersionService;
    @Autowired
    ExamineMapper examineMapper;
    @Autowired
    ExamineService examineService;

    @Autowired
    WorkOrderService worService;

    @org.junit.Test
    public void test() {
        List<Qualification> qualifications = qualificationService.selectAll();
        System.out.println(qualifications);
    }


    @org.junit.Test
    public void testexample() {
        Example example = new Example(Qualification.class);
        example.createCriteria().
                andEqualTo("delFlag", 0).
                andEqualTo("check", 2).
                andEqualTo("type", 1).
                andGreaterThan("createDate", "2017-05-01").
                andLessThan("createDate", "2019-12-23");

        List<Qualification> qualifications = qualificationService.selectByExample(example);


        for (Qualification qualification : qualifications) {
            System.out.println(qualification);
        }

    }

    @org.junit.Test
    public void testqul() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("delFlag", "0");
        map.put("check", "2");
        map.put("type", "1");
        map.put("createDate", "2017-05-01");
        map.put("endDate", "2019-12-23");
        PageInfo<Qualification> pageInfo = qualificationService.selsctByorder(map);
        System.out.println(pageInfo.toString());
    }

    @org.junit.Test
    public void TestExaMapper() {
        HashMap<String, Object> examine = new HashMap<>();
        examine.put("officeId", 56);
        List<Examine> examines = examineMapper.selectByCondition(examine);
        for (Examine examine1 : examines) {
            System.out.println(examine1);
        }
    }

    @org.junit.Test
    public void TestExaservice() {
        HashMap<String, Object> examine = new HashMap<>();
        examine.put("officeId", 56);
        PageInfo<Examine> examinePageInfo = examineService.selectByCondition(examine);
        List<Examine> list = examinePageInfo.getList();
        for (Examine examine1 : list) {
            System.out.println(examine1);

        }
    }

    @org.junit.Test
    public void TestWorkservice() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("officeName", "");
        PageInfo<WorkOrder> examinePageInfo = worService.selectByCondition(map);
        List<WorkOrder> list = examinePageInfo.getList();
        for (WorkOrder workOrder : list) {
            System.out.println(workOrder);

        }
    }

    @org.junit.Test
    public void TestworkOneCondition() {
        WorkOrder workOrder = worService.selectOneByCondition(2);
        System.out.println(workOrder.getCreateName());
        System.out.println(workOrder.getTransportName());
        System.out.println(workOrder.getRecipientName());
        System.out.println(workOrder.getDetail());
        System.out.println(workOrder.getTransfer());
    }

    @Autowired
    AreaMapper areaMapper;

//    @org.junit.Test
//    public void TestdetailOneCondition() {
//        Area area = areaMapper.selectOneById(1L);
//        System.out.println(area);
//    }


    @Autowired
    TransferService transferService;

    @org.junit.Test
    public void TesttransfOneCondition() {
        List<Transfer> transfers = transferService.selectOneByCondition(1);
        System.out.println(transfers);
    }


    @Autowired
    AreaService areaService;

    @org.junit.Test
    public void TestAreaService() {
        HashMap<String, Object> map = new HashMap<>();
//        "treeId":"",
//        "areaName":"广"，
//        "pageNum":"1",
//        "pageSize":"5"
        map.put("treeId", "");
        map.put("areaName", "广");
        map.put("pageNum", 1);
        map.put("pageSize", 5);
        PageInfo<Area> areaPageInfo = areaService.selectByCondition(map);
        System.out.println(areaPageInfo.getList());
    }

    @org.junit.Test
    public void TestExcelWrite() {
        ExcelWriter excelWriter = EasyExcel.write("D:\\excel\\area.xlsx", Area.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet(0).build();
        List<Area> areas = areaMapper.selectAll();
        excelWriter.write(areas, writeSheet);
        excelWriter.finish();

    }

    @org.junit.Test
    public void TestExcelSave() {

        ExcelReader excelReader = EasyExcel.read("D:\\excel\\area.xlsx", Area.class, new SysAreaListener(areaMapper)).build();
        ReadSheet readSheet = EasyExcel.readSheet(0).build();
        excelReader.read(readSheet);
        excelReader.finish();
    }

    @org.junit.Test
    public void TestAreUpDataOne() {
        Area area = new Area();
        area.setId(5L);
        area.setOdlparentId(3L);
        area.setParentId(2L);

        System.out.println(areaMapper.updateOne(area));
    }

    @org.junit.Test
    public void TestAreUpDataAll() {
        Area area = new Area();
        area.setId(6L);
        area.setOldparentIds("0,1,");
        area.setParentIds("0,2,");

        System.out.println(areaMapper.updateAllParentIds(area));
    }

    @org.junit.Test
    public void Test() {
        List<Area> areas = areaService.selectAll();
        for (Area area : areas) {
            System.out.println(area);
        }
    }

    @Autowired
    SysUserMapper sysUserapper;
@Autowired
SysUserService sysUserService;
    @org.junit.Test
    public void TestSysRole() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("pageNum",2);
        map.put("pageSize",2);
        PageInfo<SysUser> sysUserPageInfo = sysUserService.selectByCondition(map);
        System.out.println(sysUserPageInfo);

    }

    @Autowired
    SysRoleMapper sysRoleMapper;
    @Autowired
    SysRoleService sysRoleService;
    @org.junit.Test
    public void TestRole(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("offId",56);
        map.put("roleName","超级管理员");
        PageInfo<SysRole> sysRolePageInfo = sysRoleService.selectByCondition(map);
        System.out.println(sysRolePageInfo.getList());

    }

    @org.junit.Test
    public void NotRole(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("roleId",1);
        map.put("offId",56);
        List<SysUser> sysUsers = sysUserService.selectNotRole(map);
        for (SysUser sysUser : sysUsers) {
            System.out.println(sysUser);
        }
        List<SysUser> sysUsers1 = sysUserService.selectHaveRole(map);
        for (SysUser sysUser : sysUsers1) {
            System.out.println(sysUser);
        }
    }
    @org.junit.Test
    public void insertRoleUser(){
        List<Long> insertUserId = new ArrayList<>();
        insertUserId.add(2l);
        insertUserId.add(2l);
        insertUserId.add(2l);
        insertUserId.add(2l);
        insertUserId.add(2l);
        Long insertRoleId=1L;
       int i =sysRoleService.insertBatch(insertUserId,insertRoleId);

    }
    @org.junit.Test
    public void deleteRoleUser(){
        int i = sysRoleMapper.deleteByRoleAndUser(1L, 2L);
    }
    @Autowired
    SysResourceService resourceService;
    @org.junit.Test
    public void selectAllRole(){
        List<SysResource> sys_resources = resourceService.selectAll();
        for (SysResource sys_resource : sys_resources) {
            System.out.println(sys_resource);
        }
    }
}

