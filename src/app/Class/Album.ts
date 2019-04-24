export class Album{

    private id: number;
    private albumName: string;
    private albumLengthSecounds: number;
    private releaseDate: Date;
    private company: string;

    constructor(obj?: any) {
        this.albumName = (obj && obj.albumName) || '';
        this.albumLengthSecounds = (obj && obj.albumLengthSecounds) || 0;
        this.releaseDate = (obj && obj.releaseDate) || null;
        this.company = (obj && obj.company) || '';
      }

    // Getters

    public getId(): number{
        return this.id;
    }

    public getAlbumName(): string{
        return this.albumName;
    }

    public getAlbumLengthSecounds(): string{
        return this.albumLengthSecounds;
    }

    public getReleaseDate(): Date{
        return this.releaseDate;
    }

    public getCompany(): string{
        return this.company;
    }

    // Setters

    public setAlbumName(albumName: string){
        return this.albumName = albumName;
    }

    public setAlbumLengthSecounds(albumLengthSecounds: number){
        return this.albumLengthSecounds = albumLengthSecounds;
    }

    public setReleaseDate(releaseDate: Date){
        return this.releaseDate = releaseDate;
    }

    public setCompany(company: string){
        return this.company = company;
    }

}