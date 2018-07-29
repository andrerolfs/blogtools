package de.wartbar.files

class BlogPosts {

  File pathToBlogPosts

  BlogPosts(File path) {
    pathToBlogPosts = path
  }

  List<String> getBlogPostFileNames() {
    List<String> blogPosts = new ArrayList<>()
    pathToBlogPosts.list().sort().reverse().each { currentFile ->
      if (currentFile.startsWith("20")) {
        blogPosts.add(currentFile)
      }
    }
    return blogPosts
  }
}
