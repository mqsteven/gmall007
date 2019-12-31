import com.igeekhome.gmall.util.RedisUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.Jedis;

public class Test001 {



    @Test
    public void contextLoads() {
        RedisUtil redisUtil=new RedisUtil();
        redisUtil.initPool("10.168.124.129",6379,0);
        Jedis jedis = redisUtil.getJedis();

        jedis.set("sku", "1101:jiwei");
        String test = jedis.get("sku");
        System.out.println("redis值："+test);
    }
}
