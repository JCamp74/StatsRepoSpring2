public class Main {
    public static void main(String[] args) {
        JacksonDummyHash<String, Integer> jmap = new JacksonDummyHash<>();
        jmap.put("Hello", 5);
        jmap.put("olleH", 5);
        jmap.put("Goodbye", 7);
        jmap.put("Hellow", 23);
        jmap.put("Hello", 3);
        jmap.put("aaaaA", 23);
        System.out.println(jmap);
    }
}
