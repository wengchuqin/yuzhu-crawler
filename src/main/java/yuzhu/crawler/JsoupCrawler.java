package yuzhu.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

public class JsoupCrawler {
    Queue<String> queue = new LinkedList<>();
    Set<String> visited = new HashSet<>();

    @Test
    public void test() throws IOException {
        String rootUrl = "http://www.infzm.com/content/134730/";
        queue.add(rootUrl);
        visited.add(rootUrl);

        while (!queue.isEmpty()) {
            String url = queue.poll();
            System.out.println("url:" + url);

            if (!shouldVisit(url)) {
                continue;
            }

            Document document = Jsoup.connect(url).get();
            Elements aElems = document.getElementsByTag("a");
            try {
                visit(url, document);
            } catch (Exception e) {
                e.printStackTrace();
            }

            aElems.eachAttr("href").stream().forEach(s -> {
                System.out.println(String.format("页面上找到的链接: %s", s));
                if (visited.contains(s)) {
                    return;
                }

                //防止OOM
                if (visited.size() <= 100000) {
                    visited.add(s);
                }
                if (queue.size() <= 100000) {
                    queue.offer(s);
                }

                System.out.println(String.format("visited.size", visited.size()));
                System.out.println(String.format("queue.size", queue.size()));
            });
        }


    }

    public Boolean shouldVisit(String url) {
        if (url != null && url.contains("http://www.infzm.com")) {
            return true;
        }
        return false;
    }

    public void visit(String url, Document doc) {
        System.out.println(doc);
        Element keywordSelect = doc.selectFirst(".cate");
        String keyword = keywordSelect.text();
        System.out.println(keyword);

        Element articleContentSelect = doc.selectFirst("#articleContent");
        String articleContent = articleContentSelect.text();
        System.out.println(articleContent);


        Element articleHeadlineSelect = doc.selectFirst("h1.articleHeadline");
        String articleHeadline = articleHeadlineSelect.text();
        System.out.println(articleHeadline);

        Elements tagSelects = doc.select(".tagContent");
        List<String> tagList = tagSelects.eachText();
        System.out.println(tagList);

        Article article = new Article(url, articleHeadline, articleContent, tagList);
        processArticle(article);
    }

    /**
     * 处理文章对象。
     * 例如：把文章对象持久化到磁盘上。
     * @param article
     */
    public void processArticle(Article article) {
        System.out.println(String.format("article:%s", article));
    }
}
