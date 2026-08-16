public class Jasper {
    public static void main(String[] args) {
        String banner = """
                    _                                \s
                 _ | |  __ _   ___  _ __   ___   _ _ \s
                | || | / _` | (_-< | '_ \\ / -_) | '_|\s
                 \\__/  \\__,_| /__/ | .__/ \\___| |_|  \s
                                   |_|               \s""";
        String hello = """
                Hello! I'm Jasper.
                What can I do for you?""";
        String bye = "Farewell. Hope to see you again soon!";
        String lineSeparator = "-".repeat(60);

        System.out.println(lineSeparator);
        System.out.println(banner);
        System.out.println(hello);
        System.out.println(lineSeparator);
        System.out.println(bye);
        System.out.println(lineSeparator);
    }
}
