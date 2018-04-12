package yuzhu.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ParseTest {
    @Test
    public void test() throws URISyntaxException, IOException {
//        String html = new String(Files.readAllBytes(Paths.get(ParseTest.class.getClassLoader().getResource("test.html").toURI())));
//        System.out.println(html);
        Document doc = Jsoup.connect("http://www.infzm.com/content/134730/").get();
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
    }
}
