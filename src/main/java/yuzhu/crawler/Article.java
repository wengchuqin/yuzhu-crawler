package yuzhu.crawler;

import java.util.List;
import java.util.Objects;

public class Article {
    private String url;
    private String title;
    private String content;
    private List<String> tagList;

    public Article() {
    }

    public Article(String url, String title, String content, List<String> tagList) {
        this.url = url;
        this.title = title;
        this.content = content;
        this.tagList = tagList;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(url, article.url) &&
                Objects.equals(title, article.title) &&
                Objects.equals(content, article.content) &&
                Objects.equals(tagList, article.tagList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(url, title, content, tagList);
    }

    @Override
    public String toString() {
        return "Article{" +
                "url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", tagList=" + tagList +
                '}';
    }
}
