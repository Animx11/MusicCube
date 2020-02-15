import { Component, OnInit, SecurityContext } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NewsService } from 'src/app/Services/news.service';
import { News } from 'src/app/Class/news';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { TokenStorageService } from 'src/app/Services/token-storage.service';
import { this_url } from 'src/app/Utils/API_URL';

const thisURL = this_url;


@Component({
  selector: 'app-display-news',
  templateUrl: './display-news.component.html',
  styleUrls: ['./display-news.component.css']
})
export class DisplayNewsComponent implements OnInit {

  private news: News;

  private roles: string[];
  private authority: string;

  constructor(
    private route: ActivatedRoute,
    private newsService: NewsService,
    private sanitizer: DomSanitizer,
    private tokenStorage: TokenStorageService
  ) { }

  ngOnInit() {
    this.getNews();
    this.news = new News();

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

  private getNews() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.newsService.getNewsById(id).subscribe(
      el => {
        this.news.setId(el.id);
        this.news.setNewsTitle(el.newsTitle);
        this.news.setCreator(el.creator);
        this.news.setNewsCreationDate(el.newsCreationDate);
        this.news.setNewsContent(el.contentNewsDirFile);
        this.news.setNewsImage(this.sanitizer.sanitize(SecurityContext.RESOURCE_URL, this.sanitizer.bypassSecurityTrustResourceUrl(el.imageUrl)));
      }
    );
  }

  deleteNews(){
    const id = +this.route.snapshot.paramMap.get('id');
    this.newsService.delete(id).subscribe(
      res => {
        window.location.assign(thisURL);
        console.log(res);
      },
      err => {
        window.alert("Error has occured, try log out and log in, and then try again");
      }
    )
  }

}
