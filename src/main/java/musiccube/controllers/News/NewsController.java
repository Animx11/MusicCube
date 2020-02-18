package musiccube.controllers.News;


import musiccube.controllers.Defaults;
import musiccube.entities.News;
import musiccube.services.news.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "${serverAddress")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @PostMapping("/admin/news")
    public ResponseEntity<News> create(@RequestBody @Valid @NotNull NewsFrontendReceiver newsFrontendReceiver){

        String dir = System.getProperty("user.dir");
        dir = dir.concat("/News/");

        Date date = new Date();
        News news = new News();

        newsService.save(news);

        news.setCreator(newsFrontendReceiver.getCreator());
        news.setNewsTitle(newsFrontendReceiver.getNewsTitle());
        news.setNewsCreationDate(date);
        news.setImageUrl(newsFrontendReceiver.getNewsImage());

        String fileDirWithName = dir.concat(String.valueOf(news.getId()).concat(".txt"));

        try {
            File newTextFile = new File(fileDirWithName);
            newTextFile.getParentFile().mkdirs();
            FileWriter fileWriter = new FileWriter(newTextFile);
            fileWriter.write(newsFrontendReceiver.getNewsContent());
            fileWriter.close();
        } catch (IOException e){
            System.out.println(e);
        }

        news.setContentNewsDirFile(fileDirWithName);

        newsService.save(news);

        return ResponseEntity.ok().body(news);

    }

    @GetMapping(path = "/news", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<News> getAll() {
        return newsService.getAll();
    }

    @GetMapping(path = "/news/page/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<News> getAllPaging(@PathVariable("page") Integer pageNr, @RequestParam(name = "size", required = false) Integer perPage) {

        Iterable<News> news;
        if(perPage == null){
            news = newsService.getAllPaging(pageNr, 10);
        } else {
            news = newsService.getAllPaging(pageNr, perPage);
        }

        for (News eachNews: news) {

            try {
                File fileToRead = new File(eachNews.getContentNewsDirFile());
                FileReader fileReader = new FileReader(fileToRead);

                char[] chars = new char[(int) fileToRead.length()];
                fileReader.read(chars);
                String content = new String(chars);

                eachNews.setContentNewsDirFile(content);

                fileReader.close();
            } catch (IOException e){
                System.out.println(e);
            }

        }

        return news;

    }

    @GetMapping(path = "/news/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public News getNewsById(@PathVariable("id") Integer id){

        News news = newsService.getById(id).orElse(null);
        if(news != null) {
            try {
                File fileToRead = new File(news.getContentNewsDirFile());
                FileReader fileReader = new FileReader(fileToRead);

                char[] chars = new char[(int) fileToRead.length()];
                fileReader.read(chars);
                String content = new String(chars);

                news.setContentNewsDirFile(content);

                fileReader.close();
            } catch (IOException e) {
                System.out.println(e);
            }

        }
        return news;

    }

    @DeleteMapping(path = "admin/news/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        News news = newsService.getById(id).orElse(null);
        if(news != null) {
            File file = new File(news.getContentNewsDirFile());

            System.gc();
            if(new File("./__tmp.txt").delete()){
                System.out.println("OK");
            }

            if(file.delete())
            {
                System.out.println("File deleted successfully");
            }
            else
            {
                System.out.println("Failed to delete the file");
            }

            newsService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
