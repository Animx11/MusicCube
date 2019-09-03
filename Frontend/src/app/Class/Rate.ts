import { Users } from './Users';
import { Song } from './Song';
import { Album } from './Album';
import { Band } from './Band';
import { Artist } from './Artist';

export class Rate {

    private id: number;
    private user: Users;
    private song: Song;
    private album: Album;
    private band: Band;
    private artist: Artist;
    private rate: number;

    constructor(obj?: any) {
        this.id = (obj && obj.id) || 0;
        this.user = (obj && obj.user) || null;
        this.song = (obj && obj.song) || null;
        this.album = (obj && obj.album) || null;
        this.band = (obj && obj.band) || null;
        this.artist = (obj && obj.artist) || null;
        this.rate = (obj && obj.rate) || 0;
    }

    getId(): number {
        return this.id;
    }

    getUser(): Users {
        return this.user;
    }

    getSong(): Song {
        return this.song;
    }

    getAlbum(): Album {
        return this.album;
    }

    getBand(): Band {
        return this.band;
    }

    getArtist(): Artist {
        return this.artist;
    }

    getRate(): number {
        return this.rate;
    }

    setUser(user: Users) {
        this.user = user;
    }
    
    setSong(song: Song) {
        this.song = song;
    }

    setAlbum(album: Album) {
        this.album = album;
    }

    setBand(band: Band) {
        this.band = band;
    }

    setArtist(artist: Artist) {
        this.artist = artist;
    }
    
    setRate(rate: number) {
        this.rate = rate;
    }
}
