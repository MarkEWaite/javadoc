import groovy.json.*;

// define sort order for plugins
def keyComparator = [compare: { e1, e2 -> e1.name.compareToIgnoreCase(e2.name) }] as Comparator

def components = new ArrayList<Artifact>();
components.addAll(Arrays.asList(
    new Artifact("Remoting", "org.jenkins-ci.main", "remoting", null, "https://github.com/jenkinsci/remoting"),
    new Artifact("XStream", "org.jvnet.hudson", "xstream", null, "https://github.com/jenkinsci/xstream"),
    new Artifact("GitHub API", "org.kohsuke", "github-api", null, "http://github-api.kohsuke.org/")
))


// For each plugin located in the update center
def indexBuilder = new JavadocGroupBuilder("component", "component", "Jenkins Componentss Javadoc");
components.toSorted(keyComparator).eachWithIndex { value, idx ->
    indexBuilder.withArtifact(value)
}

// Build all
indexBuilder.build()



