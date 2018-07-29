package de.wartbar.markdown

class FileLinkCreator {

  static String getLink(String fileName) {
    String name = fileName.replace(".md","")
    name = name.replace("_"," ")
    String linkDescription = "[" + name + "]"
    String linkAddress = "(" + fileName + ")"
    return linkDescription + linkAddress
  }
}