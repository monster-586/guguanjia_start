
import com.dfbz.config.SpringMybatis;
import com.dfbz.entity.Area;
import com.dfbz.service.AreaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringMybatis.class)
public class redisTest {
    @Test
    public void TestRedisConnection() {
        Jedis jedis = new Jedis("127.0.0.1");
        jedis.auth("root");
//        String ping = jedis.ping();
//        System.out.println(ping);
//        Set<String> keys = jedis.keys("*");
//        for (String key : keys) {
//            System.out.println(key);
//        }
//        jedis.lpush("mylist", "a,b,c");
//        jedis.lpush("mylist", "ni", "shi", "sha", "biu", "me");
//        System.out.println(jedis.lindex("mylist", 0));
////        List<String> mylist = jedis.lrange("mylist", 0, -1);
////        for (String s : mylist) {
////            System.out.println(s);
////
////        }
//
//        jedis.hset("frend", "second", "damimi");
//        jedis.hset("frend", "four", "daxiong");
//        jedis.hset("frend", "thrid", "laoming");
//        System.out.println(jedis.hgetAll("frend"));


        jedis.zadd("teach", 2.0, "xiaohu");
        jedis.zadd("teach", 2.8, "xiaoliu");
        jedis.zadd("teach", 2.2, "xiaoli");
        jedis.zadd("teach", 2.1, "xiaoliu");
        jedis.zadd("teach", 2.1, "xiaoliu");
        Set<String> teach = jedis.zrangeByScore("teach", 0, -1);
        for (String s : teach) {
            System.out.println(s);
        }

    }

    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    @Autowired
    AreaService areaService;
//@Test
//    public void testRedisSave() {
//        List<Area> areas = areaService.selectAll();
//        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
//        operations.set("Area:SelectAll",areas);
//}

    @Test
    public void Test() {
        List<Area> areas = areaService.selectAll();
        System.out.println(areas);
        List<Area> areas2 = areaService.selectAll();
        System.out.println(areas2);
        List<Area> areas3 = areaService.selectAll();
        System.out.println(areas3);
        List<Area> areas4 = areaService.selectAll();
        for (Area area : areas4) {
            System.out.println(area);
        }
    }

}
