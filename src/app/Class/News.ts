import { SafeUrl, SafeResourceUrl } from '@angular/platform-browser';

export class News {

    id: number;
    newsTitle: string;
    newsCreationDate: Date;
    creator: string;
    newsContent: string;
    newsImage: SafeResourceUrl;

    constructor(obj?: any) {
        this.id = (obj && obj.id) || 0;
        this.newsTitle = (obj && obj.newsTitle) || '';
        this.newsCreationDate = (obj && obj.newsCreationDate) || null;
        this.creator = (obj && obj.creator) || '';
        this.newsContent = (obj && obj.contentNewsDirFile) || '';
        this.newsImage = (obj && obj.fotoNewsDirFile) || '';
    }

    // Getters

    getId(): number {
        return this.id;
    }

    getNewsTitle(): string {
        return this.newsTitle;
    }

    getNewsCreationDate(): Date {
        return this.newsCreationDate;
    }

    getCreator(): string {
        return this.creator;
    }

    getNewsContent(): string {
        return this.newsContent;
    }

    getNewsImage(): SafeResourceUrl {
        return this.newsImage;
    }

    // Setters

    setId(id: number) {
        this.id = id;
    }

    setNewsTitle(newsTitle: string) {
        this.newsTitle = newsTitle;
    }

    setNewsCreationDate(creationDate: number) {
        this.newsCreationDate = new Date(creationDate);
    }

    setCreator(creator: string) {
        this.creator = creator;
    }

    setNewsContent(newsContent: string) {
        this.newsContent = newsContent;
    }

    setNewsImage(newsImage: SafeResourceUrl) {
        this.newsImage = newsImage;
    }

}
