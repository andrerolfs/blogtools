package de.wartbar.markdown

import de.wartbar.files.BlogPosts

class BlogPostIndexFileCreator {

  static create(String path) {
    File pathFile = new File(path)
    File blogPostIndex = new File(pathFile,"dailynoise.md")
    if (blogPostIndex.exists()) {
      blogPostIndex.delete()
    }

    blogPostIndex << "{% include_relative menu.md %}\n\n"
    blogPostIndex << "# daily noise - My Blog\n\n"
    blogPostIndex << "Here I share my experiences about small problems for which I have found solutions working for me.\n\n"

    BlogPosts blogPosts = new BlogPosts(pathFile)
    blogPosts.getBlogPostFileNames().each { currentFileName ->
      blogPostIndex << FileLinkCreator.getLink(currentFileName) << "<br/><br/>\n"
    }

    blogPostIndex << "\n"
  }
}
