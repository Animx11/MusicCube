import { Component, OnInit } from '@angular/core';
import { News } from 'src/app/Class/News';
import { NewsService } from 'src/app/Services/news.service';
import { TokenStorageService } from 'src/app/Services/token-storage.service';

@Component({
  selector: 'app-add-news',
  templateUrl: './add-news.component.html',
  styleUrls: ['./add-news.component.css']
})
export class AddNewsComponent implements OnInit {

  news: News;
  newsTitle: string;
  content: string;
  imageUrl: string;

  constructor(
    private newsService: NewsService,
    private tokenStorage: TokenStorageService) { }

  ngOnInit() {
    this.news = new News();
    this.newsTitle = '';
    this.content = '';
    this.imageUrl = '';
  }

  addNews() {
    this.news.setNewsTitle(this.newsTitle);
    this.news.setNewsContent(this.content);
    this.news.setNewsImage(this.imageUrl);
    this.news.setCreator(this.tokenStorage.getUsername());

    this.newsService.create(this.news).subscribe(
      res => {
        console.log('add-news-component received:');
        console.log(res);
        window.alert('News added');
        this.ngOnInit();
      },
      err => {
        window.alert('Error occurred');
        console.error(err);
      }
    );

  }

}
