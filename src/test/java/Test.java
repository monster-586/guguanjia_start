import com.dfbz.config.SpringMybatis;
import com.dfbz.controller.AppVersionContorller;
import com.dfbz.entity.AppVersion;
import com.dfbz.service.AppVersionService;
import com.dfbz.service.AppVersionServiceImp;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringMybatis.class)
public class Test {

    @Autowired
    AppVersionServiceImp mapper;

    @org.junit.Test
    public void Testselectall() {
        PageHelper.startPage(1, 3);
        List<AppVersion> list = mapper.selectAll();
        PageInfo<AppVersion> pageInfo = new PageInfo<>(list);
        System.out.println(pageInfo.getList());
    }

}
