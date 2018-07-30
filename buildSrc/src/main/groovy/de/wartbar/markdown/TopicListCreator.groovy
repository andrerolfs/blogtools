package de.wartbar.markdown

import de.wartbar.files.BlogPosts

class TopicListCreator {


  static create(String path) {

    File topicsFile = new File("topics.txt")
    List<String> topicsList = topicsFile.readLines()

    Map<String, List<String>> topicMap = new HashMap<>()

    topicsList.each { topic ->
      List<String> blogPostList = new ArrayList<>()
      topicMap.put(topic, blogPostList)
    }

    File pathFile = new File(path)
    File topicFile = new File(pathFile,"topics.md")
    if (topicFile.exists()) {
      topicFile.delete()
    }

    topicFile << "{% include_relative menu.md %}\n\n"
    topicFile << "# My Topics\n\n"

    BlogPosts blogPosts = new BlogPosts(pathFile)
    blogPosts.getBlogPostFileNames().each { currentFileName ->
      File blogPostFile = new File(currentFileName)

      String content = blogPostFile.toString().toLowerCase()

      topicsList.each { topic ->
        if (content.contains(topic)) {
          List<String> blogPostList = topicMap.get(topic)
          blogPostList.add(blogPostFile.getName())
        }
      }
    }

    topicsList.toSorted().each { topic ->
      List<String> fileList = topicMap.get(topic).toSorted()

      if (fileList.isEmpty()) {
        return
      }

      topicFile << "## "
      topicFile << topic.toUpperCase()
      topicFile << "\n\n"

      fileList.each { fileName ->
        topicFile << FileLinkCreator.getLink(fileName)
        topicFile << "\n\n"

      }
    }

    topicFile << "\n"
  }

}
