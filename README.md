
# AI Deep Learning for Software Engineering (AI-DL4SE) Data Hub

The AI Deep Learning for Software Engineering (AI-DL4SE) Data Hub platform facilitates the crafting of extensive datasets. These datasets can be leveraged to conduct empirical MSR studies or to train Deep Learning algorithms for software engineering tasks automation.

## Contents

This framework comprises multiple modules:

- `dl4se-model`: A module incorporating domain model iterations for the relational database structural mapping to the programming sphere;
- `dl4se-analyzer`: A module hosting the `tree-sitter` code analysis operations implementations;
- `dl4se-transformer`: Playing host to the `tree-sitter` code transformation operations in a module;
- `dl4se-crawler`: A standalone crawler app used to mine source code from GitHub repositories using [GitHub Search](https://seart-ghs.si.usi.ch/);
- `dl4se-server`: A Spring Boot server app acting as our platform's back-end;
- `dl4se-spring`: Contains common Spring Boot configuration and utilities employed both in the server and crawler;
- `dl4se-website`: A user-friendly web-application front end developed in Vue.

## Installation and Usage

Details necessary for setting up and running the project are provided under different segments:

- [Environment](README_ENV.md)
- [Database](README_DB.md)
- [Usage](README_RUN.md)
- [Dockerization](README_DOCKER.md)

## License

License covered under [MIT](LICENSE)

## FAQ

### How are language-specific analysis heuristics implemented?

Heuristic information pertaining to test code identification in Java and Python can be found [here](dl4se-analyzer/src/main/java/ch/usi/si/seart/analyzer/predicate/path/JavaTestFilePredicate.java) and [here](dl4se-analyzer/src/main/java/ch/usi/si/seart/analyzer/predicate/path/PythonTestFilePredicate.java). Information on identifying boilerplate code is available [here](dl4se-analyzer/src/main/java/ch/usi/si/seart/analyzer/enumerate/JavaBoilerplateEnumerator.java) and [here](dl4se-analyzer/src/main/java/ch/usi/si/seart/analyzer/enumerate/PythonBoilerplateEnumerator.java) respectively.

### How can I request a feature or ask a question?

For any feature requests or inquiries, we encourage you to initiate a new [discussion](https://github.com/tiangua18cm/AI-DeepLearning4SoftwareEngg/discussions/). This enables engagement with our team and the community, and ensures your queries or feature suggestions are promptly addressed.

### How can I report a bug?

To note any encountered issues or bugs, please open a [new issue](https://github.com/tiangua18cm/AI-DeepLearning4SoftwareEngg/issues/). Detailed information about your problem helps us understand and efficiently address it. We are committed to swiftly reviewing and responding to your issues and to collaboratively work towards bug resolution and an improved user experience.