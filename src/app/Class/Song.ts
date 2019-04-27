import { Album } from './Album';
import { Band } from './Band';

export class Song {

    private id: number;
    private songName: string;
    private songLengthSecounds: number;
    private album: Album;
    private band: Band;

    constructor(obj?: any) {
        this.songName = (obj && obj.songName) || '';
        this.songLengthSecounds = (obj && obj.songLengthSecounds) || 0;
        this.album = (obj && obj.album) || null;
        this.band = (obj && obj.band) || null;
    }

    // Getters

    getId(): number {
        return this.id;
    }

    getSongName(): string {
        return this.songName;
    }

    getSongLengthSecounds(): number {
        return this.songLengthSecounds;
    }

    getAlbum(): Album {
        return this.album;
    }

    getBand(): Band {
        return this.band;
    }

    // Setters

    setSongName(songName: string) {
        this.songName = songName;
    }

    setSongLengthSecounds(songLengthSecounds: number) {
        this.songLengthSecounds = songLengthSecounds;
    }

    setAlbum(album: Album) {
        this.album = album;
    }

    setBand(band: Band) {
        this.band = band;
    }

}
