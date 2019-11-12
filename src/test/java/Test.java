import com.dfbz.config.SpringMybatis;
import com.dfbz.controller.AppVersionContorller;
import com.dfbz.entity.AppVersion;
import com.dfbz.service.AppVersionService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringMybatis.class)
public class Test {

    @Autowired
    AppVersionService appVersionService;
    @org.junit.Test
    public void Testselectall() {
        List<AppVersion> appVersions = appVersionService.selectAll();
        for (AppVersion appVersion : appVersions) {
            System.out.println(appVersion);
        }
    }

}
