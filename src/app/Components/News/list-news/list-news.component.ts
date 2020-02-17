import { Component, OnInit, SecurityContext } from '@angular/core';
import { NewsService } from 'src/app/Services/news.service';
import { News } from 'src/app/Class/news';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { TokenStorageService } from 'src/app/Services/token-storage.service';

@Component({
  selector: 'app-list-news',
  templateUrl: './list-news.component.html',
  styleUrls: ['./list-news.component.css']
})
export class ListNewsComponent implements OnInit {

  newsPaged: News[];
  news: News;
  shorterContent: string;

  pageNr: number;
  pageSize: number;
  reachedLastPage: boolean;
  pageCount: number;

  private roles: string[];
  private authority: string;


  constructor(
    private newsService: NewsService,
    private sanitizer: DomSanitizer,
    private tokenStorage: TokenStorageService) { }

  ngOnInit() {
    this.pageNr = 0;
    this.pageSize = 10;
    this.newsPaged = new Array<News>();
    this.getNewsList();

    if (this.tokenStorage.getToken()) {
      this.roles = this.tokenStorage.getAuthorities();
      this.roles.forEach(role => {
        if(role === 'ROLE_ADMIN'){
          this.authority = 'admin';
        }
        else if(role === 'ROLE_MOD' && this.authority !== 'admin'){
          this.authority = 'mod';
        }
        else if(role === 'ROLE_USER' && this.authority !== 'admin' && this.authority !== 'mod'){
          this.authority = 'user';
        }
      });
 
    }
  }

  getNewsList() {
    this.newsService.listPaging(this.pageNr, this.pageSize).subscribe(res => {
      console.log('recived news:', res);
      res.content.map(el => {
        this.news = new News();
        this.news.setId(el.id);
        this.news.setNewsTitle(el.newsTitle);
        this.news.setCreator(el.creator);
        this.news.setNewsCreationDate(el.newsCreationDate);
        this.news.setNewsImage(this.sanitizer.sanitize(SecurityContext.RESOURCE_URL, this.sanitizer.bypassSecurityTrustResourceUrl(el.imageUrl)));
        if(el.contentNewsDirFile.length > 250) {
          this.news.setNewsContent(el.contentNewsDirFile.substring(0, 250).concat('...'));
        } else {
          this.news.setNewsContent(el.contentNewsDirFile);
        }
        this.newsPaged.push(this.news);
      });
      this.reachedLastPage = res.last;
      this.pageCount = res.totalPages;
    });
  }

  nextPage() { if (this.reachedLastPage) {
    window.alert('No more data');
  } else {
      this.pageNr++;
      this.newsPaged = new Array<News>();
      this.getNewsList();
    }
  }

  prevPage() {
    this.pageNr--;
    this.newsPaged = new Array<News>();
    this.getNewsList();
  }

}
