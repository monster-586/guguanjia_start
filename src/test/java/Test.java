import com.dfbz.config.SpringMybatis;
import com.dfbz.dao.ExamineMapper;
import com.dfbz.entity.Examine;
import com.dfbz.entity.Qualification;
import com.dfbz.service.AppVersionService;
import com.dfbz.service.ExamineService;
import com.dfbz.service.QualificationService;
import com.github.pagehelper.PageInfo;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tk.mybatis.mapper.entity.Example;

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
                andEqualTo("check",2).
                andEqualTo("type", 1).
                andGreaterThan("createDate","2017-05-01").
                andLessThan("createDate","2019-12-23");

        List<Qualification> qualifications = qualificationService.selectByExample(example);


        for (Qualification qualification : qualifications) {
            System.out.println(qualification);
        }

    }
    @org.junit.Test
    public void testqul(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("delFlag","0");
        map.put("check","2");
        map.put("type","1");
        map.put("createDate","2017-05-01");
        map.put("endDate","2019-12-23");
        PageInfo<Qualification> pageInfo = qualificationService.selsctByorder(map);
        System.out.println(pageInfo.toString());
    }
    @org.junit.Test
    public  void TestExaMapper(){
        HashMap<String, Object> examine = new HashMap<>();
        examine.put("officeId",56);
        List<Examine> examines = examineMapper.selectByCondition(examine);
        for (Examine examine1 : examines) {
            System.out.println(examine1);
        }
    }
    @org.junit.Test
    public void TestExaservice(){
        HashMap<String, Object> examine = new HashMap<>();
        examine.put("officeId",56);
        PageInfo<Examine> examinePageInfo = examineService.selectByCondition(examine);
        List<Examine> list = examinePageInfo.getList();
        for (Examine examine1 : list) {
            System.out.println(examine1);

        }
    }

}
