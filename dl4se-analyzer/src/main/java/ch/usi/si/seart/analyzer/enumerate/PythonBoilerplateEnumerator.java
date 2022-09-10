package ch.usi.si.seart.analyzer.enumerate;

import ch.usi.si.seart.model.code.Boilerplate;
import ch.usi.si.seart.treesitter.Node;

public class PythonBoilerplateEnumerator extends BoilerplateEnumerator {

    @Override
    public Boilerplate asEnum(Node node) {
        if (!node.getType().equals("function_definition")) return null;
        traverser: {
            for (Node prev = node.getPrevSibling(); prev != null; prev = prev.getPrevSibling()) {
                switch (prev.getType()) {
                    case "decorator":
                        String decorator = prev.getContent();
                        if (decorator.equals("@property")) return Boilerplate.GETTER;
                        else if (decorator.endsWith(".setter")) return Boilerplate.SETTER;
                    case "comment":
                        break;
                    default:
                        break traverser;
                }
            }
        }
        Node identifier = node.getChildByFieldName("name");
        String name = identifier.getContent();
        switch (name) {
            case "__new__":
                return Boilerplate.CONSTRUCTOR;
            case "__init__":
                return Boilerplate.INITIALIZER;
            case "__str__":
                return Boilerplate.STRING_CONVERSION;
            case "__repr__":
                return Boilerplate.STRING_REPRESENTATION;
            case "__hash__":
                return Boilerplate.HASHER;
            case "__get__":
            case "__getattr__":
            