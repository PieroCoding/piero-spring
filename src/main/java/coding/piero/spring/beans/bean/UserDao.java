package coding.piero.spring.beans.bean;

import java.util.HashMap;
import java.util.Map;

public class UserDao {

    private static Map<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("10001", "xiaofuge");
        hashMap.put("10002", "piero");
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }
}
