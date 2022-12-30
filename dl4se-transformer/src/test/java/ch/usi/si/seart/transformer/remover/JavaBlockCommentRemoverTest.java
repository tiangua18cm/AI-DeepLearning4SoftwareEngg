package ch.usi.si.seart.transformer.remover;

import ch.usi.si.seart.transformer.JavaBaseTest;
import ch.usi.si.seart.transformer.Transformer;

class JavaBlockCommentRemoverTest extends JavaBaseTest {

    @Override
    protected Transformer getTestSubject() {
        return new JavaBlockCommentRemover();
    }

    @Override
    protected String getTestInput() {
        return "package ch.usi.si;\n" +
                "\n" +
                "/**\n" +
                " * Class JavaDoc\n" +
                " */\n" +
                "public class Main {\n" +
                "\n" +
                "    /**\n" +
                "     * Method JavaDoc\n" +
                "     *//**/\n" +
                "    public static void main(String[] args 