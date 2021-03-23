import java.util.concurrent.ConcurrentHashMap;

public class Test {
    public static void main(String[] args) {
        ConcurrentHashMap <Long, String> c =new ConcurrentHashMap<>();
        c.put(1l, "dsfafd");
        String s = c.get(2l);
        System.out.println(s);
    }
}
